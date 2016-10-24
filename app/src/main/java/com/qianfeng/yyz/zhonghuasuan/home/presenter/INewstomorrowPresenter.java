package com.qianfeng.yyz.zhonghuasuan.home.presenter;

import com.qianfeng.yyz.zhonghuasuan.home.view.INewsNoticeTodayFView;
import com.qianfeng.yyz.zhonghuasuan.home.view.INewsNoticeTomorrowFView;

/**
 * Created by Administrator on 2016/10/18 0018.
 */
public interface INewstomorrowPresenter {
    void enterNewsNoticeActivity(INewsNoticeTomorrowFView view);
    void refresh();
}
