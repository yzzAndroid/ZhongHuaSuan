package com.qianfeng.yyz.zhonghuasuan.home.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.qianfeng.yyz.zhonghuasuan.apublic.IDataFronNetCallback;
import com.qianfeng.yyz.zhonghuasuan.bean.AppIndexBean;
import com.qianfeng.yyz.zhonghuasuan.home.model.HomeModelImpl;
import com.qianfeng.yyz.zhonghuasuan.home.model.IHomeModel;
import com.qianfeng.yyz.zhonghuasuan.home.view.IHomeFragment;

/**
 * Created by Administrator on 2016/10/15 0015.
 */
public class HomePresenterImpl implements IHomePresenter, IDataFronNetCallback<AppIndexBean> {

    private IHomeFragment mHomeFragment;
    private IHomeModel mModel;
    private static HomePresenterImpl homePresenter;
    private AppIndexBean mAppIndexBean;
    private Context context;

    private HomePresenterImpl() {
    }

    public static HomePresenterImpl getInstance() {
        if (null == homePresenter) {
            homePresenter = new HomePresenterImpl();
        }
        return homePresenter;
    }


    @Override
    public void enterHome(IHomeFragment fragment, Context context) {

        this.context = context;
        //检查网络
        if (!checkNet()) {
            //显示没网
            Toast.makeText(context, "没网", Toast.LENGTH_SHORT).show();
            return;
        }
        mModel = new HomeModelImpl();
        this.mHomeFragment = fragment;

        //显示加载dialog
        mHomeFragment.showDialog();


        //开始,网络请求
        mModel.getAppIndexDataFromNet(this);
        guessLikelLoading();

    }

    @Override
    public void guessLikelLoading() {

    }

    //检查网络
    @Override
    public boolean checkNet() {
        return true;
    }

    @Override
    public void success(AppIndexBean appIndexBean) {
        //取消dialog
        mHomeFragment.dimissDialog();

        mAppIndexBean = appIndexBean;
        if (null == mAppIndexBean) {
            Toast.makeText(context, "数据错误", Toast.LENGTH_SHORT).show();
            return;
        }
        //要进行数据的展示
        mHomeFragment.initAutoPagerPlayer(mAppIndexBean.getData().getBanner());
        mHomeFragment.initNavigation();
        mHomeFragment.initHelp();
        //初始化推荐标签
        mHomeFragment.initRecommend();
        mHomeFragment.initList(mAppIndexBean);
    }


    @Override
    public void failed(String msg) {

    }
}
