package com.qianfeng.yyz.zhonghuasuan.desinview;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;

import com.qianfeng.yyz.zhonghuasuan.R;

/**
 * Created by Administrator on 2016/10/19 0019.
 */
public class MyRefrechView extends SwipeRefreshLayout {
    private ListView TlistView;
    public MyRefrechView(Context context) {
        this(context,null);
    }

    public MyRefrechView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (null==TlistView){
            TlistView = (ListView) findViewById(R.id.today_lv);
        }
        if (TlistView.getFirstVisiblePosition()!=0&&TlistView.getCount()!=0){
            return false;
        }

        return super.onInterceptTouchEvent(ev);
    }
}
