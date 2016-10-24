package com.qianfeng.yyz.zhonghuasuan.detail.model;



import com.qianfeng.yyz.zhonghuasuan.apublic.MyApi;
import com.qianfeng.yyz.zhonghuasuan.bean.GoodsDetail;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/10/20 0020.
 */
public interface HttpServiceDetails {

    @FormUrlEncoded
    @POST(MyApi.DETAILS)
    Observable<GoodsDetail> postDetails(
            @Field(MyApi.UID)String uid,
            @Field(MyApi.GID)String gid,
            @Field(MyApi.VERSION)String version,
            @Field(MyApi.CLIENT_TYPE)String client_type
    );

}
