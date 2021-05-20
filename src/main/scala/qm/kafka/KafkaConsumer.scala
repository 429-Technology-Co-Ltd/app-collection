package qm.kafka

import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
import org.apache.kafka.clients.consumer.ConsumerConfig
import qm.tools.PropertiesTools

/**
 * @ClassName: KafkaConsumer
 * @Description: TODO
 * @Create by: LinYoung
 * @Date: 2021/1/13 14:54
 */
object KafkaConsumer {

  private[this] val properties: Properties = PropertiesTools.getProperties
  private[this] val topic: String = properties.getProperty("kafka.topic")
  private[this] val bootstrapServer: String = properties.getProperty("kafka.cluster")
  private[this] val group: String = properties.getProperty("kafka.consumer.group")
  private[this] val keyDeserializer: String = properties.getProperty("kafka.consumer.key.deserializer")
  private[this] val valueDeserializer: String = properties.getProperty("kafka.consumer.value.deserializer")
  private[this] val commitInterval: String = properties.getProperty("kafka.consumer.offset.commit.auto.interval")
  private[this] val AutoCommit: String = properties.getProperty("kafka.consumer.offset.commit.auto")


  def getConsumer: FlinkKafkaConsumer[String] = {
    val property = new Properties()
    // 对接kafka
    property.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer)
    property.put(ConsumerConfig.GROUP_ID_CONFIG, group)
    property.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializer)
    property.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializer)
    property.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, commitInterval)
    property.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, AutoCommit)
    new FlinkKafkaConsumer[String](topic, new SimpleStringSchema(), property)
  }
}
