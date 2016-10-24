package com.qianfeng.yyz.zhonghuasuan.newest.view;

import com.qianfeng.yyz.zhonghuasuan.bean.EightGoodsBean;

/**
 * Created by Administrator on 2016/10/20 0020.
 */
public interface INewestSecondView {

    void initList(EightGoodsBean bean);
    void refresh();
    void completeRefresh();
    void showError(String msg);
}
