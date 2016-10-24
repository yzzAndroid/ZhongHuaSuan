package com.qianfeng.yyz.zhonghuasuan.datacallback;

import com.qianfeng.yyz.zhonghuasuan.bean.AppIndexGussLikeBean;

/**
 * Created by Administrator on 2016/10/15 0015.
 */
public interface IDataFronNetCallbackGussLike<T> {
    void successLike(AppIndexGussLikeBean appIndexGussLikeBean);
    void failedLike(String msg);
}
