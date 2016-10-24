package com.qianfeng.yyz.zhonghuasuan.home.presenter;


import com.qianfeng.yyz.zhonghuasuan.bean.EightGoodsBean;
import com.qianfeng.yyz.zhonghuasuan.datacallback.IDataFronNetCallback;
import com.qianfeng.yyz.zhonghuasuan.home.model.EightGoodsModelImpl;
import com.qianfeng.yyz.zhonghuasuan.home.view.IClassifyView;
import com.qianfeng.yyz.zhonghuasuan.home.view.IEightFView;

import java.util.Map;

/**
 * Created by Administrator on 2016/10/19 0019.
 */
public class EightGoodsPresentImpl implements IEightGoodsPresenter{

    private IEightFView eightFView;
    private EightGoodsModelImpl model;
    private static EightGoodsPresentImpl present;
    private IClassifyView classifyView;


    public EightGoodsPresentImpl() {
        model = new EightGoodsModelImpl();
    }

    //单利
    public static EightGoodsPresentImpl getInstance(){
        if (null==present){
            present = new EightGoodsPresentImpl();
        }
        return present;
    }

    @Override
    public void enterERightGoodsView(IEightFView activity, Map<String,String> boday) {
        this.eightFView = activity;
        //刷新动画
        eightFView.refresh();
        model.getEightGoodsBean(new IDataFronNetCallback<EightGoodsBean>() {
            @Override
            public void success(EightGoodsBean eightGoodsBean) {
               eightFView.completeRefresh();
                eightFView.initList(eightGoodsBean);
            }

            @Override
            public void failed(String msg) {
                eightFView.showError();
            }
        }, boday);
    }




    @Override
    public void refresh(Map<String, String> boday) {
        eightFView.refresh();
        model.getEightGoodsBean(new IDataFronNetCallback<EightGoodsBean>() {
            @Override
            public void success(EightGoodsBean eightGoodsBean) {
                eightFView.completeRefresh();
                eightFView.initList(eightGoodsBean);
            }

            @Override
            public void failed(String msg) {
                eightFView.showError();
            }
        },boday);
    }

    @Override
    public void reQuestGoodsInfo(final IClassifyView classifyView, Map<String, String> boday) {
        this.classifyView = classifyView;
        classifyView.refresh();
        model.getEightGoodsBean(new IDataFronNetCallback<EightGoodsBean>() {
            @Override
            public void success(EightGoodsBean bean) {
                classifyView.completeRefresh();
               //待定
            }

            @Override
            public void failed(String msg) {

            }
        },boday);
    }

}
