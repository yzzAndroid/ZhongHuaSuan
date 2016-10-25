package com.qianfeng.yyz.zhonghuasuan.apublic;

import android.app.Application;

/**
 * Created by Administrator on 2016/10/14 0014.
 */
public class MyApplication extends Application{

    private boolean isLogin;
    private String uname;
    private String avatar;
    private String sign;
    private String password;
    private String uid;




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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
