package com.hnxy.farmshop.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hnxy.farmshop.MyApp;
import com.hnxy.farmshop.R;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by chenchen on 2017/3/23.
 */

public class BaseActivity extends AppCompatActivity{
    @ViewInject(R.id.iv_left)
    ImageView left;
    @ViewInject(R.id.iv_right)
    ImageView right;
    @ViewInject(R.id.tv_title)
    TextView title;
    protected MyApp myApp;
    protected SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        myApp = MyApp.instances;
        sharedPreferences = myApp.getSharedPreferences();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myApp.getmRefWatcher().watch(this);
    }

    protected String getToken(){
        return myApp.getToken();
    }
    protected void setTitle(String title){
        if(!TextUtils.isEmpty(title)){
            this.title.setText(title);
        }
    }
    protected void setIcon(@DrawableRes int resId, boolean isLeft){
        if(isLeft){
            showLeft();
            left.setImageResource(resId);
        }else {
            showRight();
            right.setImageResource(resId);
        }
    }

    protected void showLeft(){
        if(left.getVisibility() != View.VISIBLE){
            left.setVisibility(View.VISIBLE);
        }
    }
    protected void showRight(){
        if(right.getVisibility() != View.VISIBLE){
            right.setVisibility(View.VISIBLE);
        }
    }

    protected void changeActivity(Class<?> activity,boolean isFinish){
        Intent intent = new Intent(this,activity);
        startActivity(intent);
        if(isFinish){
            finish();
        }
    }
    protected void changeActivity(Intent intent,boolean isFinish){
        startActivity(intent);
        if(isFinish){
            finish();
        }
    }

    public void tip(String tip) {
        Toast.makeText(this,tip, Toast.LENGTH_SHORT).show();
    }
}
