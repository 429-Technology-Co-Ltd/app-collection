package qm.model

/**
 * @ClassName: ActionItem
 * @Description: 每条用户行为事件信息
 * @Create by: LinYoung
 * @Date: 2021/1/26 11:21
 */
case class ActionItem(
                       ip: String,
                       deviceId: String,
                       eventName: String,
                       eventData: String,
                       ctime: String
                     ) {
  override def toString: String = {
    "{ip:"+ip+
      ",deviceId:"+deviceId+
      ",eventName:"+eventName+
      ",ctime:"+ctime+
      ",eventData:"+eventData+
    "}"
  }
}
