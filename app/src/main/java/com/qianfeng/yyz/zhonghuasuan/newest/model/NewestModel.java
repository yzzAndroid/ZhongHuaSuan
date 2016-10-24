package com.qianfeng.yyz.zhonghuasuan.newest.model;

import com.qianfeng.yyz.zhonghuasuan.apublic.MyApi;
import com.qianfeng.yyz.zhonghuasuan.bean.EightGoodsBean;
import com.qianfeng.yyz.zhonghuasuan.datacallback.IDataFronNetCallback;
import com.qianfeng.yyz.zhonghuasuan.home.model.HttpUtils;

import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/20 0020.
 */
public class NewestModel implements INewestGoodsModel {


    private HttpServicesNewestTYGoods eightFiledGoods;
    private HttpServicesNewestHourGoods eightFiledGoods2;

    @Override
    public void getEightTYGoodsBean(final IDataFronNetCallback<EightGoodsBean> callback, Map<String, String> boady) {
        eightFiledGoods = HttpUtils.getHttServiceAppIndex(HttpServicesNewestTYGoods.class);
        eightFiledGoods.getTYgoodsBean(
                boady.get(MyApi.UID),
                boady.get(MyApi.CLIENT_TYPE),
                boady.get(MyApi.TYPE),
                boady.get(MyApi.VERSION),
                boady.get(MyApi.CATEGORY_ID),
                boady.get(MyApi.SIZE),
                boady.get(MyApi.PAGE),
                boady.get(MyApi.SORT),
                boady.get(MyApi.DAY)

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

    @Override
    public void getEightHourGoodsBean(final IDataFronNetCallback<EightGoodsBean> callback, Map<String, String> boady) {
        eightFiledGoods2 = HttpUtils.getHttServiceAppIndex(HttpServicesNewestHourGoods.class);
        eightFiledGoods2.getTYgoodsBean(
                boady.get(MyApi.UID),
                boady.get(MyApi.CLIENT_TYPE),
                boady.get(MyApi.TYPE),
                boady.get(MyApi.VERSION),
                boady.get(MyApi.CATEGORY_ID),
                boady.get(MyApi.SIZE),
                boady.get(MyApi.PAGE),
                boady.get(MyApi.SORT),
                boady.get(MyApi.HOUR)

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
