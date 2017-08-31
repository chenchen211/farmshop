package com.hnxy.farmshop.contract;

import com.hnxy.farmshop.bean.Token;
import com.hnxy.farmshop.http.HttpResult;

/**
 * Created by Administrator on 2017/7/25.
 */

public interface LoginContract {
    interface Model {
        void login(String username, String password, HttpResult<Token> result);
    }

    interface View {
        void loginSuccess();
        void loginFail(String error);
    }

    interface Presenter {
        void login(String username, String password);
    }
}
