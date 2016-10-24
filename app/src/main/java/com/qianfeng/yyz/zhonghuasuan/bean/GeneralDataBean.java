package com.qianfeng.yyz.zhonghuasuan.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/10/19 0019.
 */
public class GeneralDataBean implements Serializable{


    /**
     * code : 201
     * msg : 请求正确
     * data : {"goods_new_parvial_field":[{"title":"10:00","day":"2","hour":"10:00"},{"title":"14:00","day":"2","hour":"14:00"},{"title":"20:00","day":"2","hour":"20:00"}],"appeal_type":[{"id":"1","name":"申请修改单号","utype":"1","need_reply":"1","sort":"1"},{"id":"2","name":"申请取消资格","utype":"1","need_reply":"0","sort":"2"},{"id":"3","name":"申请延长返款时间","utype":"1","need_reply":"1","sort":"3"},{"id":"4","name":"网购价有误","utype":"1","need_reply":"1","sort":"4"},{"id":"6","name":"单号被审核有误","utype":"1","need_reply":"1","sort":"6"},{"id":"7","name":"其他","utype":"1","need_reply":"1","sort":"7"}],"reg_active_type":2,"goods_category":[{"id":0,"name":"热门推荐","child":[{"id":"10","pid":"1","name":"上衣","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/1_10.png"},{"id":"11","pid":"1","name":"女裤","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/1_11.png"},{"id":"12","pid":"1","name":"裙子","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/1_12.png"},{"id":"13","pid":"1","name":"内衣","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/1_13.png"},{"id":"15","pid":"2","name":"上衣","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/2_15.png"},{"id":"20","pid":"3","name":"女鞋","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/3_20.png"},{"id":"27","pid":"5","name":"休闲零食","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/5_27.png"},{"id":"29","pid":"5","name":"茶叶","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/5_29.png"},{"id":"42","pid":"7","name":"生活日用品","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/7_42.png"},{"id":"43","pid":"7","name":"床上用品","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/7_43.png"},{"id":"46","pid":"8","name":"护肤","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/8_46.png"},{"id":"50","pid":"1","name":"外套","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/1_50.png"},{"id":"84","pid":"83","name":"婴童服装","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/83_84.png"},{"id":"89","pid":"83","name":"玩具益智","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/83_89.png"}]},{"id":"1","pid":"0","name":"潮流女装","child":[{"id":"10","pid":"1","name":"上衣","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/1_10.png"},{"id":"50","pid":"1","name":"外套","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/1_50.png"},{"id":"11","pid":"1","name":"女裤","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/1_11.png"},{"id":"12","pid":"1","name":"裙子","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/1_12.png"},{"id":"51","pid":"1","name":"套装/礼服","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/1_51.png"},{"id":"93","pid":"1","name":"中年女装","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/1_93.png"},{"id":"122","pid":"1","name":"户外服装","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/1_122.png"},{"id":"13","pid":"1","name":"内衣","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/1_13.png"},{"id":"111","pid":"1","name":"女袜","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/1_111.png"},{"id":"14","pid":"1","name":"其它","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/1_14.png"}]},{"id":"2","pid":"0","name":"精品男装","child":[{"id":"15","pid":"2","name":"上衣","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/2_15.png"},{"id":"70","pid":"2","name":"外套","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/2_70.png"},{"id":"112","pid":"2","name":"男袜","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/2_112.png"},{"id":"17","pid":"2","name":"男士内衣","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/2_17.png"},{"id":"16","pid":"2","name":"下装","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/2_16.png"},{"id":"53","pid":"2","name":"套装/礼服","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/2_53.png"},{"id":"104","pid":"2","name":"中年男装","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/2_104.png"},{"id":"123","pid":"2","name":"户外服装","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/2_123.png"},{"id":"18","pid":"2","name":"其它","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/2_18.png"}]},{"id":"3","pid":"0","name":"鞋子箱包","child":[{"id":"19","pid":"3","name":"男鞋","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/3_19.png"},{"id":"21","pid":"3","name":"男包","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/3_21.png"},{"id":"20","pid":"3","name":"女鞋","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/3_20.png"},{"id":"22","pid":"3","name":"女包","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/3_22.png"},{"id":"71","pid":"3","name":"儿童背包","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/3_71.png"},{"id":"72","pid":"3","name":"钱包/卡包","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/3_72.png"},{"id":"73","pid":"3","name":"旅行箱包","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/3_73.png"},{"id":"103","pid":"3","name":"皮具配件","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/3_103.png"},{"id":"124","pid":"3","name":"户外鞋类","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/3_124.png"},{"id":"126","pid":"3","name":"居家拖鞋","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/3_126.png"},{"id":"23","pid":"3","name":"其它","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/3_23.png"}]},{"id":"5","pid":"0","name":"美食/特产","child":[{"id":"27","pid":"5","name":"休闲零食","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/5_27.png"},{"id":"56","pid":"5","name":"零食特产","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/5_56.png"},{"id":"31","pid":"5","name":"进口食品","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/5_31.png"},{"id":"28","pid":"5","name":"地方特产","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/5_28.png"},{"id":"116","pid":"5","name":"生鲜果蔬","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/5_116.png"},{"id":"55","pid":"5","name":"粮油米面","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/5_55.png"},{"id":"29","pid":"5","name":"茶叶","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/5_29.png"},{"id":"30","pid":"5","name":"私房菜","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/5_30.png"},{"id":"58","pid":"5","name":"酒类","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/5_58.png"},{"id":"57","pid":"5","name":"生鲜熟食","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/5_57.png"},{"id":"59","pid":"5","name":"功能/营养","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/5_59.png"},{"id":"94","pid":"5","name":"传统/滋补","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/5_94.png"},{"id":"32","pid":"5","name":"其它","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/5_32.png"}]},{"id":"83","pid":"0","name":"母婴用品","child":[{"id":"84","pid":"83","name":"婴童服装","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/83_84.png"},{"id":"85","pid":"83","name":"婴幼用品","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/83_85.png"},{"id":"86","pid":"83","name":"早教玩具","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/83_86.png"},{"id":"87","pid":"83","name":"童鞋配饰","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/83_87.png"},{"id":"88","pid":"83","name":"孕产新妈","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/83_88.png"},{"id":"89","pid":"83","name":"玩具益智","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/83_89.png"},{"id":"90","pid":"83","name":"其他","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/83_90.png"}]},{"id":"8","pid":"0","name":"美容护肤","child":[{"id":"46","pid":"8","name":"护肤","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/8_46.png"},{"id":"47","pid":"8","name":"彩妆","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/8_47.png"},{"id":"60","pid":"8","name":"美妆工具","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/8_60.png"},{"id":"96","pid":"8","name":"精油香薰","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/8_96.png"},{"id":"97","pid":"8","name":"美发","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/8_97.png"},{"id":"98","pid":"8","name":"美容仪器","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/8_98.png"},{"id":"99","pid":"8","name":"身体护理","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/8_99.png"},{"id":"114","pid":"8","name":"香水","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/8_114.png"},{"id":"48","pid":"8","name":"其它","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/8_48.png"}]},{"id":"4","pid":"0","name":"时尚配饰","child":[{"id":"25","pid":"4","name":"流行饰品","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/4_25.png"},{"id":"24","pid":"4","name":"珠宝首饰","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/4_24.png"},{"id":"75","pid":"4","name":"男女围巾","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/4_75.png"},{"id":"76","pid":"4","name":"品质手表","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/4_76.png"},{"id":"77","pid":"4","name":"眼镜配饰","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/4_77.png"},{"id":"113","pid":"4","name":"男人饰品","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/4_113.png"},{"id":"118","pid":"4","name":"帽子","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/4_118.png"},{"id":"26","pid":"4","name":"其它","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/4_26.png"}]},{"id":"6","pid":"0","name":"数码家电","child":[{"id":"33","pid":"6","name":"电脑/平板","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/6_33.png"},{"id":"34","pid":"6","name":"手机","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/6_34.png"},{"id":"121","pid":"6","name":"相机","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/6_121.png"},{"id":"35","pid":"6","name":"生活电器","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/6_35.png"},{"id":"119","pid":"6","name":"厨房电器","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/6_119.png"},{"id":"120","pid":"6","name":"大家电","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/6_120.png"},{"id":"37","pid":"6","name":"手机/相机配件","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/6_37.png"},{"id":"61","pid":"6","name":"电脑外设/配件","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/6_61.png"},{"id":"95","pid":"6","name":"其他电器配件","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/6_95.png"},{"id":"38","pid":"6","name":"其它","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/6_38.png"}]},{"id":"7","pid":"0","name":"家居日用","child":[{"id":"39","pid":"7","name":"灯饰建材","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/7_39.png"},{"id":"40","pid":"7","name":"家具","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/7_40.png"},{"id":"41","pid":"7","name":"装饰品","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/7_41.png"},{"id":"42","pid":"7","name":"生活日用品","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/7_42.png"},{"id":"43","pid":"7","name":"床上用品","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/7_43.png"},{"id":"44","pid":"7","name":"办公文具","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/7_44.png"},{"id":"100","pid":"7","name":"餐具/水具","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/7_100.png"},{"id":"101","pid":"7","name":"清洁护理","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/7_101.png"},{"id":"102","pid":"7","name":"成人用品","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/7_102.png"},{"id":"115","pid":"7","name":"厨房卫浴","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/7_115.png"},{"id":"45","pid":"7","name":"其它","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/7_45.png"}]},{"id":"9","pid":"0","name":"综合商品","child":[{"id":"65","pid":"9","name":"汽车用品","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/9_65.png"},{"id":"66","pid":"9","name":"运动/户外器材","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/9_66.png"},{"id":"69","pid":"9","name":"办公设备","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/9_69.png"},{"id":"67","pid":"9","name":"宠物用品","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/9_67.png"},{"id":"109","pid":"9","name":"钓鱼用品","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/9_109.png"},{"id":"110","pid":"9","name":"绿植园艺","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/9_110.png"},{"id":"117","pid":"9","name":"鲜花花卉","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/9_117.png"},{"id":"49","pid":"9","name":"其它商品","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/9_49.png"}]}],"invite":{"iv_expiry":7776000,"iv_order_sum":150,"iv_commission":20},"server_time":1476875878,"system_notice":{"id":"123","title":"关于不法人员以众划算身份进行虚假诈骗的公告","content":"近日有不法分子假借众划算工作人员的名义对会员进行虚假宣传、教唆会员加QQ群、注册其他网站会员等。如收到类似信息，请第一时间向众划算客服反馈，谨防受骗。"}}
     */

