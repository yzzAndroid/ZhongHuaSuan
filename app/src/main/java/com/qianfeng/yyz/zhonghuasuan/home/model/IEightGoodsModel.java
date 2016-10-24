package com.qianfeng.yyz.zhonghuasuan.home.model;

import com.qianfeng.yyz.zhonghuasuan.bean.EightGoodsBean;
import com.qianfeng.yyz.zhonghuasuan.datacallback.IDataFronNetCallback;

import java.util.Map;

/**
 * Created by Administrator on 2016/10/19 0019.
 */
public interface IEightGoodsModel {

     void  getEightGoodsBean(IDataFronNetCallback<EightGoodsBean> callback,
                             Map<String,String> boady);
}
