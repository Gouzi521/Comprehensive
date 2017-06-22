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
import com.ma.pingan.comprehensive.ui.adapter.TopCategoryListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TopCategoryListActivity extends BaseActivity {


    public static final String TAG="TopCategoryListActivity";

    @BindView(R.id.rvMaleCategory)
    RecyclerView rvMaleCategory;
    @BindView(R.id.rvFemaleCategory)
    RecyclerView rvFemaleCategory;

    private List<CategoryList.MaleBean> mMaleCategoryList = new ArrayList<>();
    private List<CategoryList.MaleBean> mFemaleCategoryList = new ArrayList<>();
    private TopCategoryListAdapter maleAdapter;
    private TopCategoryListAdapter femaleAdapter;
    private String gender;

    @Override
    protected void configViews() {

    }

    @Override
    protected void initToolBar() {
        mCommonToolbar.setTitle(getString(R.string.category));
        mCommonToolbar.setNavigationIcon(R.drawable.ab_back);
    }

    @Override
    protected void initData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getCategoryList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CategoryList>() {
                    @Override
                    public void accept(@NonNull final CategoryList categoryList) throws Exception {
                        mMaleCategoryList.addAll(categoryList.male);
                        mFemaleCategoryList.addAll(categoryList.female);
                        maleAdapter = new TopCategoryListAdapter(R.layout.item_top_category_list, mMaleCategoryList);
                        rvMaleCategory.setAdapter(maleAdapter);
                        femaleAdapter = new TopCategoryListAdapter(R.layout.item_top_category_list, mFemaleCategoryList);
                        rvFemaleCategory.setAdapter(femaleAdapter);
                        rvMaleCategory.setLayoutManager(new GridLayoutManager(TopCategoryListActivity.this, 4));
                        rvFemaleCategory.setLayoutManager(new GridLayoutManager(TopCategoryListActivity.this, 4));

                        maleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                gender = Constant.Gender.MALE;
                                RankingActivity.startActivity(TopCategoryListActivity.this, categoryList.male.get(position).name, gender);
                                Log.e(TAG,categoryList.male.get(0).name+gender);
                            }
                        });
                        femaleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                gender = Constant.Gender.FEMALE;
                                RankingActivity.startActivity(TopCategoryListActivity.this, categoryList.male.get(position).name, gender);
                                Log.e(TAG,categoryList.male.get(0).name+gender);
                            }
                        });

                    }
                });


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
}
