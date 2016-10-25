package com.qianfeng.yyz.zhonghuasuan.mine.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.qianfeng.yyz.zhonghuasuan.R;
import com.qianfeng.yyz.zhonghuasuan.adapter.MyNativeGoodsAdapter;
import com.qianfeng.yyz.zhonghuasuan.apublic.BaseActivity;
import com.qianfeng.yyz.zhonghuasuan.apublic.MyApi;
import com.qianfeng.yyz.zhonghuasuan.datacallback.Callback;
import com.qianfeng.yyz.zhonghuasuan.datacallback.ICheckNotify;
import com.qianfeng.yyz.zhonghuasuan.datacallback.IDataFronNetCallback;
import com.qianfeng.yyz.zhonghuasuan.db.MyNativeInfo;
import com.qianfeng.yyz.zhonghuasuan.db.Utilsdb;
import com.qianfeng.yyz.zhonghuasuan.detail.view.DetailsActivity;

import org.greenrobot.greendao.DbUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyTracesActivity extends BaseActivity implements ICheckNotify{


    @BindView(R.id.traces_pullListView)
    PullToRefreshListView listView;

    @BindView(R.id.trace_title_delete)
    CheckBox delete;

    @BindView(R.id.traces_bottom)
    RelativeLayout bottom;

    @BindView(R.id.traces_bottom_check_all)
    TextView checkAll;

    private MyNativeGoodsAdapter mMyNativeGoodsAdapter;

    private List<Boolean> stateBoolean;
    private List<MyNativeInfo> list;
    Handler handler;

    private boolean isCheckAll = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_traces);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        handler = new Handler();
        stateBoolean = new ArrayList<>();
        mMyNativeGoodsAdapter = new MyNativeGoodsAdapter(this,this);
        listView.setAdapter(mMyNativeGoodsAdapter);
        Utilsdb.querryGoods(this, new IDataFronNetCallback<List<MyNativeInfo>>() {
            @Override
            public void success(List<MyNativeInfo> myNativeInfos) {
                list = myNativeInfos;
                mMyNativeGoodsAdapter.setList(myNativeInfos);
                int size = myNativeInfos.size();
                for (int i = 0;i<size;i++){
                    stateBoolean.add(false);
                }
                Log.e("===========","============"+stateBoolean.size() );
                mMyNativeGoodsAdapter.setBooleanList(stateBoolean);
                mMyNativeGoodsAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(String msg) {
                //do nothing
            }
        });

        listView.getRefreshableView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!delete.isChecked()&&position>0){
                    position--;
                    Log.e("=====","========"+position);
                    Intent intent = new Intent(MyTracesActivity.this, DetailsActivity.class);
                        intent.putExtra(MyApi.GID,list.get(position).getGid());
                    startActivity(intent);
                }
            }
        });
    }

    public void back(View view) {
        finish();
    }

    //删除
    public void delete(View view) {
        if (delete.isChecked()){
            delete.setText(getString(R.string.cancel));
            bottom.setVisibility(View.VISIBLE);
        }else {
            delete.setText(getString(R.string.delete));
            bottom.setVisibility(View.GONE);
        }
        mMyNativeGoodsAdapter.showDelete(delete.isChecked());
    }

    //全选
    public void checkAll(View view) {
        int size = stateBoolean.size();
        if (isCheckAll){
            for (int i = 0;i<size;i++){
                stateBoolean.set(i,isCheckAll);
            }
            checkAll.setText(getString(R.string.cancel_all));
            mMyNativeGoodsAdapter.notifyDataSetChanged();
            isCheckAll = false;
        }else {
            for (int i = 0;i<size;i++){
                stateBoolean.set(i,isCheckAll);
            }
            checkAll.setText(getString(R.string.all_check));
            isCheckAll = true;
            mMyNativeGoodsAdapter.notifyDataSetChanged();
        }
    }

    //当删除按钮俺的时候
    public void deleteChecked(View view) {
        final int size = stateBoolean.size();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0;i<size;i++){
                    if (stateBoolean.get(i)){
                        final int finalI = i;
                        Utilsdb.deleteGoods(list.get(i), MyTracesActivity.this, new Callback() {
                            @Override
                            public void sucess() {

                            }
                        });
                    }
                }

                Utilsdb.querryGoods(MyTracesActivity.this, new IDataFronNetCallback<List<MyNativeInfo>>() {
                    @Override
                    public void success(List<MyNativeInfo> myNativeInfos) {
                        list = myNativeInfos;
                        stateBoolean.clear();
                        for (int i = 0;i<size;i++){
                            stateBoolean.add(false);
                        }
                        mMyNativeGoodsAdapter.setBooleanList(stateBoolean);
                        mMyNativeGoodsAdapter.setList(list);
                        mMyNativeGoodsAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void failed(String msg) {

                    }
                });
            }
        }).start();


    }

    //改变全选的功能
    @Override
    public void notifyCheck() {
        for (boolean b:stateBoolean){
            if (!b){
                isCheckAll = true;
                checkAll.setText(getString(R.string.all_check));
                return;
            }
        }
        isCheckAll = false;
        checkAll.setText(getString(R.string.cancel_all));
    }
}
