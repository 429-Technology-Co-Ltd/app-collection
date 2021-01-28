package qm.flink

import qm.jackson.Jackson
import qm.model.ActionItem

import java.util
import scala.collection.mutable.ArrayBuffer


/**
 * @ClassName: Analysis
 * @Description: TODO
 * @Create by: LinYoung
 * @Date: 2020/12/24 12:33
 */
object Analysis {

  /**
   * 对接过来的数据进行处理，返回多个平台比价
   *
   * @param x 要处理的数据，json字符串 
   * @return 返回搜索结果
   */
  def analysis(x: String): Array[ActionItem] = {

    val collections: Array[String] = x.split("#QM#MQ#")
    if (collections.length < 2) {
      return Array(ActionItem("", "", "", "", ""))
    }
    val head: String = collections(0)
    val tail: String = collections(1)

    //用jackson处理json字符串
    val headMap = new util.HashMap[String, Any]()
    val tailMap = new util.HashMap[String, Any]()

    Jackson.autoParseJson(head, headMap)
    Jackson.autoParseJson(tail, tailMap)

    //创建时间
    val ctime = headMap.getOrDefault("ctime", "").toString

    //用户的IP地址
    val ip = headMap.getOrDefault("ip", "").toString

    //取出单条埋点数据的信息
    val project = tailMap.getOrDefault("projects", "").toString

    //设备id
    val deviceId = tailMap.getOrDefault("device_id", "").toString

    if (project.isEmpty) {
      return Array(ActionItem(ip, deviceId, "", "", ctime))
    }

    val events = parseProjects(project)

    val results = new ArrayBuffer[ActionItem]()

    for (event <- events) {
      results.append(ActionItem(ip, deviceId, event._1, event._2, ctime))
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
        eventTuple.append((eventName, eventDataStr))
      }
    }

    eventTuple.toArray
  }
}
