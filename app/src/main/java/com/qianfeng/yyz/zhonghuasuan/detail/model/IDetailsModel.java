package com.qianfeng.yyz.zhonghuasuan.detail.model;

import com.qianfeng.yyz.zhonghuasuan.bean.GoodsDetail;
import com.qianfeng.yyz.zhonghuasuan.datacallback.IDataFronNetCallback;

import java.util.Map;

/**
 * Created by Administrator on 2016/10/20 0020.
 */
public interface IDetailsModel {

    void getDetailsBean(IDataFronNetCallback<GoodsDetail> callback, Map<String,String> body);

}
