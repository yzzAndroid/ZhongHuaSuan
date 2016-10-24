package com.qianfeng.yyz.zhonghuasuan.home.presenter;

import android.content.Context;

import com.qianfeng.yyz.zhonghuasuan.home.view.IHomeFragment;

/**
 * Created by Administrator on 2016/10/15 0015.
 */
public interface IHomePresenter {
    void enterHome(IHomeFragment fragment, Context context);
    void guessLikelLoading();
    boolean checkNet();
    void refresh();
    void requestGeneralData(Context context);
}
