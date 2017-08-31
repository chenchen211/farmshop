package com.hnxy.farmshop.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.chenchen.utils.DensityUtils;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.view.CommonHeader;
import com.hnxy.farmshop.R;
import com.hnxy.farmshop.bean.Classify;
import com.hnxy.farmshop.bean.Goods;
import com.hnxy.farmshop.contract.MainContract;
import com.hnxy.farmshop.http.HttpService;
import com.hnxy.farmshop.presenter.MainPresenter;
import com.squareup.picasso.Picasso;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements MainContract.View{
    private static final String TAG = "MainActivity";
    RecyclerView recyclerView;

    @ViewInject(R.id.lrecyclerview)
    LRecyclerView lRecyclerView;

    @ViewInject(R.id.refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @ViewInject(R.id.tv_search)
    EditText searchView;

    private List<Classify> classifies = new ArrayList<>();
    private String[] texts = {"新品上市","促销","我的收藏","我的订单","分类","购物车","个人中心","客服"};
    private LRecyclerViewAdapter mLRecyclerViewAdapter;

    private List<Goods.DataBean> goods = new ArrayList<>();
    private MainContract.Presenter presenter;
    private int page=1;
    private boolean isRefresh;
    private static final int REQUEST_CODE_SCAN_GALLERY = 100;
    public static final int RESULT_CODE_QR_SCAN = 0xA1;
    public static final String INTENT_EXTRA_KEY_QR_SCAN = "qr_scan_result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setIcon(R.mipmap.code,true);
        setIcon(R.mipmap.cart,false);
        presenter = new MainPresenter(this);
        presenter.getList(page);
        CommonHeader commonHeader = new CommonHeader(this,R.layout.layout_grid_menu);
        recyclerView = (RecyclerView) commonHeader.findViewById(R.id.classify);
        for(int i=0;i<texts.length;i++){
            classifies.add(new Classify(texts[i]));
        }
        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        recyclerView.setAdapter(new CommonAdapter<Classify>(this, R.layout.layout_classify_item, classifies) {
            @Override
            protected void convert(ViewHolder holder, Classify classify, int position) {
                int resId = getResources().getIdentifier("icon_indexn_0" + (position + 1), "mipmap", getPackageName());
                holder.setImageResource(R.id.item_img, resId);
                holder.setText(R.id.item_text, classify.getText());
                holder.setTag(R.id.item_root, position);
                holder.setOnClickListener(R.id.item_root, mClickListener);
            }
        });
        dataAdapter = new CommonAdapter<Goods.DataBean>(this,R.layout.layout_good_item,goods) {
            @Override
            protected void convert(ViewHolder holder, Goods.DataBean good, int position) {
                holder.setText(R.id.tv_goods_name,good.getProduct_name());
                holder.setText(R.id.tv_goods_price,"￥"+Float.parseFloat(good.getMoney_real()));
                MainActivity mainActivity = MainActivity.this;
                int targetWidth = DensityUtils.dp2px(mainActivity,360);
                int targetHeight = DensityUtils.dp2px(mainActivity,360);
                Picasso.with(mainActivity)
                        .load(HttpService.IMG_URL+good.getProduct_logo())
                        .resize(targetWidth, targetHeight)
                        .centerInside()
                        .error(R.mipmap.loading)
                        .into((ImageView) holder.getView(R.id.iv_good_img));

            }
        };
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(dataAdapter);
        mLRecyclerViewAdapter.addHeaderView(commonHeader);
        lRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        lRecyclerView.setAdapter(mLRecyclerViewAdapter);
        lRecyclerView.setPullRefreshEnabled(false);
    }
    @Event(value = R.id.tv_search,type = View.OnFocusChangeListener.class)
    private void onFocusChange(View v, boolean hasFocus){
        switch (v.getId()){
            case R.id.tv_search:
                if(hasFocus){
                    changeActivity(SearchActivity.class,false);
                }
                v.clearFocus();
                break;
        }
    }
    @Event({R.id.iv_left,R.id.iv_right})
    private void onClick(View view){
        switch (view.getId()){
            case R.id.iv_left:
                break;
            case R.id.iv_right:
                Intent innerIntent = new Intent(Intent.ACTION_GET_CONTENT);
                innerIntent.setType("image/*");
                Intent wrapperIntent = Intent.createChooser(innerIntent, "选择二维码图片");
                this.startActivityForResult(wrapperIntent, REQUEST_CODE_SCAN_GALLERY);
                break;
        }
    }
    @Event(value = R.id.refresh,type = SwipeRefreshLayout.OnRefreshListener.class)
    private void onRefresh(){
        isRefresh=true;
        page=1;
        presenter.getList(page);
    }

    @Event(value = R.id.lrecyclerview,type = OnLoadMoreListener.class)
    private void onLoadMore(){
        isRefresh=false;
        page++;
        presenter.getList(page);
    }
    @Override
    public void showGoods(Goods goods) {
        if(lRecyclerView.getAdapter() != null){
            if(isRefresh){
                this.goods.clear();
                swipeRefreshLayout.setRefreshing(false);
            }
            lRecyclerView.refreshComplete(goods.getData().size());
            if(goods.getData().size()>0){
                this.goods.addAll(goods.getData());
                mLRecyclerViewAdapter.notifyDataSetChanged();
            }else {
                lRecyclerView.setNoMore(true);
            }
            isRefresh=false;
        }
    }

    private CommonAdapter<Goods.DataBean> dataAdapter;
    private View.OnClickListener mClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            int position = ((int) v.getTag());
            tip(texts[position]);
        }
    };
}
