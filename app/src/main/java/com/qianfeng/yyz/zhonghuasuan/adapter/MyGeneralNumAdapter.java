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
 * 是类目下面的一个列表
 */
public class MyGeneralNumAdapter extends BaseAdapter {

    private GeneralDataBean generalDataBean;
    private Context context;
    public static final String ALL_TYPE = "全部类型";
    private int mcurrentselectposition = 0;

    public void setMcurrentselectposition(int mcurrentselectposition) {
        this.mcurrentselectposition = mcurrentselectposition;
    }

    public MyGeneralNumAdapter(Context context, GeneralDataBean generalDataBean) {
        this.context = context;
        this.generalDataBean = generalDataBean;
    }


    @Override
    public int getCount() {
        return null == generalDataBean ? 0 : generalDataBean.getData().getGoods_category().size() + 1;
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
        MyGeneralViewHolder holder;
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.generalnum_item, null);
            holder = new MyGeneralViewHolder(convertView);
        } else {
            holder = (MyGeneralViewHolder) convertView.getTag();
        }

        //设置类容
        if (position == 0) {
            holder.tv.setText(ALL_TYPE);
        }else {
            holder.tv.setText(generalDataBean.getData().getGoods_category().get(position-1).getName());
        }

        //改变颜色
        if (position == mcurrentselectposition) {
            holder.tv.setTextColor(context.getResources().getColor(R.color.white));
            convertView.setBackgroundResource(R.drawable.general_num_shap);
        } else {
            holder.tv.setTextColor(context.getResources().getColor(R.color.alpha_dark));
            convertView.setBackgroundResource(R.color.white);
        }


        return convertView;
    }

    class MyGeneralViewHolder {
        View view;
        @BindView(R.id.general_item_tv)
        TextView tv;

        public MyGeneralViewHolder(View view) {
            this.view = view;
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }
}
