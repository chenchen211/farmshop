package com.hnxy.farmshop.gen;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/7/27.
 */
@Entity
public class Search {
    @Id private long id;

    private String name;

    private long time;

    public long getTime() {
        return this.time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Generated(hash = 1473376805)
    public Search(long id, String name, long time) {
        this.id = id;
        this.name = name;
        this.time = time;
    }

    @Generated(hash = 1644193961)
    public Search() {
    } 
}
