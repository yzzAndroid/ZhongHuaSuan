package com.qianfeng.yyz.zhonghuasuan.login.presenter;

import android.content.Context;

import com.qianfeng.yyz.zhonghuasuan.login.view.ILoginView;
import com.qianfeng.yyz.zhonghuasuan.login.view.IregisterView;

import java.util.Map;

/**
 * Created by Administrator on 2016/10/24 0024.
 */
public interface ILoginPresenter {

    void login(ILoginView loginView, Map<String,String> map, Context context);
    void regist(IregisterView iregisterView,Map<String,String> map,Context context);
}
