package com.qianfeng.yyz.zhonghuasuan.welcome.view;

import android.widget.ImageView;

/**
 * Created by Administrator on 2016/10/14 0014.
 */
public interface IwelcomeView {

    void startUse();
    void resetWindowSize();
    void initViewPager();
    void changePoint(int position);
    void initPoint();
    ImageView initImageView(int resId);
    void initList();
}
