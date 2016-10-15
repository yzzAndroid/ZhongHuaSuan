package com.qianfeng.yyz.zhonghuasuan.home.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.qianfeng.yyz.zhonghuasuan.R;
import com.qianfeng.yyz.zhonghuasuan.mine.view.MineFragment;
import com.qianfeng.yyz.zhonghuasuan.newest.view.NewestFragment;
import com.qianfeng.yyz.zhonghuasuan.search.view.SearchFragmentFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements IHomeActivity{
    @BindView(R.id.home_container)
    RelativeLayout mHomeContainer;
    @BindView(R.id.home_bottom)
    RadioGroup mRadioGroup;
    @BindView(R.id.home_rb)
    RadioButton home_rb;
    @BindView(R.id.newest_rb)
    RadioButton newest_rb;
    @BindView(R.id.search_rb)
    RadioButton search_rb;
    @BindView(R.id.mine_rb)
    RadioButton mine_rb;

    private RadioButton mCurrentRadioButton;
    private Fragment mCurrentFragment;
    private Fragment mHomeFragment;
    private Fragment mNewestFragment;
    private Fragment mSearchFragment;
    private Fragment mMineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initContainer();
    }

    @Override
    public void initContainer() {
        mHomeFragment = new HomeFragment();
        mNewestFragment = new NewestFragment();
        mSearchFragment = new SearchFragmentFragment();
        mMineFragment = new MineFragment();
        //初始化
        mCurrentRadioButton = home_rb;
        mCurrentFragment = mHomeFragment;
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.home_rb:
                        changeColor(home_rb);
                        changeFragment(mHomeFragment);
                        break;
                    case R.id.newest_rb:
                        changeColor(newest_rb);
                        changeFragment(mNewestFragment);
                        break;
                    case R.id.search_rb:
                        changeColor(search_rb);
                        changeFragment(mSearchFragment);
                        break;
                    case R.id.mine_rb:
                        changeColor(mine_rb);
                        changeFragment(mMineFragment);
                        break;
                }
            }
        });
    }
    //控制颜色
    public void changeColor(RadioButton now){
        now.setTextColor(getResources().getColor(R.color.point_select));
        mCurrentRadioButton.setTextColor(getResources().getColor(R.color.default_color));
        mCurrentRadioButton = now;
    }

    //控制碎片切换
    public void changeFragment(Fragment now){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!now.isAdded()){
            transaction.add(R.id.home_container,now);
        }
        transaction.hide(mCurrentFragment);
        transaction.show(now);
        transaction.commit();
        mCurrentFragment = now;
    }
}
