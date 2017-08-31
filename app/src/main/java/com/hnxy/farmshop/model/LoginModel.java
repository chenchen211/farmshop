package com.hnxy.farmshop.model;

import com.hnxy.farmshop.bean.Token;
import com.hnxy.farmshop.contract.LoginContract;
import com.hnxy.farmshop.http.HttpResult;
import com.hnxy.farmshop.encrypt.RSASignature;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/25.
 */

public class LoginModel extends BaseModel  implements LoginContract.Model {

    @Override
    public void login(String username, String password, HttpResult<Token> result) {
        Map<String, String> map = new HashMap<>();
        map.put("username",username);
        map.put("pwd",password);
        map.put("sign", RSASignature.sign(map));
        service.login(map).enqueue(result);
    }
}
