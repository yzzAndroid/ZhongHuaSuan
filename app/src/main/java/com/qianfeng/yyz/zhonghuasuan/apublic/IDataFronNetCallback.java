package com.qianfeng.yyz.zhonghuasuan.apublic;

/**
 * Created by Administrator on 2016/10/15 0015.
 */
public interface  IDataFronNetCallback <T> {
    void success(T t);
    void failed(String msg);
}
