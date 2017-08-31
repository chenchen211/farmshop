package com.hnxy.farmshop.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.hnxy.farmshop.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_search)
public class SearchActivity extends BaseActivity{

    @ViewInject(R.id.tv_title)
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setIcon(R.mipmap.search_white,false);
        setIcon(R.mipmap.back,true);
        editText.requestFocus();
    }

    @Event({R.id.iv_right,R.id.iv_left})
    private void onClick(View view){
        switch (view.getId()){
            case R.id.iv_right:
                String search = editText.getText().toString();
                if(TextUtils.isEmpty(search)) return;
                Intent intent = new Intent(this,SearchResultActivity.class);
                intent.putExtra("search",search);
                changeActivity(intent,false);
                break;
            case R.id.iv_left:
                finish();
                break;
        }
    }
}
