package com.qianfeng.yyz.zhonghuasuan.home.view;


import com.qianfeng.yyz.zhonghuasuan.bean.AppIndexBean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/15 0015.
 */
public interface IHomeFragment {

    //初始化轮播图
    void initAutoPagerPlayer(List<AppIndexBean.DataBean.BannerBean> list);
    //初始化帮助栏
    void initHelp();
    //初始化导航栏
    void initNavigation();
    //void 初始化推荐标签
    void initRecommend();
    //初始化列表
    void initList(AppIndexBean appIndexBean);


    void showDialog();

    void dimissDialog();
}
