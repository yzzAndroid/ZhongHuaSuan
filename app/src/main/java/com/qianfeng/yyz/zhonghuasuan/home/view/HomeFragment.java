package com.qianfeng.yyz.zhonghuasuan.home.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshListView2;
import com.qianfeng.yyz.zhonghuasuan.R;
import com.qianfeng.yyz.zhonghuasuan.home.model.HomeModelImpl;
import com.qianfeng.yyz.zhonghuasuan.home.model.HttpServiceAppIndex;
import com.qianfeng.yyz.zhonghuasuan.home.presenter.HomePresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements IHomeFragment{


    @BindView(R.id.home_pullListView)
    PullToRefreshListView2 mPullToRefreshListView;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        HomePresenterImpl.getInstance().enterHome(this);
        ButterKnife.bind(this,view);
//        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
//        mPullToRefreshListView.getLoadingLayoutProxy().setLoadingDrawable(getResources().getDrawable(R.mipmap.index_category));
        return view;
    }
}
