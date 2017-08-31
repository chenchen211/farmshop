package com.hnxy.farmshop;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.hnxy.farmshop.gen.DaoMaster;
import com.hnxy.farmshop.gen.DaoSession;
import com.hnxy.farmshop.http.HttpHelper;
import com.hnxy.farmshop.http.HttpService;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * Created by chenchen on 2017/3/22.
 * 多种类库集合
 */
public class MyApp extends Application {
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    public static MyApp instances;

    private Context context;
    private HttpService service;
    private ImageOptions options;
    private RefWatcher mRefWatcher;
    private String token;
    private final String SP_NAME ="farmshop";
    private final String APP_KEY="mYMSTSdR7mCeLGXGlYZ4UoxclRDqCen6";

    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        instances =this;
        context=getApplicationContext();
        sharedPreferences = getSharedPreferences(SP_NAME,MODE_PRIVATE);
        //greenDao数据库
        setDatabase();

        //内存泄漏监测
        mRefWatcher = LeakCanary.install(this);

        //初始化xutils
        x.Ext.init(this);
        x.Ext.setDebug(false);
        options = new ImageOptions.Builder()
                .setLoadingDrawableId(R.mipmap.loading)
                .setFailureDrawableId(R.mipmap.loading)
                .build();

        //retrofit
        service = HttpHelper.getInstance().getService();

        //百度云推送
        PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY,APP_KEY);
    }

    /**
     * 设置greenDao
     * 说明：通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
     *      可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
     * 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
     *      所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
     */
    private void setDatabase() {

        mHelper = new DaoMaster.DevOpenHelper(this, "shop-db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }
    public DaoSession getDaoSession() {
        return mDaoSession;
    }
    public SQLiteDatabase getDb() {
        return db;
    }

    public Context getContext() {
        return context;
    }

    public HttpService getService(){
        return service;
    }

    public ImageOptions getOptions() {
        return options;
    }

    public RefWatcher getmRefWatcher() {
        return mRefWatcher;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        instances.token = token;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }
}
