package com.qianfeng.yyz.zhonghuasuan.search.presenter;

import android.content.Context;

import com.qianfeng.yyz.zhonghuasuan.db.SearchHistory;
import com.qianfeng.yyz.zhonghuasuan.search.view.ISearchFView;
import com.qianfeng.yyz.zhonghuasuan.search.view.SearchFragmentFragment;

import java.util.List;

/**
 * Created by Administrator on 2016/10/19.
 */
public interface ISearchPresenter {
    void enterSearch(ISearchFView fragment, Context context);
    boolean checkNet();
    void saveSearchHistory(SearchHistory history);
    void getSearchHistories();
    void clearSearchHistory();
    void getSearchLike(SearchHistory history);
    void searching(String key);

}
