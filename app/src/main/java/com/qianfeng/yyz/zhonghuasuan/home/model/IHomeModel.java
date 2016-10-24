package com.qianfeng.yyz.zhonghuasuan.home.model;

import android.content.Context;

import com.qianfeng.yyz.zhonghuasuan.bean.GeneralDataBean;
import com.qianfeng.yyz.zhonghuasuan.datacallback.IDataFronNetCallback;
import com.qianfeng.yyz.zhonghuasuan.datacallback.IDataFronNetCallbackGussLike;
import com.qianfeng.yyz.zhonghuasuan.bean.AppIndexBean;

/**
 * Created by Administrator on 2016/10/15 0015.
 */
public interface IHomeModel {

    /**
     * 获取首页数据（不包含“猜你喜欢”）
     */
    boolean getOSNetInformation();
    void getAppIndexDataFromNet(IDataFronNetCallback<AppIndexBean> callback);
    void getAppIndexDataGussLike(IDataFronNetCallbackGussLike callback);
    void getGeneralData(IDataFronNetCallback<GeneralDataBean> callback, Context context);
    void saveGeneralData();
}
