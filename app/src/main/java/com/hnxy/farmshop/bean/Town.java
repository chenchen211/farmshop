package com.hnxy.farmshop.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/25.
 */

public class Town extends BaseBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2472
         * town : 乌审旗
         */

        private int id;
        private String town;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTown() {
            return town;
        }

        public void setTown(String town) {
            this.town = town;
        }
    }
}