    private int code;
    private String msg;
    /**
     * goods_new_parvial_field : [{"title":"10:00","day":"2","hour":"10:00"},{"title":"14:00","day":"2","hour":"14:00"},{"title":"20:00","day":"2","hour":"20:00"}]
     * appeal_type : [{"id":"1","name":"申请修改单号","utype":"1","need_reply":"1","sort":"1"},{"id":"2","name":"申请取消资格","utype":"1","need_reply":"0","sort":"2"},{"id":"3","name":"申请延长返款时间","utype":"1","need_reply":"1","sort":"3"},{"id":"4","name":"网购价有误","utype":"1","need_reply":"1","sort":"4"},{"id":"6","name":"单号被审核有误","utype":"1","need_reply":"1","sort":"6"},{"id":"7","name":"其他","utype":"1","need_reply":"1","sort":"7"}]
     * reg_active_type : 2
     * goods_category : [{"id":0,"name":"热门推荐","child":[{"id":"10","pid":"1","name":"上衣","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/1_10.png"},{"id":"11","pid":"1","name":"女裤","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/1_11.png"},{"id":"12","pid":"1","name":"裙子","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/1_12.png"},{"id":"13","pid":"1","name":"内衣","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/1_13.png"},{"id":"15","pid":"2","name":"上衣","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/2_15.png"},{"id":"20","pid":"3","name":"女鞋","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/3_20.png"},{"id":"27","pid":"5","name":"休闲零食","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/5_27.png"},{"id":"29","pid":"5","name":"茶叶","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/5_29.png"},{"id":"42","pid":"7","name":"生活日用品","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/7_42.png"},{"id":"43","pid":"7","name":"床上用品","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/7_43.png"},{"id":"46","pid":"8","name":"护肤","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/8_46.png"},{"id":"50","pid":"1","name":"外套","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/1_50.png"},{"id":"84","pid":"83","name":"婴童服装","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/83_84.png"},{"id":"89","pid":"83","name":"玩具益智","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/83_89.png"}]},{"id":"1","pid":"0","name":"潮流女装","child":[{"id":"10","pid":"1","name":"上衣","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/1_10.png"},{"id":"50","pid":"1","name":"外套","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/1_50.png"},{"id":"11","pid":"1","name":"女裤","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/1_11.png"},{"id":"12","pid":"1","name":"裙子","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/1_12.png"},{"id":"51","pid":"1","name":"套装/礼服","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/1_51.png"},{"id":"93","pid":"1","name":"中年女装","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/1_93.png"},{"id":"122","pid":"1","name":"户外服装","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/1_122.png"},{"id":"13","pid":"1","name":"内衣","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/1_13.png"},{"id":"111","pid":"1","name":"女袜","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/1_111.png"},{"id":"14","pid":"1","name":"其它","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/1_14.png"}]},{"id":"2","pid":"0","name":"精品男装","child":[{"id":"15","pid":"2","name":"上衣","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/2_15.png"},{"id":"70","pid":"2","name":"外套","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/2_70.png"},{"id":"112","pid":"2","name":"男袜","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/2_112.png"},{"id":"17","pid":"2","name":"男士内衣","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/2_17.png"},{"id":"16","pid":"2","name":"下装","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/2_16.png"},{"id":"53","pid":"2","name":"套装/礼服","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/2_53.png"},{"id":"104","pid":"2","name":"中年男装","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/2_104.png"},{"id":"123","pid":"2","name":"户外服装","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/2_123.png"},{"id":"18","pid":"2","name":"其它","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/2_18.png"}]},{"id":"3","pid":"0","name":"鞋子箱包","child":[{"id":"19","pid":"3","name":"男鞋","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/3_19.png"},{"id":"21","pid":"3","name":"男包","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/3_21.png"},{"id":"20","pid":"3","name":"女鞋","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/3_20.png"},{"id":"22","pid":"3","name":"女包","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/3_22.png"},{"id":"71","pid":"3","name":"儿童背包","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/3_71.png"},{"id":"72","pid":"3","name":"钱包/卡包","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/3_72.png"},{"id":"73","pid":"3","name":"旅行箱包","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/3_73.png"},{"id":"103","pid":"3","name":"皮具配件","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/3_103.png"},{"id":"124","pid":"3","name":"户外鞋类","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/3_124.png"},{"id":"126","pid":"3","name":"居家拖鞋","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/3_126.png"},{"id":"23","pid":"3","name":"其它","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/3_23.png"}]},{"id":"5","pid":"0","name":"美食/特产","child":[{"id":"27","pid":"5","name":"休闲零食","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/5_27.png"},{"id":"56","pid":"5","name":"零食特产","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/5_56.png"},{"id":"31","pid":"5","name":"进口食品","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/5_31.png"},{"id":"28","pid":"5","name":"地方特产","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/5_28.png"},{"id":"116","pid":"5","name":"生鲜果蔬","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/5_116.png"},{"id":"55","pid":"5","name":"粮油米面","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/5_55.png"},{"id":"29","pid":"5","name":"茶叶","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/5_29.png"},{"id":"30","pid":"5","name":"私房菜","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/5_30.png"},{"id":"58","pid":"5","name":"酒类","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/5_58.png"},{"id":"57","pid":"5","name":"生鲜熟食","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/5_57.png"},{"id":"59","pid":"5","name":"功能/营养","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/5_59.png"},{"id":"94","pid":"5","name":"传统/滋补","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/5_94.png"},{"id":"32","pid":"5","name":"其它","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/5_32.png"}]},{"id":"83","pid":"0","name":"母婴用品","child":[{"id":"84","pid":"83","name":"婴童服装","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/83_84.png"},{"id":"85","pid":"83","name":"婴幼用品","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/83_85.png"},{"id":"86","pid":"83","name":"早教玩具","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/83_86.png"},{"id":"87","pid":"83","name":"童鞋配饰","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/83_87.png"},{"id":"88","pid":"83","name":"孕产新妈","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/83_88.png"},{"id":"89","pid":"83","name":"玩具益智","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/83_89.png"},{"id":"90","pid":"83","name":"其他","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/83_90.png"}]},{"id":"8","pid":"0","name":"美容护肤","child":[{"id":"46","pid":"8","name":"护肤","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/8_46.png"},{"id":"47","pid":"8","name":"彩妆","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/8_47.png"},{"id":"60","pid":"8","name":"美妆工具","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/8_60.png"},{"id":"96","pid":"8","name":"精油香薰","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/8_96.png"},{"id":"97","pid":"8","name":"美发","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/8_97.png"},{"id":"98","pid":"8","name":"美容仪器","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/8_98.png"},{"id":"99","pid":"8","name":"身体护理","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/8_99.png"},{"id":"114","pid":"8","name":"香水","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/8_114.png"},{"id":"48","pid":"8","name":"其它","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/8_48.png"}]},{"id":"4","pid":"0","name":"时尚配饰","child":[{"id":"25","pid":"4","name":"流行饰品","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/4_25.png"},{"id":"24","pid":"4","name":"珠宝首饰","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/4_24.png"},{"id":"75","pid":"4","name":"男女围巾","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/4_75.png"},{"id":"76","pid":"4","name":"品质手表","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/4_76.png"},{"id":"77","pid":"4","name":"眼镜配饰","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/4_77.png"},{"id":"113","pid":"4","name":"男人饰品","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/4_113.png"},{"id":"118","pid":"4","name":"帽子","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/4_118.png"},{"id":"26","pid":"4","name":"其它","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/4_26.png"}]},{"id":"6","pid":"0","name":"数码家电","child":[{"id":"33","pid":"6","name":"电脑/平板","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/6_33.png"},{"id":"34","pid":"6","name":"手机","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/6_34.png"},{"id":"121","pid":"6","name":"相机","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/6_121.png"},{"id":"35","pid":"6","name":"生活电器","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/6_35.png"},{"id":"119","pid":"6","name":"厨房电器","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/6_119.png"},{"id":"120","pid":"6","name":"大家电","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/6_120.png"},{"id":"37","pid":"6","name":"手机/相机配件","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/6_37.png"},{"id":"61","pid":"6","name":"电脑外设/配件","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/6_61.png"},{"id":"95","pid":"6","name":"其他电器配件","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/6_95.png"},{"id":"38","pid":"6","name":"其它","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/6_38.png"}]},{"id":"7","pid":"0","name":"家居日用","child":[{"id":"39","pid":"7","name":"灯饰建材","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/7_39.png"},{"id":"40","pid":"7","name":"家具","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/7_40.png"},{"id":"41","pid":"7","name":"装饰品","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/7_41.png"},{"id":"42","pid":"7","name":"生活日用品","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/7_42.png"},{"id":"43","pid":"7","name":"床上用品","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/7_43.png"},{"id":"44","pid":"7","name":"办公文具","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/7_44.png"},{"id":"100","pid":"7","name":"餐具/水具","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/7_100.png"},{"id":"101","pid":"7","name":"清洁护理","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/7_101.png"},{"id":"102","pid":"7","name":"成人用品","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/7_102.png"},{"id":"115","pid":"7","name":"厨房卫浴","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/7_115.png"},{"id":"45","pid":"7","name":"其它","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/7_45.png"}]},{"id":"9","pid":"0","name":"综合商品","child":[{"id":"65","pid":"9","name":"汽车用品","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/9_65.png"},{"id":"66","pid":"9","name":"运动/户外器材","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/9_66.png"},{"id":"69","pid":"9","name":"办公设备","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/9_69.png"},{"id":"67","pid":"9","name":"宠物用品","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/9_67.png"},{"id":"109","pid":"9","name":"钓鱼用品","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/9_109.png"},{"id":"110","pid":"9","name":"绿植园艺","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/9_110.png"},{"id":"117","pid":"9","name":"鲜花花卉","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/9_117.png"},{"id":"49","pid":"9","name":"其它商品","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/9_49.png"}]}]
     * invite : {"iv_expiry":7776000,"iv_order_sum":150,"iv_commission":20}
     * server_time : 1476875878
     * system_notice : {"id":"123","title":"关于不法人员以众划算身份进行虚假诈骗的公告","content":"近日有不法分子假借众划算工作人员的名义对会员进行虚假宣传、教唆会员加QQ群、注册其他网站会员等。如收到类似信息，请第一时间向众划算客服反馈，谨防受骗。"}
     */

    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        private int reg_active_type;
        /**
         * iv_expiry : 7776000
         * iv_order_sum : 150
         * iv_commission : 20
         */

