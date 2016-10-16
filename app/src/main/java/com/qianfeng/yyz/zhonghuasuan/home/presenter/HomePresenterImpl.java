package com.qianfeng.yyz.zhonghuasuan.home.presenter;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.qianfeng.yyz.zhonghuasuan.apublic.IDataFronNetCallback;
import com.qianfeng.yyz.zhonghuasuan.bean.AppIndexBean;
import com.qianfeng.yyz.zhonghuasuan.home.model.HomeModelImpl;
import com.qianfeng.yyz.zhonghuasuan.home.model.IHomeModel;
import com.qianfeng.yyz.zhonghuasuan.home.view.IHomeFragment;

/**
 * Created by Administrator on 2016/10/15 0015.
 */
public class HomePresenterImpl implements IHomePresenter,IDataFronNetCallback<AppIndexBean> {

    private IHomeFragment mHomeFragment;
    private IHomeModel mModel;
    private static HomePresenterImpl homePresenter;

    private HomePresenterImpl() {
    }

    public static HomePresenterImpl getInstance(){
        if (null==homePresenter){
            homePresenter = new HomePresenterImpl();
        }
        return homePresenter;
    }



    @Override
    public void enterHome(IHomeFragment fragment) {
        mModel = new HomeModelImpl();
        this.mHomeFragment = fragment;
        //开始,网络请求
        mModel.getAppIndexDataFromNet(this);
        guessLikelLoading();

    }

    @Override
    public void guessLikelLoading() {

    }

    @Override
    public void success(AppIndexBean appIndexBean) {
    }


    @Override
    public void failed(String msg) {

    }
}
