package com.hnxy.farmshop.gen;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class GoodsBean {
    private String money_real;
    private String money_original;
    private String money_freight;
    @Id private long product_id;
    private String product_name;
    private String product_logo;
    private String product_mark;
    private int product_num;
    private int product_sellnum;
    public int getProduct_sellnum() {
        return this.product_sellnum;
    }
    public void setProduct_sellnum(int product_sellnum) {
        this.product_sellnum = product_sellnum;
    }
    public int getProduct_num() {
        return this.product_num;
    }
    public void setProduct_num(int product_num) {
        this.product_num = product_num;
    }
    public String getProduct_mark() {
        return this.product_mark;
    }
    public void setProduct_mark(String product_mark) {
        this.product_mark = product_mark;
    }
    public String getProduct_logo() {
        return this.product_logo;
    }
    public void setProduct_logo(String product_logo) {
        this.product_logo = product_logo;
    }
    public String getProduct_name() {
        return this.product_name;
    }
    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }
    public long getProduct_id() {
        return this.product_id;
    }
    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }
    public String getMoney_freight() {
        return this.money_freight;
    }
    public void setMoney_freight(String money_freight) {
        this.money_freight = money_freight;
    }
    public String getMoney_original() {
        return this.money_original;
    }
    public void setMoney_original(String money_original) {
        this.money_original = money_original;
    }
    public String getMoney_real() {
        return this.money_real;
    }
    public void setMoney_real(String money_real) {
        this.money_real = money_real;
    }
    @Generated(hash = 1070765961)
    public GoodsBean(String money_real, String money_original,
            String money_freight, long product_id, String product_name,
            String product_logo, String product_mark, int product_num,
            int product_sellnum) {
        this.money_real = money_real;
        this.money_original = money_original;
        this.money_freight = money_freight;
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_logo = product_logo;
        this.product_mark = product_mark;
        this.product_num = product_num;
        this.product_sellnum = product_sellnum;
    }
    @Generated(hash = 1806305570)
    public GoodsBean() {
    }
}
