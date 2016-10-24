package com.qianfeng.yyz.zhonghuasuan.home.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qianfeng.yyz.zhonghuasuan.R;
import com.qianfeng.yyz.zhonghuasuan.apublic.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsNoticeActivity extends BaseActivity implements INewsNoticeView {
    @BindView(R.id.newsNotice_today)
    TextView left;
    @BindView(R.id.newsNotice_tomorrow)
    TextView right;
    @BindView(R.id.newsNotice_container)
    ViewPager viewPager;
    private NewsNoticeTodayFragment todayFragment;
    private NewsTomorrowFragment tomorrowFragment;
    private List<Fragment> listF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_notice);
        ButterKnife.bind(this);
        initLR();
        initControal();
        initViewPager();
    }

    private void initViewPager() {
        todayFragment = new NewsNoticeTodayFragment();
        tomorrowFragment = new NewsTomorrowFragment();
        listF = new ArrayList<>();
        listF.add(todayFragment);
        listF.add(tomorrowFragment);
        viewPager.setCurrentItem(0);
        viewPager.setAdapter(new FPagerAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==0){
                    left.setBackgroundResource(R.drawable.news_noyice_left_select_shap);
                    right.setBackgroundResource(R.drawable.news_noyice_right_unselect_shap);
                    left.setTextColor(R.color.white);
                    right.setTextColor(R.color.point_select);
                    left.setTag(true);
                    right.setTag(false);
                }
                if (position==1){
                    left.setBackgroundResource(R.drawable.news_noyice_left_unselect_shap);
                    right.setBackgroundResource(R.drawable.news_noyice_right_select_shap);
                    left.setTag(false);
                    right.setTag(true);
                    left.setTextColor(R.color.point_select);
                    right.setTextColor(R.color.white);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initLR() {
        left.setTag(true);
        right.setTag(false);
    }


    @Override
    public void initControal() {

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean leftSelect = (boolean) left.getTag();
                if (!leftSelect){
                    left.setBackgroundResource(R.drawable.news_noyice_left_select_shap);
                    right.setBackgroundResource(R.drawable.news_noyice_right_unselect_shap);
                    left.setTag(true);
                    right.setTag(false);
                    viewPager.setCurrentItem(0);
                }
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean rightSelect = (boolean) right.getTag();
                if (!rightSelect){
                    left.setBackgroundResource(R.drawable.news_noyice_left_unselect_shap);
                    right.setBackgroundResource(R.drawable.news_noyice_right_select_shap);
                    left.setTag(false);
                    right.setTag(true);
                    viewPager.setCurrentItem(1);
                }
            }
        });
    }

    //返回首界面
    public void back(View view) {
        finish();
    }

    class FPagerAdapter extends FragmentPagerAdapter{

        public FPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return listF.get(position);
        }

        @Override
        public int getCount() {
            return listF.size();
        }
    }
}
