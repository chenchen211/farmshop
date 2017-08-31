package com.hnxy.farmshop.presenter;

import android.util.Log;

import com.hnxy.farmshop.bean.City;
import com.hnxy.farmshop.bean.Province;
import com.hnxy.farmshop.contract.AddressContract;
import com.hnxy.farmshop.http.HttpResult;
import com.hnxy.farmshop.model.AddressModel;

import java.util.Map;

import retrofit2.Call;

/**
 * Created by Administrator on 2017/7/25.
 */

public class AddressPresenter implements AddressContract.Presenter {
    private static final String TAG = "address";
    private AddressContract.Model model;
    private AddressContract.View view;

    public AddressPresenter(AddressContract.View view) {
        this.view = view;
        model = new AddressModel();
    }

    @Override
    public void getProvince() {
        model.getProvince(new HttpResult<Province>() {
            @Override
            public void response(Call<Province> call, Province province) {
                if(null != province){
                    Log.i(TAG, "response: "+province.toString());
                    view.showContent(province.toString());
                }
            }
        });
    }

    @Override
    public void getCity(int parent_id) {
        model.getCity(parent_id, new HttpResult<City>() {
            @Override
            public void response(Call<City> call, City city) {

            }
        });
    }

    @Override
    public void getTown(int parent_id) {

    }

    @Override
    public void getList(String token) {

    }

    @Override
    public void add(String token, Map<String, String> map) {

    }

    @Override
    public void edit(String token, Map<String, String> map) {

    }

    @Override
    public void del(String token, int id) {

    }
}
