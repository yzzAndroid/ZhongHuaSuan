package com.qianfeng.yyz.zhonghuasuan.newest.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.qianfeng.yyz.zhonghuasuan.R;
import com.qianfeng.yyz.zhonghuasuan.adapter.MyEightAdapter;
import com.qianfeng.yyz.zhonghuasuan.apublic.DialogUtils;
import com.qianfeng.yyz.zhonghuasuan.apublic.MyApi;
import com.qianfeng.yyz.zhonghuasuan.bean.EightGoodsBean;
import com.qianfeng.yyz.zhonghuasuan.detail.view.DetailsActivity;
import com.qianfeng.yyz.zhonghuasuan.newest.presenter.NewestPresenter;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewestSecondFragment extends Fragment implements INewestSecondView {

    private Map<String, String> map;

    private boolean isVisiable = false;
    private boolean isParaed = false;

    @BindView(R.id.Newest_pullGidView)
    PullToRefreshGridView gridView;

    private MyEightAdapter adapter;
    private AlertDialog dialog;
    private int type;
    private EightGoodsBean mBean;
    private boolean isDY = true;

    private NewestPresenter presenter;

    public NewestSecondFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newest_second, container, false);
        ButterKnife.bind(this, view);
        initFileds();
        isParaed = true;
        lazy();
        return view;
    }

    private void lazy() {
            if (isParaed&&isVisiable) {
                if (null!=mBean){
                    return;
                }
                if (type == 0 || type == 4) {
                    presenter.getYDData(this, map);
                } else {
                    presenter.getHourData(this, map);
                }
            }
    }

    public Map<String, String> getMap() {
        return map;
    }

    //点击刷新
    public void doFresh(){
        presenter.refresh(map,isDY);
    }

    private void initFileds() {
        presenter = new NewestPresenter();
        map = new HashMap<>();
        map.put(MyApi.UID, "-1");
        map.put(MyApi.CLIENT_TYPE, "3");
        map.put(MyApi.TYPE, "new");
        map.put(MyApi.VERSION, "3003201");
        map.put(MyApi.CATEGORY_ID, "0");
        map.put(MyApi.SIZE, "30");
        map.put(MyApi.PAGE, "1");
        map.put(MyApi.SORT, "default");
        map.put(MyApi.HOUR, "10");
        map.put(MyApi.DAY, "2");
        type = getArguments().getInt("type");
        //初始化
        dialog = DialogUtils.beginLoading(getContext());

        adapter = new MyEightAdapter(getActivity());
        gridView.getRefreshableView().setAdapter(adapter);
        gridView.getRefreshableView().setNumColumns(2);
        gridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                if (type == 0 || type == 4) {
                    isDY = true;
                    presenter.refresh(map, true);
                } else {
                    isDY = false;
                    presenter.refresh(map, false);
                }
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {

            }
        });

        gridView.getRefreshableView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                if (null==mBean){
                    return;
                }
                Intent intent = new Intent(getActivity(),DetailsActivity.class);
                intent.putExtra(MyApi.GID,mBean.getData().getRows().get(position).getGid());
                startActivity(intent);
            }
        });


        //初始化map
        switch (type) {
            case 0:
                map.put(MyApi.DAY, "2");
                break;
            case 1:
                map.put(MyApi.HOUR, "10:00");
                break;
            case 2:
                map.put(MyApi.HOUR, "14:00");
                break;
            case 3:
                map.put(MyApi.HOUR, "20:00");
                break;
            case 4:
                map.put(MyApi.DAY, "1");
                break;
        }
    }

    @Override
    public void initList(EightGoodsBean bean) {
        mBean = bean;
        if (bean==null){
            return;
        }
        adapter.setGoodsBean(bean);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void refresh() {
        dialog.show();
    }

    @Override
    public void completeRefresh() {
        dialog.dismiss();
        gridView.onRefreshComplete();
    }

    @Override
    public void showError(String msg) {
        //
        dialog.dismiss();
        gridView.onRefreshComplete();
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisiable = true;
            lazy();
        } else {
            isVisiable = false;
        }
    }
}
