package com.hnxy.farmshop.bean;

import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/7/26.
 *
 */

public class Goods extends BaseBean {

    /**
     * money_real : 2999.00
     * money_original : 4599.00
     * money_freight : 0.0
     * product_id : 1
     * product_name : 洗衣机
     * product_logo : uploads/20170720/2a860edddb09cc6d8d2f2ab3754c897f.jpg
     * product_mark : XYJ4599
     * product_num : 1
     * product_sellnum : 0
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }
    public static class DataBean{
        private String money_real;
        private String money_original;
        private String money_freight;
        private long product_id;
        private String product_name;
        private String product_logo;
        private String product_mark;
        private int product_num;
        private int product_sellnum;
        public String getMoney_real() {
            return money_real;
        }

        public void setMoney_real(String money_real) {
            this.money_real = money_real;
        }

        public String getMoney_original() {
            return money_original;
        }

        public void setMoney_original(String money_original) {
            this.money_original = money_original;
        }

        public String getMoney_freight() {
            return money_freight;
        }

        public void setMoney_freight(String money_freight) {
            this.money_freight = money_freight;
        }

        public long getProduct_id() {
            return product_id;
        }

        public void setProduct_id(long product_id) {
            this.product_id = product_id;
        }

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public String getProduct_logo() {
            return product_logo;
        }

        public void setProduct_logo(String product_logo) {
            this.product_logo = product_logo;
        }

        public String getProduct_mark() {
            return product_mark;
        }

        public void setProduct_mark(String product_mark) {
            this.product_mark = product_mark;
        }

        public int getProduct_num() {
            return product_num;
        }

        public void setProduct_num(int product_num) {
            this.product_num = product_num;
        }

        public int getProduct_sellnum() {
            return product_sellnum;
        }

        public void setProduct_sellnum(int product_sellnum) {
            this.product_sellnum = product_sellnum;
        }
    }
}
