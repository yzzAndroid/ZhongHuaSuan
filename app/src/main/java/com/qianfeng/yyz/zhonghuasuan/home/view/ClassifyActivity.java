package com.qianfeng.yyz.zhonghuasuan.home.view;

import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.qianfeng.yyz.zhonghuasuan.R;
import com.qianfeng.yyz.zhonghuasuan.adapter.ClassfiGridViewAdapter;
import com.qianfeng.yyz.zhonghuasuan.adapter.ClassifyListAdapter;
import com.qianfeng.yyz.zhonghuasuan.apublic.BaseActivity;
import com.qianfeng.yyz.zhonghuasuan.apublic.MyApi;
import com.qianfeng.yyz.zhonghuasuan.bean.GeneralDataBean;
import com.qianfeng.yyz.zhonghuasuan.classify_second.view.ClassifySecondActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClassifyActivity extends BaseActivity implements IClassifyView {

    @BindView(R.id.classify_lv)
    ListView listView;
    @BindView(R.id.classify_giv)
    GridView gridView;
    private GeneralDataBean mGeneralBean;
    private int selectPosition = 0;
    private ClassifyListAdapter listAdapter;
    private ClassfiGridViewAdapter gridAdapter;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify);
        handler = new Handler();
        ButterKnife.bind(this);
        Intent intent = getIntent();
        mGeneralBean = (GeneralDataBean) intent.getSerializableExtra("bean");
        initfiled();
        initGridList();
        initList();

    }

    private void initfiled() {
        gridAdapter = new ClassfiGridViewAdapter(this);
        listAdapter = new ClassifyListAdapter(this);
        listAdapter.setGeneralDataBean(mGeneralBean);
        if (mGeneralBean != null) {
            gridAdapter.setList(mGeneralBean.getData().getGoods_category().get(selectPosition).getChild());
        }
        listView.setAdapter(listAdapter);
        gridView.setAdapter(gridAdapter);
        //读取本地数据
        if (null == mGeneralBean) {
            readGeneral();
        }
    }

    @Override
    public void initList() {

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listAdapter.setCurrentPosition(position);
                listAdapter.notifyDataSetChanged();
                gridAdapter.setList(mGeneralBean.getData().getGoods_category().get(position).getChild());
                gridAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void initGridList() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ClassifyActivity.this, ClassifySecondActivity.class);
                intent.putExtra(MyApi.CATEGORY_ID, mGeneralBean.getData().getGoods_category().get(selectPosition).getChild().get(position).getId());
                intent.putExtra("name", mGeneralBean.getData().getGoods_category().get(selectPosition).getChild().get(position).getName());
                startActivity(intent);
            }
        });
    }


    //读取本地数据
    private void readGeneral() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String path = "/storage/emulated/0/zhs/general";
                StringBuilder sb = new StringBuilder();
                String temp = null;
                try {
                    BufferedReader read = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
                    while ((temp = read.readLine()) != null) {
                        sb.append(temp);
                    }
                    Gson gosn = new Gson();
                    mGeneralBean = gosn.fromJson(sb.toString(), GeneralDataBean.class);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            initGridListNAtive();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //刷新数据
    private void initGridListNAtive() {
        if (null != mGeneralBean) {
            listAdapter.setGeneralDataBean(mGeneralBean);
            gridAdapter.setList(mGeneralBean.getData().getGoods_category().get(selectPosition).getChild());
            listAdapter.notifyDataSetChanged();
            gridAdapter.notifyDataSetChanged();
        }else {
            //显示空数据

        }
    }

    @Override
    public void refresh() {

    }

    @Override
    public void completeRefresh() {

    }

    @Override
    public void showError() {

    }

    public void back(View view) {
        finish();
    }
}
