package com.qianfeng.yyz.zhonghuasuan.home.view;

import com.qianfeng.yyz.zhonghuasuan.bean.EightGoodsBean;

/**
 * Created by Administrator on 2016/10/19 0019.
 */
public interface IEightFView {

    void initList(EightGoodsBean bean);
    void refresh();
    void completeRefresh();
    void showError();
}
