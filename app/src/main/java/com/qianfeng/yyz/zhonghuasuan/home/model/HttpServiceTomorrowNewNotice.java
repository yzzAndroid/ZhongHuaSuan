package com.qianfeng.yyz.zhonghuasuan.home.model;

import com.qianfeng.yyz.zhonghuasuan.apublic.MyApi;
import com.qianfeng.yyz.zhonghuasuan.bean.NewsNoticeTodayBean;
import com.qianfeng.yyz.zhonghuasuan.bean.NewsNoticeTomorrowBean;
import com.qianfeng.yyz.zhonghuasuan.home.presenter.NewsTomorrowPresenter;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/10/18 0018.
 */
public interface HttpServiceTomorrowNewNotice {
    @FormUrlEncoded
    @POST(MyApi.Home.New_NOTICE)
    Observable<NewsNoticeTomorrowBean> postNewNotice(@Field(MyApi.UID) String uid
            , @Field(MyApi.VERSION) String version, @Field(MyApi.TYPE) String type
            , @Field(MyApi.CLIENT_TYPE) String client_type);
}
