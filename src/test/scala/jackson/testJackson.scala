package jackson

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import qm.jackson.Jackson

import java.util
import scala.collection.JavaConversions._


/**
 * @ClassName: testJackson
 * @Description: TODO
 * @Create by: LinYoung
 * @Date: 2021/1/26 15:26
 */
object testJackson {
  private val mapper = new ObjectMapper

  mapper.registerModule(DefaultScalaModule)

  def main(args: Array[String]): Unit = {
    val json: String =
      """
        |[{"event_name":"elem_exposure",
        |"event_data":[
        |{"id":"4c3488b5c649ef1c08d2850b55c29262","source":null,"page_id":"SearchResultPage","exposure_id":"good_list_淘宝","exposure_time":1611562621,"extras":"{\"item_id\":\"606915331883\",\"item_pic\":\"https://img.alicdn.com/bao/uploaded/i1/3855545787/O1CN012HyXEP1scUbX7Vfyz_!!0-item_pic.jpg\",\"item_title\":\"儿童裤子秋冬装外穿男童女童装宝宝保暖加绒加厚羊羔绒休闲运动裤\",\"discount_type\":1,\"discount_amount\":\"5\",\"platform\":\"10\",\"shop_title\":\"熹丫熊旗舰店\",\"user_commission\":\"5.22\",\"price\":\"29.9\",\"end_price\":\"24.90\",\"volume_text\":\"已抢1.44万件\",\"discount_text\":\"¥5券\",\"user_commission_text\":\"返现5.22\"}"},
        |{"id":"2c5843989db9528ce57001b31d36c4c4","source":null,"page_id":"SearchResultPage","exposure_id":"good_list_淘宝","exposure_time":1611562621,"extras":"{\"item_id\":\"599054484198\",\"item_pic\":\"https://img.alicdn.com/bao/uploaded/i1/2568321327/O1CN01ubj9am1LfoEcs6Upz_!!2568321327.jpg\",\"item_title\":\"高梵童装2020新款正品洋气宝宝短款儿童羽绒服男童女童白鸭绒反季\",\"discount_type\":1,\"discount_amount\":\"70\",\"platform\":\"10\",\"shop_title\":\"高梵童装旗舰店\",\"user_commission\":\"3.40\",\"price\":\"209\",\"end_price\":\"139.00\",\"volume_text\":\"已抢2552件\",\"discount_text\":\"¥70券\",\"user_commission_text\":\"返现3.40\"}"},
        |{"id":"e27992e929db55ac7bec4673663af90b","source":null,"page_id":"SearchResultPage","exposure_id":"good_list_淘宝","exposure_time":1611562621,"extras":"{\"item_id\":\"618352788187\",\"item_pic\":\"https://img.alicdn.com/bao/uploaded/i1/4131192570/O1CN0141GrBm1Ur6XRdousi_!!0-item_pic.jpg\",\"item_title\":\"童装男童加绒裤子一体绒冬装加厚保暖长裤潮流帅气中大儿童运动裤\",\"discount_type\":1,\"discount_amount\":\"40\",\"platform\":\"10\",\"shop_title\":\"西努克旗舰店\",\"user_commission\":\"4.18\",\"price\":\"59.9\",\"end_price\":\"19.90\",\"volume_text\":\"已抢1089件\",\"discount_text\":\"¥40券\",\"user_commission_text\":\"返现4.18\"}"},
        |{"id":"8b153a2b99d2b961b8570ad10a76f574","source":null,"page_id":"SearchResultPage","exposure_id":"good_list_淘宝","exposure_time":1611562621,"extras":"{\"item_id\":\"597402685837\",\"item_pic\":\"https://img.alicdn.com/bao/uploaded/i3/2568321327/O1CN01mD6Rut1LfoEfNfYlR_!!0-item_pic.jpg\",\"item_title\":\"高梵童装2020新款男童女童儿童羽绒服轻薄款白鸭绒反季宝宝外套冬\",\"discount_type\":1,\"discount_amount\":\"60\",\"platform\":\"10\",\"shop_title\":\"高梵童装旗舰店\",\"user_commission\":\"2.91\",\"price\":\"179\",\"end_price\":\"119.00\",\"volume_text\":\"已抢1281件\",\"discount_text\":\"¥60券\",\"user_commission_text\":\"返现2.91\"}"},
        |{"id":"7ae86f5a2b4baa17c0389b66e0ab5f75","source":null,"page_id":"SearchResultPage","exposure_id":"good_list_淘宝","exposure_time":1611562621,"extras":"{\"item_id\":\"603043360748\",\"item_pic\":\"https://img.alicdn.com/bao/uploaded/i4/2204116491838/O1CN01IOsCql1PRqXoqYLGu_!!0-item_pic.jpg\",\"item_title\":\"儿童套头毛衣时尚女童针织衫洋气童装水貂绒中大童2020秋冬新款潮\",\"discount_type\":1,\"discount_amount\":\"30\",\"platform\":\"10\",\"shop_title\":\"婴爱小鱼旗舰店\",\"user_commission\":\"8.19\",\"price\":\"69\",\"end_price\":\"39.00\",\"volume_text\":\"已抢1120件\",\"discount_text\":\"¥30券\",\"user_commission_text\":\"返现8.19\"}"},
        |{"id":"a16dda404d3cf65c4a1238cf6a37997e","source":null,"page_id":"SearchResultPage","exposure_id":"good_list_淘宝","exposure_time":1611562631,"extras":"{\"item_id\":\"609550274328\",\"item_pic\":\"https://img.alicdn.com/bao/uploaded/i3/3855545787/O1CN01xVdM0j1scUcUmp76Z_!!0-item_pic.jpg\",\"item_title\":\"女童裤子春秋冬装儿童装宝宝打底裤外穿保暖一体加绒加厚棉裤洋气\",\"discount_type\":1,\"discount_amount\":\"5\",\"platform\":\"10\",\"shop_title\":\"熹丫熊旗舰店\",\"user_commission\":\"3.12\",\"price\":\"19.9\",\"end_price\":\"14.90\",\"volume_text\":\"已抢2.39万件\",\"discount_text\":\"¥5券\",\"user_commission_text\":\"返现3.12\"}"},
        |{"id":"e0b32b3f6a8428a9937b69324dcebdb5","source":null,"page_id":"SearchResultPage","exposure_id":"good_list_淘宝","exposure_time":1611562640,"extras":"{\"item_id\":\"600791085404\",\"item_pic\":\"https://img.alicdn.com/bao/uploaded/i3/1974808586/O1CN01WcE0E62DIRGBUBQQN_!!0-item_pic.jpg\",\"item_title\":\"童装女童打底裤加绒加厚保暖儿童洋气棉裤春秋冬装宝宝长裤休闲裤\",\"discount_type\":1,\"discount_amount\":\"5\",\"platform\":\"10\",\"shop_title\":\"米酷童装专营店\",\"user_commission\":\"1.73\",\"price\":\"29.8\",\"end_price\":\"24.80\",\"volume_text\":\"已抢1.08万件\",\"discount_text\":\"¥5券\",\"user_commission_text\":\"返现1.73\"}"},
        |{"id":"a0e72d13784567d508f918e8104d1761","source":null,"page_id":"SearchResultPage","exposure_id":"good_list_淘宝","exposure_time":1611562640,"extras":"{\"item_id\":\"609132645940\",\"item_pic\":\"https://img.alicdn.com/bao/uploaded/i1/2103209139/O1CN01wTySWc2HNiIfpCEVA_!!0-item_pic.jpg\",\"item_title\":\"女童爱莎公主裙秋冬2020新款儿童装洋气冰雪奇缘连衣裙蓬蓬纱裙子\",\"discount_type\":1,\"discount_amount\":\"10\",\"platform\":\"10\",\"shop_title\":\"波亚米特旗舰店\",\"user_commission\":\"25.29\",\"price\":\"139\",\"end_price\":\"129.00\",\"volume_text\":\"已抢809件\",\"discount_text\":\"¥10券\",\"user_commission_text\":\"返现25.29\"}"},
        |{"id":"9cfa6bc77f2ce509daa6f87dab8c50d6","source":null,"page_id":"SearchResultPage","exposure_id":"good_list_淘宝","exposure_time":1611562641,"extras":"{\"item_id\":\"600572212490\",\"item_pic\":\"https://img.alicdn.com/bao/uploaded/i2/2201519411661/O1CN01sHXRV51O8mVcjhM3u_!!0-item_pic.jpg\",\"item_title\":\"专柜加绒男童牛仔裤2020新款洋气中大童装加绒秋冬裤子儿童长裤潮\",\"discount_type\":1,\"discount_amount\":\"20\",\"platform\":\"10\",\"shop_title\":\"布丁车旗舰店\",\"user_commission\":\"5.05\",\"price\":\"48.9\",\"end_price\":\"28.90\",\"volume_text\":\"已抢733件\",\"discount_text\":\"¥20券\",\"user_commission_text\":\"返现5.05\"}"},
        |{"id":"4ddbbb2f1de71cc2a78eda5a11dc58da","source":null,"page_id":"SearchResultPage","exposure_id":"good_list_淘宝","exposure_time":1611562641,"extras":"{\"item_id\":\"602296989546\",\"item_pic\":\"https://img.alicdn.com/bao/uploaded/i1/1974808586/O1CN01kygfy32DIRFU4G7hy_!!0-item_pic.jpg\",\"item_title\":\"童装女童棉裤加绒加厚保暖儿童长裤子2020春秋冬装新款宝宝打底裤\",\"discount_type\":1,\"discount_amount\":\"5\",\"platform\":\"10\",\"shop_title\":\"米酷童装专营店\",\"user_commission\":\"7.30\",\"price\":\"39.8\",\"end_price\":\"34.80\",\"volume_text\":\"已抢9075件\",\"discount_text\":\"¥5券\",\"user_commission_text\":\"返现7.30\"}"},
        |{"id":"37f562a2f91af994f5692fe5ecf94317","source":null,"page_id":"SearchResultPage","exposure_id":"good_list_淘宝","exposure_time":1611562647,"extras":"{\"item_id\":\"606915331883\",\"item_pic\":\"https://img.alicdn.com/bao/uploaded/i1/3855545787/O1CN012HyXEP1scUbX7Vfyz_!!0-item_pic.jpg\",\"item_title\":\"儿童裤子秋冬装外穿男童女童装宝宝保暖加绒加厚羊羔绒休闲运动裤\",\"discount_type\":1,\"discount_amount\":\"5\",\"platform\":\"10\",\"shop_title\":\"熹丫熊旗舰店\",\"user_commission\":\"5.22\",\"price\":\"29.9\",\"end_price\":\"24.90\",\"volume_text\":\"已抢1.44万件\",\"discount_text\":\"¥5券\",\"user_commission_text\":\"返现5.22\"}"},
        |{"id":"0892a2f60bc79c2c6857d0497d79fb21","source":null,"page_id":"SearchResultPage","exposure_id":"good_list_淘宝","exposure_time":1611562648,"extras":"{\"item_id\":\"597402685837\",\"item_pic\":\"https://img.alicdn.com/bao/uploaded/i3/2568321327/O1CN01mD6Rut1LfoEfNfYlR_!!0-item_pic.jpg\",\"item_title\":\"高梵童装2020新款男童女童儿童羽绒服轻薄款白鸭绒反季宝宝外套冬\",\"discount_type\":1,\"discount_amount\":\"60\",\"platform\":\"10\",\"shop_title\":\"高梵童装旗舰店\",\"user_commission\":\"2.91\",\"price\":\"179\",\"end_price\":\"119.00\",\"volume_text\":\"已抢1281件\",\"discount_text\":\"¥60券\",\"user_commission_text\":\"返现2.91\"}"},
        |{"id":"683e58f968a4f0661ae6c58e58c81dac","source":null,"page_id":"SearchResultPage","exposure_id":"good_list_淘宝","exposure_time":1611562648,"extras":"{\"item_id\":\"603043360748\",\"item_pic\":\"https://img.alicdn.com/bao/uploaded/i4/2204116491838/O1CN01IOsCql1PRqXoqYLGu_!!0-item_pic.jpg\",\"item_title\":\"儿童套头毛衣时尚女童针织衫洋气童装水貂绒中大童2020秋冬新款潮\",\"discount_type\":1,\"discount_amount\":\"30\",\"platform\":\"10\",\"shop_title\":\"婴爱小鱼旗舰店\",\"user_commission\":\"8.19\",\"price\":\"69\",\"end_price\":\"39.00\",\"volume_text\":\"已抢1120件\",\"discount_text\":\"¥30券\",\"user_commission_text\":\"返现8.19\"}"},
        |{"id":"efb0df6e98187bf8f22a3f4971624348","source":null,"page_id":"SearchResultPage","exposure_id":"good_list_淘宝","exposure_time":1611562651,"extras":"{\"item_id\":\"609550274328\",\"item_pic\":\"https://img.alicdn.com/bao/uploaded/i3/3855545787/O1CN01xVdM0j1scUcUmp76Z_!!0-item_pic.jpg\",\"item_title\":\"女童裤子春秋冬装儿童装宝宝打底裤外穿保暖一体加绒加厚棉裤洋气\",\"discount_type\":1,\"discount_amount\":\"5\",\"platform\":\"10\",\"shop_title\":\"熹丫熊旗舰店\",\"user_commission\":\"3.12\",\"price\":\"19.9\",\"end_price\":\"14.90\",\"volume_text\":\"已抢2.39万件\",\"discount_text\":\"¥5券\",\"user_commission_text\":\"返现3.12\"}"},
        |{"id":"7f920a1a753540ad82437d940ae60120","source":null,"page_id":"SearchResultPage","exposure_id":"good_list_淘宝","exposure_time":1611562658,"extras":"{\"item_id\":\"600572212490\",\"item_pic\":\"https://img.alicdn.com/bao/uploaded/i2/2201519411661/O1CN01sHXRV51O8mVcjhM3u_!!0-item_pic.jpg\",\"item_title\":\"专柜加绒男童牛仔裤2020新款洋气中大童装加绒秋冬裤子儿童长裤潮\",\"discount_type\":1,\"discount_amount\":\"20\",\"platform\":\"10\",\"shop_title\":\"布丁车旗舰店\",\"user_commission\":\"5.05\",\"price\":\"48.9\",\"end_price\":\"28.90\",\"volume_text\":\"已抢733件\",\"discount_text\":\"¥20券\",\"user_commission_text\":\"返现5.05\"}"},
        |{"id":"063025fcc8ae00c6309ab9af28c03716","source":null,"page_id":"SearchResultPage","exposure_id":"good_list_淘宝","exposure_time":1611562658,"extras":"{\"item_id\":\"602296989546\",\"item_pic\":\"https://img.alicdn.com/bao/uploaded/i1/1974808586/O1CN01kygfy32DIRFU4G7hy_!!0-item_pic.jpg\",\"item_title\":\"童装女童棉裤加绒加厚保暖儿童长裤子2020春秋冬装新款宝宝打底裤\",\"discount_type\":1,\"discount_amount\":\"5\",\"platform\":\"10\",\"shop_title\":\"米酷童装专营店\",\"user_commission\":\"7.30\",\"price\":\"39.8\",\"end_price\":\"34.80\",\"volume_text\":\"已抢9075件\",\"discount_text\":\"¥5券\",\"user_commission_text\":\"返现7.30\"}"},
        |{"id":"e4339222ff037c9810c00533cd0c45e4","source":null,"page_id":"SearchResultPage","exposure_id":"good_list_淘宝","exposure_time":1611562658,"extras":"{\"item_id\":\"600791085404\",\"item_pic\":\"https://img.alicdn.com/bao/uploaded/i3/1974808586/O1CN01WcE0E62DIRGBUBQQN_!!0-item_pic.jpg\",\"item_title\":\"童装女童打底裤加绒加厚保暖儿童洋气棉裤春秋冬装宝宝长裤休闲裤\",\"discount_type\":1,\"discount_amount\":\"5\",\"platform\":\"10\",\"shop_title\":\"米酷童装专营店\",\"user_commission\":\"1.73\",\"price\":\"29.8\",\"end_price\":\"24.80\",\"volume_text\":\"已抢1.08万件\",\"discount_text\":\"¥5券\",\"user_commission_text\":\"返现1.73\"}"},
        |{"id":"9809774598cfd1afdb17cf56545cf473","source":null,"page_id":"SearchResultPage","exposure_id":"good_list_淘宝","exposure_time":1611562659,"extras":"{\"item_id\":\"597402685837\",\"item_pic\":\"https://img.alicdn.com/bao/uploaded/i3/2568321327/O1CN01mD6Rut1LfoEfNfYlR_!!0-item_pic.jpg\",\"item_title\":\"高梵童装2020新款男童女童儿童羽绒服轻薄款白鸭绒反季宝宝外套冬\",\"discount_type\":1,\"discount_amount\":\"60\",\"platform\":\"10\",\"shop_title\":\"高梵童装旗舰店\",\"user_commission\":\"2.91\",\"price\":\"179\",\"end_price\":\"119.00\",\"volume_text\":\"已抢1281件\",\"discount_text\":\"¥60券\",\"user_commission_text\":\"返现2.91\"}"},
        |{"id":"a655c2372d014184570301cc347162f5","source":null,"page_id":"SearchResultPage","exposure_id":"good_list_淘宝","exposure_time":1611562659,"extras":"{\"item_id\":\"618352788187\",\"item_pic\":\"https://img.alicdn.com/bao/uploaded/i1/4131192570/O1CN0141GrBm1Ur6XRdousi_!!0-item_pic.jpg\",\"item_title\":\"童装男童加绒裤子一体绒冬装加厚保暖长裤潮流帅气中大儿童运动裤\",\"discount_type\":1,\"discount_amount\":\"40\",\"platform\":\"10\",\"shop_title\":\"西努克旗舰店\",\"user_commission\":\"4.18\",\"price\":\"59.9\",\"end_price\":\"19.90\",\"volume_text\":\"已抢1089件\",\"discount_text\":\"¥40券\",\"user_commission_text\":\"返现4.18\"}"},
        |{"id":"341bab5a884e19f6608d99debdeaa07a","source":null,"page_id":"SearchResultPage","exposure_id":"good_list_淘宝","exposure_time":1611562659,"extras":"{\"item_id\":\"599054484198\",\"item_pic\":\"https://img.alicdn.com/bao/uploaded/i1/2568321327/O1CN01ubj9am1LfoEcs6Upz_!!2568321327.jpg\",\"item_title\":\"高梵童装2020新款正品洋气宝宝短款儿童羽绒服男童女童白鸭绒反季\",\"discount_type\":1,\"discount_amount\":\"70\",\"platform\":\"10\",\"shop_title\":\"高梵童装旗舰店\",\"user_commission\":\"3.40\",\"price\":\"209\",\"end_price\":\"139.00\",\"volume_text\":\"已抢2552件\",\"discount_text\":\"¥70券\",\"user_commission_text\":\"返现3.40\"}"},
        |{"id":"3a464612abbb6fc3c139ac3c0b9a58a7","source":null,"page_id":"SearchResultPage","exposure_id":"good_list_淘宝","exposure_time":1611562666,"extras":"{\"item_id\":\"606915331883\",\"item_pic\":\"https://img.alicdn.com/bao/uploaded/i1/3855545787/O1CN012HyXEP1scUbX7Vfyz_!!0-item_pic.jpg\",\"item_title\":\"儿童裤子秋冬装外穿男童女童装宝宝保暖加绒加厚羊羔绒休闲运动裤\",\"discount_type\":1,\"discount_amount\":\"5\",\"platform\":\"10\",\"shop_title\":\"熹丫熊旗舰店\",\"user_commission\":\"5.22\",\"price\":\"29.9\",\"end_price\":\"24.90\",\"volume_text\":\"已抢1.44万件\",\"discount_text\":\"¥5券\",\"user_commission_text\":\"返现5.22\"}"
        |}
        |]
        |}
        |]
        |""".stripMargin

    val array: Array[AnyRef] = Jackson.string2Array(json)

    if (!array.isEmpty) {
      println("转换成功" + array.length)
      val headMap = new util.HashMap[String, Any]()
      val tailMap = new util.HashMap[String, Any]()
      array.map(x => Jackson.autoParseJson(x.toString, headMap))

      val eventName = headMap.getOrDefault("event_name", "").toString
      val eventDataStr = headMap.getOrDefault("event_data", "").toString

      val eventDatas: Array[AnyRef] = Jackson.string2Array(eventDataStr)

      eventDatas.map(x => Jackson.autoParseJson(x.toString, tailMap))

      val tailKeys = tailMap.keySet

      for (key <- tailKeys) {
        println((key, tailMap.get(key)))
      }


    } else {
      println("转换失败")
    }
  }
}
