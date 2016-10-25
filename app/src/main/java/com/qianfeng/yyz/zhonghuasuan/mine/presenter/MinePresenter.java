package com.qianfeng.yyz.zhonghuasuan.mine.presenter;

import com.qianfeng.yyz.zhonghuasuan.datacallback.IDataFronNetCallback;
import com.qianfeng.yyz.zhonghuasuan.mine.model.MineModel;
import com.qianfeng.yyz.zhonghuasuan.mine.view.IMineView;

import java.util.Map;

/**
 * Created by Administrator on 2016/10/24 0024.
 */
public class MinePresenter implements IMinepresebter {

    private static IMinepresebter iMinepresebter;
    private MineModel model;
    private static IMineView mineView;


    public MinePresenter() {
        model = new MineModel();
    }

    public static IMinepresebter getInstance(IMineView view){
        if (null==iMinepresebter){
            mineView = view;
            iMinepresebter = new MinePresenter();
        }
        return iMinepresebter;
    }

    @Override
    public void postImg(Map<String, Object> map) {
        model.postImg(map, new IDataFronNetCallback<String>() {
            @Override
            public void success(String s) {
                mineView.postImageSuccess(s);
            }

            @Override
            public void failed(String msg) {
                mineView.postImageFailed(msg);
            }
        });
    }

    @Override
    public void postCollection(Map<String, Object> map) {

    }

    @Override
    public void postFoots(Map<String, Object> map) {

    }
}
