package com.qianfeng.yyz.zhonghuasuan.welcome.model;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/10/14 0014.
 */
public class CheckIsFirstUseAppImpl implements ICheckIsFirstUseApp {
    public static final String USE_STATE = "use_state";
    public static final String USE_FIRST = "use_first";
    public static final String USER_INFO = "user_info";
    public static final String USER_LOGIN = "user_login";
    @Override
    public boolean isFirstUse(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(USE_STATE,Context.MODE_PRIVATE);
        boolean state  = preferences.getBoolean(USE_FIRST,true);
        return state;
    }

    @Override
    public void changeRecord(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(USE_STATE,Context.MODE_PRIVATE);
        preferences.edit().putBoolean(USE_FIRST,false).commit();
    }

    @Override
    public boolean checkLoginState(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(USER_INFO,Context.MODE_PRIVATE);
        return preferences.getBoolean(USER_LOGIN,false);
    }
}
