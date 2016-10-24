package com.qianfeng.yyz.zhonghuasuan.newest.model;

import com.qianfeng.yyz.zhonghuasuan.bean.EightGoodsBean;
import com.qianfeng.yyz.zhonghuasuan.datacallback.IDataFronNetCallback;

import java.util.Map;

/**
 * Created by Administrator on 2016/10/19 0019.
 */
public interface INewestGoodsModel {

     void  getEightTYGoodsBean(IDataFronNetCallback<EightGoodsBean> callback,
                             Map<String, String> boady);

     void  getEightHourGoodsBean(IDataFronNetCallback<EightGoodsBean> callback,
                               Map<String, String> boady);
}
