package com.qianfeng.yyz.zhonghuasuan.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2016/10/19.
 */

@Entity
public class SearchHistory {
    @Id(autoincrement = true)
    private Long id;
    private String content;
    private int count;
    @Generated(hash = 84967768)
    public SearchHistory(Long id, String content, int count) {
        this.id = id;
        this.content = content;
        this.count = count;
    }
    @Generated(hash = 1905904755)
    public SearchHistory() {
    }

    public SearchHistory(String content, int count) {
        this.content = content;
        this.count = count;
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getCount() {
        return this.count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "SearchHistory{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", count=" + count +
                '}';
    }
}
