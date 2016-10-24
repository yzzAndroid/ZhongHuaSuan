package com.qianfeng.yyz.zhonghuasuan.classify_second.presenter;

import com.qianfeng.yyz.zhonghuasuan.bean.EightGoodsBean;
import com.qianfeng.yyz.zhonghuasuan.classify_second.model.ClassifySeconndModel;
import com.qianfeng.yyz.zhonghuasuan.classify_second.model.IClassifySecondModel;
import com.qianfeng.yyz.zhonghuasuan.classify_second.view.IClassifySecondView;
import com.qianfeng.yyz.zhonghuasuan.datacallback.IDataFronNetCallback;

import java.util.Map;

/**
 * Created by Administrator on 2016/10/20 0020.
 */
public class ClassifySecondPresenter implements IClassifySecondPresenter, IDataFronNetCallback<EightGoodsBean> {
    private static ClassifySecondPresenter presenter;
    private IClassifySecondModel model;
    private IClassifySecondView classifyView;

    public ClassifySecondPresenter() {
        model = new ClassifySeconndModel();
    }

    public static IClassifySecondPresenter getInstance(){
        if (presenter==null){
            presenter = new ClassifySecondPresenter();
        }
        return presenter;
    }

    @Override
    public void enterClassifySecondView(IClassifySecondView classifyView, Map<String,String> map) {
        this.classifyView = classifyView;
        classifyView.refresh();
        model.getClasifySecondBean(this,map);
    }

    @Override
    public void enterClassifySecondSearching(IClassifySecondView classifyView, Map<String, String> map) {
        this.classifyView = classifyView;
        classifyView.refresh();
        model.getClasifySecondSearchingBean(this,map);
    }

    @Override
    public void refresh(Map<String, String> map,boolean isSeaching) {
        if (isSeaching){
            model.getClasifySecondSearchingBean(this,map);
        }else {
            model.getClasifySecondBean(this,map);
        }
        classifyView.refresh();
    }

    @Override
    public void success(EightGoodsBean bean) {
        classifyView.initView(bean);
        classifyView.completeRefresh();
    }

    @Override
    public void failed(String msg) {
        classifyView.completeRefresh();
        classifyView.showError(msg);
    }
}
