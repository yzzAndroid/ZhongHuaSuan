package com.qianfeng.yyz.zhonghuasuan.home.model;

import com.qianfeng.yyz.zhonghuasuan.bean.NewsNoticeTodayBean;
import com.qianfeng.yyz.zhonghuasuan.bean.NewsNoticeTomorrowBean;
import com.qianfeng.yyz.zhonghuasuan.datacallback.IDataFronNetCallback;

/**
 * Created by Administrator on 2016/10/18 0018.
 */
public interface IHomeScondModel {

    void NewsNoticeToday(IDataFronNetCallback<NewsNoticeTodayBean> callback);
    void NewsNoticeTomorrow(IDataFronNetCallback<NewsNoticeTomorrowBean> callback);
}
