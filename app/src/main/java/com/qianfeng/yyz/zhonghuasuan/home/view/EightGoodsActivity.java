package com.qianfeng.yyz.zhonghuasuan.home.view;

import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.qianfeng.yyz.zhonghuasuan.R;
import com.qianfeng.yyz.zhonghuasuan.adapter.MyEightAdapter;
import com.qianfeng.yyz.zhonghuasuan.adapter.MyGeneralNumAdapter;
import com.qianfeng.yyz.zhonghuasuan.apublic.BaseActivity;
import com.qianfeng.yyz.zhonghuasuan.apublic.DialogUtils;
import com.qianfeng.yyz.zhonghuasuan.apublic.MyApi;
import com.qianfeng.yyz.zhonghuasuan.bean.EightGoodsBean;
import com.qianfeng.yyz.zhonghuasuan.bean.GeneralDataBean;
import com.qianfeng.yyz.zhonghuasuan.detail.view.DetailsActivity;
import com.qianfeng.yyz.zhonghuasuan.home.presenter.EightGoodsPresentImpl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EightGoodsActivity extends BaseActivity implements IEightFView {

    public static final int TYPE_TWO = 2;
    public static final int TYPE_THREE = 3;
    public static final int TYPE_FOUR = 4;
    public static final String TYPE = "type";
    private MyEightAdapter mAdapter;

    @BindView(R.id.eight_pull_giv)
    PullToRefreshGridView pullToRefreshGridView;
    @BindView(R.id.eight_title)
    TextView title;
    @BindView(R.id.eight_sort_newest)
    TextView newestTv;
    @BindView(R.id.eight_sort_hotest)
    TextView hotestTv;
    @BindView(R.id.eight_sort_count)
    TextView countTv;
    @BindView(R.id.eight_sort_default)
    TextView defaultTv;
    @BindView(R.id.sort_price_tv)
    TextView priceTv;
    @BindView(R.id.sort_price_img)
    ImageView priceImg;
    @BindView(R.id.sort_layout)
    LinearLayout sortLayout;
    @BindView(R.id.sort_count_layout)
    LinearLayout sortCountLayout;
    @BindView(R.id.sort_count_img)
    ImageView sortCountImg;
    @BindView(R.id.eight_sort_priceLayout)
    LinearLayout sortPriceLayout;
    @BindView(R.id.sort_count_grid_view)
    GridView gridView;
    Handler mHandler;


    private View mNowView;
    //要跳转的类型
    private int mType = 2;
    private HashMap<String, String> map;
    private AlertDialog dialog;
    private GeneralDataBean mGeneralDataBean;
    private MyGeneralNumAdapter generalNumAdapter;
    //gridview当前的选择项
    private int mCurrentSelect = 0;
    private EightGoodsBean mEightGoodsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eight_goods);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        mType = intent.getIntExtra(TYPE,2);
        mGeneralDataBean = (GeneralDataBean) intent.getSerializableExtra("bean");
        initMap();
        iniFiled();
        controalSwitch();
        initGridView();
        //网络请求
        initCreat();
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
                   }else if (mNowView==sortCountLayout){
                       countTv.setTextColor(getResources().getColor(R.color.alpha_dark));
                       sortCountImg.setVisibility(View.GONE);
                       //还有一个布局
                        gridView.setVisibility(View.GONE);
                   }
                    switch (v.getId()){
                        case R.id.eight_sort_default:
                            map.put(MyApi.SORT,"default");
                            defaultTv.setTextColor(getResources().getColor(R.color.point_select));
                            mNowView = defaultTv;
                            EightGoodsPresentImpl.getInstance().refresh(map);
                            break;
                        case R.id.eight_sort_newest:
                            map.put(MyApi.SORT,"time-desc");
                            newestTv.setTextColor(getResources().getColor(R.color.point_select));
                            mNowView = newestTv;
                            EightGoodsPresentImpl.getInstance().refresh(map);
                            break;
                        case R.id.eight_sort_hotest:
                            map.put(MyApi.SORT,"hot-desc");
                            hotestTv.setTextColor(getResources().getColor(R.color.point_select));
                            mNowView = hotestTv;
                            EightGoodsPresentImpl.getInstance().refresh(map);
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
                            EightGoodsPresentImpl.getInstance().refresh(map);
                            break;
                        case R.id.sort_count_layout:
                            countTv.setTextColor(getResources().getColor(R.color.point_select));
                            sortCountImg.setVisibility(View.VISIBLE);
                            gridView.setVisibility(View.VISIBLE);
                            mNowView = sortCountLayout;
                            break;
                    }

                }
            });
        }
    }

    private void initGridView() {
        if (null==mGeneralDataBean){
            //从本地读取
            new Thread(new Runnable() {
                @Override
                public void run() {
                    readGeneral();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            initGrideViewNative();
                        }
                    });
                }
            }).start();
            return;
        }
        generalNumAdapter = new MyGeneralNumAdapter(this,mGeneralDataBean);
        gridView.setAdapter(generalNumAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                generalNumAdapter.setMcurrentselectposition(position);
                generalNumAdapter.notifyDataSetChanged();
                //网络请求
                int iPosition = 0;
                if (position!=0){
                    iPosition = position-1;
                }
                map.put(MyApi.CATEGORY_ID,String.valueOf(iPosition));
                EightGoodsPresentImpl.getInstance().refresh(map);
            }
        });
    }

    private void initGrideViewNative(){
        generalNumAdapter = new MyGeneralNumAdapter(this,mGeneralDataBean);
        gridView.setAdapter(generalNumAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                generalNumAdapter.setMcurrentselectposition(position);
                generalNumAdapter.notifyDataSetChanged();
                //网络请求
                int iPosition = 0;
                if (position!=0){
                    iPosition = position-1;
                }
                map.put(MyApi.CATEGORY_ID,String.valueOf(iPosition));
                EightGoodsPresentImpl.getInstance().refresh(map);
            }
        });
    }

    private void readGeneral() {
        String path = "/storage/emulated/0/zhs/general";
        StringBuilder sb = new StringBuilder();
        String temp = null;
        try {
            BufferedReader read = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            while ((temp=read.readLine())!=null){
                sb.append(temp);
            }
            Gson gosn = new Gson();
            mGeneralDataBean = gosn.fromJson(sb.toString(),GeneralDataBean.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void iniFiled() {
        mHandler = new Handler();
        mAdapter = new MyEightAdapter(this);
        pullToRefreshGridView.getRefreshableView().setAdapter(mAdapter);
        pullToRefreshGridView.getRefreshableView().setNumColumns(2);
        //设置默认值
        mNowView = defaultTv;
        pullToRefreshGridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                int page = Integer.valueOf(map.get(MyApi.PAGE));
                map.put(MyApi.PAGE,String.valueOf(page));
                EightGoodsPresentImpl.getInstance().refresh(map);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {

            }
        });

        pullToRefreshGridView.getRefreshableView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    if (null==mEightGoodsBean){
                        return;
                    }
                    Intent intent = new Intent(EightGoodsActivity.this,DetailsActivity.class);
                    intent.putExtra(MyApi.GID,mEightGoodsBean.getData().getRows().get(position).getGid());
                    startActivity(intent);
            }
        });
    }

    private void initMap() {
        map = new HashMap<>();
        map.put(MyApi.UID,"-1");
        map.put(MyApi.CLIENT_TYPE,"3");
        map.put(MyApi.TYPE,"mpg");
        map.put(MyApi.VERSION,"3003201");
        map.put(MyApi.CATEGORY_ID,"0");
        map.put(MyApi.SIZE,"30");
        map.put(MyApi.PAGE,"1");
        map.put(MyApi.SORT,"default");

    }

