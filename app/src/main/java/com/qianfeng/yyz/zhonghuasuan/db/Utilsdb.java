package com.qianfeng.yyz.zhonghuasuan.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;

import com.qianfeng.yyz.zhonghuasuan.datacallback.Callback;
import com.qianfeng.yyz.zhonghuasuan.datacallback.IDataFronNetCallback;

import java.util.List;

/**
 * Created by Administrator on 2016/10/21 0021.
 */
public class Utilsdb {

    public static final String NAME = "search";
    public static final String NAME_2 = "goods";
    private static Handler handler;

    public static SearchHistoryDao getDB(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, NAME);
        SQLiteDatabase database = helper.getWritableDatabase();
        DaoMaster master = new DaoMaster(database);
        DaoSession session = master.newSession();
        return session.getSearchHistoryDao();
    }

    public static void saveHistory(SearchHistory searchHistory, SearchHistoryDao dao) {
        if (null != searchHistory){

            List<SearchHistory> list = dao.queryBuilder().where(SearchHistoryDao.Properties.Content.eq(searchHistory.getContent())).build().list();
            if (list==null||list.size()==0){
                dao.insert(searchHistory);
            }else {
                SearchHistory searchHistory1 = list.get(0);
                searchHistory1.setCount(searchHistory1.getCount()+1);
                dao.update(searchHistory1);
            }
        }
    }

    public static void clear(SearchHistoryDao dao){
        dao.deleteAll();
    }

    public static List<SearchHistory> querry(SearchHistoryDao dao){
        return  dao.queryBuilder().orderDesc(SearchHistoryDao.Properties.Id).build().list();
    }

    public static List<SearchHistory> like(SearchHistory searchHistory,SearchHistoryDao dao){
        List<SearchHistory> searchHistories = dao.queryBuilder().where(SearchHistoryDao.Properties.Content.like(searchHistory.getContent()+"%")).build().list();
        return searchHistories;
    }


    private static MyNativeInfoDao getGoodsDB(Context context){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context,NAME_2);
        SQLiteDatabase database = helper.getWritableDatabase();
        DaoMaster master = new DaoMaster(database);
        MyNativeInfoDao dao = master.newSession().getMyNativeInfoDao();
        return dao;
    }

    public static void saveGoods(final MyNativeInfo info, final Context context){
        new Thread(new Runnable() {
            @Override
            public void run() {
                final MyNativeInfoDao dao = getGoodsDB(context);
                List<MyNativeInfo> list = dao.queryBuilder().where(MyNativeInfoDao.Properties.Title.eq(info.getTitle())).build().list();
                if (list.size()<=0){
                    dao.save(info);
                }else {
                    deleteGoods(list.get(0), context, new Callback() {
                        @Override
                        public void sucess() {
                            dao.save(info);
                        }
                    });
                }
            }
        }).start();


    }

    public static void updataGoods(MyNativeInfo info){
        //nothing
    }

    public static void querryGoods(final Context context,final IDataFronNetCallback<List<MyNativeInfo>> callback){
        if (handler==null){
            handler = new Handler();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                final MyNativeInfoDao dao = getGoodsDB(context);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.success(dao.queryBuilder().orderDesc().build().list());
                    }
                });

            }
        }).start();
    }

    public static void deleteGoods(final MyNativeInfo info, final Context context, final Callback callback){

                MyNativeInfoDao dao = getGoodsDB(context);
                dao.delete(info);
                callback.sucess();
    }
}
