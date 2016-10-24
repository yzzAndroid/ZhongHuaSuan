package com.qianfeng.yyz.zhonghuasuan.adapter;

/**
 * Created by Administrator on 2016/10/17 0017.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianfeng.yyz.zhonghuasuan.R;
import com.qianfeng.yyz.zhonghuasuan.apublic.MyApi;
import com.qianfeng.yyz.zhonghuasuan.bean.AppIndexBean;
import com.qianfeng.yyz.zhonghuasuan.bean.AppIndexGussLikeBean;
import com.qianfeng.yyz.zhonghuasuan.detail.view.DetailsActivity;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2016/10/17 0017.
 */
public class MyHomeListAdapter extends BaseAdapter {

    private AppIndexBean mAppIndexBean;
    private AppIndexGussLikeBean mAppIndexGussLikeBean;
    private  int sizeF;
    private int sizeL;
    private int sizeM;
    private int sizeLike;
    public static final int F = 1;
    public static final int L = 2;
    public static final int M = 3;
    public static final int LIKE = 4;
    private int flag = F;
    public static final String MONEY = "￥";
    private Context context;

    public MyHomeListAdapter(Context context) {
        this.context = context;
    }

    public void setmAppIndexBean(AppIndexBean mAppIndexBean) {
        this.mAppIndexBean = mAppIndexBean;
        if (null == mAppIndexBean){
            return;
        }
        sizeF = mAppIndexBean.getData().getFashion_goods().size();
        sizeL = mAppIndexBean.getData().getLife_goods().size();
        sizeM = mAppIndexBean.getData().getMombaby_goods().size();
    }

    public void setmAppIndexGussLikeBean(AppIndexGussLikeBean mAppIndexGussLikeBean) {
        this.mAppIndexGussLikeBean = mAppIndexGussLikeBean;
        if (null == mAppIndexGussLikeBean ||null==mAppIndexGussLikeBean.getData()){
            sizeLike = 0;
            return;
        }
        sizeLike = mAppIndexGussLikeBean.getData().size();
    }

    @Override
    public int getCount() {
        if (null==mAppIndexBean){
            return 0;
        }else if (null == mAppIndexGussLikeBean){
            return mAppIndexBean.getData().getFashion_goods().size()+mAppIndexBean.getData().getLife_goods().size()+
                    mAppIndexBean.getData().getMombaby_goods().size();
        }else {

            return mAppIndexBean.getData().getFashion_goods().size()+mAppIndexBean.getData().getLife_goods().size()+
                    mAppIndexBean.getData().getMombaby_goods().size()+mAppIndexGussLikeBean.getData().size();
        }

    }

