package qm.flink

import qm.jackson.Jackson
import qm.model.ActionItem

import java.util
import scala.collection.mutable.ArrayBuffer


/**
 * @ClassName: Analysis
 * @Description: TODO
 * @Create
 * @Date: 2021/2/11 12:33
 */
object Analysis {

  /**
   * 对接过来的数据进行处理，返回多个平台比价
   *
   * @param x 要处理的数据，json字符串 
   * @return 返回搜索结果
   */
  def analysis(x: String): Array[ActionItem] = {
    //按照指定的字符进行切割

    val collections: Array[String] = x.split("#QM#MQ#")
    if (collections.length < 2) {
      return Array(ActionItem("", "", "", "0", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
    }
    val head: String = collections(0)
    val tail: String = collections(1)

    //用jackson处理json字符串
    val headMap = new util.HashMap[String, Any]()
    val tailMap = new util.HashMap[String, Any]()

    Jackson.autoParseJson(head, headMap)
    Jackson.autoParseJson(tail, tailMap)

    //创建时间
    val ctime = (java.lang.Long.valueOf(headMap.getOrDefault("ctime", 0L).toString) / 1000).toString

    //用户的IP地址
    val ip = headMap.getOrDefault("ip", "").toString

    //取出单条埋点数据的信息
    val project = tailMap.getOrDefault("projects", "").toString

    //设备id
    val deviceId = tailMap.getOrDefault("device_id", "").toString

    if (project.isEmpty) {
      return Array(ActionItem(ip, deviceId, "", ctime, "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
    }

    val events = parseProjects(project)

    val results = new ArrayBuffer[ActionItem]()

    for (event <- events) {
      val eventMap = new util.HashMap[String, Any]()
      Jackson.autoParseJson(event._2, eventMap)
      val page_id = eventMap.getOrDefault("page_id", "").toString
      val last_page_id = eventMap.getOrDefault("last_page_id", "").toString
      val appear_time = eventMap.getOrDefault("appear_time", "").toString
      val disappear_time = eventMap.getOrDefault("disappear_time", "").toString
      val extras = eventMap.getOrDefault("extras", "").toString
      val click_id = eventMap.getOrDefault("click_id", "").toString
      val click_time = eventMap.getOrDefault("click_time", "").toString
      val exposure_id = eventMap.getOrDefault("exposure_id", "").toString
      val exposure_time = eventMap.getOrDefault("exposure_time", "").toString
      val extras_map = new util.HashMap[String, Any]()
      Jackson.autoParseJson(extras, extras_map)
      val user_id = extras_map.getOrDefault("user_id", "").toString
      val source = extras_map.getOrDefault("source", "").toString
      val itemId = extras_map.getOrDefault("itemId", "").toString
      val platform = extras_map.getOrDefault("platform", "").toString
      val inter = eventMap.getOrDefault("inter", "").toString
      val app_channel = eventMap.getOrDefault("app_channel", "").toString
      val app_version = eventMap.getOrDefault("app_version", "").toString
      results.append(ActionItem(ip, deviceId, event._1, ctime, page_id, last_page_id, appear_time, disappear_time, click_id, click_time, exposure_id, exposure_time, inter, app_channel, app_version, user_id, source, itemId, platform))
    }
    results.toArray
  }

  def parseProjects(projectStr: String): Array[(String, String)] = {

    // json数组转数组
    val projects: Array[AnyRef] = Jackson.string2Array(projectStr)

    val eventTuple = new ArrayBuffer[(String, String)]()

    //数组不为空
    if (projects.nonEmpty) {
      val eventMap = new util.HashMap[String, Any]()
      for (project <- projects) {
        Jackson.autoParseJson(project.toString, eventMap)
        val eventName = eventMap.getOrDefault("event_name", "").toString
        val eventDataStr = eventMap.getOrDefault("event_data", "").toString

        // 解析eventData
        val eventdata = Jackson.string2Array(eventDataStr)
        for (data <- eventdata) {

          eventTuple.append((eventName, data.toString))
        }

      }
    }

    eventTuple.toArray
  }
}
