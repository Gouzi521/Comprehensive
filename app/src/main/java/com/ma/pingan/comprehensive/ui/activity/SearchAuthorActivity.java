package com.ma.pingan.comprehensive.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ma.pingan.comprehensive.R;
import com.ma.pingan.comprehensive.base.BaseActivity;
import com.ma.pingan.comprehensive.bean.BooksByTag;
import com.ma.pingan.comprehensive.bean.SearchDetail;
import com.ma.pingan.comprehensive.dagger.component.AppComponent;
import com.ma.pingan.comprehensive.dagger.component.DaggerActivityComponent;
import com.ma.pingan.comprehensive.mvp.contract.SearchAuthorContract;
import com.ma.pingan.comprehensive.mvp.presenter.SearchAuthorPresenter;
import com.ma.pingan.comprehensive.ui.adapter.SearchAuthorAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchAuthorActivity extends BaseActivity implements SearchAuthorContract.View{


    public static final String INTENT_AUTHOR = "author";
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;

    @Inject
    SearchAuthorPresenter mPresenter;
    private String author = BookDetailActivity.author;

    private  List<SearchDetail.SearchBooks> mList  = new ArrayList<>();
    private SearchAuthorAdapter adapter;
    public static void startActivity(Context context, String author) {
        context.startActivity(new Intent(context, SearchAuthorActivity.class)
                .putExtra(INTENT_AUTHOR, author));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_author;
    }

    @Override
    protected void initToolBar() {
        author = getIntent().getStringExtra(INTENT_AUTHOR);
        mCommonToolbar.setTitle(author);
        mCommonToolbar.setNavigationIcon(R.drawable.ab_back);
    }

    @Override
    protected void initInjector(AppComponent appComponent) {

        DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void configViews() {

        adapter=new SearchAuthorAdapter(R.layout.item_search_result_list,mList);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SearchDetail.SearchBooks data = (SearchDetail.SearchBooks) adapter.getData().get(position);
                BookDetailActivity.startActivity(SearchAuthorActivity.this, data._id);
            }
        });

        mPresenter.attachView(this);
        mPresenter.getSearchResultList(author);
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    @Override
    public void showSearchResultList(List<BooksByTag.TagBook> list) {

         List<SearchDetail.SearchBooks> mList= new ArrayList<>();
        for (BooksByTag.TagBook book : list) {
            mList.add(new SearchDetail.SearchBooks(book._id, book.title, book.author, book.cover, book.retentionRatio, book.latelyFollower));
        }

        adapter.addData(mList);
    }
}
