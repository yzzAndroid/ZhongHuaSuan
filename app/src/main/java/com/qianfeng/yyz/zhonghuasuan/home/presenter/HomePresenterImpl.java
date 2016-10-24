package com.qianfeng.yyz.zhonghuasuan.home.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.qianfeng.yyz.zhonghuasuan.bean.GeneralDataBean;
import com.qianfeng.yyz.zhonghuasuan.datacallback.IDataFronNetCallback;
import com.qianfeng.yyz.zhonghuasuan.datacallback.IDataFronNetCallbackGussLike;
import com.qianfeng.yyz.zhonghuasuan.bean.AppIndexBean;
import com.qianfeng.yyz.zhonghuasuan.bean.AppIndexGussLikeBean;
import com.qianfeng.yyz.zhonghuasuan.home.model.HomeModelImpl;
import com.qianfeng.yyz.zhonghuasuan.home.model.IHomeModel;
import com.qianfeng.yyz.zhonghuasuan.home.view.IHomeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/15 0015.
 */
public class HomePresenterImpl implements IHomePresenter, IDataFronNetCallback<AppIndexBean>, IDataFronNetCallbackGussLike {

    private IHomeFragment mHomeFragment;
    private IHomeModel mModel;
    private static HomePresenterImpl homePresenter;
    private AppIndexBean mAppIndexBean;
    private Context context;
    private AppIndexGussLikeBean mAppIndexGussLikeBean;
    private List<String> emptyListPlayer;
    private boolean isAppIndexFirst = true;
    private boolean isGussLikeFirst = true;

    private HomePresenterImpl() {
        emptyListPlayer = new ArrayList<>();
        emptyListPlayer.add("empty");
        emptyListPlayer.add("empty");
        emptyListPlayer.add("empty");
        emptyListPlayer.add("empty");
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
        mHomeFragment.initAutoPagerPlayer(emptyListPlayer);
        mHomeFragment.initHelp();
        mHomeFragment.initNavigation();
        //初始化推荐标签
        mHomeFragment.initRecommend();
        //要进行数据的展示

        mHomeFragment.initList(null);

        //开始,网络请求
        //请求日常数据
        requestGeneralData(context);
        mModel.getAppIndexDataFromNet(this);
        mModel.getAppIndexDataGussLike(this);

    }

    @Override
    public void guessLikelLoading() {

    }

    //检查网络
    @Override
    public boolean checkNet() {
        return true;
    }

    //刷新
    @Override
    public void refresh() {
        //开始,网络请求
        //请求首页数据
        mModel.getAppIndexDataFromNet(this);
        //请求猜你喜欢
        mModel.getAppIndexDataGussLike(this);
        guessLikelLoading();
    }

    @Override
    public void requestGeneralData(Context context) {
        mModel.getGeneralData(new IDataFronNetCallback<GeneralDataBean>() {
            @Override
            public void success(GeneralDataBean generalDataBean) {
                mHomeFragment.getGeneralDataBean(generalDataBean);
            }

            @Override
            public void failed(String msg) {
                mHomeFragment.getGeneralDataFailed(msg);
            }
        },context);
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
        List<String> list = new ArrayList<>();
        for (AppIndexBean.DataBean.BannerBean bannerBean : mAppIndexBean.getData().getBanner()) {
            list.add(bannerBean.getImg());
        }
        mHomeFragment.notifyPlayer(list);
        if (isAppIndexFirst) {

            mHomeFragment.initList(mAppIndexBean);
            isAppIndexFirst = false;
        } else {
            mHomeFragment.notifiAppIndexList(appIndexBean);
        }
    }


    @Override
    public void successLike(AppIndexGussLikeBean appIndexGussLikeBean) {
        if (isGussLikeFirst) {
            mAppIndexGussLikeBean = appIndexGussLikeBean;
            if (null == mAppIndexGussLikeBean) {
                Toast.makeText(context, "数据错误", Toast.LENGTH_SHORT).show();
                return;
            }
            isGussLikeFirst = false;
        }
        //完成刷新
        mHomeFragment.completeRefresh();
        mHomeFragment.removeEmptyLayout();
        mHomeFragment.initGussLikeList(mAppIndexGussLikeBean);
    }

    @Override
    public void failedLike(String msg) {
        //doNothing
    }

    @Override
    public void failed(String msg) {
        mHomeFragment.addEmptyLayout();
        mHomeFragment.dimissDialog();
    }
}
