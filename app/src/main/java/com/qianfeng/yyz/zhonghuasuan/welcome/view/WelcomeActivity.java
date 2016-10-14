package com.qianfeng.yyz.zhonghuasuan.welcome.view;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qianfeng.yyz.zhonghuasuan.R;
import com.qianfeng.yyz.zhonghuasuan.welcome.presenter.StartPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity implements IwelcomeView{

    @BindView(R.id.welcome_vp)
    ViewPager mViewPager;
    @BindView(R.id.welcome_container)
    LinearLayout mContainer;
    @BindView(R.id.welcome_enter_btn)
    Button welcomeEnterBtn;
    private List<View> mViewList;
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int FOUR = 4;
    public static final int FIVE = 5;
    private int currentPositon = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resetWindowSize();
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        //初始化
        initViewPager();

    }

    @Override
    public void startUse() {
        //进入主页
    }

    @Override
    public void resetWindowSize() {
        //no title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //fullScreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void initViewPager() {
        mViewList = new ArrayList<>();
        initList();
        ViewPagerAdapter adapter = new ViewPagerAdapter();
        mViewPager.setAdapter(adapter);
        initPoint();
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //控制btn
                if (position==mViewList.size()-1){
                    welcomeEnterBtn.setVisibility(View.VISIBLE);
                }else {
                    welcomeEnterBtn.setVisibility(View.GONE);
                }
                changePoint(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 改变指示器的背景
     */
    @Override
    public void changePoint(int position) {
        ImageView imageViewNow = (ImageView) mContainer.getChildAt(position);
        imageViewNow.setImageResource(R.drawable.point_shape_select);

            if (currentPositon<position){
                if (position==0){
                    return;
                }
                ImageView imageViewLast = (ImageView) mContainer.getChildAt(position-1);
                imageViewLast.setImageResource(R.drawable.point_shape_unselect);
            }else {
                ImageView imageViewLast = (ImageView) mContainer.getChildAt(position+1);
                imageViewLast.setImageResource(R.drawable.point_shape_unselect);
            }
           currentPositon = position;

    }
    @Override
    public void initPoint() {
        for (int i =0;i<mViewList.size();i++){
            ImageView imageView = new ImageView(this);
            imageView.setPadding(5,0,5,0);
            mContainer.addView(imageView);
            if (i==0){
                imageView.setImageResource(R.drawable.point_shape_select);
            }else {
                imageView.setImageResource(R.drawable.point_shape_unselect);
            }
        }
    }

    @Override
    public ImageView initImageView(int resId){
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(resId);
        return imageView;
    }

    @Override
    public void initList() {
        for (int i=1;i<7;i++){
            switch (i){
                case ONE:
                    mViewList.add(initImageView(R.mipmap.guild_2));
                    break;
                case TWO:
                    mViewList.add(initImageView(R.mipmap.guild_3));
                    break;
                case THREE:
                    mViewList.add(initImageView(R.mipmap.guild_4));
                    break;
                case FOUR:
                    mViewList.add(initImageView(R.mipmap.guild_5));
                    break;
                case FIVE:
                    mViewList.add(initImageView(R.mipmap.guild_6));
                    break;
            }
        }
    }

    //点击进入按钮，第一次进入程序
    public void startUse(View view) {
        StartPresenterImpl.getInstance().enterHome(this);
    }

    /**
     * 适配器
     */
    class ViewPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViewList.get(position));
            return mViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
           container.removeView(mViewList.get(position));
        }
    }
}
