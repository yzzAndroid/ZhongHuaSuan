package com.qianfeng.yyz.zhonghuasuan.classify_second.model;

import com.qianfeng.yyz.zhonghuasuan.bean.EightGoodsBean;
import com.qianfeng.yyz.zhonghuasuan.datacallback.IDataFronNetCallback;

import java.util.Map;

/**
 * Created by Administrator on 2016/10/20 0020.
 */
public interface IClassifySecondModel {
    void  getClasifySecondBean(IDataFronNetCallback<EightGoodsBean> callback,
                            Map<String,String> boady);


    void  getClasifySecondSearchingBean(IDataFronNetCallback<EightGoodsBean> callback,
                               Map<String,String> boady);
}
