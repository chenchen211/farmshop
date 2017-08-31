package com.hnxy.farmshop.presenter;

import com.hnxy.farmshop.bean.Goods;
import com.hnxy.farmshop.contract.MainContract;
import com.hnxy.farmshop.model.MainModel;
import com.hnxy.farmshop.http.HttpResult;

import retrofit2.Call;

/**
 * Created by Administrator on 2017/7/26.
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.Model model;
    private MainContract.View view;

    public MainPresenter(MainContract.View view) {
        this.view = view;
        model = new MainModel();
    }

    @Override
    public void search(int page, String search) {
        model.search(page, search, new HttpResult<Goods>() {
            @Override
            public void response(Call<Goods> call, Goods goods) {
                view.showGoods(goods);
            }
        });
    }

    @Override
    public void getList(int page) {
        model.getList(page, new HttpResult<Goods>() {
            @Override
            public void response(Call<Goods> call, Goods goods) {
                view.showGoods(goods);
            }
        });
    }
}
