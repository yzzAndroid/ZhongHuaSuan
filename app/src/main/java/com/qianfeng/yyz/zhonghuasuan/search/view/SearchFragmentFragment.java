package com.qianfeng.yyz.zhonghuasuan.search.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.internal.MyLoadingLayout;
import com.qianfeng.yyz.zhonghuasuan.R;
import com.qianfeng.yyz.zhonghuasuan.adapter.HotGridViewAdapter;
import com.qianfeng.yyz.zhonghuasuan.adapter.RecentSearchAdapter;
import com.qianfeng.yyz.zhonghuasuan.apublic.MyApi;
import com.qianfeng.yyz.zhonghuasuan.apublic.MyLinerLayout;
import com.qianfeng.yyz.zhonghuasuan.bean.HotKeyWord;
import com.qianfeng.yyz.zhonghuasuan.classify_second.view.ClassifySecondActivity;
import com.qianfeng.yyz.zhonghuasuan.db.SearchHistory;
import com.qianfeng.yyz.zhonghuasuan.search.presenter.ISearchPresenter;
import com.qianfeng.yyz.zhonghuasuan.search.presenter.SearchPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragmentFragment extends Fragment implements ISearchFView{


    ISearchPresenter mSearchPresenter;
    List<SearchHistory> mHistories;

    @BindView(R.id.search_search_et)
    EditText editText;

    @BindView(R.id.search_tv_search)
    TextView searchTv;

    @BindView(R.id.search_ll_clear_history)
    LinearLayout clear;

    @BindView(R.id.search_lv)
    ListView listView;

    @BindView(R.id.mysearch_native)
    MyLinerLayout nativel;

    @BindView(R.id.mysearch_net)
    MyLinerLayout netl;

    private MyLinerLayout.MyLinerAdapter adapterNative;
    private MyLinerLayout.MyLinerAdapter adapterNet;



    private ArrayAdapter<String> mArrayAdapter;
    private List<String> searing;

    public SearchFragmentFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this,view);
        initView();
        initDatas();
        //网络搜索热门
        mSearchPresenter.enterSearch(this,getActivity());
        //本地搜索历史
        mSearchPresenter.getSearchHistories();
        return view;


    }

    private void initDatas() {
        mSearchPresenter = SearchPresenterImpl.getInstance();
        //mHistories = mSearchPresenter.getSearchHistories("");
    }

    private void initView() {

        adapterNative = nativel.getMyLinerAdapter();
        adapterNet = netl.getMyLinerAdapter();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)){
                    listView.setVisibility(View.GONE);
                }else {

                    SearchHistory searchHistory = new SearchHistory(String.valueOf(s),0);
                    mSearchPresenter.getSearchLike(searchHistory);
                    //网络(搜索)
                    mSearchPresenter.searching(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        searchTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchHistory searchHistory = new SearchHistory(editText.getText().toString(),0);
                mSearchPresenter.saveSearchHistory(searchHistory);
                //刷新历史记录
                mSearchPresenter.getSearchHistories();
                //网络搜索
                mSearchPresenter.searching(editText.getText().toString());
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchPresenter.clearSearchHistory();
                adapterNative.setList(null);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //搜索
                editText.setText(searing.get(position));
                listView.setVisibility(View.GONE);
                //保存
                mSearchPresenter.saveSearchHistory(new SearchHistory(searing.get(position),0));
                //跳转
                changeActivity(searing.get(position));
            }
        });


        nativel.setOnItemClickListener(new MyLinerLayout.Listener() {
            @Override
            public void click(View view) {
                //跳转
               changeActivity(view);
                //保存
                mSearchPresenter.saveSearchHistory(new SearchHistory(((TextView)view).getText().toString(),0));
                //刷新历史记录
                mSearchPresenter.getSearchHistories();
            }
        });

        netl.setOnItemClickListener(new MyLinerLayout.Listener() {
            @Override
            public void click(View view) {
                //跳转
                changeActivity(view);
                mSearchPresenter.saveSearchHistory(new SearchHistory(((TextView)view).getText().toString(),0));
                //刷新历史记录
                mSearchPresenter.getSearchHistories();
            }
        });
    }

    public void changeActivity(View view){
        Intent intent = new Intent(getActivity(),ClassifySecondActivity.class);
        String search_key = ((TextView)view).getText().toString();
        intent.putExtra("isSearching",true);
        intent.putExtra(MyApi.CATEGORY_ID,"0");
        intent.putExtra(MyApi.SEARCH_KEY,search_key);
        intent.putExtra("name",search_key);
        getActivity().startActivity(intent);
    }

    public void changeActivity(String search_key){
        Intent intent = new Intent(getActivity(),ClassifySecondActivity.class);
        intent.putExtra("isSearching",true);
        intent.putExtra(MyApi.CATEGORY_ID,"0");
        intent.putExtra(MyApi.SEARCH_KEY,search_key);
        intent.putExtra("name",search_key);
        getActivity().startActivity(intent);
    }




    @Override
    public void getNativeBean(SearchHistory searchHistory) {

    }

    @Override
    public void getSearchFromNet(HotKeyWord hotKeyWord) {
        //填充数据
        Log.e("=========","============"+hotKeyWord);
        if (hotKeyWord==null){
            return;
        }
        List<SearchHistory> list = new ArrayList<>();
          for (int i = 0;i<hotKeyWord.getData().size();i++){
              list.add(new SearchHistory(hotKeyWord.getData().get(i).getKeyword(),0));
          }
          adapterNet.setList(list);
    }

    @Override
    public void showError(String msg) {
        Log.e("=======","========="+msg);
    }

    @Override
    public void initNativeSearch(List<SearchHistory> list) {
        if (list==null||list.size()<=5){
            adapterNative.setList(list);
        }else {
            List<SearchHistory> list1 = new ArrayList<>();
            for (int i = 0;i<5;i++){
                list1.add(list.get(i));
            }
            adapterNative.setList(list1);
        }

    }

    @Override
    public void initNativeSearchingNative(List<SearchHistory> list) {

    }

    @Override
    public void refreshHistory() {
        mSearchPresenter.getSearchHistories();
    }

    @Override
    public void initSearching(List<String> list) {
        searing = list;
        listView.setVisibility(View.VISIBLE);
        if (list==null){
            return;
        }
        mArrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.textview,R.id.search_tv,list);
        listView.setAdapter(mArrayAdapter);
    }
}
