package com.ma.pingan.comprehensive.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.ma.pingan.comprehensive.R;
import com.ma.pingan.comprehensive.api.Api;
import com.ma.pingan.comprehensive.base.BaseActivity;
import com.ma.pingan.comprehensive.base.Constant;
import com.ma.pingan.comprehensive.bean.CategoryListLv2;
import com.ma.pingan.comprehensive.bean.Ranking;
import com.ma.pingan.comprehensive.dagger.component.AppComponent;
import com.ma.pingan.comprehensive.dagger.component.DaggerActivityComponent;
import com.ma.pingan.comprehensive.ui.fragment.SubCategoryFragment;
import com.ma.pingan.comprehensive.widget.RVPIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 分类小说
 */
public class RankingActivity extends BaseActivity {


    @BindView(R.id.indicatorSub)
    RVPIndicator mIndicator;
    @BindView(R.id.viewpagerSubRank)
    ViewPager mViewPager;

    private String[] types = new String[]{Constant.CateType.NEW, Constant.CateType.HOT, Constant.CateType.REPUTATION, Constant.CateType.OVER};
    public static final String INTENT_CATE_NAME = "name";
    public static final String INTENT_GENDER = "gender";
    private String cate = "";
    private String gender = "";

    private MenuItem menuItem = null;
    private List<String> mMinors = new ArrayList<>();
    private List<Fragment> mTabContents;
    private List<String> mDatas;
    private FragmentPagerAdapter mAdapter;

    public static void startActivity(Context context, String name, @Constant.Gender String gender) {
        Intent intent = new Intent(context, RankingActivity.class);
        intent.putExtra(INTENT_CATE_NAME, name);
        intent.putExtra(INTENT_GENDER, gender);
        context.startActivity(intent);
    }
    @Override
    protected void configViews() {
        mIndicator.setTabItemTitles(mDatas);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(4);
        mIndicator.setViewPager(mViewPager, 0);

    }

    @Override
    protected void initToolBar() {
        cate = getIntent().getStringExtra(INTENT_CATE_NAME);
        if (menuItem != null) {
            menuItem.setTitle(cate);
        }
        gender = getIntent().getStringExtra(INTENT_GENDER);
        mCommonToolbar.setTitle(cate);
        mCommonToolbar.setNavigationIcon(R.drawable.ab_back);
    }

    @Override
    protected void initData() {
        mMinors.clear();
        mDatas=new ArrayList<>();
        mDatas.add("新书");
        mDatas.add("热门");
        mDatas.add("口碑");
        mDatas.add("完结");


        mTabContents = new ArrayList<>();
        mTabContents.add(SubCategoryFragment.newInstance(cate, "", gender, Constant.CateType.NEW));
        mTabContents.add(SubCategoryFragment.newInstance(cate, "", gender, Constant.CateType.HOT));
        mTabContents.add(SubCategoryFragment.newInstance(cate, "", gender, Constant.CateType.REPUTATION));
        mTabContents.add(SubCategoryFragment.newInstance(cate, "", gender, Constant.CateType.OVER));

        mAdapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mTabContents.get(position);
            }

            @Override
            public int getCount() {
                return mTabContents.size();
            }
        };

        Api api=new Api();
        api.getCategoryListLv2()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CategoryListLv2>() {
                    @Override
                    public void accept(@NonNull CategoryListLv2 data) throws Exception {
                        mMinors.add(cate);
                        if (gender.equals(Constant.Gender.MALE)){
                            for (CategoryListLv2.MaleBean bean:data.male){
                                if (cate.equals(bean.major)){
                                    mMinors.addAll(bean.mins);
                                }
                            }
                        }else {
                            for (CategoryListLv2.MaleBean bean:data.female){
                                if (cate.equals(bean.major)){
                                    mMinors.addAll(bean.mins);
                                    break;
                                }
                            }
                        }
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
        return R.layout.activity_ranking;
    }


}
