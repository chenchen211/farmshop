package com.hnxy.farmshop.contract;

import com.hnxy.farmshop.bean.Goods;
import com.hnxy.farmshop.http.HttpResult;

/**
 * Created by Administrator on 2017/7/26.
 */

public interface MainContract {
    interface Model {
        void search(int page,String search, HttpResult<Goods> result);
        void getList(int page, HttpResult<Goods> result);
    }

    interface View {
        void showGoods(Goods goods);
    }

    interface Presenter {
        void search(int page,String search);
        void getList(int page);
    }
}
