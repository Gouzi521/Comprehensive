package com.ma.pingan.comprehensive.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ma.pingan.comprehensive.R;
import com.ma.pingan.comprehensive.base.BaseRvFragment;
import com.ma.pingan.comprehensive.bean.BookLists;
import com.ma.pingan.comprehensive.dagger.component.AppComponent;
import com.ma.pingan.comprehensive.dagger.component.DaggerActivityComponent;
import com.ma.pingan.comprehensive.manger.SettingManager;
import com.ma.pingan.comprehensive.mvp.contract.SubjectFragmentContract;
import com.ma.pingan.comprehensive.mvp.presenter.SubjectFragmentPresenter;
import com.ma.pingan.comprehensive.widget.EasyLoadMoreView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubjectFragment extends BaseRvFragment<SubjectFragmentPresenter,BookLists.BookListsBean> implements SubjectFragmentContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    public final static String BUNDLE_TAG = "tag";
    public final static String BUNDLE_TAB = "tab";
    public String currendTag;
    public int currentTab;

    public String duration = "";
    public String sort = "";
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;


    protected int start = 0;

    private int page;
    private final static int PRE_PAGE = 20;
    private List<BookLists.BookListsBean> data;

    private boolean isRefresh = false;
    public static SubjectFragment newInstance(String tag, int tab) {
        SubjectFragment fragment = new SubjectFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TAG, tag);
        bundle.putInt(BUNDLE_TAB, tab);
        fragment.setArguments(bundle);
        return fragment;
    }



    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }


    @Override
    protected void loadData() {
        mPresenter.getBookLists(duration, sort, 0, PRE_PAGE, currendTag, SettingManager.getInstance().getUserChooseSex());
    }

    @Override
    public int getLayoutResId() {
        return R.layout.common_easy_recyclerview;
    }


    @Override
    protected void initView() {

        currentTab = getArguments().getInt(BUNDLE_TAB);
        switch (currentTab) {
            case 0:
                duration = "last-seven-days";
                sort = "collectorCount";
                break;
            case 1:
                duration = "all";
                sort = "created";
                break;
            case 2:
            default:
                duration = "all";
                sort = "collectorCount";
                break;
        }
        swipeLayout.setColorSchemeColors(getResources().getColor(R.color.light_black));
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerview.setAdapter(mAdapter);
        swipeLayout.setOnRefreshListener(this);
        mAdapter.setLoadMoreView(new EasyLoadMoreView());
        mAdapter.setOnLoadMoreListener(this,recyclerview);
    }




    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    @Override
    public void showBookList(List<BookLists.BookListsBean> bookLists) {
        data=new ArrayList<>();
        data.addAll(bookLists);
        if (isRefresh){
            swipeLayout.setRefreshing(false);
            mAdapter.setEnableLoadMore(true);
            isRefresh = false;
            mAdapter.setNewData(data);
        }else{
            swipeLayout.setEnabled(true);
            page++;
            mAdapter.addData(data);
            mAdapter.loadMoreComplete();
        }
    }


    @Override
    public void onRefresh() {
        page = 0;
        isRefresh =true;
        mAdapter.setEnableLoadMore(false);
        mPresenter.getBookLists(duration, sort, 0, PRE_PAGE, currendTag, SettingManager.getInstance().getUserChooseSex());

    }

    @Override
    public void onLoadMoreRequested() {
        if (page >= 20) {
            mAdapter.loadMoreEnd();
            swipeLayout.setEnabled(true);
        } else {
            loadData();
            swipeLayout.setEnabled(false);
        }
    }
}