    @Override
    public Object getItem(int position) {

        if (null==mAppIndexBean){
            return null;
        }

        if (position<sizeL){
            return mAppIndexBean.getData().getFashion_goods().get(position);
        }else if (position<sizeL+sizeF&&position>sizeL-1){
            return mAppIndexBean.getData().getLife_goods().get(position-sizeF);
        }
        else if (position<sizeF+sizeL+sizeM&&position>sizeL+sizeF-1){
            return mAppIndexBean.getData().getMombaby_goods().get(position-sizeF-sizeL);
        }else if (position<sizeF+sizeL+sizeM+sizeLike&&position>sizeL+sizeF+sizeM-1){
            return mAppIndexGussLikeBean.getData().get(position);
        }

        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //判断类型
        if (position<sizeL){
            flag = F;
        }else if (position<sizeL+sizeF&&position>sizeL-1){
            flag = L;
        }
        else if (position<sizeF+sizeL+sizeM&&position>sizeL+sizeF-1){
            flag = M;
        }
        else if (position<sizeF+sizeL+sizeM+sizeLike&&position>sizeL+sizeF+sizeM-1){
            flag = LIKE;
        }

        ViewHolder holder;
        if (null==convertView){
            convertView = LayoutInflater.from(context).inflate(R.layout.home_list_item,null);
            holder = new ViewHolder(convertView);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        switch (flag){
            case F:
                initF(holder,position,convertView);
                break;
            case L:
                initL(holder,position,convertView);
                break;
            case M:
                initM(holder,position,convertView);
                break;
            case LIKE:
                initLIke(holder,position,convertView);
                break;
        }
        return convertView;
    }

    private void initM(ViewHolder holder,int position1,View convertView) {
        final int position = position1 - sizeF - sizeL;
        Picasso.with(context).load(mAppIndexBean.getData().getMombaby_goods().get(position).getImg())
                .placeholder(R.mipmap.default_image).into(holder.imageView);
        holder.name.setText(mAppIndexBean.getData().getMombaby_goods().get(position).getTitle());
        Float price = Float.valueOf(mAppIndexBean.getData().getMombaby_goods().get(position).getCost_price());
        Float priceOrigin = Float.valueOf(mAppIndexBean.getData().getMombaby_goods().get(position).getPrice());
        BigDecimal bigDecimal1 = new BigDecimal(price.toString());
        BigDecimal bigDecimal2 = new BigDecimal(priceOrigin.toString());
        holder.price.setText(String.valueOf(price));
        holder.priceBack.setText(MONEY+String.valueOf(bigDecimal2.subtract(bigDecimal1)));
        holder.priceOrigin.setText(MONEY+String.valueOf(priceOrigin));
        holder.numPart.setText(mAppIndexBean.getData().getMombaby_goods().get(position).getRemain_quantity());
        holder.num.setText(mAppIndexBean.getData().getMombaby_goods().get(position).getQuantity());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra(MyApi.GID,mAppIndexBean.getData().getMombaby_goods().get(position).getGid());
                context.startActivity(intent);
            }
        });

    }

    private void initL(ViewHolder holder,int position1,View convertView) {
        final int position = position1-sizeF;
        Picasso.with(context).load(mAppIndexBean.getData().getLife_goods().get(position).getImg())
                .placeholder(R.mipmap.default_image).into(holder.imageView);
        holder.name.setText(mAppIndexBean.getData().getLife_goods().get(position).getTitle());
        Float price = Float.valueOf(mAppIndexBean.getData().getLife_goods().get(position).getCost_price());
        Float priceOrigin = Float.valueOf(mAppIndexBean.getData().getLife_goods().get(position).getPrice());
        BigDecimal bigDecimal1 = new BigDecimal(price.toString());
        BigDecimal bigDecimal2 = new BigDecimal(priceOrigin.toString());
        holder.price.setText(String.valueOf(price));
        holder.priceBack.setText(MONEY+String.valueOf(bigDecimal2.subtract(bigDecimal1)));
        holder.priceOrigin.setText(MONEY+String.valueOf(priceOrigin));
        holder.numPart.setText(mAppIndexBean.getData().getLife_goods().get(position).getRemain_quantity());
        holder.num.setText(mAppIndexBean.getData().getLife_goods().get(position).getQuantity());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra(MyApi.GID,mAppIndexBean.getData().getLife_goods().get(position).getGid());
                context.startActivity(intent);
            }
        });
    }

    private void initF(ViewHolder holder, final int position, View convertView) {
        Picasso.with(context).load(mAppIndexBean.getData().getFashion_goods().get(position).getImg())
                .placeholder(R.mipmap.default_image).into(holder.imageView);
        holder.name.setText(mAppIndexBean.getData().getFashion_goods().get(position).getTitle());
        Float price = Float.valueOf(mAppIndexBean.getData().getFashion_goods().get(position).getCost_price());
        Float priceOrigin = Float.valueOf(mAppIndexBean.getData().getFashion_goods().get(position).getPrice());
        BigDecimal bigDecimal1 = new BigDecimal(price.toString());
        BigDecimal bigDecimal2 = new BigDecimal(priceOrigin.toString());
        holder.price.setText(String.valueOf(price));
        holder.priceBack.setText(MONEY+String.valueOf(bigDecimal2.subtract(bigDecimal1)));
        holder.priceOrigin.setText(MONEY+String.valueOf(priceOrigin));
        holder.numPart.setText(mAppIndexBean.getData().getFashion_goods().get(position).getRemain_quantity());
        holder.num.setText(mAppIndexBean.getData().getFashion_goods().get(position).getQuantity());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra(MyApi.GID,mAppIndexBean.getData().getFashion_goods().get(position).getGid());
                context.startActivity(intent);
            }
        });
    }

    private void initLIke(ViewHolder holder,int position2,View convertView){
        final int position = position2-sizeF-sizeL-sizeM;
        Picasso.with(context).load(mAppIndexGussLikeBean.getData().get(position).getImg())
                .placeholder(R.mipmap.default_image).into(holder.imageView);
        holder.name.setText(mAppIndexGussLikeBean.getData().get(position).getTitle());
        Float price = Float.valueOf(mAppIndexGussLikeBean.getData().get(position).getCost_price());
        Float priceOrigin = Float.valueOf(mAppIndexGussLikeBean.getData().get(position).getPrice());
        BigDecimal bigDecimal1 = new BigDecimal(price.toString());
        BigDecimal bigDecimal2 = new BigDecimal(priceOrigin.toString());
        holder.price.setText(String.valueOf(price));
        holder.priceBack.setText(MONEY+String.valueOf(bigDecimal2.subtract(bigDecimal1)));
        holder.priceOrigin.setText(MONEY+String.valueOf(priceOrigin));
        holder.numPart.setText(String.valueOf(mAppIndexGussLikeBean.getData().get(position).getRemain_quantity()));
        holder.num.setText(String.valueOf(mAppIndexGussLikeBean.getData().get(position).getQuantity()));
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra(MyApi.GID,mAppIndexGussLikeBean.getData().get(position).getGid());
                context.startActivity(intent);
            }
        });
    }


    class ViewHolder{
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
        public ViewHolder(View view) {
            this.view = view;
            ButterKnife.bind(this,view);
            priceOrigin.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            //抗锯齿
            priceOrigin.getPaint().setAntiAlias(true);
            view.setTag(this);
        }
    }
}
