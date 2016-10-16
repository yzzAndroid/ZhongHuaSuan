package com.qianfeng.yyz.zhonghuasuan.home.model;

import android.util.Log;

import com.qianfeng.yyz.zhonghuasuan.apublic.IDataFronNetCallback;
import com.qianfeng.yyz.zhonghuasuan.apublic.MyApi;
import com.qianfeng.yyz.zhonghuasuan.bean.AppIndexBean;

import java.io.IOException;
import java.util.concurrent.ScheduledExecutorService;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/15 0015.
 */
public class HomeModelImpl implements IHomeModel {

    private  HttpServiceAppIndex mHttpServiceAppIndex;

    @Override
    public boolean getOSNetInformation() {
        return false;
    }

    @Override
    public void getAppIndexDataFromNet(final IDataFronNetCallback callback) {

        mHttpServiceAppIndex = HttpUtils.getHttServiceAppIndex(HttpServiceAppIndex.class);
        mHttpServiceAppIndex.postAppIndex("-1","3","3003201")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<AppIndexBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.failed(e.getMessage());
                    }

                    @Override
                    public void onNext(AppIndexBean appIndexBean) {
                        callback.success(appIndexBean);
                    }
                });
    }
}
