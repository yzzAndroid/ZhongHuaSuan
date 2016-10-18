package com.qianfeng.yyz.zhonghuasuan.desinview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.util.AttributeSet;
import android.view.View;

import com.qianfeng.yyz.zhonghuasuan.R;

/**
 * Created by Administrator on 2016/10/17 0017.
 */
public class MyGiftView extends View{

    private Movie mMovie;
    private long mStartTime;


    public MyGiftView(Context context) {
        this(context,null);
    }

    public MyGiftView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyGiftView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mMovie = Movie.decodeStream(getResources().openRawResource(R.mipmap.zhs_loading));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        long now = android.os.SystemClock.uptimeMillis();

        if (mStartTime==0){
            mStartTime = now;
        }
        if (null!=mMovie){
            int dur = mMovie.duration();

            if (dur == 0) {
                dur = 1000;
            }

            int relTime = (int) ((now - mStartTime) % dur);
            mMovie.setTime(relTime);
            mMovie.draw(canvas, 0, 0);
            //重新绘制
            invalidate();
        }
        super.onDraw(canvas);
    }
}
