package com.qianfeng.yyz.zhonghuasuan.newest.presenter;

import com.qianfeng.yyz.zhonghuasuan.home.view.IClassifyView;
import com.qianfeng.yyz.zhonghuasuan.home.view.IEightFView;
import com.qianfeng.yyz.zhonghuasuan.newest.view.INewestSecondView;

import java.util.Map;

/**
 * Created by Administrator on 2016/10/19 0019.
 */
public interface INewestPresenter {

    void getYDData(INewestSecondView view, Map<String, String> boday);
    void refresh(Map<String, String> boday,boolean isYD);
    void getHourData(INewestSecondView view, Map<String, String> boday);
}
