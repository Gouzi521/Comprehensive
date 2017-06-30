package com.ma.pingan.comprehensive.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ma.pingan.comprehensive.R;
import com.ma.pingan.comprehensive.base.BaseFragment;
import com.ma.pingan.comprehensive.bilientity.RegionRecommendInfo;
import com.ma.pingan.comprehensive.dagger.component.AppComponent;
import com.ma.pingan.comprehensive.dagger.component.DaggerActivityComponent;
import com.ma.pingan.comprehensive.mvp.contract.RegionTypeRecommendContract;
import com.ma.pingan.comprehensive.mvp.presenter.RegionTypeRecommendPresenter;
import com.ma.pingan.comprehensive.ui.activity.VideoDetailsActivity;
import com.ma.pingan.comprehensive.ui.adapter.HotRecyclerAdapter;
import com.ma.pingan.comprehensive.ui.adapter.NewRecyclerAdapter;
import com.ma.pingan.comprehensive.ui.adapter.RecommendRecyclerAdapter;
import com.ma.pingan.comprehensive.widget.FullyLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RegionTypeRecommendFragment extends BaseFragment implements RegionTypeRecommendContract.View {



    @BindView(R.id.recyclerview_item1)
    RecyclerView titleRecycler;
    @BindView(R.id.recyclerview_item2)
    RecyclerView hotRecycler;
    @BindView(R.id.recyclerview_item3)
    RecyclerView newRecycler;
    @BindView(R.id.recyclerview_item4)
    RecyclerView recommeRecycler;

    private List<RegionRecommendInfo.DataBean.RecommendBean> recommendData ;
    private List<RegionRecommendInfo.DataBean.NewBean> newData ;
    private List<RegionRecommendInfo.DataBean.DynamicBean> dynamiData ;


    public final static String BUNDLE_TAB = "tab";

    private int rid;

    @Inject
    RegionTypeRecommendPresenter mPresenter;

    private HotRecyclerAdapter hotAdapter;
    private NewRecyclerAdapter newAdapter;
    private RecommendRecyclerAdapter recomAdapter;


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

        hotAdapter = new HotRecyclerAdapter(R.layout.layout_region_recommend_card_item, recommendData);
        newAdapter = new NewRecyclerAdapter(R.layout.layout_region_recommend_card_item, newData);
        recomAdapter = new RecommendRecyclerAdapter(R.layout.layout_region_recommend_card_item, dynamiData);


        hotRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        newRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recommeRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
//
//
        hotRecycler.setAdapter(hotAdapter);
        newRecycler.setAdapter(newAdapter);
        recommeRecycler.setAdapter(recomAdapter);


        mPresenter.attachView(this);
        mPresenter.getRegionList(23);


        hotAdapter.setOnItemClickListener((adapter, view, position) ->
            VideoDetailsActivity.launch((Activity) mContext, Integer.valueOf(recommendData.get(position).getParam()), recommendData.get(position).getCover()));

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
    public void showRegion(List<RegionRecommendInfo> list) {
        recommendData=new ArrayList<>();
        newData=new ArrayList<>();
        dynamiData=new ArrayList<>();
        recommendData.clear();
        newData.clear();
        dynamiData.clear();

        for (int i = 0; i < 4; i++) {
            recommendData.addAll(list.get(i).getData().getRecommend());
            newData.addAll(list.get(i).getData().getNewX());
            dynamiData.addAll(list.get(i).getData().getDynamic());
        }


        hotAdapter.notifyDataSetChanged();
        newAdapter.notifyDataSetChanged();
        recomAdapter.notifyDataSetChanged();
    }


}
