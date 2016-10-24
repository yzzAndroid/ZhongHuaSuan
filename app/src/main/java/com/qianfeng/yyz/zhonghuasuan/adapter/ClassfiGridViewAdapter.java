package com.qianfeng.yyz.zhonghuasuan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianfeng.yyz.zhonghuasuan.R;
import com.qianfeng.yyz.zhonghuasuan.bean.GeneralDataBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/19 0019.
 */
public class ClassfiGridViewAdapter extends BaseAdapter {
    Context context;
    List<GeneralDataBean.DataBean.GoodsCategoryBean.ChildBean> list;

    public ClassfiGridViewAdapter(Context context) {
        this.context = context;
    }


    public void setList(List<GeneralDataBean.DataBean.GoodsCategoryBean.ChildBean> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return null == list ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyClassifyGivViewHolder holder;
        if (null==convertView){
            convertView = LayoutInflater.from(context).inflate(R.layout.classify_giv_item,null);
            holder = new MyClassifyGivViewHolder(convertView);
        }else {
            holder = (MyClassifyGivViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(list.get(position).getImg()).placeholder(R.mipmap.default_image)
                .into(holder.imageView);
        holder.textView.setText(list.get(position).getName());
        return convertView;
    }

    class MyClassifyGivViewHolder {
        View view;
        @BindView(R.id.calssify_giv_img)
        ImageView imageView;
        @BindView(R.id.calssify_giv_tv)
        TextView textView;

        public MyClassifyGivViewHolder(View view) {
            this.view = view;
            ButterKnife.bind(this, view);
            view.setTag(this);
        }

    }
}
