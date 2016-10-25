package com.qianfeng.yyz.zhonghuasuan.login.model;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.qianfeng.yyz.zhonghuasuan.apublic.MyApi;
import com.qianfeng.yyz.zhonghuasuan.datacallback.IDataFronNetCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/10/24 0024.
 */
public class LoginModel {
    Handler handler;

    public LoginModel() {
        handler = new Handler();
    }

    public void login(Map<String,String> map, final IDataFronNetCallback<Map<String,String>> callback, final Context context){
        OkHttpClient client = new OkHttpClient();
        Request.Builder request = new Request.Builder();
        FormBody.Builder body = new FormBody.Builder();
        body.add(MyApi.UID,map.get(MyApi.UID))
                .add(MyApi.CLIENT_TYPE,map.get(MyApi.CLIENT_TYPE))
                .add(MyApi.VERSION,map.get(MyApi.VERSION))
                .add(MyApi.ACCOUNT,map.get(MyApi.ACCOUNT))
                .add(MyApi.PASSWORD,map.get(MyApi.PASSWORD));
        request.post(body.build()).url(MyApi.Mine.LOGIN);
        client.newCall(request.build()).enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.failed(e.getMessage());
                    }
                });
            }


//             "uid": "2093557574",
//            "uname": "1587hdjb",
//                    "is_premium": "0",
//                    "avatar": "http:\/\/avatar.zhonghuasuan.com\/data\/avatar\/020\/935\/575\/74_avatar_big.jpg",
//                    "sign": "4aa789707000088c95df2d80cff9f36b"
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String json = response.body().string();
                            if (TextUtils.isEmpty(json)){
                                Toast.makeText(context, "未知的错误", Toast.LENGTH_SHORT).show();
                            }else {
                                JSONObject jonject = new JSONObject(json);
                                Log.e("==========","==========="+jonject);
                                String msg = jonject.getString("msg");
                                JSONObject obj = jonject.getJSONObject("data");
                                Map<String,String> map = new HashMap<>();
                                map.put(MyApi.UID,obj.getString(MyApi.UID));
                                map.put(MyApi.U_NAME,obj.getString(MyApi.U_NAME));
                                map.put(MyApi.IS_PREMIUM,obj.getString(MyApi.IS_PREMIUM));
                                map.put(MyApi.AVATAR,obj.getString(MyApi.AVATAR));
                                map.put(MyApi.SIGN,obj.getString(MyApi.SIGN));
                                callback.success(map);
                                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}
