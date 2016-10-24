package com.qianfeng.yyz.zhonghuasuan.detail.view;

import com.qianfeng.yyz.zhonghuasuan.bean.GoodsDetail;

/**
 * Created by Administrator on 2016/10/20 0020.
 */
public interface IDetailsView {

    void refresh();
    void completeRefresh();
    void showError(String msg);
    void initData(GoodsDetail goodsDetail);


}
