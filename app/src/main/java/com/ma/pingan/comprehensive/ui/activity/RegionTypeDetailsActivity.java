package com.ma.pingan.comprehensive.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.ma.pingan.comprehensive.R;
import com.ma.pingan.comprehensive.base.BaseActivity;
import com.ma.pingan.comprehensive.base.Constant;
import com.ma.pingan.comprehensive.bilientity.RegionTypesInfo;
import com.ma.pingan.comprehensive.dagger.component.AppComponent;
import com.ma.pingan.comprehensive.ui.fragment.RegionTypeDetailsFragment;
import com.ma.pingan.comprehensive.ui.fragment.RegionTypeRecommendFragment;
import com.ma.pingan.comprehensive.widget.RVPIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

public class RegionTypeDetailsActivity extends BaseActivity {

    @BindView(R.id.indicatorBili)
    RVPIndicator mIndicator;
    @BindView(R.id.viewpagerBili)
    ViewPager mViewPager;
    private List<Fragment> mTabContents;
    private FragmentPagerAdapter mAdapter;

    private RegionTypesInfo.DataBean mDataBean;
    private List<String> titles = new ArrayList<>();
    private static String titile;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_region_type_details;
    }

    @Override
    protected void initToolBar() {
        mCommonToolbar.setTitle(titile);
        mCommonToolbar.setNavigationIcon(R.drawable.ab_back);
    }

    @Override
    protected void initInjector(AppComponent appComponent) {

    }

    @Override
    protected void initData() {
        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            mDataBean = mBundle.getParcelable(Constant.EXTRA_PARTITION);
        }
        Observable.fromIterable(mDataBean.getChildren())
                .subscribe(childrenBeen -> {
                    titles.add(childrenBeen.getName());
                });


        mTabContents = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            mTabContents.add(RegionTypeRecommendFragment.newInstance( i));
        }

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
        mIndicator.setTabItemTitles(titles);
        mViewPager.setAdapter(mAdapter);
        mIndicator.setViewPager(mViewPager, 0);
    }


    public static void launch(FragmentActivity activity, RegionTypesInfo.DataBean dataBean,String itemName) {

        Intent mIntent = new Intent(activity, RegionTypeDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(titile,itemName);
        bundle.putParcelable(Constant.EXTRA_PARTITION, dataBean);
        mIntent.putExtras(bundle);
        activity.startActivity(mIntent);
    }


}
