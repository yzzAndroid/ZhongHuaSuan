package com.qianfeng.yyz.zhonghuasuan.home.presenter;

import com.qianfeng.yyz.zhonghuasuan.home.view.INewsNoticeTodayFView;

/**
 * Created by Administrator on 2016/10/18 0018.
 */
public interface INewsTodayPresenter {

    void enterNewsNoticeActivity(INewsNoticeTodayFView view);
    void refresh();
}
