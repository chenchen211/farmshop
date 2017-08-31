package com.hnxy.farmshop.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.chenchen.utils.DensityUtils;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.hnxy.farmshop.R;
import com.hnxy.farmshop.bean.Goods;
import com.hnxy.farmshop.contract.MainContract;
import com.hnxy.farmshop.presenter.MainPresenter;
import com.hnxy.farmshop.http.HttpService;
import com.squareup.picasso.Picasso;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_search_result)
public class SearchResultActivity extends BaseActivity implements MainContract.View{

    @ViewInject(R.id.search_goods)
    LRecyclerView lRecyclerView;

    @ViewInject(R.id.refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    private int page=1;
    private boolean isRefresh;
    private String mSearch;
    private MainContract.Presenter presenter;
    private List<Goods.DataBean> goods = new ArrayList<>();
    private LRecyclerViewAdapter mLRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setIcon(R.mipmap.back,true);
        setIcon(R.mipmap.cart,false);
        mSearch = getIntent().getStringExtra("search");
        setTitle(mSearch);
        try {
            mSearch = URLEncoder.encode(mSearch,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        presenter = new MainPresenter(this);
        presenter.search(page,mSearch);

        mLRecyclerViewAdapter = new LRecyclerViewAdapter(new CommonAdapter<Goods.DataBean>(this,R.layout.layout_good_item_small,goods) {
            @Override
            protected void convert(ViewHolder holder, Goods.DataBean good, int position) {
                holder.setText(R.id.tv_goods_name,good.getProduct_name());
                holder.setText(R.id.tv_goods_price,"￥"+Float.parseFloat(good.getMoney_real()));
                SearchResultActivity context = SearchResultActivity.this;
                int targetWidth = DensityUtils.dp2px(context,180);
                int targetHeight = DensityUtils.dp2px(context,180);
                Picasso.with(context)
                        .load(HttpService.IMG_URL+good.getProduct_logo())
                        .resize(targetWidth, targetHeight)
                        .error(R.mipmap.loading)
                        .into((ImageView) holder.getView(R.id.iv_good_img));
            }
        });
        lRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        lRecyclerView.setAdapter(mLRecyclerViewAdapter);
        lRecyclerView.setPullRefreshEnabled(false);
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

    @Event({R.id.iv_right,R.id.iv_left})
    private void onClick(View view){
        switch (view.getId()){
            case R.id.iv_right:
                break;
            case R.id.iv_left:
                finish();
                break;
        }
    }
    @Event(value = R.id.refresh,type = SwipeRefreshLayout.OnRefreshListener.class)
    private void onRefresh(){
        isRefresh=true;
        page=1;
        presenter.search(page,mSearch);
    }

    @Event(value = R.id.lrecyclerview,type = OnLoadMoreListener.class)
    private void onLoadMore(){
        isRefresh=false;
        page++;
        presenter.search(page,mSearch);
    }
}
