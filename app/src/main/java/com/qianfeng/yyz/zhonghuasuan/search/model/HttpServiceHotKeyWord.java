package com.qianfeng.yyz.zhonghuasuan.search.model;

import com.qianfeng.yyz.zhonghuasuan.apublic.MyApi;
import com.qianfeng.yyz.zhonghuasuan.bean.HotKeyWord;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/10/19.
 */
public interface HttpServiceHotKeyWord {
    @FormUrlEncoded
    @POST(MyApi.Search.KER_WORD)
    Observable<HotKeyWord> postKeyWord(@Field(MyApi.UID) String uid, @Field(MyApi.CLIENT_TYPE) String client_type, @Field(MyApi.VERSION) String version, @Field(MyApi.SIZE) String size);
}