        private InviteBean invite;
        private int server_time;
        /**
         * id : 123
         * title : 关于不法人员以众划算身份进行虚假诈骗的公告
         * content : 近日有不法分子假借众划算工作人员的名义对会员进行虚假宣传、教唆会员加QQ群、注册其他网站会员等。如收到类似信息，请第一时间向众划算客服反馈，谨防受骗。
         */

        private SystemNoticeBean system_notice;
        /**
         * title : 10:00
         * day : 2
         * hour : 10:00
         */

        private List<GoodsNewParvialFieldBean> goods_new_parvial_field;
        /**
         * id : 1
         * name : 申请修改单号
         * utype : 1
         * need_reply : 1
         * sort : 1
         */

        private List<AppealTypeBean> appeal_type;
        /**
         * id : 0
         * name : 热门推荐
         * child : [{"id":"10","pid":"1","name":"上衣","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/1_10.png"},{"id":"11","pid":"1","name":"女裤","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/1_11.png"},{"id":"12","pid":"1","name":"裙子","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/1_12.png"},{"id":"13","pid":"1","name":"内衣","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/1_13.png"},{"id":"15","pid":"2","name":"上衣","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/2_15.png"},{"id":"20","pid":"3","name":"女鞋","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/3_20.png"},{"id":"27","pid":"5","name":"休闲零食","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/5_27.png"},{"id":"29","pid":"5","name":"茶叶","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/5_29.png"},{"id":"42","pid":"7","name":"生活日用品","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/7_42.png"},{"id":"43","pid":"7","name":"床上用品","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/7_43.png"},{"id":"46","pid":"8","name":"护肤","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/8_46.png"},{"id":"50","pid":"1","name":"外套","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/1_50.png"},{"id":"84","pid":"83","name":"婴童服装","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/83_84.png"},{"id":"89","pid":"83","name":"玩具益智","img":"http://appstaticv.zhonghuasuan.com/show/img/category/child/83_89.png"}]
         */

