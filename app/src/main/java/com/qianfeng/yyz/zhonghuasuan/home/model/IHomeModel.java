package com.qianfeng.yyz.zhonghuasuan.home.model;

import com.qianfeng.yyz.zhonghuasuan.apublic.IDataFronNetCallback;
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

}
