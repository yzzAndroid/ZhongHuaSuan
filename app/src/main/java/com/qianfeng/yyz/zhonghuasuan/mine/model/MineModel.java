package com.qianfeng.yyz.zhonghuasuan.mine.model;

import android.os.Handler;
import android.util.Log;

import com.qianfeng.yyz.zhonghuasuan.apublic.MyApi;
import com.qianfeng.yyz.zhonghuasuan.datacallback.IDataFronNetCallback;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/10/24 0024.
 */
public class MineModel {

    Handler handler ;

    public MineModel() {
        handler = new Handler();
    }

    public void postImg(Map<String,Object> map, final IDataFronNetCallback<String> callback){
        OkHttpClient client = new OkHttpClient();
        MultipartBody.Builder body = new MultipartBody.Builder();
        body.setType(MultipartBody.FORM);
        for (Map.Entry<String,Object> entry:map.entrySet()){
           if (entry.getValue() instanceof File){
               body.addFormDataPart(entry.getKey(),((File) entry.getValue()).getAbsolutePath(), RequestBody.create(MediaType.parse("image/*"),(File) entry.getValue()));
           }else {
               body.addFormDataPart(entry.getKey(),entry.getValue().toString());
           }
        }
        final Request.Builder request = new Request.Builder();
        request.url(MyApi.Mine.UPDATAIMG).post(body.build());
        client.newCall(request.build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.failed(e.getMessage());
                        Log.e("===========","==========="+e.getMessage());
                    }
                });

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String str = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.success(str);
                    }
                });
            }
        });
    }
}
