package com.ma.pingan.comprehensive.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.ma.pingan.comprehensive.R;
import com.ma.pingan.comprehensive.base.BaseActivity;
import com.ma.pingan.comprehensive.dagger.component.AppComponent;
import com.ma.pingan.comprehensive.dagger.component.DaggerActivityComponent;
import com.ma.pingan.comprehensive.ui.fragment.SubjectFragment;
import com.ma.pingan.comprehensive.widget.RVPIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubjectBookListActivity extends BaseActivity {


    @BindView(R.id.indicatorSubject)
    RVPIndicator mIndicator;
    @BindView(R.id.viewpagerSubject)
    ViewPager mViewPager;

    private List<Fragment> mTabContents;
    private FragmentPagerAdapter mAdapter;
    private List<String> mDatas;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, SubjectBookListActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_subject_book_list;
    }

    @Override
    protected void initToolBar() {
        mCommonToolbar.setTitle(R.string.subject_book_list);
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
        mDatas=new ArrayList<>();
        mDatas.add("本周最热");
        mDatas.add("最新发布");
        mDatas.add("最多收藏");


        mTabContents = new ArrayList<>();
        mTabContents.add(SubjectFragment.newInstance("", 0));
        mTabContents.add(SubjectFragment.newInstance("", 1));
        mTabContents.add(SubjectFragment.newInstance("", 2));

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mTabContents.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mTabContents.get(position);
            }
        };
    }

    @Override
    protected void configViews() {
        mIndicator.setTabItemTitles(mDatas);
        mViewPager.setAdapter(mAdapter);
        mIndicator.setViewPager(mViewPager, 0);
    }


}
