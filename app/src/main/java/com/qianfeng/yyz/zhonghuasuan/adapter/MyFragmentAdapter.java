package com.qianfeng.yyz.zhonghuasuan.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.qianfeng.yyz.zhonghuasuan.newest.view.NewestSecondFragment;

import java.util.List;

/**
 * Created by Administrator on 2016/10/20 0020.
 */
public class MyFragmentAdapter extends FragmentStatePagerAdapter {

    List<NewestSecondFragment> fragmentList;

    public MyFragmentAdapter(FragmentManager fm,List<NewestSecondFragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }


}
