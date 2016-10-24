package com.qianfeng.yyz.zhonghuasuan.home.view;


import com.qianfeng.yyz.zhonghuasuan.bean.AppIndexBean;
import com.qianfeng.yyz.zhonghuasuan.bean.AppIndexGussLikeBean;
import com.qianfeng.yyz.zhonghuasuan.bean.GeneralDataBean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/15 0015.
 */
public interface IHomeFragment {

    //请求General_data
    void getGeneralDataBean(GeneralDataBean generalDataBean);
    //请求General_data
    void getGeneralDataFailed(String msg);
    //初始化轮播图
    void initAutoPagerPlayer(List<String> list);
    //初始化帮助栏
    void initHelp();
    //初始化导航栏
    void initNavigation();
    //void 初始化推荐标签
    void initRecommend();
    //初始化列表
    void initList(AppIndexBean appIndexBean);
    void initGussLikeList(AppIndexGussLikeBean appIndexGussLikeBean);
    void addEmptyLayout();
    void removeEmptyLayout();

    void showDialog();

    void dimissDialog();

    void notifyPlayer(List<String> list);
    void notifiAppIndexList(AppIndexBean appIndexBean);
    void completeRefresh();
}
