package qm.entry

import qm.flink.FlinkEntry

/**
 * @ClassName: Entry
 * @Description: 流入口
 * @Create by: LinYoung
 * @Date: 2020/12/24 12:31
 */
object Entry {
  def main(args: Array[String]): Unit = {
    FlinkEntry.entry()
  }
}
