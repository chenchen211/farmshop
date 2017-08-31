package com.hnxy.farmshop.presenter;

import com.hnxy.farmshop.bean.BaseBean;
import com.hnxy.farmshop.contract.UploadContract;
import com.hnxy.farmshop.http.HttpResult;
import com.hnxy.farmshop.model.UploadModel;

import retrofit2.Call;

/**
 * Created by Administrator on 2017/7/25.
 * 上传图片的主导器
 */

public class UploadPresenter implements UploadContract.Presenter {
    private UploadContract.Model model;
    private UploadContract.View view;

    public UploadPresenter(UploadContract.View view) {
        this.view = view;
        this.model = new UploadModel();
    }

    @Override
    public void upload(String filePath) {
        model.upload(filePath, new HttpResult<BaseBean>() {
            @Override
            public void response(Call<BaseBean> call, BaseBean baseBean) {
                if(baseBean.getCode()==1){
                    view.tip("图片上传成功");
                }else{
                    view.tip(baseBean.getMsg());
                }
            }
        });
    }
}
