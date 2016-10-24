package com.qianfeng.yyz.zhonghuasuan.home.model;

import com.qianfeng.yyz.zhonghuasuan.apublic.MyApi;
import com.qianfeng.yyz.zhonghuasuan.bean.EightGoodsBean;
import com.qianfeng.yyz.zhonghuasuan.datacallback.IDataFronNetCallback;

import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/19 0019.
 */
public class EightGoodsModelImpl implements IEightGoodsModel {

    private HttpServicesEightFiledGoods eightFiledGoods;

    @Override
    public void getEightGoodsBean(final IDataFronNetCallback<EightGoodsBean> callback, Map<String, String> boady) {
        eightFiledGoods = HttpUtils.getHttServiceAppIndex(HttpServicesEightFiledGoods.class);
        eightFiledGoods.getEightFiledGoodsBean(
                boady.get(MyApi.UID),
                boady.get(MyApi.CLIENT_TYPE),
                boady.get(MyApi.TYPE),
                boady.get(MyApi.VERSION),
                boady.get(MyApi.CATEGORY_ID),
                boady.get(MyApi.SIZE),
                boady.get(MyApi.PAGE),
                boady.get(MyApi.SORT)

        )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<EightGoodsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.failed(e.getMessage());
                    }

                    @Override
                    public void onNext(EightGoodsBean eightGoodsBean) {
                        callback.success(eightGoodsBean);
                    }
                });
    }
}
