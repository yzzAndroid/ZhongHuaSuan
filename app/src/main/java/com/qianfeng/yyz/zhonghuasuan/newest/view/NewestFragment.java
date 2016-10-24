package com.qianfeng.yyz.zhonghuasuan.newest.view;


import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.qianfeng.yyz.zhonghuasuan.R;
import com.qianfeng.yyz.zhonghuasuan.adapter.MyFragmentAdapter;
import com.qianfeng.yyz.zhonghuasuan.adapter.MyGeneralNumAdapter;
import com.qianfeng.yyz.zhonghuasuan.apublic.MyApi;
import com.qianfeng.yyz.zhonghuasuan.bean.GeneralDataBean;
import com.qianfeng.yyz.zhonghuasuan.home.presenter.EightGoodsPresentImpl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewestFragment extends Fragment {

    @BindView(R.id.newest_tab)
    TabLayout tableLayout;

    @BindView(R.id.newest_vp)
    ViewPager viewPager;



    @BindView(R.id.eight_sort_newest)
    TextView newestTv;
    @BindView(R.id.eight_sort_hotest)
    TextView hotestTv;
    @BindView(R.id.eight_sort_count)
    TextView countTv;
    @BindView(R.id.eight_sort_default)
    TextView defaultTv;
    @BindView(R.id.sort_price_tv)
    TextView priceTv;
    @BindView(R.id.sort_price_img)
    ImageView priceImg;
    @BindView(R.id.sort_layout)
    LinearLayout sortLayout;
    @BindView(R.id.sort_count_layout)
    LinearLayout sortCountLayout;
    @BindView(R.id.sort_count_img)
    ImageView sortCountImg;
    @BindView(R.id.eight_sort_priceLayout)
    LinearLayout sortPriceLayout;
    @BindView(R.id.sort_count_grid_view)
    GridView gridView;
    public static final String TODAY  = "今日上新";
    public static final String TEN = "10:00";
    public static final String TWo = "14:00";
    public static final String EIGHT = "20:00";
    public static final String YESTERDAY = "昨日上新";
    private List<String> title1;
    private List<NewestSecondFragment> fragmentList;
    private List<LinearLayout> linearLayouts;
    private View mNowView;
    private String nowTime;
    private List<TextView> list1;
    private List<TextView>list2;
    Handler handler ;

    private int lastPosition = 0;

    private NewestSecondFragment child;
    private GeneralDataBean mGeneralDataBean;

    public NewestFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newest, container, false);
        ButterKnife.bind(this,view);
        initFiled();
        controalSwitch();
        return view;
    }

    private void initFiled() {
        mNowView = defaultTv;
        //初始化list
        title1 = new ArrayList<>();
        title1.add(TODAY);
        title1.add(TEN);
        title1.add(TWo);
        title1.add(EIGHT);
        title1.add(YESTERDAY);
        //初始化FM
        fragmentList = new ArrayList<>();
        int size = title1.size();
        for (int i = 0; i < size; i++) {
            NewestSecondFragment fragment = new NewestSecondFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("type",i);
            fragment.setArguments(bundle);
            fragmentList.add(fragment);
        }
        //初始化child
        child = fragmentList.get(0);

        linearLayouts = new ArrayList<>();
        viewPager.setAdapter(new MyFragmentAdapter(getChildFragmentManager(), fragmentList));
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(5);
        //时间
       Long time =  System.currentTimeMillis();

        SimpleDateFormat format = new SimpleDateFormat("HH");
        nowTime = format.format(time);

        tableLayout.setupWithViewPager(viewPager);

        tableLayout.setSelectedTabIndicatorHeight(0);

        list1 = new ArrayList<>();
        list2 = new ArrayList<>();

        for (int i = 0;i<tableLayout.getTabCount();i++){
            LinearLayout linearLayout  = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.tab_item,null);
            TextView tv1  = (TextView) linearLayout.getChildAt(0);
            tv1.setText(title1.get(i));
            TextView tv2 = (TextView) linearLayout.getChildAt(1);
            list1.add(tv1);
            list2.add(tv2);
            if (i==0||i==4){
                tv2.setVisibility(View.GONE);
            }
            tableLayout.getTabAt(i).setCustomView(linearLayout);
            linearLayouts.add(linearLayout);
        }

        int t = Integer.valueOf(nowTime);

        if (t<10){
            viewPager.setCurrentItem(0);
            lastPosition = 0;
            for (int i =1;i<list2.size()-1;i++){
                list2.get(i).setText("未开枪");
            }
            linearLayouts.get(0).setBackgroundColor(getResources().getColor(R.color.point_select));
            list1.get(0).setTextColor(getResources().getColor(R.color.white));
            list2.get(0).setTextColor(getResources().getColor(R.color.white));
        }else if (10<=t&&t<14){
            for (int i =1;i<list2.size()-1;i++){
                if (i==1){
                    list2.get(i).setText("已开抢");
                }else {
                    list2.get(i).setText("未开枪");
                }
            }
            viewPager.setCurrentItem(1);
            lastPosition = 1;
            linearLayouts.get(1).setBackgroundColor(getResources().getColor(R.color.point_select));
            list1.get(1).setTextColor(getResources().getColor(R.color.white));
            list2.get(1).setTextColor(getResources().getColor(R.color.white));
        }else if (14<=t&&t<20){
            for (int i =1;i<list2.size()-1;i++){
                if (i==3){
                    list2.get(i).setText("未开抢");
                }else {
                    list2.get(i).setText("已开枪");
                }
            }
            viewPager.setCurrentItem(2);
            lastPosition = 2;
            linearLayouts.get(2).setBackgroundColor(getResources().getColor(R.color.point_select));
            list1.get(2).setTextColor(getResources().getColor(R.color.white));
            list2.get(2).setTextColor(getResources().getColor(R.color.white));
        }else if (t>=20){
            for (int i =1;i<list2.size()-1;i++){
                list2.get(i).setText("已开枪");
            }
            viewPager.setCurrentItem(3);
            lastPosition = 3;
            linearLayouts.get(3).setBackgroundColor(getResources().getColor(R.color.point_select));
            list1.get(3).setTextColor(getResources().getColor(R.color.white));
            list2.get(3).setTextColor(getResources().getColor(R.color.white));
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                linearLayouts.get(lastPosition).setBackgroundColor(getResources().getColor(R.color.newest_tab_bg));
                list1.get(lastPosition).setTextColor(getResources().getColor(R.color.dark));
                list2.get(lastPosition).setTextColor(getResources().getColor(R.color.dark));

                linearLayouts.get(position).setBackgroundColor(getResources().getColor(R.color.point_select));
                list1.get(position).setTextColor(getResources().getColor(R.color.white));
                list2.get(position).setTextColor(getResources().getColor(R.color.white));
                lastPosition = position;
                //网络请求
                child = fragmentList.get(position);
                Log.e("==p=","==p="+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //读取本地
        handler = new Handler();
        readGeneral();
    }


    //查询的切换
    private void controalSwitch() {
        int count = sortLayout.getChildCount();
        for (int i = 0;i<count;i++){
            sortLayout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mNowView instanceof TextView){
                        ((TextView)mNowView).setTextColor(getResources().getColor(R.color.alpha_dark));
                    }else if (mNowView==sortPriceLayout){
                        priceTv.setTextColor(getResources().getColor(R.color.alpha_dark));
                        priceImg.setImageResource(R.mipmap.search_price);
                        priceImg.setTag(0);
                    }else if (mNowView==sortCountLayout){
                        countTv.setTextColor(getResources().getColor(R.color.alpha_dark));
                        sortCountImg.setVisibility(View.GONE);
                        //还有一个布局
                        gridView.setVisibility(View.GONE);
                    }
                    switch (v.getId()){
                        case R.id.eight_sort_default:
                            child.getMap().put(MyApi.SORT,"default");
                            defaultTv.setTextColor(getResources().getColor(R.color.point_select));
                            mNowView = defaultTv;
                            child.doFresh();
                            break;
                        case R.id.eight_sort_newest:
                            child.getMap().put(MyApi.SORT,"time-desc");
                            newestTv.setTextColor(getResources().getColor(R.color.point_select));
                            mNowView = newestTv;
                            child.doFresh();
                            break;
                        case R.id.eight_sort_hotest:
                            child.getMap().put(MyApi.SORT,"hot-desc");
                            hotestTv.setTextColor(getResources().getColor(R.color.point_select));
                            mNowView = hotestTv;
                            child.doFresh();
                            break;
                        case R.id.eight_sort_priceLayout:
                            //再次点击会有效果
                            if (mNowView instanceof LinearLayout){
                                //第二次点击
                                if ((int)priceImg.getTag()==1){
                                    priceImg.setImageResource(R.mipmap.search_price_up);
                                    child.getMap().put(MyApi.SORT,"price-asc");
                                    priceImg.setTag(2);
                                }else if ((int)priceImg.getTag()==2){
                                    priceImg.setImageResource(R.mipmap.search_price_down);
                                    child.getMap().put(MyApi.SORT,"price-desc");
                                    priceImg.setTag(1);
                                }
                                priceTv.setTextColor(getResources().getColor(R.color.point_select));


                            }else {
                                //第一次点击
                                priceTv.setTextColor(getResources().getColor(R.color.point_select));
                                priceImg.setImageResource(R.mipmap.search_price_down);
                                priceImg.setTag(1);
                                child.getMap().put(MyApi.SORT,"price-desc");
                            }
                            mNowView = sortPriceLayout;
                            child.doFresh();
                            break;
                        case R.id.sort_count_layout:
                            countTv.setTextColor(getResources().getColor(R.color.point_select));
                            sortCountImg.setVisibility(View.VISIBLE);
                            gridView.setVisibility(View.VISIBLE);
                            mNowView = sortCountLayout;
                            break;
                    }

                }
            });
        }
    }


    private void readGeneral() {
        String path = "/storage/emulated/0/zhs/general";
        StringBuilder sb = new StringBuilder();
        String temp = null;
        try {
            BufferedReader read = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            while ((temp=read.readLine())!=null){
                sb.append(temp);
            }
            Gson gosn = new Gson();
            mGeneralDataBean = gosn.fromJson(sb.toString(),GeneralDataBean.class);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    initgridView();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initgridView() {
        if (null==mGeneralDataBean){
            return;
        }
        final MyGeneralNumAdapter generalNumAdapter = new MyGeneralNumAdapter(getActivity(),mGeneralDataBean);
        gridView.setAdapter(generalNumAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                generalNumAdapter.setMcurrentselectposition(position);
                generalNumAdapter.notifyDataSetChanged();
                //网络请求
                int iPosition = 0;
                if (position!=0){
                    iPosition = position-1;
                }
                child.getMap().put(MyApi.CATEGORY_ID,String.valueOf(iPosition));
                child.doFresh();
            }
        });
    }
}
