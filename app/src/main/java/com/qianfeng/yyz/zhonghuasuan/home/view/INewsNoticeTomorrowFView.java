package com.qianfeng.yyz.zhonghuasuan.home.view;

import com.qianfeng.yyz.zhonghuasuan.bean.NewsNoticeTodayBean;
import com.qianfeng.yyz.zhonghuasuan.bean.NewsNoticeTomorrowBean;

/**
 * Created by Administrator on 2016/10/18 0018.
 */
public interface INewsNoticeTomorrowFView {

    void completeRefresh();
    void refresh();
    void initList(NewsNoticeTomorrowBean todayBean);
    void showError();
    void dimissError();
}
