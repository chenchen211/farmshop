package com.hnxy.farmshop.model;

import com.hnxy.farmshop.http.HttpHelper;
import com.hnxy.farmshop.http.HttpService;

/**
 * Created by Administrator on 2017/7/26.
 */

public class BaseModel {
    protected HttpService service;

    public BaseModel() {
        this.service = HttpHelper.getInstance().getService();
    }
}
