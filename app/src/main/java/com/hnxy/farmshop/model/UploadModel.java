package com.hnxy.farmshop.model;

import com.hnxy.farmshop.bean.BaseBean;
import com.hnxy.farmshop.contract.UploadContract;
import com.hnxy.farmshop.http.HttpResult;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/7/25.
 * 上传图片的model
 */

public class UploadModel extends BaseModel  implements UploadContract.Model {

    @Override
    public void upload(String filePath, HttpResult<BaseBean> result) {
        File file = new File(filePath);//filePath 图片地址
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);//表单类型;
        RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        builder.addFormDataPart("uploadedfile", file.getName(),imageBody);//imgfile 后台接收图片流的参数名
        List<MultipartBody.Part> parts = builder.build().parts();
        service.upload(parts).enqueue(result);
    }
}
