package com.qianfeng.yyz.zhonghuasuan.classify_second.view;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.qianfeng.yyz.zhonghuasuan.R;
import com.qianfeng.yyz.zhonghuasuan.adapter.MyEightAdapter;
import com.qianfeng.yyz.zhonghuasuan.apublic.DialogUtils;
import com.qianfeng.yyz.zhonghuasuan.apublic.MyApi;
import com.qianfeng.yyz.zhonghuasuan.bean.EightGoodsBean;
import com.qianfeng.yyz.zhonghuasuan.classify_second.presenter.ClassifySecondPresenter;
import com.qianfeng.yyz.zhonghuasuan.detail.view.DetailsActivity;
import com.qianfeng.yyz.zhonghuasuan.home.presenter.EightGoodsPresentImpl;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClassifySecondActivity extends AppCompatActivity implements IClassifySecondView {

    @BindView(R.id.classify_second_giv)
    GridView gridView;


    @BindView(R.id.eight_sort_newest)
    TextView newestTv;
    @BindView(R.id.eight_sort_hotest)
    TextView hotestTv;
    @BindView(R.id.eight_sort_default)
    TextView defaultTv;
    @BindView(R.id.sort_price_tv)
    TextView priceTv;
    @BindView(R.id.sort_price_img)
    ImageView priceImg;
    @BindView(R.id.sort_layout)
    LinearLayout sortLayout;
    @BindView(R.id.eight_sort_priceLayout)
    LinearLayout sortPriceLayout;
    @BindView(R.id.classify_second_swip)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.classify_second_search_now)
    EditText editText;
    private String mName;
    private MyEightAdapter adapter;
    private Map<String,String> map;
    private String id;
    private Intent intent;
    private AlertDialog dialog;
    private View mNowView;
    private EightGoodsBean mEightGoodsBean;
    private boolean isSearching = false;
    private String search_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify_second);
        ButterKnife.bind(this);
        intent = getIntent();
        controalSwitch();
        initFiled();
        if (isSearching){
            ClassifySecondPresenter.getInstance().enterClassifySecondSearching(this,map);
        }else {

            ClassifySecondPresenter.getInstance().enterClassifySecondView(this,map);
        }
    }

    private void initFiled() {
        adapter = new MyEightAdapter(this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (null==mEightGoodsBean){
                    return;
                }
                Intent intent = new Intent(ClassifySecondActivity.this,DetailsActivity.class);
                intent.putExtra(MyApi.GID,mEightGoodsBean.getData().getRows().get(position).getGid());
                startActivity(intent);
            }
        });
        id = intent.getStringExtra(MyApi.CATEGORY_ID);
        if (TextUtils.isEmpty(id)){
            id = "0";
        }
        isSearching = intent.getBooleanExtra("isSearching",false);
        search_key = intent.getStringExtra(MyApi.SEARCH_KEY);
        map = new HashMap<>();
        map.put(MyApi.UID,"-1");
        map.put(MyApi.CLIENT_TYPE,"3");
        map.put(MyApi.TYPE,"default");
        map.put(MyApi.VERSION,"3003201");
        map.put(MyApi.CATEGORY_ID,id);
        map.put(MyApi.SIZE,"30");
        map.put(MyApi.PAGE,"1");
        map.put(MyApi.SORT,"default");
        map.put(MyApi.SEARCH_KEY,search_key);
        dialog = DialogUtils.beginLoading(this);
        mNowView = defaultTv;
        mName = intent.getStringExtra("name");
        editText.setHint(getString(R.string.search_now)+mName);



        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ClassifySecondPresenter.getInstance().refresh(map,isSearching);
            }
        });
    }

    @Override
    public void initView(EightGoodsBean bean) {
        mEightGoodsBean = bean;
        adapter.setGoodsBean(bean);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void refresh() {
        dialog.show();
    }

    @Override
    public void completeRefresh() {
        dialog.dismiss();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showError(String msg) {
        Log.e("=========","======"+msg);
        swipeRefreshLayout.setRefreshing(false);
    }

    public void search(View view) {
    }

    public void back(View view) {
        finish();
    }


    //查询的切换
    private void controalSwitch() {
        int count = sortLayout.getChildCount();
        for (int i = 0;i<count;i++){
            sortLayout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mNowView instanceof TextView){
                        ((TextView)mNowView).setTextColor(getResources().getColor(R.color.alpha_dark));
                    }else if (mNowView==sortPriceLayout){
                        priceTv.setTextColor(getResources().getColor(R.color.alpha_dark));
                        priceImg.setImageResource(R.mipmap.search_price);
                    }
                    switch (v.getId()){
                        case R.id.eight_sort_default:
                            map.put(MyApi.SORT,"default");
                            defaultTv.setTextColor(getResources().getColor(R.color.point_select));
                            mNowView = defaultTv;
                            break;
                        case R.id.eight_sort_newest:
                            map.put(MyApi.SORT,"time-desc");
                            newestTv.setTextColor(getResources().getColor(R.color.point_select));
                            mNowView = newestTv;
                            break;
                        case R.id.eight_sort_hotest:
                            map.put(MyApi.SORT,"hot-desc");
                            hotestTv.setTextColor(getResources().getColor(R.color.point_select));
                            mNowView = hotestTv;
                            break;
                        case R.id.eight_sort_priceLayout:
                            //再次点击会有效果
                            if (mNowView instanceof LinearLayout){
                                //第二次点击
                                priceImg.setImageResource(R.mipmap.search_price_up);
                                priceTv.setTextColor(getResources().getColor(R.color.point_select));
                                map.put(MyApi.SORT,"price-asc");
                            }else {
                                //第一次点击
                                priceTv.setTextColor(getResources().getColor(R.color.point_select));
                                priceImg.setImageResource(R.mipmap.search_price_down);
                                map.put(MyApi.SORT,"price-desc");
                            }
                            mNowView = sortPriceLayout;
                            break;
                    }
                    ClassifySecondPresenter.getInstance().refresh(map,isSearching);

                }
            });
        }
    }
}
