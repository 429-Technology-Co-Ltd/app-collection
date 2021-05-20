package qm.tools

import java.util.Properties

/**
 * @ClassName: qm.qiaomeng.tools
 * @Description:
 * @Author:LinYoung
 * @Date: 2021/4/17
 * @Time: 13:52
 */
object PropertiesTools {
  private[this] val properties = new Properties()

  properties.load(this.getClass.getResourceAsStream("/app.properties"))

  def getProperties: Properties = properties
}

