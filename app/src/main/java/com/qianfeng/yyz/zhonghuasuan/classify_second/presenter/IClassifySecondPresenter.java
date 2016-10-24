package com.qianfeng.yyz.zhonghuasuan.classify_second.presenter;

import com.qianfeng.yyz.zhonghuasuan.classify_second.view.IClassifySecondView;
import com.qianfeng.yyz.zhonghuasuan.home.view.IClassifyView;

import java.util.Map;

/**
 * Created by Administrator on 2016/10/20 0020.
 */
public interface IClassifySecondPresenter {
    void enterClassifySecondView(IClassifySecondView classifyView, Map<String,String> map);
    void enterClassifySecondSearching(IClassifySecondView classifyView, Map<String,String> map);
    void refresh(Map<String,String> map,boolean isSeaching);
}
