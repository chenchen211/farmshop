package com.hnxy.farmshop.model;

import com.hnxy.farmshop.bean.Goods;
import com.hnxy.farmshop.contract.MainContract;
import com.hnxy.farmshop.http.HttpResult;

/**
 * Created by Administrator on 2017/7/26.
 */

public class MainModel extends BaseModel implements MainContract.Model {

    @Override
    public void search(int page, String search, HttpResult<Goods> result) {
        service.getSearchList(page, search).enqueue(result);
    }

    @Override
    public void getList(int page, HttpResult<Goods> result) {
        service.getGoodsList(page).enqueue(result);
    }
}
