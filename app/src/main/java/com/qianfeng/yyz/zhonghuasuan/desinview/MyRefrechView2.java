package com.qianfeng.yyz.zhonghuasuan.desinview;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

import com.qianfeng.yyz.zhonghuasuan.R;

/**
 * Created by Administrator on 2016/10/19 0019.
 */
public class MyRefrechView2 extends SwipeRefreshLayout {
    private ListView MlistView;
    public MyRefrechView2(Context context) {
        this(context,null);
    }

    public MyRefrechView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (null==MlistView){
            MlistView = (ListView) findViewById(R.id.tomorrow_lv);
        }
        if (MlistView.getFirstVisiblePosition()!=0&&MlistView.getCount()!=0){
            return false;
        }

        return super.onInterceptTouchEvent(ev);
    }
}
