package qm.flink

import qm.jackson.Jackson

import java.util
import scala.collection.JavaConversions._
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
  def analysis(x: String): Array[(String, Any)] = {

    val tuples: ArrayBuffer[(String, Any)] = new ArrayBuffer[(String, Any)]()
    val collections: Array[String] = x.split("#QM#MQ#")
    if (collections.length < 2) {
      return Array(("",""))
    }
    val head: String = collections(0)
    val tail: String = collections(1)

    //用jackson处理json字符串
    val headMap = new util.HashMap[String, Any]()
    val tailMap = new util.HashMap[String, Any]()

    Jackson.autoParseJson(head, headMap)
    Jackson.autoParseJson(tail, tailMap)

    val keys = headMap.keySet()
    val tails = tailMap.keySet()


    for (key <- keys) {
      tuples.append((key, headMap.get(key)))
    }

    for (tailKey <- tails) {
      tuples.append((tailKey, tailMap.get(tailKey)))
    }

    tuples.toArray

  }
}
