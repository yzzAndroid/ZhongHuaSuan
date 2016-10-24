package com.qianfeng.yyz.zhonghuasuan.home.model;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;
import com.qianfeng.yyz.zhonghuasuan.apublic.MyApi;
import com.qianfeng.yyz.zhonghuasuan.bean.GeneralDataBean;
import com.qianfeng.yyz.zhonghuasuan.datacallback.IDataFronNetCallback;
import com.qianfeng.yyz.zhonghuasuan.datacallback.IDataFronNetCallbackGussLike;
import com.qianfeng.yyz.zhonghuasuan.bean.AppIndexBean;
import com.qianfeng.yyz.zhonghuasuan.bean.AppIndexGussLikeBean;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/15 0015.
 */
public class HomeModelImpl implements IHomeModel {

    private  HttpServiceAppIndex mHttpServiceAppIndex;
    private HttpServiceAppIndexGussLike mHttpServiceAppIndexGussLike;
    private HttpServiceGeneralData httpServiceGeneralData;


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
    @Override
    public void getAppIndexDataGussLike(final IDataFronNetCallbackGussLike callback) {
        mHttpServiceAppIndexGussLike = HttpUtils.getHttServiceAppIndex(HttpServiceAppIndexGussLike.class);
        mHttpServiceAppIndexGussLike.postAppIndexGussLike("-1","3","3003201")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<AppIndexGussLikeBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.failedLike(e.getMessage());
                    }

                    @Override
                    public void onNext(AppIndexGussLikeBean appIndexGussLikeBean) {
                        callback.successLike(appIndexGussLikeBean);
                    }
                });
    }

    @Override
    public void getGeneralData(final IDataFronNetCallback<GeneralDataBean> callback, final Context context) {
        final Handler handler = new Handler();
//        httpServiceGeneralData = HttpUtils.getHttServiceAppIndex(HttpServiceGeneralData.class);
//        httpServiceGeneralData.postGeneralData("-1","3","3003201")
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Subscriber<GeneralDataBean>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        callback.failed(e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(GeneralDataBean generalDataBean) {
//                        callback.success(generalDataBean);
//                    }
//                });

        OkHttpClient client = new OkHttpClient();
        final Request.Builder request = new Request.Builder();
        FormBody.Builder body = new FormBody.Builder();
        body.add(MyApi.UID,"-1");
        body.add(MyApi.CLIENT_TYPE,"3");
        body.add(MyApi.VERSION,"3003201");
        request.url(MyApi.BASE_URL+MyApi.Home.GENERAL_DATA)
                .post(body.build());
        client.newCall(request.build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                 callback.failed(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Gson gson = new Gson();
                final GeneralDataBean ben = gson.fromJson(json,GeneralDataBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.success(ben);
                    }
                });
                //保存
                saveGeneralJson(context,json);
            }
        });
    }

    private void saveGeneralJson(Context context,String json) {
        File root;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            root = Environment.getExternalStorageDirectory();
        } else {
            root = context.getCacheDir();
        }
        File file = new File(root, MyApi.GENERAL_FPATH);
        if (!file.exists()) {
            file.mkdirs();
        }

        File f = new File(file,"general");
        try {
            FileOutputStream out = new FileOutputStream(f.getAbsolutePath());
            out.write(json.getBytes());
            out.flush();
            out.close();
            Log.e("=========","============"+"成功"+f.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("=======","==========="+e.getMessage());
        }
    }


    @Override
    public void saveGeneralData() {

    }
}
