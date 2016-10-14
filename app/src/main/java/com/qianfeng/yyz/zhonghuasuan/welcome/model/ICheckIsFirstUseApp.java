package com.qianfeng.yyz.zhonghuasuan.welcome.model;

import android.content.Context;

/**
 * Created by Administrator on 2016/10/14 0014.
 */
public interface ICheckIsFirstUseApp {

    /**是否是第一次登陆
     * @param context
     * @return
     */
    boolean isFirstUse(Context context);

    /**
     * 保存登陆状态
     * @param context
     */
    void changeRecord(Context context);


    /**
     * 检查登陆状态
     * @param context
     */
    boolean checkLoginState(Context context);
}
