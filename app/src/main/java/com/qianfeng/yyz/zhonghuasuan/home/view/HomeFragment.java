package com.qianfeng.yyz.zhonghuasuan.home.view;


import android.graphics.Paint;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.qianfeng.yyz.zhonghuasuan.R;
import com.qianfeng.yyz.zhonghuasuan.adapter.MyHomeListAdapter;
import com.qianfeng.yyz.zhonghuasuan.adapter.MyPagerAdapter;
import com.qianfeng.yyz.zhonghuasuan.apublic.DialogUtils;
import com.qianfeng.yyz.zhonghuasuan.apublic.ViewPagerAutoPlay;
import com.qianfeng.yyz.zhonghuasuan.bean.AppIndexBean;
import com.qianfeng.yyz.zhonghuasuan.desinview.MyGridView;
import com.qianfeng.yyz.zhonghuasuan.home.presenter.HomePresenterImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements IHomeFragment {


    @BindView(R.id.home_pullListView)
    PullToRefreshListView mPullToRefreshListView;
    @BindView(R.id.home_default_title)
    RelativeLayout titleLayout;
    private List<String> stringList;
    private List<ImageView> imageViewList;
    private AlertDialog mAlertDialog;
    private int mCurrentAngle = 0;
    //轮播加help的布局
    private RelativeLayout relativeLayout;
    //导航栏的gridView
    private MyGridView gridView;
    //导航栏上下两侧的阴影布局
    private LinearLayout navigationTop;
    private LinearLayout navigationBootom;
    //精品推荐的布局
    private LinearLayout recommendLayout;
    private MyHomeListAdapter listAdapter;
    private AppIndexBean appIndexBean;
    //是fragment的布局
    private FrameLayout mFrameLayout;

    //是否还需要替换布局
    private boolean isNeedReplace = false;
    //是否是第一次滑动到item==6的位置
    private boolean ifFirstScrollTo6 = true;
    //精品推荐的dialog
    AlertDialog recommendDialog;

    public HomeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFrameLayout = (FrameLayout) inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, mFrameLayout);
        HomePresenterImpl.getInstance().enterHome(this, getActivity());
        return mFrameLayout;
    }

    @Override
    public void initAutoPagerPlayer(List<AppIndexBean.DataBean.BannerBean> list) {
        relativeLayout = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.home_vp_layout, null);
        LinearLayout point = (LinearLayout) relativeLayout.findViewById(R.id.home_vp_point);
        ViewPager viewPager = (ViewPager) relativeLayout.findViewById(R.id.home_vp);
        initListData(list);
        //设置适配器
        viewPager.setAdapter(new MyPagerAdapter(getActivity(),imageViewList,stringList));
        viewPager.setCurrentItem(0);
        ViewPagerAutoPlay.initPoint(point, viewPager, stringList, getActivity(), this);
        //开始播
        ViewPagerAutoPlay.startPlay();
        mPullToRefreshListView.getRefreshableView().addHeaderView(relativeLayout);

        //要始化listView才会有头显示
        mPullToRefreshListView.getRefreshableView().setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1));
    }


    /**
     * 操作help块
     *
     * @param view
     */
    private void controlHelp(View view) {
        final ImageView imageview = (ImageView) view.findViewById(R.id.help_img);
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.help_layout);

        final ImageView imageViewHelp = new ImageView(getContext());
        imageViewHelp.setBackgroundResource(R.mipmap.purchase_process);
        imageViewHelp.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        //动态的添加和移除
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RotateAnimation animation = inithelpRotate();
                animation.setDuration(300);
                animation.setFillAfter(true);
                imageview.startAnimation(animation);


                if (mCurrentAngle == 180) {
                    if (null!=imageViewHelp){
                        //先移除导航栏
                        mPullToRefreshListView.getRefreshableView().removeHeaderView(navigationTop);
                        mPullToRefreshListView.getRefreshableView().removeHeaderView(gridView);
                        mPullToRefreshListView.getRefreshableView().removeHeaderView(navigationBootom);
                        mPullToRefreshListView.getRefreshableView().removeHeaderView(recommendLayout);
                        mPullToRefreshListView.getRefreshableView().addHeaderView(imageViewHelp);
                        //将item的个数置为0
                        if (listAdapter==null){
                            return;
                        }
                        listAdapter.setmAppIndexBean(null);
                        listAdapter.notifyDataSetChanged();
                    }
                } else {
                    if (null!=imageViewHelp){
                        mPullToRefreshListView.getRefreshableView().removeHeaderView(imageViewHelp);
                        mPullToRefreshListView.getRefreshableView().addHeaderView(navigationTop);
                        mPullToRefreshListView.getRefreshableView().addHeaderView(gridView);
                        mPullToRefreshListView.getRefreshableView().addHeaderView(navigationBootom);
                        mPullToRefreshListView.getRefreshableView().addHeaderView(recommendLayout);
                        //数据还原
                        if (null==listAdapter){
                            return;
                        }
                        listAdapter.setmAppIndexBean(appIndexBean);
                        listAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

    }

    //旋转的动画
    private RotateAnimation inithelpRotate() {
        RotateAnimation animation;
        if (mCurrentAngle == 0) {
            animation = new RotateAnimation(mCurrentAngle, 180, Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            mCurrentAngle = 180;

        } else {
            animation = new RotateAnimation(mCurrentAngle, 0, Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            mCurrentAngle = 0;
        }
        return animation;
    }

    //初始化轮播的数据
    private void initListData(List<AppIndexBean.DataBean.BannerBean> list) {
        stringList = new ArrayList<>();
        imageViewList = new ArrayList<>();
        for (AppIndexBean.DataBean.BannerBean bannerBean : list) {
            stringList.add(bannerBean.getImg());
            imageViewList.add(new ImageView(getActivity()));
        }
        //再加一张
        stringList.add(list.get(list.size() - 1).getImg());
        imageViewList.add(new ImageView(getActivity()));
    }

    //添加帮助栏
    @Override
    public void initHelp() {
        //操作help(添加到头中)
        controlHelp(relativeLayout);
    }

     //初始化导航栏
    @Override
    public void initNavigation() {
        //初始化
        navigationTop = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.navigation_top_bootom_item,
                null);
        navigationBootom = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.navigation_top_bootom_item,
                null);
        gridView = new MyGridView(getActivity());
        gridView.setNumColumns(3);
        List<Map<String,Object>> list = new ArrayList<>();
        //初始化图片数组
        int imglist[] = new int[]{
                R.mipmap.index_new_forecast,
                R.mipmap.index_hall,
                R.mipmap.index_famous,
                R.mipmap.index_list,
                R.mipmap.index_category,
                R.mipmap.index_indiana
        };
        String textArray[] = getResources().getStringArray(R.array.navigation_text_array);
        String text_img[] = getResources().getStringArray(R.array.text_img);
        for (int i = 0;i<6;i++){
            Map<String,Object> map = new HashMap<>();
            map.put(text_img[0],imglist[i]);
            map.put(text_img[1],textArray[i]);
            list.add(map);
        }
        gridView.setAdapter(new SimpleAdapter(getContext(),list,R.layout.home_navigation_item,
                text_img,new int[]{R.id.home_navigation_img
                ,R.id.home_navigation_text}));
        //先添加导航栏
        mPullToRefreshListView.getRefreshableView().addHeaderView(navigationTop);
        mPullToRefreshListView.getRefreshableView().addHeaderView(gridView);
        mPullToRefreshListView.getRefreshableView().addHeaderView(navigationBootom);
    }

    //初始化推荐标签
    @Override
    public void initRecommend() {
        recommendLayout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.recommend_item,null);
        mPullToRefreshListView.getRefreshableView().addHeaderView(recommendLayout);
        recommendLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dialog
                if (null==recommendDialog){

                    creatDialog();
                }else {
                    recommendDialog.show();
                }

                   //设置listView的sellection
                    //将title布局进行替换
                   //先去掉父容器
                    mPullToRefreshListView.getRefreshableView().removeHeaderView(recommendLayout);
                    mFrameLayout.removeView(titleLayout);
                    mFrameLayout.addView(recommendLayout);
            }
        });
    }

    //精品推荐的dialog
    private void creatDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LinearLayout dialogLayout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.recommend_dialog_item,null);
        builder.setView(dialogLayout);
        recommendDialog = builder.create();
        //监听
        LinearLayout fatherlayout = (LinearLayout) dialogLayout.findViewById(R.id.recommend_father);
        int count = fatherlayout.getChildCount();
        for (int i =0;i<count;i++){
            fatherlayout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null==appIndexBean){
                        return;
                    }
                    //获取头不是图的个数
                    int position = mPullToRefreshListView.getRefreshableView().getHeaderViewsCount();
                    switch (v.getId()){
                        case R.id.recommend_dialog_f:
                            break;
                        case R.id.recommend_dialog_l:
                            position += appIndexBean.getData().getFashion_goods().size();
                            break;
                        case R.id.recommend_dialog_m:
                            position += appIndexBean.getData().getFashion_goods().size()
                                    +appIndexBean.getData().getLife_goods().size();
                            break;
                        case R.id.recommend_dialog_like:
                            position += appIndexBean.getData().getFashion_goods().size()
                                    +appIndexBean.getData().getLife_goods().size()
                                    +appIndexBean.getData().getMombaby_goods().size();
                            break;
                    }
                    mPullToRefreshListView.getRefreshableView().setSelection(position);
                    recommendDialog.dismiss();
                }
            });
        }
        recommendDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        WindowManager.LayoutParams lp = recommendDialog.getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        recommendDialog.getWindow().getDecorView().setPadding(10,0,10,10);
        recommendDialog.getWindow().setAttributes(lp);
        recommendDialog.setCancelable(false);
        recommendDialog.show();

    }

    @Override
    public void initList(AppIndexBean appIndexBean) {
        this.appIndexBean = appIndexBean;
        listAdapter = new MyHomeListAdapter(appIndexBean,getActivity());
        mPullToRefreshListView.getRefreshableView().setAdapter(listAdapter);

        mPullToRefreshListView.getRefreshableView().setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem==mPullToRefreshListView.getRefreshableView().getHeaderViewsCount()){
                    if (ifFirstScrollTo6){

                        //将title布局进行替换
                        //先去掉父容器
                        mPullToRefreshListView.getRefreshableView().removeHeaderView(recommendLayout);
                        mFrameLayout.addView(recommendLayout);
                        mFrameLayout.removeView(titleLayout);

                        isNeedReplace = true;
                        ifFirstScrollTo6 = false;
                        //listView停止滚动
                        view.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_CANCEL, 0, 0, 0));
                    }
                }else {
                    if (isNeedReplace&&firstVisibleItem<mPullToRefreshListView.getRefreshableView().getHeaderViewsCount()){

                        mFrameLayout.removeView(recommendLayout);
                        mPullToRefreshListView.getRefreshableView().addHeaderView(recommendLayout);
                        mFrameLayout.addView(titleLayout);
                        isNeedReplace = false;
                        ifFirstScrollTo6 = true;
                    }
                }
            }
        });

        mPullToRefreshListView.getRefreshableView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("==========","==========="+position);
            }
        });
    }

    @Override
    public void showDialog() {
        mAlertDialog = DialogUtils.beginLoading(getActivity());
        mAlertDialog.show();
    }

    @Override
    public void dimissDialog() {
        mAlertDialog.dismiss();
    }




}
