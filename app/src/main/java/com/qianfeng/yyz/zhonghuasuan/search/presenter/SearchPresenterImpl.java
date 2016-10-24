package com.qianfeng.yyz.zhonghuasuan.search.presenter;


import android.content.Context;

import com.qianfeng.yyz.zhonghuasuan.bean.HotKeyWord;
import com.qianfeng.yyz.zhonghuasuan.datacallback.IDataFronNetCallback;
import com.qianfeng.yyz.zhonghuasuan.db.SearchHistory;
import com.qianfeng.yyz.zhonghuasuan.search.model.DBClllback;
import com.qianfeng.yyz.zhonghuasuan.search.model.ISearchModel;
import com.qianfeng.yyz.zhonghuasuan.search.model.SearchModelImpl;
import com.qianfeng.yyz.zhonghuasuan.search.view.ISearchFView;

import java.util.List;

/**
 * Created by Administrator on 2016/10/19.
 */
public class SearchPresenterImpl implements ISearchPresenter, IDataFronNetCallback<HotKeyWord> {

    private static ISearchPresenter presenter;
    private ISearchModel model;
    private ISearchFView view;
    private Context context;

    private SearchPresenterImpl(){
        model = new SearchModelImpl();
    }

    public static ISearchPresenter getInstance(){
        if (null==presenter){
            presenter = new SearchPresenterImpl();
        }
        return presenter;
    }


    @Override
    public void success(HotKeyWord hotKeyWord) {
        view.getSearchFromNet(hotKeyWord);
    }

    @Override
    public void failed(String msg) {
        view.showError(msg);
    }

    @Override
    public void enterSearch(ISearchFView fragment, Context context) {
        view = fragment;
        this.context = context;
        //请求网络数据
        model.getKeyWordDataFromNet(this);

    }

    @Override
    public boolean checkNet() {
        return false;
    }

    @Override
    public void saveSearchHistory(SearchHistory history) {
        model.saveHistory(history, context, new DBClllback() {
            @Override
            public void success(List<SearchHistory> list) {
                //no thing
            }

            @Override
            public void success() {
                view.refreshHistory();
            }
        });
    }

    @Override
    public void getSearchHistories() {
       model.getSearchNativeData(context, new DBClllback() {
           @Override
           public void success(List<SearchHistory> list) {
               view.initNativeSearch(list);
           }

           @Override
           public void success() {

           }
       });
    }

    @Override
    public void clearSearchHistory() {
        model.clearhistory(context);
    }

    @Override
    public void getSearchLike(SearchHistory history) {
        model.getLike(history, context, new DBClllback() {
            @Override
            public void success(List<SearchHistory> list) {
                view.initNativeSearchingNative(list);
            }

            @Override
            public void success() {

            }
        });
    }

    @Override
    public void searching(String key) {
        model.searching(new IDataFronNetCallback<List<String>>() {
            @Override
            public void success(List<String> list) {
                view.initSearching(list);
            }

            @Override
            public void failed(String msg) {
                view.initSearching(null);
            }
        },key);
    }
}
