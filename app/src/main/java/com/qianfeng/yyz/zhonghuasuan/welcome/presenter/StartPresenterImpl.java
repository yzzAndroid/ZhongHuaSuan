package com.qianfeng.yyz.zhonghuasuan.welcome.presenter;

import android.content.Context;

import com.qianfeng.yyz.zhonghuasuan.welcome.model.CheckIsFirstUseAppImpl;
import com.qianfeng.yyz.zhonghuasuan.welcome.model.ICheckIsFirstUseApp;
import com.qianfeng.yyz.zhonghuasuan.welcome.model.ReadShardPreferenceCallback;
import com.qianfeng.yyz.zhonghuasuan.welcome.view.IStartView;
import com.qianfeng.yyz.zhonghuasuan.welcome.view.IwelcomeView;

/**
 * Created by Administrator on 2016/10/14 0014.
 */
public class StartPresenterImpl implements IStartPresenter,ReadShardPreferenceCallback,EnterHomeCallback{
    private ICheckIsFirstUseApp checkIsFirstUseApp;
    private IStartView startView;
    private  static StartPresenterImpl startPresenter;

    //单利
    public static StartPresenterImpl getInstance(){
        if (null==startPresenter){
            startPresenter = new StartPresenterImpl();
        }
        return startPresenter;
    }



    @Override
    public void start(final Context context, IStartView startView) {
        checkIsFirstUseApp = new CheckIsFirstUseAppImpl();
        this.startView = startView;

        //检查登陆
                checkIsFirstUseApp.checkLoginState(context);
                checkIsFirstUseApp.isFirstUse(context,StartPresenterImpl.this);
    }

        @Override
        public void isTrue() {
            startView.firstUse();
        }

        @Override
        public void isFalse() {
            startView.secondUse();
        }

        @Override
        public void enterHome(Context context) {
            checkIsFirstUseApp.changeRecord(context);
        }
    }
