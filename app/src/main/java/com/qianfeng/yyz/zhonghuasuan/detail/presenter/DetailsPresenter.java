package com.qianfeng.yyz.zhonghuasuan.detail.presenter;

import com.qianfeng.yyz.zhonghuasuan.bean.GoodsDetail;
import com.qianfeng.yyz.zhonghuasuan.datacallback.IDataFronNetCallback;
import com.qianfeng.yyz.zhonghuasuan.detail.model.DetailsModel;
import com.qianfeng.yyz.zhonghuasuan.detail.model.IDetailsModel;
import com.qianfeng.yyz.zhonghuasuan.detail.view.IDetailsView;

import java.util.Map;

/**
 * Created by Administrator on 2016/10/20 0020.
 */
public class DetailsPresenter implements IDetailspresenter, IDataFronNetCallback<GoodsDetail> {

    private static DetailsPresenter presenter;
    private IDetailsModel model;
    private IDetailsView detailsView;

    public DetailsPresenter() {
        model = new DetailsModel();
    }

    public static DetailsPresenter getInstance() {
        if (null == presenter) {
            presenter = new DetailsPresenter();
        }
        return presenter;
    }

    @Override
    public void getDetailsData(IDetailsView view, Map<String, String> body) {
        detailsView = view;
        detailsView.refresh();
        model.getDetailsBean(this,body);
    }

    @Override
    public void refresh(Map<String, String> body) {
        detailsView.refresh();
        model.getDetailsBean(this,body);
    }

    @Override
    public void success(GoodsDetail goodsDetail) {
        detailsView.completeRefresh();
        detailsView.initData(goodsDetail);
    }

    @Override
    public void failed(String msg) {
        detailsView.showError(msg);
        detailsView.completeRefresh();
    }
}
