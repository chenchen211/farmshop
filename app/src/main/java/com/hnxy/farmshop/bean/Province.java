package com.hnxy.farmshop.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/25.
 */

public class Province extends BaseBean {

    /**
     * id : 3262
     * privince : 重庆
     * pinyin : chongqing
     * abbre : C
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
        private String privince;
        private String pinyin;
        private String abbre;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPrivince() {
            return privince;
        }

        public void setPrivince(String privince) {
            this.privince = privince;
        }

        public String getPinyin() {
            return pinyin;
        }

        public void setPinyin(String pinyin) {
            this.pinyin = pinyin;
        }

        public String getAbbre() {
            return abbre;
        }

        public void setAbbre(String abbre) {
            this.abbre = abbre;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", privince='" + privince + '\'' +
                    ", pinyin='" + pinyin + '\'' +
                    ", abbre='" + abbre + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Province{" +
                "data=" + data +
                '}';
    }
}
