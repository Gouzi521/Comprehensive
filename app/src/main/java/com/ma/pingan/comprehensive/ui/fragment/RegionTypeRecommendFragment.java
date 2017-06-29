package com.ma.pingan.comprehensive.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ma.pingan.comprehensive.R;
import com.ma.pingan.comprehensive.base.BaseFragment;
import com.ma.pingan.comprehensive.bilientity.RegionRecommendInfo;
import com.ma.pingan.comprehensive.dagger.component.AppComponent;
import com.ma.pingan.comprehensive.dagger.component.DaggerActivityComponent;
import com.ma.pingan.comprehensive.mvp.contract.RegionTypeRecommendContract;
import com.ma.pingan.comprehensive.mvp.presenter.RegionTypeRecommendPresenter;
import com.ma.pingan.comprehensive.ui.adapter.RegionTypeRecommendAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RegionTypeRecommendFragment extends BaseFragment implements RegionTypeRecommendContract.View {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    Unbinder unbinder;
    private List<RegionRecommendInfo> allData = new ArrayList<>();
    private List<RegionRecommendInfo.DataBean.BannerBean.TopBean> banners = new ArrayList<>();

    public final static String BUNDLE_TAB = "tab";

    private int rid;

    @Inject
    RegionTypeRecommendPresenter mPresenter;

    private RegionTypeRecommendAdapter adapter;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_region_type_recommend;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

        DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);

    }

    @Override
    public void attachView() {

    }

    @Override
    public void initDatas() {

    }

    @Override
    public void configViews() {

        adapter = new RegionTypeRecommendAdapter(allData);
        recyclerview.setLayoutManager(new GridLayoutManager(getContext(),2));

        recyclerview.setAdapter(adapter);
        mPresenter.attachView(this);
        mPresenter.getRegionList(rid);
    }


    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }



    public static Fragment newInstance(int rid) {
        RegionTypeRecommendFragment fragment = new RegionTypeRecommendFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_TAB, rid);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showRegion(List<RegionRecommendInfo> list) {
        allData.clear();
        allData.addAll(list);
        adapter.notifyDataSetChanged();
    }
}
