package com.qianfeng.yyz.zhonghuasuan.login.presenter;

import android.content.Context;

import com.qianfeng.yyz.zhonghuasuan.datacallback.IDataFronNetCallback;
import com.qianfeng.yyz.zhonghuasuan.login.model.LoginModel;
import com.qianfeng.yyz.zhonghuasuan.login.view.ILoginView;
import com.qianfeng.yyz.zhonghuasuan.login.view.IregisterView;

import java.util.Map;

/**
 * Created by Administrator on 2016/10/24 0024.
 */
public class LoginPresenterImpl implements ILoginPresenter {

    private static ILoginPresenter presenter;
    private LoginModel model;
    private ILoginView loginView;
    private IregisterView iregisterView;

    public LoginPresenterImpl() {
        model = new LoginModel();
    }

    public static ILoginPresenter getInstance(){
        if (null==presenter){
            presenter = new LoginPresenterImpl();
        }
        return presenter;
    }

    @Override
    public void login(final ILoginView loginView, Map<String, String> map, Context context) {
        this.loginView = loginView;
        model.login(map, new IDataFronNetCallback<Map<String, String>>() {
            @Override
            public void success(Map<String, String> map) {
                loginView.loginSuccess(map);
            }

            @Override
            public void failed(String msg) {
                loginView.loginFailed();
            }
        },context);
    }

    @Override
    public void regist(IregisterView iregisterView, Map<String, String> map, Context context) {

    }
}
