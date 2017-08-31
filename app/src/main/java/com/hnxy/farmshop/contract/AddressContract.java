package com.hnxy.farmshop.contract;

import com.hnxy.farmshop.bean.BaseBean;
import com.hnxy.farmshop.bean.City;
import com.hnxy.farmshop.bean.Province;
import com.hnxy.farmshop.bean.Town;
import com.hnxy.farmshop.http.HttpResult;

import java.util.Map;

/**
 * Created by Administrator on 2017/7/25.
 */

public interface AddressContract {
    interface Model {
        void getProvince(HttpResult<Province> result);
        void getCity(int parent_id, HttpResult<City> result);
        void getTown(int parent_id, HttpResult<Town> result);
        void getList(String token, HttpResult<BaseBean> result);
        void add(String token, Map<String,String> map, HttpResult<BaseBean> result);
        void edit(String token, Map<String,String> map, HttpResult<BaseBean> result);
        void del(String token,int id,HttpResult<BaseBean> result);
    }

    interface View {
        void showContent(String content);
        void tip(String tip);
    }

    interface Presenter {
        void getProvince();
        void getCity(int parent_id);
        void getTown(int parent_id);
        void getList(String token);
        void add(String token, Map<String,String> map);
        void edit(String token, Map<String,String> map);
        void del(String token,int id);
    }
}
