package com.qianfeng.yyz.zhonghuasuan.newest.presenter;


import android.util.Log;

import com.qianfeng.yyz.zhonghuasuan.bean.EightGoodsBean;
import com.qianfeng.yyz.zhonghuasuan.datacallback.IDataFronNetCallback;
import com.qianfeng.yyz.zhonghuasuan.newest.model.INewestGoodsModel;
import com.qianfeng.yyz.zhonghuasuan.newest.model.NewestModel;
import com.qianfeng.yyz.zhonghuasuan.newest.view.INewestSecondView;

import java.util.Map;

/**
 * Created by Administrator on 2016/10/20 0020.
 */
public class NewestPresenter implements INewestPresenter {

    private INewestGoodsModel model;
    private INewestSecondView view;
    private static  NewestPresenter presenter;

    public NewestPresenter() {
        this.model = new NewestModel();
    }

//    public static NewestPresenter getInstance(){
//        if (null==presenter){
//            presenter = new NewestPresenter();
//        }
//        return presenter;
//    }


    @Override
    public void getYDData(final INewestSecondView view, Map<String, String> boday) {

        this.view = view;
        view.refresh();
        getDY(boday);
    }

    private void getDY(Map<String,String> boday){
        model.getEightTYGoodsBean(new IDataFronNetCallback<EightGoodsBean>() {
            @Override
            public void success(EightGoodsBean bean) {

                view.completeRefresh();
                view.initList(bean);
            }

            @Override
            public void failed(String msg) {
                view.completeRefresh();
                view.showError(msg);
            }
        }, boday);
    }

    @Override
    public void refresh(Map<String, String> boday, boolean isYD) {
        view.refresh();
        if (isYD){
            getDY(boday);
        }else {
            getHour(boday);
        }
    }

    @Override
    public void getHourData(final INewestSecondView view, Map<String, String> boday) {
        this.view = view;
        view.refresh();
        getHour(boday);
    }

    private void getHour(Map<String,String> boday){
        model.getEightHourGoodsBean(new IDataFronNetCallback<EightGoodsBean>() {
            @Override
            public void success(EightGoodsBean bean) {
                view.completeRefresh();
                view.initList(bean);
            }

            @Override
            public void failed(String msg) {
                view.completeRefresh();
                view.showError(msg);
            }
        }, boday);
    }
}
