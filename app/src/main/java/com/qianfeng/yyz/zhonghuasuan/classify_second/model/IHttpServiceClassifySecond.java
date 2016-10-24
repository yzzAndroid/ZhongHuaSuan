package com.qianfeng.yyz.zhonghuasuan.classify_second.model;

import com.qianfeng.yyz.zhonghuasuan.apublic.MyApi;
import com.qianfeng.yyz.zhonghuasuan.bean.EightGoodsBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/10/20 0020.
 */
public interface IHttpServiceClassifySecond {

    @FormUrlEncoded
    @POST(MyApi.Home.CLASSIFY_SECOND)
    Observable<EightGoodsBean> postClassifySecond(
            @Field(MyApi.UID)String uid,
            @Field(MyApi.CLIENT_TYPE)String client_type,
            @Field(MyApi.TYPE)String type,
            @Field(MyApi.VERSION)String viersion,
            @Field(MyApi.CATEGORY_ID)String category_id,
            @Field(MyApi.SIZE)String size,
            @Field(MyApi.PAGE)String page,
            @Field(MyApi.SORT)String sort
    );
}
