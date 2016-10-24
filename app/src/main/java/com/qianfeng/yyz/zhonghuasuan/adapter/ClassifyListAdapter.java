package com.qianfeng.yyz.zhonghuasuan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qianfeng.yyz.zhonghuasuan.R;
import com.qianfeng.yyz.zhonghuasuan.bean.GeneralDataBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/19 0019.
 */
public class ClassifyListAdapter extends BaseAdapter{
    Context context;
    GeneralDataBean generalDataBean;
    int currentPosition = 0;

    public void setGeneralDataBean(GeneralDataBean generalDataBean) {
        this.generalDataBean = generalDataBean;
    }

    public ClassifyListAdapter(Context context) {
        this.context = context;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    @Override
    public int getCount() {
        return generalDataBean==null?0:generalDataBean.getData().getGoods_category().size();
    }

    @Override
    public Object getItem(int position) {
        return generalDataBean.getData().getGoods_category().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyClassiFyListViewHolder holder;
        if (null==convertView){
            convertView = LayoutInflater.from(context).inflate(R.layout.classify_list_item,null);
            holder = new MyClassiFyListViewHolder(convertView);
        }else {
            holder = (MyClassiFyListViewHolder) convertView.getTag();
        }

        holder.tv.setText(generalDataBean.getData().getGoods_category().get(position).getName());
        holder.tv.setTextColor(context.getResources().getColor(R.color.dark));

        if (!(position==currentPosition)){
            convertView.setBackgroundColor(context.getResources().getColor(R.color.count_down_bg));
        }else {
           convertView.setBackgroundColor(context.getResources().getColor(R.color.white));
        }
        return convertView;
    }

    class MyClassiFyListViewHolder {
        View view;
        @BindView(R.id.general_item_tv)
        TextView tv;

        public MyClassiFyListViewHolder(View view) {
            this.view = view;
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }
}
