package com.qianfeng.yyz.zhonghuasuan.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by Administrator on 2016/10/21 0021.
 */
public class Utilsdb {

    public static final String NAME = "search";


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
}
