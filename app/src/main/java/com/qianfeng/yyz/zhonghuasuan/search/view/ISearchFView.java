package com.qianfeng.yyz.zhonghuasuan.search.view;

import com.qianfeng.yyz.zhonghuasuan.bean.HotKeyWord;
import com.qianfeng.yyz.zhonghuasuan.db.SearchHistory;

import java.util.List;

/**
 * Created by Administrator on 2016/10/21 0021.
 */
public interface ISearchFView {

    void getNativeBean(SearchHistory searchHistory);
    void getSearchFromNet(HotKeyWord hotKeyWord);
    void showError(String msg);
    void initNativeSearch(List<SearchHistory> list);
    void initNativeSearchingNative(List<SearchHistory> list);
    void refreshHistory();
    void initSearching(List<String> list);
}
