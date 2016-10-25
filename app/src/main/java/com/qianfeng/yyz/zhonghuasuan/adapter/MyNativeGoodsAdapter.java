package com.qianfeng.yyz.zhonghuasuan.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianfeng.yyz.zhonghuasuan.R;
import com.qianfeng.yyz.zhonghuasuan.bean.EightGoodsBean;
import com.qianfeng.yyz.zhonghuasuan.bean.NewsNoticeTodayBean;
import com.qianfeng.yyz.zhonghuasuan.datacallback.Callback;
import com.qianfeng.yyz.zhonghuasuan.datacallback.ICheckNotify;
import com.qianfeng.yyz.zhonghuasuan.db.MyNativeInfo;
import com.qianfeng.yyz.zhonghuasuan.db.Utilsdb;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/19 0019.
 */
public class MyNativeGoodsAdapter extends BaseAdapter {
    private static final String MONEY = "￥";
    private Context context;
    private List<MyNativeInfo> list;
    private boolean deleteClick = false;
    private List<Boolean> booleanList;
    private Handler handler;
    private ICheckNotify checkNotify;


    public void setBooleanList(List<Boolean> booleanList) {
        this.booleanList = booleanList;
    }


    public void setList(List<MyNativeInfo> list) {
        this.list = list;
    }

    public MyNativeGoodsAdapter(Context context,ICheckNotify checkNotify) {
        this.context = context;
        handler = new Handler();
        this.checkNotify = checkNotify;
    }

    public void showDelete(boolean isChecked){
        deleteClick = isChecked;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return null==list?0:list.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final TodayViewHolder holder;
        if (null==convertView){
            convertView = LayoutInflater.from(context).inflate(R.layout.my_native_traces,null);
            holder = new TodayViewHolder(convertView);
        }else {
            holder = (TodayViewHolder) convertView.getTag();
        }
        MyNativeInfo  bean = list.get(position);
        Picasso.with(context).load(bean.getImg())
                .placeholder(R.mipmap.default_image).into(holder.imageView);

        holder.name.setText(bean.getTitle());
        BigDecimal bigDecimal1 = new BigDecimal(bean.getPriceCost());
        BigDecimal bigDecimal2 = new BigDecimal(bean.getPrice());
        holder.price.setText(bean.getPriceCost());
        holder.priceBack.setText(MONEY+bigDecimal2.subtract(bigDecimal1));
        holder.priceOrigin.setText(MONEY+bean.getPrice());
        holder.numPart.setText(String.valueOf(bean.getNowNum()));
        holder.num.setText(String.valueOf(bean.getNowNum()));

        holder.delete_cb.setChecked(booleanList.get(position));
        if (deleteClick){
            holder.delete_cb.setVisibility(View.VISIBLE);
            holder.delete_btn.setVisibility(View.VISIBLE);
        }else {
            holder.delete_cb.setVisibility(View.GONE);
            holder.delete_btn.setVisibility(View.GONE);
        }

        holder.delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utilsdb.deleteGoods(list.get(position), context, new Callback() {
                    @Override
                    public void sucess() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                booleanList.remove(position);
                                list.remove(position);
                                notifyDataSetChanged();
                            }
                        });
                    }
                });
            }
        });

        holder.delete_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                booleanList.set(position,holder.delete_cb.isChecked());
                checkNotify.notifyCheck();
            }
        });
        return convertView;
    }


    class TodayViewHolder{
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

        @BindView(R.id.traces_delete_cb)
        CheckBox delete_cb;
        @BindView(R.id.traces_delete_btn)
        Button delete_btn;
        public TodayViewHolder(View view) {
            this.view = view;
            ButterKnife.bind(this,view);
            view.setTag(this);
        }
    }
}
