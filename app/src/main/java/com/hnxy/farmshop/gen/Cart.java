package com.hnxy.farmshop.gen;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/7/27.
 */
@Entity
public class Cart {
    @Id private long id;
    private String product_id;
    private String product_name;
    private String product_logo;
    private String product_price;
    public String getProduct_price() {
        return this.product_price;
    }
    public void setProduct_price(String product_price) {
        this.product_price = product_price;
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
    public String getProduct_id() {
        return this.product_id;
    }
    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    @Generated(hash = 1387975609)
    public Cart(long id, String product_id, String product_name,
            String product_logo, String product_price) {
        this.id = id;
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_logo = product_logo;
        this.product_price = product_price;
    }
    @Generated(hash = 1029823171)
    public Cart() {
    }
}
