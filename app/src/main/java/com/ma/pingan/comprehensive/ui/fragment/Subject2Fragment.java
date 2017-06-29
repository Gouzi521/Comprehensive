package com.ma.pingan.comprehensive.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ma.pingan.comprehensive.R;
import com.ma.pingan.comprehensive.base.BaseFragment;
import com.ma.pingan.comprehensive.bean.BookLists;
import com.ma.pingan.comprehensive.dagger.component.AppComponent;
import com.ma.pingan.comprehensive.dagger.component.DaggerActivityComponent;
import com.ma.pingan.comprehensive.manger.SettingManager;
import com.ma.pingan.comprehensive.mvp.contract.SubjectFragmentContract;
import com.ma.pingan.comprehensive.mvp.presenter.SubjectFragmentPresenter;
import com.ma.pingan.comprehensive.ui.activity.SubjectBookListDetailActivity;
import com.ma.pingan.comprehensive.ui.adapter.SubjectBookListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by mapingan
 * on 2017/6/29 0029.
 */

public class Subject2Fragment extends BaseFragment  implements SubjectFragmentContract.View, BaseQuickAdapter.OnItemClickListener {


    @Inject
    SubjectFragmentPresenter mPresenter;
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

    private SubjectBookListAdapter mAdapter;
    private List<BookLists.BookListsBean> data =new ArrayList<>();

    public static Subject2Fragment newInstance(String tag, int tab) {
        Subject2Fragment fragment = new Subject2Fragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TAG, tag);
        bundle.putInt(BUNDLE_TAB, tab);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutResId() {
        return  R.layout.common_easy_recyclerview;
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


    }

    @Override
    public void configViews() {

        mAdapter=new SubjectBookListAdapter(R.layout.item_sub_category_list,data);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerview.setAdapter(mAdapter);
        mPresenter.attachView(this);
        mPresenter.getBookLists(duration, sort, 0, 20, currendTag, "male");

        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    @Override
    public void showBookList(List<BookLists.BookListsBean> bookLists) {

        data.clear();
        data.addAll(bookLists);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        SubjectBookListDetailActivity.startActivity(activity, mAdapter.getItem(position));
    }
}
