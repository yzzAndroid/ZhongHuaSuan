package com.qianfeng.yyz.zhonghuasuan.home.view;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.qianfeng.yyz.zhonghuasuan.R;
import com.qianfeng.yyz.zhonghuasuan.adapter.MyTomorrowAdapter;
import com.qianfeng.yyz.zhonghuasuan.apublic.MyApi;
import com.qianfeng.yyz.zhonghuasuan.bean.NewsNoticeTomorrowBean;
import com.qianfeng.yyz.zhonghuasuan.desinview.MyRefrechView2;
import com.qianfeng.yyz.zhonghuasuan.detail.view.DetailsActivity;
import com.qianfeng.yyz.zhonghuasuan.home.presenter.NewsTodayPresenter;
import com.qianfeng.yyz.zhonghuasuan.home.presenter.NewsTomorrowPresenter;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsTomorrowFragment extends Fragment implements INewsNoticeTomorrowFView {


    private boolean isFirst = true;
    private boolean isVisiable;
    private NewsNoticeTomorrowBean tomorrowBean;
    @BindView(R.id.tomorrow_time)
    TextView timeTV;

    //倒计时的效果
    @BindView(R.id.tomorrow_count_down)
    TextView countDownTV;

    @BindView(R.id.tomorrow_lv)
    ListView listView;
    @BindView(R.id.tomorrow_refresh)
    MyRefrechView2 mSwipeRefreshLayout;

    private Handler mHandler;

    private MyTomorrowAdapter tomorrowAdapter;
    private SimpleDateFormat format1;
    private SimpleDateFormat format2;
    private static final int WHAT = 1;

    private long LT;
    private String H = "00";
    private String M = "00";
    private String S = "00";
    private String now;
    public static final int TIME = 8;

    public NewsTomorrowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_tomorrow, container, false);
        ButterKnife.bind(this,view);
        initFiled();
        lazy();
        //设置监听
        initListener();
        return view;
    }

    private void initListener() {
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                NewsTomorrowPresenter.getInstance().refresh();
            }
        });
    }

    private void initFiled() {
        format1 = new SimpleDateFormat("HH:mm:ss");
        format2 = new SimpleDateFormat("HH");
        isFirst = true;
        tomorrowAdapter = new MyTomorrowAdapter(getActivity());
        //设置适配器
        listView.setAdapter(tomorrowAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    if (null==tomorrowBean){
                        return;
                    }
                    Intent intent = new Intent(getActivity(), DetailsActivity.class);
                    intent.putExtra(MyApi.GID,tomorrowBean.getData().getRows().get(position).getGid());
                    getActivity().startActivity(intent);
                }

        });
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (null!=msg){
                    if (msg.what==1){
                        //移除所有的消息
                        mHandler.removeMessages(1);
                        if (LT==0){
                            initTime();
                        }
                        countDownTV.setText(now);
                        LT -= 1;
                        //倒计时
                        creatTime();
                        mHandler.sendEmptyMessageDelayed(WHAT,1000);
                    }
                }
            }
        };
        //倒计时
        initTime();
    }

    private void lazy() {
        if (null==tomorrowBean){
                NewsTomorrowPresenter.getInstance().enterNewsNoticeActivity(this);
        }
    }

    @Override
    public void completeRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    //刷新的动画
    @Override
    public void refresh() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void initList(NewsNoticeTomorrowBean tomorrowBean) {
        if (null==tomorrowBean){
            return;
        }
        this.tomorrowBean = tomorrowBean;
        tomorrowAdapter.setTodayBean(tomorrowBean);
        tomorrowAdapter.notifyDataSetChanged();

    }

    private void initTime() {
        //倒计时
        long current = System.currentTimeMillis();

        String time1 = format1.format(current);
        //多一个小时
        timeTV.setText(getString(R.string.tomorrow_time)+"0"+TIME+":"+"00");
        //=====================
        String t[] = time1.split(":");
        int h = Integer.valueOf(t[0]);
        int m = Integer.valueOf(t[1]);
        int s = Integer.valueOf(t[2]);
        //明天的时间
        long numT = (TIME+24)*3600;
        //还剩余的时间
        LT = numT-h*3600+m*60+s;
        //开始倒计时
        mHandler.sendEmptyMessage(WHAT);
    }

    //生成倒计时
    public void creatTime(){
        if ((LT/3600)<10){
            H = "0"+String.valueOf(LT/3600);
        }else {
            H = String.valueOf(LT/3600);
        }
        //剩下的分钟+秒
        long LmF = LT%3600;
        long mm = LmF/60;
        if (mm<10){
            M = "0"+String.valueOf(mm);
        }else {
            M = String.valueOf(mm);
        }
        long ss = LmF%60;
        if (ss<10){
            S = "0"+String.valueOf(ss);
        }else {
            S = String.valueOf(ss);
        }
        now = H+":"+M+":"+S;
        //=====================
    }

    @Override
    public void showError() {

    }

    @Override
    public void dimissError() {

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()){
            isVisiable = true;
            if (!isFirst){
                lazy();
            }
        }else {
            isVisiable = false;
        }
    }
}
