package com.qianfeng.yyz.zhonghuasuan.classify_second.model;

import com.qianfeng.yyz.zhonghuasuan.apublic.MyApi;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/10/15 0015.
 */
public class HttpUtils {


    /**
     * 获得网络服务
     * @param <T> 泛型
     * @return
     */
    //首页的数据
    public static  <T> T getHttServiceAppIndex(Class<T> _class){

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(MyApi.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();

            return retrofit.create(_class);
        }
    }

