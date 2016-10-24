package com.qianfeng.yyz.zhonghuasuan.home.model;

import com.qianfeng.yyz.zhonghuasuan.apublic.MyApi;
import com.qianfeng.yyz.zhonghuasuan.bean.AppIndexGussLikeBean;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2016/10/18 0018.
 */
public interface HttpServiceAppIndexGussLike {
    @FormUrlEncoded
    @POST(MyApi.Home.APP_GUSS_LIKE)
    rx.Observable<AppIndexGussLikeBean> postAppIndexGussLike(@Field(MyApi.UID) String uid, @Field(MyApi.CLIENT_TYPE) String client_type, @Field(MyApi.VERSION) String version);
}
