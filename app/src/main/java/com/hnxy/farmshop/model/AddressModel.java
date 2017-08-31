package com.hnxy.farmshop.model;

import com.hnxy.farmshop.bean.BaseBean;
import com.hnxy.farmshop.bean.City;
import com.hnxy.farmshop.bean.Province;
import com.hnxy.farmshop.bean.Town;
import com.hnxy.farmshop.contract.AddressContract;
import com.hnxy.farmshop.http.HttpResult;

import java.util.Map;

/**
 * Created by Administrator on 2017/7/25.
 */

public class AddressModel extends BaseModel implements AddressContract.Model {

    @Override
    public void getProvince(HttpResult<Province> result) {
        service.getProvince().enqueue(result);
    }

    @Override
    public void getCity(int parent_id, HttpResult<City> result) {
        service.getCity(parent_id).enqueue(result);
    }

    @Override
    public void getTown(int parent_id, HttpResult<Town> result) {
        service.getTown(parent_id).enqueue(result);
    }

    @Override
    public void getList(String token, HttpResult<BaseBean> result) {
        service.getAddressList(token).enqueue(result);
    }

    @Override
    public void add(String token, Map<String, String> map, HttpResult<BaseBean> result) {
        service.addAddress(token,map).enqueue(result);
    }

    @Override
    public void edit(String token, Map<String, String> map, HttpResult<BaseBean> result) {
        service.editAddress(token,map).enqueue(result);
    }

    @Override
    public void del(String token, int id, HttpResult<BaseBean> result) {
        service.delAddress(token, id).enqueue(result);
    }
}
