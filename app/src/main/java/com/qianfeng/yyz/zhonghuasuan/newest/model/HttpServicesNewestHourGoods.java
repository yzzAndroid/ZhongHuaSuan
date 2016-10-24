package com.qianfeng.yyz.zhonghuasuan.newest.model;

import com.qianfeng.yyz.zhonghuasuan.apublic.MyApi;
import com.qianfeng.yyz.zhonghuasuan.bean.EightGoodsBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/10/19 0019.
 */
public interface HttpServicesNewestHourGoods {
    @FormUrlEncoded
    @POST(MyApi.Home.EIGHTFILEDS)
    Observable<EightGoodsBean> getTYgoodsBean(
            @Field(MyApi.UID) String uid,
            @Field(MyApi.CLIENT_TYPE) String client_type,
            @Field(MyApi.TYPE) String type,
            @Field(MyApi.VERSION) String viersion,
            @Field(MyApi.CATEGORY_ID) String category_id,
            @Field(MyApi.SIZE) String size,
            @Field(MyApi.PAGE) String page,
            @Field(MyApi.SORT) String sort,
            @Field(MyApi.HOUR) String hour
    );
}
