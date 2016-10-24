package com.qianfeng.yyz.zhonghuasuan.search.model;

import android.content.Context;
import android.os.Handler;

import com.handmark.pulltorefresh.library.internal.Utils;
import com.qianfeng.yyz.zhonghuasuan.apublic.MyApi;
import com.qianfeng.yyz.zhonghuasuan.bean.HotKeyWord;
import com.qianfeng.yyz.zhonghuasuan.datacallback.IDataFronNetCallback;
import com.qianfeng.yyz.zhonghuasuan.db.SearchHistory;
import com.qianfeng.yyz.zhonghuasuan.db.Utilsdb;
import com.qianfeng.yyz.zhonghuasuan.home.model.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/19.
 */
public class SearchModelImpl implements ISearchModel {
    private HttpServiceHotKeyWord mHttpServiceHotKeyWord;
    private Handler handler;


    public SearchModelImpl() {
        handler = new Handler();
    }

    @Override
    public boolean getOSNetInformation() {
        return false;
    }

    @Override
    public void getSearchNativeData(final Context context, final DBClllback clllback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<SearchHistory> list = Utilsdb.querry(Utilsdb.getDB(context));
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        clllback.success(list);
                    }
                });
            }
        }).start();



    }

    @Override
    public void saveHistory(final SearchHistory history, final Context context, final DBClllback clllback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Utilsdb.saveHistory(history,Utilsdb.getDB(context));
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        clllback.success();
                    }
                });
            }
        }).start();

    }

    @Override
    public void getLike(final SearchHistory searchHistory, final Context context, final DBClllback clllback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<SearchHistory> list = Utilsdb.like(searchHistory,Utilsdb.getDB(context));
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        clllback.success(list);
                    }
                });
            }
        }).start();


    }

    @Override
    public void clearhistory(final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Utilsdb.clear(Utilsdb.getDB(context));
            }
        }).start();

    }

    //搜索
    @Override
    public void searching(final IDataFronNetCallback<List<String>> callback, String key) {
        OkHttpClient client = new OkHttpClient();
        Request.Builder request = new Request.Builder();
        FormBody.Builder body = new FormBody.Builder();
        body.add(MyApi.UID,"-1");
        body.add(MyApi.VERSION,"3003201");
        body.add(MyApi.CLIENT_TYPE,"3");
        body.add(MyApi.KEYWORD,key);

        request.url(MyApi.BASE_URL+MyApi.Search.SEARCHING)
                .post(body.build());
        client.newCall(request.build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.success(null);
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                final List<String> list1 = new ArrayList<String>();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray array = jsonObject.getJSONArray("data");
                    if (null==array){
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                callback.success(list1);
                            }
                        });

                        return;
                    }
                    int size = array.length();
                    for (int i = 0; i<size;i++){
                        String result = array.getJSONObject(i).getString(MyApi.KEYWORD);
                        list1.add(result);
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.success(list1);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void getKeyWordDataFromNet( final IDataFronNetCallback<HotKeyWord> callback) {
        mHttpServiceHotKeyWord = HttpUtils.getHttServiceAppIndex(HttpServiceHotKeyWord.class);
      mHttpServiceHotKeyWord.postKeyWord("-1","3","3003201","10")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<HotKeyWord>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.failed(e.getMessage());
                    }

                    @Override
                    public void onNext(HotKeyWord hotKeyWord) {
                        callback.success(hotKeyWord);
                    }
                });
    }
}