//    @Field(MyApi.UID)String uid,
//    @Field(MyApi.CLIENT_TYPE)String client_type,
//    @Field(MyApi.TYPE)String type,
//    @Field(MyApi.VERSION)String viersion,
//    @Field(MyApi.CATEGORY_ID)String category_id,
//    @Field(MyApi.SIZE)String size,
//    @Field(MyApi.PAGE)String page,
//    @Field(MyApi.SORT)String sort
//    client_type	3
//    size	30
//    version	3003201
//    uid	-1
//    sort	default
//    category_id	0
//    type	mpg
//    page	1



    private void initCreat() {
        switch (mType){
            case TYPE_TWO:
                EightGoodsPresentImpl.getInstance().enterERightGoodsView(this,map);
                title.setText(getString(R.string.famous));
                break;
            case TYPE_THREE:
                map.put(MyApi.TYPE,"yzcm");
                EightGoodsPresentImpl.getInstance().enterERightGoodsView(this,map);
                title.setText(getString(R.string.one));
                break;
            case TYPE_FOUR:
                map.put(MyApi.TYPE,"default");
                EightGoodsPresentImpl.getInstance().enterERightGoodsView(this,map);
                title.setText(getString(R.string.goods_classify));
                break;
        }
    }

    public void back(View view) {
        finish();
    }

    public void search(View view) {
        //跳转
    }

    @Override
    public void initList(EightGoodsBean bean) {
        mEightGoodsBean = bean;
        mAdapter.setGoodsBean(bean);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void refresh() {
        dialog = DialogUtils.beginLoading(this);
        dialog.show();
    }

    @Override
    public void completeRefresh() {
        pullToRefreshGridView.onRefreshComplete();
        dialog.dismiss();
    }

    @Override
    public void showError() {
        dialog.dismiss();
        Toast.makeText(EightGoodsActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
    }


}
