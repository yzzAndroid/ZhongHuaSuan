package com.qianfeng.yyz.zhonghuasuan.home.model;

import com.qianfeng.yyz.zhonghuasuan.apublic.MyApi;
import com.qianfeng.yyz.zhonghuasuan.bean.AppIndexBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/10/15 0015.
 */
public interface HttpServiceAppIndex {

    @FormUrlEncoded
    @POST(MyApi.Home.APP_INDEX)
    Observable<AppIndexBean> postAppIndex(@Field(MyApi.UID) String uid, @Field(MyApi.CLIENT_TYPE) String client_type, @Field(MyApi.VERSION) String version);
}
