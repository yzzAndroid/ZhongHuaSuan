package com.qianfeng.yyz.zhonghuasuan.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianfeng.yyz.zhonghuasuan.R;
import com.qianfeng.yyz.zhonghuasuan.bean.EightGoodsBean;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/19 0019.
 */
public class MyEightAdapter extends BaseAdapter {

    private Context context;
    private EightGoodsBean goodsBean;


    public MyEightAdapter(Context context) {
        this.context = context;
    }

    public void setGoodsBean(EightGoodsBean goodsBean) {
        this.goodsBean = goodsBean;
    }

    @Override
    public int getCount() {
        return goodsBean==null?0:goodsBean.getData().getRows().size();
    }

    @Override
    public Object getItem(int position) {
        return goodsBean.getData().getRows().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyEightViewHolder holder;
        if (null==convertView){
            convertView = LayoutInflater.from(context).inflate(R.layout.eight_gride_item,null);
            holder = new MyEightViewHolder(convertView);
        }else {
            holder = (MyEightViewHolder) convertView.getTag();
        }
        EightGoodsBean.DataBean.RowsBean bean = goodsBean.getData().getRows().get(position);
        Picasso.with(context).load(bean.getImg()).placeholder(R.mipmap.default_image).into(holder.imageView);
        holder.title.setText(bean.getTitle());
        holder.price_cost.setText(MyHomeListAdapter.MONEY+bean.getCost_price());
        holder.price_orign.setText(MyHomeListAdapter.MONEY+bean.getPrice());
        holder.count_now.setText(String.valueOf(bean.getQuantity()));
        holder.count.setText(String.valueOf(bean.getRemain_quantity()));

        return convertView;
    }

    class MyEightViewHolder{
        View view;
        @BindView(R.id.eight_grid_img)
        ImageView imageView;
        @BindView(R.id.eight_grid_title)
        TextView title;
        @BindView(R.id.eight_grid_price_cost)
        TextView price_cost;
        @BindView(R.id.eight_grid_price_orign)
        TextView price_orign;
        @BindView(R.id.eight_grid_count_now)
        TextView count_now;
        @BindView(R.id.eight_grid_count)
        TextView count;

        public MyEightViewHolder(View view) {
            this.view = view;
            ButterKnife.bind(this,view);
            view.setTag(this);
            price_orign.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            price_orign.getPaint().setAntiAlias(true);
        }
    }
}
