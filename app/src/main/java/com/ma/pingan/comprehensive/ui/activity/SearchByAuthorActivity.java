package com.ma.pingan.comprehensive.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.ma.pingan.comprehensive.R;
import com.ma.pingan.comprehensive.base.BaseActivity;
import com.ma.pingan.comprehensive.bean.BooksByTag;
import com.ma.pingan.comprehensive.dagger.component.AppComponent;
import com.ma.pingan.comprehensive.dagger.component.DaggerActivityComponent;
import com.ma.pingan.comprehensive.mvp.contract.SearchByAuthorContract;
import com.ma.pingan.comprehensive.mvp.presenter.SearchByAuthorPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchByAuthorActivity extends BaseActivity implements SearchByAuthorContract.View {


    public static final String INTENT_AUTHOR = "author";
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    public static void startActivity(Context context, String author) {
        context.startActivity(new Intent(context, SearchByAuthorActivity.class)
                .putExtra(INTENT_AUTHOR, author));
    }

    @Inject
    SearchByAuthorPresenter mPresenter;

    private String author = "";

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    @Override
    public void showSearchResultList(List<BooksByTag.TagBook> list) {

    }

    @Override
    protected void configViews() {
        mPresenter.attachView(this);
        mPresenter.getSearchResultList(author);
    }

    @Override
    protected void initToolBar() {
        author = getIntent().getStringExtra(INTENT_AUTHOR);
        mCommonToolbar.setTitle(author);
        mCommonToolbar.setNavigationIcon(R.drawable.ab_back);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initInjector(AppComponent appComponent) {
        DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_by_author;
    }


}
