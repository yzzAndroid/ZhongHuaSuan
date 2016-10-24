package com.qianfeng.yyz.zhonghuasuan.home.presenter;

import com.qianfeng.yyz.zhonghuasuan.bean.NewsNoticeTodayBean;
import com.qianfeng.yyz.zhonghuasuan.bean.NewsNoticeTomorrowBean;
import com.qianfeng.yyz.zhonghuasuan.datacallback.IDataFronNetCallback;
import com.qianfeng.yyz.zhonghuasuan.home.model.HomeScondModelImpl;
import com.qianfeng.yyz.zhonghuasuan.home.model.IHomeScondModel;
import com.qianfeng.yyz.zhonghuasuan.home.view.INewsNoticeTomorrowFView;

/**
 * Created by Administrator on 2016/10/18 0018.
 */
public class NewsTomorrowPresenter implements INewstomorrowPresenter, IDataFronNetCallback<NewsNoticeTomorrowBean> {
    private static NewsTomorrowPresenter presenter;
    private static IHomeScondModel model;
    private INewsNoticeTomorrowFView newsNoticeView;
    private boolean isError = false;

    public static NewsTomorrowPresenter getInstance(){
        if (null==presenter){
            presenter = new NewsTomorrowPresenter();
            model = new HomeScondModelImpl();
        }
        return presenter;
    }

    @Override
    public void enterNewsNoticeActivity(INewsNoticeTomorrowFView view) {
        newsNoticeView = view;
        //刷新
        view.refresh();
        //网络请求
        model.NewsNoticeTomorrow(this);
    }

    @Override
    public void refresh() {
        //刷新
        newsNoticeView.refresh();
        //网络请求
        model.NewsNoticeTomorrow(this);
    }

    @Override
    public void success(NewsNoticeTomorrowBean tomorrowBean) {
        //结束刷新
        if (isError){
            newsNoticeView.dimissError();
        }
        newsNoticeView.completeRefresh();
        newsNoticeView.initList(tomorrowBean);
    }

    @Override
    public void failed(String msg) {
        //出错
        newsNoticeView.showError();
        isError = true;
    }
}