        private List<GoodsCategoryBean> goods_category;

        public int getReg_active_type() {
            return reg_active_type;
        }

        public void setReg_active_type(int reg_active_type) {
            this.reg_active_type = reg_active_type;
        }

        public InviteBean getInvite() {
            return invite;
        }

        public void setInvite(InviteBean invite) {
            this.invite = invite;
        }

        public int getServer_time() {
            return server_time;
        }

        public void setServer_time(int server_time) {
            this.server_time = server_time;
        }

        public SystemNoticeBean getSystem_notice() {
            return system_notice;
        }

        public void setSystem_notice(SystemNoticeBean system_notice) {
            this.system_notice = system_notice;
        }

        public List<GoodsNewParvialFieldBean> getGoods_new_parvial_field() {
            return goods_new_parvial_field;
        }

        public void setGoods_new_parvial_field(List<GoodsNewParvialFieldBean> goods_new_parvial_field) {
            this.goods_new_parvial_field = goods_new_parvial_field;
        }

        public List<AppealTypeBean> getAppeal_type() {
            return appeal_type;
        }

        public void setAppeal_type(List<AppealTypeBean> appeal_type) {
            this.appeal_type = appeal_type;
        }

        public List<GoodsCategoryBean> getGoods_category() {
            return goods_category;
        }

