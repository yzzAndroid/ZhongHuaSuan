package com.qianfeng.yyz.zhonghuasuan.detail.presenter;

import com.qianfeng.yyz.zhonghuasuan.detail.view.IDetailsView;

import java.util.Map;

/**
 * Created by Administrator on 2016/10/20 0020.
 */
public interface IDetailspresenter {

    void getDetailsData(IDetailsView view,Map<String,String>body);
    void refresh(Map<String,String>body);


}
