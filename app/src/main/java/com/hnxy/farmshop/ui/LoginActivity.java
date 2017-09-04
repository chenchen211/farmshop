package com.hnxy.farmshop.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.dd.CircularProgressButton;
import com.hnxy.farmshop.MyApp;
import com.hnxy.farmshop.R;
import com.hnxy.farmshop.contract.LoginContract;
import com.hnxy.farmshop.presenter.LoginPresenter;
import com.hnxy.farmshop.util.SPUtils;

import org.xutils.common.util.MD5;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity implements LoginContract.View {
    @ViewInject(R.id.et_username)
    EditText etUsername;

    @ViewInject(R.id.et_password)
    EditText etPassword;

    @ViewInject(R.id.btn_login)
    CircularProgressButton btnLogin;

    private LoginContract.Presenter presenter;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("登录商城");
        presenter = new LoginPresenter(this);
        username = sharedPreferences.getString("username",null);
        if( !TextUtils.isEmpty(username)){
            etUsername.setText(username);
            etPassword.requestFocus();
        }
    }
    @Event({R.id.btn_login,R.id.chart})
    private void onClick(View view){
        switch (view.getId()){
            case R.id.btn_login:
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();
                login(username,password);
                break;
            case R.id.chart:
                changeActivity(ChartActivity.class,false);
                break;
        }
    }

    private void login(String username, String password) {
        if(TextUtils.isEmpty(username)){
            return;
        }else if(TextUtils.isEmpty(password)){
            return;
        }
        btnLogin.setClickable(false);
        password = MD5.md5(password).toLowerCase();
        btnLogin.setIndeterminateProgressMode(true);
        btnLogin.setProgress(50);
        presenter.login(username,password);
    }

    @Override
    public void loginSuccess() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username",username);
        editor.commit();
        btnLogin.setProgress(100);
        new Handler().postDelayed(new Runnable(){
            public void run() {
                changeActivity(MainActivity.class,true);
            }
        }, 1000);
    }

    @Override
    public void loginFail(String error) {
        btnLogin.setErrorText(error);
        btnLogin.setProgress(-1);
        btnLogin.setClickable(true);
        new Handler().postDelayed(new Runnable(){
            public void run() {
                btnLogin.setProgress(0);
            }
        }, 2000);
    }
}
