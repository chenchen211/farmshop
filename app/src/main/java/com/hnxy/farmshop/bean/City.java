package com.hnxy.farmshop.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/25.
 */

public class City extends BaseBean {

    /**
     * id : 2469
     * city : 鄂尔多斯
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private int id;
        private String city;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }
}
