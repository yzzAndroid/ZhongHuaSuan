package com.qianfeng.yyz.zhonghuasuan.detail.model;

import com.qianfeng.yyz.zhonghuasuan.apublic.MyApi;
import com.qianfeng.yyz.zhonghuasuan.bean.GoodsDetail;
import com.qianfeng.yyz.zhonghuasuan.classify_second.model.HttpUtils;
import com.qianfeng.yyz.zhonghuasuan.datacallback.IDataFronNetCallback;

import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/20 0020.
 */
public class DetailsModel implements IDetailsModel {
    HttpServiceDetails httpServiceDetails;

    @Override
    public void getDetailsBean(final IDataFronNetCallback<GoodsDetail> callback, Map<String, String> body) {
        httpServiceDetails = HttpUtils.getHttServiceAppIndex(HttpServiceDetails.class);
        httpServiceDetails.postDetails(
                body.get(MyApi.UID),
                body.get(MyApi.GID),
                body.get(MyApi.VERSION),
                body.get(MyApi.CLIENT_TYPE)
        ).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<GoodsDetail>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.failed(e.getMessage());
                    }

                    @Override
                    public void onNext(GoodsDetail goodsDetail) {
                        callback.success(goodsDetail);
                    }
                });
    }
}
