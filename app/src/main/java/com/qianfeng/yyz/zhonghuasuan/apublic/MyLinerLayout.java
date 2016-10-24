package com.qianfeng.yyz.zhonghuasuan.apublic;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qianfeng.yyz.zhonghuasuan.R;
import com.qianfeng.yyz.zhonghuasuan.db.SearchHistory;

import java.util.List;

/**
 * Created by Administrator on 2016/10/21 0021.
 */
public class MyLinerLayout extends LinearLayout {

    private MyLinerAdapter adapter;
    private Listener listener;



    public MyLinerLayout(Context context) {
        this(context,null);
    }

    public MyLinerLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyLinerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAdapter(MyLinerAdapter adapter) {
        this.adapter = adapter;
    }

    public MyLinerAdapter getMyLinerAdapter(){
        return new MyLinerAdapter(getContext());

    }

    public void setOnItemClickListener(Listener listener) {
        this.listener = listener;
    }

    public class MyLinerAdapter {
        private int mCurrentLength = 0;
        List<SearchHistory> list;
        Context context;
        LinearLayout view;

        public MyLinerAdapter(Context context) {
            this.context = context;
        }

        public void setList(List<SearchHistory> list) {
            this.list = list;
            removeAllViews();
            addMyView();
        }

        private void addMyView() {
            if (list == null) {
                return;
            }
            //先定以一个
            view = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.item_search_gridview, null);
            for (SearchHistory history : list) {

                if (TextUtils.isEmpty(history.getContent())) {

                } else {
                    if (mCurrentLength + history.getContent().length() >0.14&&view.getChildCount()==0) {
                        addView(view);
                        view.setTag(true);
                        creatTextView(history);
                        mCurrentLength += history.getCount() ;
                    } else if (mCurrentLength+history.getContent().length()>14&&view.getChildCount()>0){
                        view = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.item_search_gridview, null);
                        creatTextView(history);
                        addView(view);
                        view.setTag(true);
                        mCurrentLength = history.getContent().length();
                    }else{
                       if (view.getTag()==null||!(boolean)view.getTag()){
                           addView(view);
                           view.setTag(true);
                       }
                        creatTextView(history);
                        mCurrentLength += history.getContent().length();
                    }
                }
                //刷新视图
                invalidate();
            }
        }


        private void creatTextView(SearchHistory history) {
            TextView tv = (TextView) LayoutInflater.from(context).inflate(R.layout.textviewself,null);
            tv.setText(history.getContent());
            view.addView(tv);
            final LinearLayout.LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.gravity = Gravity.CENTER_VERTICAL;
            lp.rightMargin = 10;
            lp.leftMargin = 10;
            lp.bottomMargin = 5;
            lp.topMargin = 5;
            tv.setLayoutParams(lp);
            tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null==listener){
                        return;
                    }else {
                        listener.click(v);
                    }
                }
            });
        }

    }

    public interface Listener{
        void click(View view);
    }

}
