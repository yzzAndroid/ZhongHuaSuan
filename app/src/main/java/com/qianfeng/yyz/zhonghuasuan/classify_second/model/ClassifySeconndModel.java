package com.qianfeng.yyz.zhonghuasuan.classify_second.model;

import com.qianfeng.yyz.zhonghuasuan.apublic.MyApi;
import com.qianfeng.yyz.zhonghuasuan.bean.EightGoodsBean;
import com.qianfeng.yyz.zhonghuasuan.datacallback.IDataFronNetCallback;

import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/20 0020.
 */
public class ClassifySeconndModel implements IClassifySecondModel {
    private IHttpServiceClassifySecond classifySecond;
    private IHttpServiceClassifySecondSearching searching;
    @Override
    public void getClasifySecondBean(final IDataFronNetCallback<EightGoodsBean> callback, Map<String, String> boady) {
        classifySecond = HttpUtils.getHttServiceAppIndex(IHttpServiceClassifySecond.class);
        classifySecond.postClassifySecond(
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
                    public void onNext(EightGoodsBean classifySecondBean) {
                        callback.success(classifySecondBean);
                    }
                });

    }

    @Override
    public void getClasifySecondSearchingBean(final IDataFronNetCallback<EightGoodsBean> callback, Map<String, String> boady) {
        searching = HttpUtils.getHttServiceAppIndex(IHttpServiceClassifySecondSearching.class);
        searching.postClassifySecond(
                boady.get(MyApi.UID),
                boady.get(MyApi.CLIENT_TYPE),
                boady.get(MyApi.TYPE),
                boady.get(MyApi.VERSION),
                boady.get(MyApi.CATEGORY_ID),
                boady.get(MyApi.SIZE),
                boady.get(MyApi.PAGE),
                boady.get(MyApi.SORT),
                boady.get(MyApi.SEARCH_KEY)
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
                    public void onNext(EightGoodsBean classifySecondBean) {
                        callback.success(classifySecondBean);
                    }
                });

    }
}
