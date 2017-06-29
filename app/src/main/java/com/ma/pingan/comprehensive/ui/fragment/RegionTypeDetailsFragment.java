package com.ma.pingan.comprehensive.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ma.pingan.comprehensive.R;
import com.ma.pingan.comprehensive.base.BaseFragment;
import com.ma.pingan.comprehensive.dagger.component.AppComponent;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegionTypeDetailsFragment extends BaseFragment {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    public final static String BUNDLE_TAB = "tab";
    public RegionTypeDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_region_type_details;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }



    @Override
    public void attachView() {

    }

    @Override
    public void initDatas() {

    }

    @Override
    public void configViews() {

    }

    public static Fragment newInstance(int tab) {
        RegionTypeDetailsFragment fragment=new RegionTypeDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_TAB, tab);
        fragment.setArguments(bundle);
        return fragment;
    }
}
