package com.qianfeng.yyz.zhonghuasuan.search.model;

import com.qianfeng.yyz.zhonghuasuan.db.SearchHistory;

import java.util.List;

/**
 * Created by Administrator on 2016/10/21 0021.
 */
public interface DBClllback {

    void success(List<SearchHistory> list);
    void success();
}
