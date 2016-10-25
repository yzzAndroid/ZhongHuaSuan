package com.qianfeng.yyz.zhonghuasuan.login.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.qianfeng.yyz.zhonghuasuan.R;
import com.qianfeng.yyz.zhonghuasuan.apublic.BaseActivity;
import com.qianfeng.yyz.zhonghuasuan.apublic.MD5Utils;
import com.qianfeng.yyz.zhonghuasuan.apublic.MyApi;
import com.qianfeng.yyz.zhonghuasuan.apublic.MyApplication;
import com.qianfeng.yyz.zhonghuasuan.login.presenter.LoginPresenterImpl;
import com.qianfeng.yyz.zhonghuasuan.welcome.model.CheckIsFirstUseAppImpl;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity implements ILoginView {

    private Map<String,String> map;

    @BindView(R.id.login_number)
    EditText number;

    @BindView(R.id.login_psw)
    EditText psw;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initFileds();
    }

    private void initFileds() {
        map = new HashMap<>();

    }

    public void back(View view) {
        finish();
    }

    public void psw_change(View view) {
        psw.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }

    public void soon_register(View view) {
    }

    public void forgetPsw(View view) {
    }

    public void threeLogin(View view) {
    }


    @Override
    public void refresh() {

    }

    @Override
    public void complete() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void loginSuccess(Map<String, String> map) {
        SharedPreferences preferences = getSharedPreferences(CheckIsFirstUseAppImpl.USER_INFO,MODE_PRIVATE);
         preferences.edit().putBoolean(CheckIsFirstUseAppImpl.USER_LOGIN,true)
        .putString(MyApi.UID,map.get(MyApi.UID))
        .putString(MyApi.U_NAME,map.get(MyApi.U_NAME))
        .putString(MyApi.IS_PREMIUM,map.get(MyApi.IS_PREMIUM))
        .putString(MyApi.AVATAR,map.get(MyApi.AVATAR))
        .putString(MyApi.SIGN,map.get(MyApi.SIGN))
        .putString(MyApi.PASSWORD,password).commit();
        MyApplication.getInstance().setLogin(true);
        MyApplication.getInstance().setAvatar(map.get(MyApi.AVATAR));
        MyApplication.getInstance().setSign(map.get(MyApi.SIGN));
        MyApplication.getInstance().setUname(map.get(MyApi.U_NAME));
        MyApplication.getInstance().setPassword(password);
        MyApplication.getInstance().setUid(map.get(MyApi.UID));
        Log.e("======","======="+MyApplication.getInstance().getUname());
        finish();

    }

    @Override
    public void loginFailed() {
        Log.e("=========","=======ffff==");
    }

    public void login(View view) {
        //加密
        password = MD5Utils.md5(psw.getText().toString());
        map.put(MyApi.CLIENT_TYPE,"3");
        map.put(MyApi.VERSION,"3003201");
        map.put(MyApi.ACCOUNT,number.getText().toString());
        map.put(MyApi.PASSWORD,password);
        map.put(MyApi.UID,"-1");
        Log.e("psw","psw"+password);
        LoginPresenterImpl.getInstance().login(this,map,this);

    }
}
