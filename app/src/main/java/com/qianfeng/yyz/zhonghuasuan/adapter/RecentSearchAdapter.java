package com.qianfeng.yyz.zhonghuasuan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qianfeng.yyz.zhonghuasuan.R;
import com.qianfeng.yyz.zhonghuasuan.bean.HotKeyWord;
import com.qianfeng.yyz.zhonghuasuan.db.SearchHistory;

import java.util.List;

/**
 * Created by Administrator on 2016/10/20.
 */
public class RecentSearchAdapter extends BaseAdapter {
    Context context;
    List<SearchHistory> mHistories;

    public RecentSearchAdapter(Context context) {
        this.context = context;
    }


    public void setmHistories(List<SearchHistory> mHistories) {
        this.mHistories = mHistories;
    }

    @Override
    public int getCount() {
        if (mHistories!=null&&mHistories.size()>5){
            return 5;
        }else {
            return mHistories == null ? 0 : mHistories.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return mHistories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_search_gridview, null, false);
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_recent_search.setText(mHistories.get(position).getContent());
        return convertView;
    }

    class ViewHolder {
        TextView tv_recent_search;
        public ViewHolder(View v) {
            //tv_recent_search= (TextView) v.findViewById(R.id.tv_good_name);
            v.setTag(this);
        }
    }
}
