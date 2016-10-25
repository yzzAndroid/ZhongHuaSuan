package com.qianfeng.yyz.zhonghuasuan.mine.view;

/**
 * Created by Administrator on 2016/10/24 0024.
 */
public interface IMineView {
    void postImageSuccess(String msg);
    void postImageFailed(String msg);

    void postCollectionSuccess(String msg);
    void postCollectionFailed(String msg);


    void postFootsSuccess(String msg);
    void postFootsFailed(String msg);
}
