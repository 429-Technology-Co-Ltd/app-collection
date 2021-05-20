package qm.model

/**
 * @ClassName: ActionItem
 * @Description: 每条用户行为事件信息
 * @Create
 * @Date: 2021/2/9 11:21
 */
case class ActionItem(
                       ip: String,
                       deviceId: String,
                       eventName: String,
                       ctime: String,
                       page_id: String,
                       last_page_id: String,
                       appear_time: String,
                       disappear_time: String,
                       click_id:String,
                       click_time:String,
                       exposure_id:String,
                       exposure_time:String,
                       inter:String,
                       app_channel:String,
                       app_version:String,
                       user_id:String,
                       source:String,
                       itemId:String,
                       platform:String
                     ) {
  override def toString: String = {
    "{ip:"+ip+
      ",deviceId:"+deviceId+
      ",eventName:"+eventName+
      ",ctime:"+ctime+
      ",page_id:"+page_id+
      ",last_page_id:"+last_page_id+
      ",appear_time:"+appear_time+
      ",disappear_time:"+disappear_time+
      ",click_id:"+click_id+
      ",click_time:"+click_time+
      ",exposure_id:"+exposure_id+
      ",exposure_time:"+exposure_time+
      ",inter:"+inter+
      ",app_channel:"+app_channel+
      ",app_version:"+app_version+
      ",user_id:" + user_id +
      ",source:"+ source +
      ", itemId:" + itemId +
      ", platform:" + platform +
      "}"
  }
}