        public void setGoods_category(List<GoodsCategoryBean> goods_category) {
            this.goods_category = goods_category;
        }

        public static class InviteBean implements Serializable{
            private int iv_expiry;
            private int iv_order_sum;
            private int iv_commission;

            public int getIv_expiry() {
                return iv_expiry;
            }

            public void setIv_expiry(int iv_expiry) {
                this.iv_expiry = iv_expiry;
            }

            public int getIv_order_sum() {
                return iv_order_sum;
            }

            public void setIv_order_sum(int iv_order_sum) {
                this.iv_order_sum = iv_order_sum;
            }

            public int getIv_commission() {
                return iv_commission;
            }

            public void setIv_commission(int iv_commission) {
                this.iv_commission = iv_commission;
            }
        }

        public static class SystemNoticeBean implements Serializable{
            private String id;
            private String title;
            private String content;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }

        public static class GoodsNewParvialFieldBean implements Serializable{
            private String title;
            private String day;
            private String hour;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }

            public String getHour() {
                return hour;
            }

            public void setHour(String hour) {
                this.hour = hour;
            }
        }

        public static class AppealTypeBean implements Serializable{
            private String id;
            private String name;
            private String utype;
            private String need_reply;
            private String sort;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUtype() {
                return utype;
            }

            public void setUtype(String utype) {
                this.utype = utype;
            }

            public String getNeed_reply() {
                return need_reply;
            }

            public void setNeed_reply(String need_reply) {
                this.need_reply = need_reply;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }
        }

        public static class GoodsCategoryBean implements Serializable{
            private int id;
            private String name;
            /**
             * id : 10
             * pid : 1
             * name : 上衣
             * img : http://appstaticv.zhonghuasuan.com/show/img/category/child/1_10.png
             */

            private List<ChildBean> child;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<ChildBean> getChild() {
                return child;
            }

            public void setChild(List<ChildBean> child) {
                this.child = child;
            }

            public static class ChildBean implements Serializable{
                private String id;
                private String pid;
                private String name;
                private String img;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getPid() {
                    return pid;
                }

                public void setPid(String pid) {
                    this.pid = pid;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }
            }
        }
    }
}
