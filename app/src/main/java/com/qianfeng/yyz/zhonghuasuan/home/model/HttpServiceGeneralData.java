package com.qianfeng.yyz.zhonghuasuan.home.model;

import com.qianfeng.yyz.zhonghuasuan.apublic.MyApi;
import com.qianfeng.yyz.zhonghuasuan.bean.AppIndexGussLikeBean;
import com.qianfeng.yyz.zhonghuasuan.bean.GeneralDataBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2016/10/18 0018.
 */
public interface HttpServiceGeneralData {
    @FormUrlEncoded
    @POST(MyApi.Home.GENERAL_DATA)
    rx.Observable<GeneralDataBean> postGeneralData(@Field(MyApi.UID) String uid, @Field(MyApi.CLIENT_TYPE) String client_type, @Field(MyApi.VERSION) String version);
}
