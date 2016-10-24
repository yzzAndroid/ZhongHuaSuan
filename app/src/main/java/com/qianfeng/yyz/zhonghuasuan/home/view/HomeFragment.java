package com.qianfeng.yyz.zhonghuasuan.home.view;


import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.qianfeng.yyz.zhonghuasuan.R;
import com.qianfeng.yyz.zhonghuasuan.adapter.MyHomeListAdapter;
import com.qianfeng.yyz.zhonghuasuan.adapter.MyPagerAdapter;
import com.qianfeng.yyz.zhonghuasuan.apublic.DialogUtils;
import com.qianfeng.yyz.zhonghuasuan.apublic.MyApi;
import com.qianfeng.yyz.zhonghuasuan.apublic.ViewPagerAutoPlay;
import com.qianfeng.yyz.zhonghuasuan.bean.AppIndexBean;
import com.qianfeng.yyz.zhonghuasuan.bean.AppIndexGussLikeBean;
import com.qianfeng.yyz.zhonghuasuan.bean.GeneralDataBean;
import com.qianfeng.yyz.zhonghuasuan.desinview.MyGridView;
import com.qianfeng.yyz.zhonghuasuan.detail.view.DetailsActivity;
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
    @BindView(R.id.home_top)
    ImageView imageViewTop;
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
    private static LinearLayout recommendLayout;
    //精品布局右侧textView
    private TextView recommendTv;
    private MyHomeListAdapter listAdapter;
    //home的布局
    private RelativeLayout mRelativeLayout;

    //是否还需要替换布局
    private boolean isNeedReplace = false;
    //是否是第一次滑动到item==6的位置
    private boolean ifFirstScrollTo6 = true;
    //精品推荐的dialog
    AlertDialog recommendDialog;
    //home第二个title
    @BindView(R.id.home_title_recommend)
    RelativeLayout homeRecommendTitle;

    //home第二个title文字
    @BindView(R.id.home_recommend_text)
    TextView homeRecommendtv;

    private AppIndexGussLikeBean gussLikeBean;
    private AppIndexBean appIndexBean;
    private MyPagerAdapter playerAdapter;
    private LinearLayout emptyLinearLaout;

    //日常的实例
    private GeneralDataBean mGeneralDataBean;
    private LinearLayout point;
    private ViewPager viewPager;

    public HomeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRelativeLayout = (RelativeLayout) inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, mRelativeLayout);
        listAdapter = new MyHomeListAdapter(getActivity());
        HomePresenterImpl.getInstance().enterHome(this, getActivity());
        return mRelativeLayout;
    }


    //得到日常数据
    @Override
    public void getGeneralDataBean(GeneralDataBean generalDataBean) {
        //得到了日常数据
        mGeneralDataBean = generalDataBean;
    }

    @Override
    public void getGeneralDataFailed(String msg) {
        //失败
    }

    @Override
    public void initAutoPagerPlayer(List<String> list) {
        relativeLayout = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.home_vp_layout, null);
        point = (LinearLayout) relativeLayout.findViewById(R.id.home_vp_point);
        viewPager = (ViewPager) relativeLayout.findViewById(R.id.home_vp);
        initListData(list);
        //设置适配器
        playerAdapter = new MyPagerAdapter(getActivity(), imageViewList, stringList);
        viewPager.setAdapter(playerAdapter);
        viewPager.setCurrentItem(0);
        ViewPagerAutoPlay.initPoint(point, viewPager, stringList,getActivity(), this);
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
                    if (null != imageViewHelp) {
                        //先移除导航栏
                        mPullToRefreshListView.getRefreshableView().removeHeaderView(navigationTop);
                        mPullToRefreshListView.getRefreshableView().removeHeaderView(gridView);
                        mPullToRefreshListView.getRefreshableView().removeHeaderView(navigationBootom);
                        mPullToRefreshListView.getRefreshableView().removeHeaderView(recommendLayout);
                        mPullToRefreshListView.getRefreshableView().addHeaderView(imageViewHelp);
                        //将item的个数置为0
                        if (listAdapter == null) {
                            return;
                        }
                        listAdapter.setmAppIndexBean(null);
                        listAdapter.notifyDataSetChanged();
                    }
                } else {
                    if (null != imageViewHelp) {
                        mPullToRefreshListView.getRefreshableView().removeHeaderView(imageViewHelp);
                        mPullToRefreshListView.getRefreshableView().addHeaderView(navigationTop);
                        mPullToRefreshListView.getRefreshableView().addHeaderView(gridView);
                        mPullToRefreshListView.getRefreshableView().addHeaderView(navigationBootom);
                        mPullToRefreshListView.getRefreshableView().addHeaderView(recommendLayout);
                        //数据还原
                        if (null == listAdapter) {
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
    private void initListData(List<String> list) {

        if (null==list||list.size()==0){
            return;
        }
        stringList = new ArrayList<>();
        imageViewList = new ArrayList<>();
        for (String str : list) {
            stringList.add(str);
            imageViewList.add(new ImageView(getActivity()));
        }
        //再加一张
        stringList.add(list.get(list.size() - 1));
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
        List<Map<String, Object>> list = new ArrayList<>();
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
        for (int i = 0; i < 6; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put(text_img[0], imglist[i]);
            map.put(text_img[1], textArray[i]);
            list.add(map);
        }
        gridView.setAdapter(new SimpleAdapter(getContext(), list, R.layout.home_navigation_item,
                text_img, new int[]{R.id.home_navigation_img
                , R.id.home_navigation_text}));
        //先添加导航栏
        mPullToRefreshListView.getRefreshableView().addHeaderView(navigationTop);
        mPullToRefreshListView.getRefreshableView().addHeaderView(gridView);
        mPullToRefreshListView.getRefreshableView().addHeaderView(navigationBootom);

        //跳转
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        getActivity().startActivity(new Intent(getActivity(),NewsNoticeActivity.class));
                        break;
                    case 1:
                        Intent intent1 = new Intent(getActivity(),EightGoodsActivity.class);
                        intent1.putExtra(EightGoodsActivity.TYPE,EightGoodsActivity.TYPE_TWO);
                        intent1.putExtra("bean",mGeneralDataBean);
                        getActivity().startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(getActivity(),EightGoodsActivity.class);
                        intent2.putExtra(EightGoodsActivity.TYPE,EightGoodsActivity.TYPE_THREE);
                        intent2.putExtra("bean",mGeneralDataBean);
                        getActivity().startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(getActivity(),EightGoodsActivity.class);
                        intent3.putExtra(EightGoodsActivity.TYPE,EightGoodsActivity.TYPE_FOUR);
                        intent3.putExtra("bean",mGeneralDataBean);
                        getActivity().startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(getActivity(),ClassifyActivity.class);
                        intent4.putExtra("bean",mGeneralDataBean);
                        getActivity().startActivity(intent4);
                        break;
                    case 5:
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(MyApi.Home.ZDB));
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    //初始化推荐标签
    @Override
    public void initRecommend() {
        recommendLayout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.recommend_item, null);
        recommendTv = (TextView) recommendLayout.findViewById(R.id.recommend_text);
        mPullToRefreshListView.getRefreshableView().addHeaderView(recommendLayout);
        recommendLayout.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   //dialog
                                                   if (null == recommendDialog) {

                                                       creatDialog();
                                                   } else {
                                                       recommendDialog.show();
                                                   }
                                                   //替换布局（会调用滑动事件）
                                               }
        });
    }

    //精品推荐的dialog
    private void creatDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LinearLayout dialogLayout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.recommend_dialog_item, null);
        builder.setView(dialogLayout);
        recommendDialog = builder.create();
        //监听
        LinearLayout fatherlayout = (LinearLayout) dialogLayout.findViewById(R.id.recommend_father);
        int count = fatherlayout.getChildCount();
        for (int i = 0; i < count; i++) {
            fatherlayout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null == appIndexBean) {
                        return;
                    }
                    //获取头不是图的个数
                    int position = mPullToRefreshListView.getRefreshableView().getHeaderViewsCount();
                    switch (v.getId()) {
                        case R.id.recommend_dialog_f:
                            homeRecommendtv.setText(getString(R.string.recommend_default_f));
                            break;
                        case R.id.recommend_dialog_l:
                            position += appIndexBean.getData().getFashion_goods().size();
                            homeRecommendtv.setText(getString(R.string.recommend_l));
                            break;
                        case R.id.recommend_dialog_m:
                            position += appIndexBean.getData().getFashion_goods().size()
                                    + appIndexBean.getData().getLife_goods().size();
                            homeRecommendtv.setText(getString(R.string.recommend_m));
                            break;
                        case R.id.recommend_dialog_like:
                            position += appIndexBean.getData().getFashion_goods().size()
                                    + appIndexBean.getData().getLife_goods().size()
                                    + appIndexBean.getData().getMombaby_goods().size();
                            homeRecommendtv.setText(getString(R.string.recommend_like));
                            break;
                        case R.id.recommend_dialog_cancel:
                            break;
                    }
                    mPullToRefreshListView.getRefreshableView().setSelection(position);
                    recommendDialog.dismiss();
                }
            });
        }
        //取消的暗流的监听
        TextView cancel = (TextView) dialogLayout.findViewById(R.id.recommend_dialog_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommendDialog.dismiss();
            }
        });

        recommendDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        WindowManager.LayoutParams lp = recommendDialog.getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        recommendDialog.getWindow().getDecorView().setPadding(10, 0, 10, 10);
        recommendDialog.getWindow().setAttributes(lp);
        recommendDialog.setCancelable(false);
        recommendDialog.show();

    }


    @Override
    public void initList(final AppIndexBean appIndexBean) {
        this.appIndexBean = appIndexBean;
        listAdapter.setmAppIndexBean(appIndexBean);
        mPullToRefreshListView.getRefreshableView().setAdapter(listAdapter);
        //初始化top图片
        initImageTop();
        initHomeRecommendTitle();
        mPullToRefreshListView.getRefreshableView().setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int count = mPullToRefreshListView.getRefreshableView().getHeaderViewsCount();
                //topimageview的逻辑
                if (firstVisibleItem>=count){
                    imageViewTop.setVisibility(View.VISIBLE);
                }else {
                    imageViewTop.setVisibility(View.GONE);
                }
                if (null==appIndexBean){
                    return;
                }
                //替换布局
                if (firstVisibleItem >= mPullToRefreshListView.getRefreshableView().getHeaderViewsCount()) {
                    if (ifFirstScrollTo6) {

                        titleLayout.setVisibility(View.GONE);
                        homeRecommendTitle.setVisibility(View.VISIBLE);
//                        将title布局进行替换
//                        先去掉父容器

//                        mPullToRefreshListView.getRefreshableView().removeHeaderView(recommendLayout);
//                        mRelativeLayout.removeView(titleLayout);
//                        mRelativeLayout.addView(recommendLayout);
//                        mRelativeLayout.setVisibility(View.VISIBLE);
//                        mRelativeLayout.updateViewLayout(recommendLayout,new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//                        mRelativeLayout.invalidate();

                        if (firstVisibleItem == mPullToRefreshListView.getRefreshableView().getHeaderViewsCount()){
                            mPullToRefreshListView.getRefreshableView().setSelection(
                                    mPullToRefreshListView.getRefreshableView().getHeaderViewsCount()
                            );
                        }else {
                            mPullToRefreshListView.getRefreshableView().setSelection(
                                    firstVisibleItem-1);
                        }
                        isNeedReplace = true;
                        ifFirstScrollTo6 = false;
                        //listView停止滚动
                        view.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_CANCEL, 0, 0, 0));

                    }
                } else {
                    if (isNeedReplace && firstVisibleItem< mPullToRefreshListView.getRefreshableView().getHeaderViewsCount()) {

                        titleLayout.setVisibility(View.VISIBLE);
                        homeRecommendTitle.setVisibility(View.GONE);

//                        mRelativeLayout.removeView(recommendLayout);
//                        mPullToRefreshListView.getRefreshableView().addHeaderView(recommendLayout);
//                        mRelativeLayout.addView(titleLayout);
                        //设置为可见
                        isNeedReplace = false;
                        ifFirstScrollTo6 = true;
                    }
                }
                //在滚动过程中动态设置recommend的文字
                if (firstVisibleItem== count||firstVisibleItem==count+appIndexBean.getData().getFashion_goods().size()-1){
                    homeRecommendtv.setText(getString(R.string.recommend_default_f));
                }
                if (firstVisibleItem==count+appIndexBean.getData().getFashion_goods().size()||firstVisibleItem==count+appIndexBean.getData().getFashion_goods().size()
                        +appIndexBean.getData().getLife_goods().size()-1){
                    homeRecommendtv.setText(getString(R.string.recommend_l));
                }
                if (firstVisibleItem==count+appIndexBean.getData().getFashion_goods().size()
                        +appIndexBean.getData().getLife_goods().size()||firstVisibleItem==count+appIndexBean.getData().getFashion_goods().size()
                        +appIndexBean.getData().getLife_goods().size()+appIndexBean.getData().getMombaby_goods()
                        .size()-1){
                    homeRecommendtv.setText(getString(R.string.recommend_m));
                }
                if (firstVisibleItem==count+appIndexBean.getData().getFashion_goods().size()
                        +appIndexBean.getData().getLife_goods().size()+appIndexBean.getData().getMombaby_goods()
                        .size()){
                    homeRecommendtv.setText(getString(R.string.recommend_like));

                }

            }
        });

        mPullToRefreshListView.getRefreshableView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("==========", "===========" + position);
            }
        });
        initRefresh();
    }

    //刷新
    private void initRefresh() {
        mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //刷新
                HomePresenterImpl.getInstance().refresh();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
    }

    //头（精品推荐的点击事件）
    private void initHomeRecommendTitle() {
        homeRecommendTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null==recommendDialog){
                    creatDialog();
                }else {

                    recommendDialog.show();
                }
            }
        });
    }

    private void initImageTop() {
        imageViewTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPullToRefreshListView.getRefreshableView().setSelection(1);
            }
        });
    }

    //初始化guss_like列表
    @Override
    public void initGussLikeList(AppIndexGussLikeBean appIndexGussLikeBean) {
        this.gussLikeBean = appIndexGussLikeBean;
        listAdapter.setmAppIndexGussLikeBean(appIndexGussLikeBean);
        listAdapter.notifyDataSetChanged();
        mPullToRefreshListView.getRefreshableView().setSelection(
                mPullToRefreshListView.getRefreshableView().getFirstVisiblePosition()
        );
    }

    //设置空的布局
    @Override
    public void addEmptyLayout() {
        emptyLinearLaout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.list_empty_view,null);
        emptyLinearLaout.findViewById(R.id.empty_click_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //操作
            }
        });
        ListView listView = mPullToRefreshListView.getRefreshableView();
        listView.addHeaderView(emptyLinearLaout);
    }

    @Override
    public void removeEmptyLayout() {
        mPullToRefreshListView.getRefreshableView().removeHeaderView(emptyLinearLaout);
    }

    @Override
    public void showDialog() {
        mAlertDialog = DialogUtils.beginLoading(getActivity());
        mAlertDialog.show();
    }

    //刷新的动画
    @Override
    public void dimissDialog() {
        mAlertDialog.dismiss();
    }

    @Override
    public void notifyPlayer(List<String> list) {
        initListData(list);
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getActivity(),imageViewList,stringList);
        viewPager.setCurrentItem(0);
        viewPager.removeAllViews();
        viewPager.setAdapter(pagerAdapter);
        Log.e("=======","=============="+viewPager.getChildCount()+imageViewList.size());
        ViewPagerAutoPlay.refresh(point,stringList,getActivity());
        //开始播

    }

    @Override
    public void notifiAppIndexList(AppIndexBean appIndexBean) {
        listAdapter.setmAppIndexBean(appIndexBean);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void completeRefresh() {
        mPullToRefreshListView.onRefreshComplete();
    }

}


