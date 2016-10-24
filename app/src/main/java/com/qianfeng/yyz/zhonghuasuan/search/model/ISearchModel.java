package com.qianfeng.yyz.zhonghuasuan.search.model;

import android.content.Context;

import com.qianfeng.yyz.zhonghuasuan.bean.HotKeyWord;
import com.qianfeng.yyz.zhonghuasuan.datacallback.IDataFronNetCallback;
import com.qianfeng.yyz.zhonghuasuan.db.SearchHistory;

import java.util.List;

/**
 * Created by Administrator on 2016/10/19.
 */
public interface ISearchModel {
    /**
     * 获取search页面的热门搜索
     * @param callback
     */

    void getKeyWordDataFromNet(IDataFronNetCallback<HotKeyWord> callback);
    boolean getOSNetInformation();
    void getSearchNativeData(Context context,DBClllback clllback);
    void saveHistory(SearchHistory history,Context context,DBClllback clllback);
    void getLike(SearchHistory searchHistory,Context context,DBClllback clllback);
    void clearhistory(Context context);
    void searching(IDataFronNetCallback<List<String>> callback,String key);
}
