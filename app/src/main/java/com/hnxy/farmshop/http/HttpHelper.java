package com.hnxy.farmshop.http;

import com.hnxy.farmshop.MyApp;
import com.hnxy.farmshop.http.converter.Base64ConverterFactory;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

public class HttpHelper {
    private static HttpHelper instance;
    private HttpService service;

    private HttpHelper(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Interceptor interceptorUA = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder().addHeader("User-Agent","farm/android 1.0").build();
                return chain.proceed(newRequest);
            }
        };

        CookieManager cookieManager = new CookieManager(new InDiskCookieStore(MyApp.instances.getContext()), CookiePolicy.ACCEPT_ALL);

        JavaNetCookieJar cookieJar = new JavaNetCookieJar(cookieManager);

        OkHttpClient client = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .addInterceptor(interceptor)
                .addInterceptor(interceptorUA)
                .retryOnConnectionFailure(true)
                .connectTimeout(30, TimeUnit.SECONDS)
                .addNetworkInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(Base64ConverterFactory.create())//解析方法
                //这里建议：- Base URL: 总是以/结尾；- @Url: 不要以/开头
                .baseUrl(HttpService.BASE_URL)
                .build();
        service=retrofit.create(HttpService.class);
    }

    public static HttpHelper getInstance(){
        if(instance==null){
            instance=new HttpHelper();
        }
        return instance;
    }

    public HttpService getService() {
        return service;
    }
}
