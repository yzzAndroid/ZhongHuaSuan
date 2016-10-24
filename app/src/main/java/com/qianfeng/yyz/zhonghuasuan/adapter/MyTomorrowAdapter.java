package com.qianfeng.yyz.zhonghuasuan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianfeng.yyz.zhonghuasuan.R;
import com.qianfeng.yyz.zhonghuasuan.bean.NewsNoticeTodayBean;
import com.qianfeng.yyz.zhonghuasuan.bean.NewsNoticeTomorrowBean;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/18 0018.
 */
public class MyTomorrowAdapter extends BaseAdapter{


    private static final String MONEY = "￥";
    private NewsNoticeTomorrowBean tomorrowBean;
    private Context context;

    public MyTomorrowAdapter(Context context) {
        this.context = context;
    }

    public void setTodayBean(NewsNoticeTomorrowBean tomorrowBean) {
        this.tomorrowBean = tomorrowBean;
    }

    @Override
    public int getCount() {
        if (null==tomorrowBean||null==tomorrowBean.getData()){
            return 0;
        }
        return null==tomorrowBean.getData().getRows()?0:tomorrowBean.getData().getRows().size();
    }

    @Override
    public Object getItem(int position) {
        return tomorrowBean.getData().getRows().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TomorrowViewHolder holder;
        if (null==convertView){
            convertView = LayoutInflater.from(context).inflate(R.layout.today_list_item,null);
            holder = new TomorrowViewHolder(convertView);
        }else {
            holder = (TomorrowViewHolder) convertView.getTag();
        }
        NewsNoticeTomorrowBean.DataBean.RowsBean bean = tomorrowBean.getData().getRows().get(position);
        Picasso.with(context).load(bean.getImg())
        .placeholder(R.mipmap.default_image).into(holder.imageView);

        holder.name.setText(bean.getTitle());
        BigDecimal bigDecimal1 = new BigDecimal(bean.getCost_price());
        BigDecimal bigDecimal2 = new BigDecimal(bean.getPrice());
        holder.price.setText(bean.getCost_price());
        holder.priceBack.setText(MONEY+bigDecimal2.subtract(bigDecimal1));
        holder.priceOrigin.setText(MONEY+bean.getPrice());
        holder.numPart.setText(String.valueOf(bean.getRemain_quantity()));
        holder.num.setText(String.valueOf(bean.getQuantity()));

        return convertView;
    }

    class TomorrowViewHolder{
        View view;

        @BindView(R.id.list_item_img)
        ImageView imageView;
        @BindView(R.id.list_item_name)
        TextView name;
        @BindView(R.id.list_item_price)
        TextView price;
        @BindView(R.id.list_item_price_back_money)
        TextView priceBack;
        @BindView(R.id.item_price_origin)
        TextView priceOrigin;
        @BindView(R.id.item_num_part)
        TextView numPart;
        @BindView(R.id.item_num)
        TextView num;
        public TomorrowViewHolder(View view) {
            this.view = view;
            ButterKnife.bind(this,view);
            view.setTag(this);
        }
    }
}
