package com.qianfeng.yyz.zhonghuasuan.welcome.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

import com.qianfeng.yyz.zhonghuasuan.apublic.MyApi;
import com.qianfeng.yyz.zhonghuasuan.apublic.MyApplication;

/**
 * Created by Administrator on 2016/10/14 0014.
 */
public class CheckIsFirstUseAppImpl implements ICheckIsFirstUseApp {
    public static final String USE_STATE = "use_state";
    public static final String USE_FIRST = "use_first";
    public static final String USER_INFO = "user_info";
    public static final String USER_LOGIN = "user_login";

    @Override
    public void isFirstUse(Context context,ReadShardPreferenceCallback callback) {
        SharedPreferences preferences = context.getSharedPreferences(USE_STATE,Context.MODE_PRIVATE);
        boolean state  = preferences.getBoolean(USE_FIRST,true);
        if (state){
            callback.isTrue();
        }else {
            callback.isFalse();
        }
    }

    @Override
    public void changeRecord(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(USE_STATE,Context.MODE_PRIVATE);
        preferences.edit().putBoolean(USE_FIRST,false).commit();
    }

    @Override
    public void checkLoginState(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(USER_INFO,Context.MODE_PRIVATE);
        boolean isLogin = preferences.getBoolean(USER_LOGIN,false);
        if (isLogin){
            MyApplication.getInstance().setLogin(isLogin);
            MyApplication.getInstance().setUid(preferences.getString(MyApi.UID,""));
            MyApplication.getInstance().setAvatar(preferences.getString(MyApi.AVATAR,""));
            MyApplication.getInstance().setSign(preferences.getString(MyApi.SIGN,""));
            MyApplication.getInstance().setUname(preferences.getString(MyApi.U_NAME,""));
            MyApplication.getInstance().setPassword(preferences.getString(MyApi.PASSWORD,""));
        }
    }
}
