package com.qianfeng.yyz.zhonghuasuan.login.view;

import java.util.Map;

/**
 * Created by Administrator on 2016/10/24 0024.
 */
public interface ILoginView {
    void refresh();
    void complete();
    void showError();
    void loginSuccess(Map<String,String> map);
    void loginFailed();
}
