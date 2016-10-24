package com.qianfeng.yyz.zhonghuasuan.home.model;

import com.qianfeng.yyz.zhonghuasuan.bean.NewsNoticeTodayBean;
import com.qianfeng.yyz.zhonghuasuan.bean.NewsNoticeTomorrowBean;
import com.qianfeng.yyz.zhonghuasuan.datacallback.IDataFronNetCallback;
import com.qianfeng.yyz.zhonghuasuan.home.presenter.NewsTomorrowPresenter;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/18 0018.
 */
public class HomeScondModelImpl implements IHomeScondModel{
    private HttpServiceTodayNewNotice httpServiceNewNotice;
    private HttpServiceTomorrowNewNotice httpServiceTomorrowNewNotice;

    @Override
    public void NewsNoticeToday(final IDataFronNetCallback<NewsNoticeTodayBean> callback) {
        httpServiceNewNotice =  HttpUtils.getHttServiceAppIndex(HttpServiceTodayNewNotice.class);
        httpServiceNewNotice.postNewNotice("-1","3003201","today_goods","3")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<NewsNoticeTodayBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.failed(e.getMessage());
                    }

                    @Override
                    public void onNext(NewsNoticeTodayBean newsNoticeBean) {
                        callback.success(newsNoticeBean);
                    }
                });


    }

    @Override
    public void NewsNoticeTomorrow(final IDataFronNetCallback<NewsNoticeTomorrowBean> callback) {
        httpServiceTomorrowNewNotice =  HttpUtils.getHttServiceAppIndex(HttpServiceTomorrowNewNotice.class);
        httpServiceTomorrowNewNotice.postNewNotice("-1","3003201","tomorow_goods","3")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<NewsNoticeTomorrowBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.failed(e.getMessage());
                    }

                    @Override
                    public void onNext(NewsNoticeTomorrowBean tomorrowBean) {
                        callback.success(tomorrowBean);
                    }
                });
    }

}
