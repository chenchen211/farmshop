package com.hnxy.farmshop.http;

import com.hnxy.farmshop.bean.BaseBean;
import com.hnxy.farmshop.bean.City;
import com.hnxy.farmshop.bean.Goods;
import com.hnxy.farmshop.bean.Province;
import com.hnxy.farmshop.bean.Token;
import com.hnxy.farmshop.bean.Town;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/7/24.
 * Api接口
 */

public interface HttpService {

    String BASE_URL = "http://192.168.0.113:8888/api/";
    String IMG_URL = "http://192.168.0.113:8888/";

    /**
     * 登录
     * @return 登录结果
     */
    @FormUrlEncoded
    @POST("login/index")
    Call<Token> login(@FieldMap Map<String,String> map);

    @GET("product/index/page/{page}")
    Call<Goods> getGoodsList(@Path("page") int page);

    @GET("product/index/page/{page}/search/{search}")
    Call<Goods> getSearchList(@Path("page") int page, @Path("search") String search);

    /**
     * 上传图片
     * @param partList  MultipartBody.Part
     * @return 上传结果
     */
    @Multipart
    @POST("upload/index")
    Call<BaseBean> upload(@Part List<MultipartBody.Part> partList);

    @GET("extras/getprovince")
    Call<Province> getProvince();

    @GET("extras/getcity/parent_id/{parent_id}")
    Call<City> getCity(@Path("parent_id") int parent_id);

    @GET("extras/gettown/parent_id/{parent_id}")
    Call<Town> getTown(@Path("parent_id") int parent_id);

    @FormUrlEncoded
    @POST("address/add/token/{token}")
    Call<BaseBean> addAddress(@Path("token") String token, @FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("address/edit/token/{token}")
    Call<BaseBean> editAddress(@Path("token") String token, @FieldMap Map<String,String> map);


    @GET("address/del/token/{token}")
    Call<BaseBean> delAddress(@Path("token") String token, @Query("id") int id);

    @GET("address/index/token/{token}")
    Call<BaseBean> getAddressList(@Path("token") String token);


}
