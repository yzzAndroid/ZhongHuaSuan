package com.qianfeng.yyz.zhonghuasuan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qianfeng.yyz.zhonghuasuan.R;
import com.qianfeng.yyz.zhonghuasuan.bean.HotKeyWord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/18.
 */
public class HotGridViewAdapter extends BaseAdapter {
    Context context;
    List<HotKeyWord.DataBean> keywords = new ArrayList<>();

    public HotGridViewAdapter(Context context) {
        this.context = context;
    }


    public void setKeywords(List<HotKeyWord.DataBean> keywords) {
        this.keywords = keywords;
    }

    @Override
    public int getCount() {
        return null == keywords ? 0 : keywords.size();
    }

    @Override
    public Object getItem(int position) {
        return keywords.get(position);
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
        viewHolder.tv_good_name.setText(keywords.get(position).getKeyword());
        return convertView;
    }

    class ViewHolder {
        TextView tv_good_name;
        private View view;
        public ViewHolder(View v) {
            view = v;
           // tv_good_name = (TextView) v.findViewById(R.id.tv_good_name);
            view.setTag(this);
        }
    }

}
