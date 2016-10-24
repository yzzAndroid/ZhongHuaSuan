package com.qianfeng.yyz.zhonghuasuan.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.qianfeng.yyz.zhonghuasuan.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/10/17 0017.
 */
public class MyPagerAdapter extends android.support.v4.view.PagerAdapter {

    private List<String> stringList;
    private List<ImageView> imageViewList;
    private Context context;

    public MyPagerAdapter(Context context, List<ImageView> imageViewList, List<String> stringList) {
        this.context = context;
        this.imageViewList = imageViewList;
        this.stringList = stringList;
    }

    public void setImageViewList(List<ImageView> imageViewList) {
        this.imageViewList = imageViewList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView(imageViewList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        imageViewList.get(position).setScaleType(ImageView.ScaleType.CENTER_CROP);
        Picasso.with(context).load(stringList.get(position))
                .placeholder(R.mipmap.default_image)
                .into(imageViewList.get(position));

        Log.e("=============","============"+position+"====="+stringList.get(position));
        container.addView(imageViewList.get(position));
        return imageViewList.get(position);
    }
}

