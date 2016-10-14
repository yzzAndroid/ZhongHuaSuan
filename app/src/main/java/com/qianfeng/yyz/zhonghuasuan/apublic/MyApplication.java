package com.qianfeng.yyz.zhonghuasuan.apublic;

import android.app.Application;

/**
 * Created by Administrator on 2016/10/14 0014.
 */
public class MyApplication extends Application{

    boolean isLogin;

    public static MyApplication application;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static MyApplication getInstance(){
        if (null==application){
            application = new MyApplication();
        }
        return application;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
}
