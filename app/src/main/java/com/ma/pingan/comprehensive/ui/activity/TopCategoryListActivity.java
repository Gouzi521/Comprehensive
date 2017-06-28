package com.ma.pingan.comprehensive.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.ma.pingan.comprehensive.R;
import com.ma.pingan.comprehensive.api.Api;
import com.ma.pingan.comprehensive.api.ApiService;
import com.ma.pingan.comprehensive.base.BaseActivity;
import com.ma.pingan.comprehensive.base.Constant;
import com.ma.pingan.comprehensive.bean.CategoryList;
import com.ma.pingan.comprehensive.dagger.component.AppComponent;
import com.ma.pingan.comprehensive.dagger.component.DaggerActivityComponent;
import com.ma.pingan.comprehensive.mvp.contract.TopCategoryListContract;
import com.ma.pingan.comprehensive.mvp.presenter.TopCategoryListPresenter;
import com.ma.pingan.comprehensive.ui.adapter.TopCategoryListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


public class TopCategoryListActivity extends BaseActivity implements TopCategoryListContract.View{


    public static final String TAG="TopCategoryListActivity";

    @BindView(R.id.rvMaleCategory)
    RecyclerView rvMaleCategory;
    @BindView(R.id.rvFemaleCategory)
    RecyclerView rvFemaleCategory;

    @Inject
    TopCategoryListPresenter mPresenter;

    private List<CategoryList.MaleBean> mMaleCategoryList = new ArrayList<>();
    private List<CategoryList.MaleBean> mFemaleCategoryList = new ArrayList<>();
    private TopCategoryListAdapter maleAdapter;
    private TopCategoryListAdapter femaleAdapter;
    private String gender;


    @Override
    protected void configViews() {

        maleAdapter = new TopCategoryListAdapter(R.layout.item_top_category_list, mMaleCategoryList);
        femaleAdapter = new TopCategoryListAdapter(R.layout.item_top_category_list, mFemaleCategoryList);
        rvMaleCategory.setLayoutManager(new GridLayoutManager(TopCategoryListActivity.this, 4));
        rvFemaleCategory.setLayoutManager(new GridLayoutManager(TopCategoryListActivity.this, 4));
        rvMaleCategory.setAdapter(maleAdapter);
        rvFemaleCategory.setAdapter(femaleAdapter);
        maleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                gender = Constant.Gender.MALE;
                RankingActivity.startActivity(TopCategoryListActivity.this, mMaleCategoryList.get(position).name, gender);

            }
        });
        femaleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                gender = Constant.Gender.FEMALE;
                RankingActivity.startActivity(TopCategoryListActivity.this, mFemaleCategoryList.get(position).name, gender);

            }
        });

        mPresenter.attachView(this);
        mPresenter.getCategoryList();

    }

    @Override
    protected void initToolBar() {
        mCommonToolbar.setTitle(getString(R.string.category));
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
        return R.layout.activity_top_category_list;
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    @Override
    public void showCategoryList(CategoryList data) {
        mMaleCategoryList.clear();
        mFemaleCategoryList.clear();
        mMaleCategoryList.addAll(data.male);
        mFemaleCategoryList.addAll(data.female);

        maleAdapter.notifyDataSetChanged();
        femaleAdapter.notifyDataSetChanged();
    }
}
