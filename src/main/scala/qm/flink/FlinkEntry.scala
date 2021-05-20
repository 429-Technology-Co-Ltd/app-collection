package qm.flink

import org.apache.flink.api.scala._
import org.apache.flink.streaming.api.CheckpointingMode
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
import org.apache.flink.table.api.{EnvironmentSettings, SqlDialect}
import org.apache.flink.table.api.bridge.scala.StreamTableEnvironment
import org.apache.flink.table.catalog.hive.HiveCatalog
import qm.auth.KerberosAuth
import qm.kafka.KafkaConsumer
import qm.model.ActionItem


/**
 * @ClassName: Flink
 * @Description: TODO
 * @Create
 * @Date: 2021/2/10 9:54
 */
object FlinkEntry {
  def entry(): Unit = {
    val env = StreamExecutionEnvironment
      .getExecutionEnvironment
      .enableCheckpointing(100000)

    env.getCheckpointConfig.setCheckpointTimeout(500)
    env.getCheckpointConfig.setTolerableCheckpointFailureNumber(2)
    env.getCheckpointConfig.setMaxConcurrentCheckpoints(1)
    env.getCheckpointConfig.setCheckpointingMode(CheckpointingMode.AT_LEAST_ONCE)

    val settings = EnvironmentSettings.newInstance().useBlinkPlanner().inStreamingMode().build()

    val tEnv = StreamTableEnvironment.create(env, settings)

    //对接kafka
    val consumer: FlinkKafkaConsumer[String] = KafkaConsumer.getConsumer
    //从头开始消费
    consumer.setStartFromEarliest()

    //添加源并设置并行度
    val ds: DataStream[String] = env.addSource(consumer).setParallelism(4)

    // 处理入口
    val dsa: DataStream[ActionItem] = ds.map(x => {
      // 数据处理流程
      Analysis.analysis(x)
    })
      //将数组压平
      .flatMap(x => x)
      //过滤掉不合理的数据
      .filter(x => x.eventName.nonEmpty)
      .map(item => {
        item
      })

    KerberosAuth.auth(false)
    // 构造hive catalog
    val name = "point_hive"; // Catalog 名称
    val defaultDatabase = "default"; // 数据库名称
    val hiveConfDir = "/etc/alternatives/hive-conf"; // hive 的配置路径
    val hive = new HiveCatalog(name, defaultDatabase, hiveConfDir)

    tEnv.registerCatalog("point_hive", hive)
    tEnv.useCatalog("point_hive")
    tEnv.getConfig.setSqlDialect(SqlDialect.HIVE)
    tEnv.useDatabase("qm_tmp")
    tEnv.createTemporaryView("users", dsa)

    val sql =
      """
        |CREATE table if NOT EXISTS qm_tmp.test_collection(
        |ip  string,
        |deviceId  string,
        |eventName  string,
        |ctime  string,
        |page_id  string,
        |last_page_id  string,
        |appear_time  string,
        |disappear_time  string,
        |click_id  string,
        |click_time  string,
        |exposure_id  string,
        |exposure_time  string,
        |inter  string,
        |app_channel  string,
        |app_version  string,
        |user_id  string,
        |source  string,
        |itemId  string,
        |platform  string
        |)
        |PARTITIONED BY (dt string,hr string)
        |STORED AS ORC
        |TBLPROPERTIES('partition.time-extractor.timestamp-pattern' = '$dt $hr:00:00',
        |                            'sink.partition-commit.delay' = '0s',
        |                            'sink.partition-commit.watermark-time-zone' = 'Aisa/Shanghai',
        |                            'sink.partition-commit.policy.kind' = 'metastore')
        |""".stripMargin
    tEnv.executeSql(sql)

    val insertSql =
      """
        |insert into qm_tmp.test_collection PARTITION(dt,hr)
        |select
        |ip,
        |deviceId,
        |eventName,
        |ctime,
        |page_id,
        |last_page_id,
        |appear_time,
        |disappear_time,
        |click_id,
        |click_time,
        |exposure_id,
        |exposure_time,
        |inter,
        |app_channel,
        |app_version,
        |user_id,
        |source,
        |itemId,
        |platform,
        |from_unixtime(cast (ctime as bigint),'yyyy-MM-dd') as dt,
        |from_unixtime(cast (ctime as bigint),'HH') as hr
        |from users
        |""".stripMargin
    tEnv.executeSql(insertSql).print()
    env.execute("point")

  }
}
