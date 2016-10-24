package com.qianfeng.yyz.zhonghuasuan.classify_second.view;

import com.qianfeng.yyz.zhonghuasuan.bean.EightGoodsBean;

/**
 * Created by Administrator on 2016/10/20 0020.
 */
public interface IClassifySecondView {
    void initView(EightGoodsBean bean);
    void refresh();
    void completeRefresh();
    void showError(String msg);
}
