package com.hnxy.farmshop.presenter;

import com.hnxy.farmshop.MyApp;
import com.hnxy.farmshop.bean.Token;
import com.hnxy.farmshop.contract.LoginContract;
import com.hnxy.farmshop.http.HttpResult;
import com.hnxy.farmshop.model.LoginModel;

import retrofit2.Call;

/**
 * Created by Administrator on 2017/7/25.
 */

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.Model model;
    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        this.model = new LoginModel();
    }

    @Override
    public void login(final String username, final String password) {
        model.login(username, password, new HttpResult<Token>() {
            @Override
            public void response(Call<Token> call, Token baseBean) {
                if(null != baseBean){
                    if(baseBean.getCode()==1){
                        MyApp.instances.setToken(baseBean.getToken());
                        view.loginSuccess();
                    }else{
                        view.loginFail(baseBean.getMsg());
                    }
                }
            }
        });
    }
}
