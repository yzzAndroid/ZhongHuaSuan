package com.qianfeng.yyz.zhonghuasuan.home.presenter;

import com.qianfeng.yyz.zhonghuasuan.home.view.IClassifyView;
import com.qianfeng.yyz.zhonghuasuan.home.view.IEightFView;

import java.util.Map;

/**
 * Created by Administrator on 2016/10/19 0019.
 */
public interface IEightGoodsPresenter {

    void enterERightGoodsView(IEightFView eightFView, Map<String,String> boday);
    void refresh(Map<String,String> boday);
    void reQuestGoodsInfo(IClassifyView classifyView,Map<String,String> boday);
}
