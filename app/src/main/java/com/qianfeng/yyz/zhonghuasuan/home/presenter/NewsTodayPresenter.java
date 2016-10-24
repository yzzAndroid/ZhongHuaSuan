package com.qianfeng.yyz.zhonghuasuan.home.presenter;

import com.qianfeng.yyz.zhonghuasuan.bean.NewsNoticeTodayBean;
import com.qianfeng.yyz.zhonghuasuan.datacallback.IDataFronNetCallback;
import com.qianfeng.yyz.zhonghuasuan.home.model.HomeScondModelImpl;
import com.qianfeng.yyz.zhonghuasuan.home.model.IHomeScondModel;
import com.qianfeng.yyz.zhonghuasuan.home.view.INewsNoticeTodayFView;

/**
 * Created by Administrator on 2016/10/18 0018.
 */
public class NewsTodayPresenter implements INewsTodayPresenter,IDataFronNetCallback<NewsNoticeTodayBean> {
    private static INewsTodayPresenter presenter;
    private static IHomeScondModel model;
    private INewsNoticeTodayFView newsNoticeView;
    private boolean isError = false;

    public static INewsTodayPresenter getInstance(){
        if (null==presenter){
            presenter = new NewsTodayPresenter();
            model = new HomeScondModelImpl();
        }
        return presenter;
    }

    @Override
    public void enterNewsNoticeActivity(INewsNoticeTodayFView view) {
        newsNoticeView = view;
        //网络请求
        newsNoticeView.refresh();
        model.NewsNoticeToday(this);
    }

    @Override
    public void refresh() {
        //刷新
        newsNoticeView.refresh();
        model.NewsNoticeToday(this);
    }

    @Override
    public void success(NewsNoticeTodayBean newsNoticeTodayBean) {
        //结束刷新
        if (isError){
            newsNoticeView.dimissError();
        }
            newsNoticeView.completeRefresh();
            newsNoticeView.initList(newsNoticeTodayBean);

    }

    @Override
    public void failed(String msg) {
        //出错
        newsNoticeView.showError();
        isError = true;
    }
}
