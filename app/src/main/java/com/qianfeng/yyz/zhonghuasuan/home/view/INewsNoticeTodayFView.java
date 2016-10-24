package com.qianfeng.yyz.zhonghuasuan.home.view;

import com.qianfeng.yyz.zhonghuasuan.bean.NewsNoticeTodayBean;

/**
 * Created by Administrator on 2016/10/18 0018.
 */
public interface INewsNoticeTodayFView {
    void completeRefresh();
    void refresh();
    void initList(NewsNoticeTodayBean todayBean);
    void showError();
    void dimissError();
}
