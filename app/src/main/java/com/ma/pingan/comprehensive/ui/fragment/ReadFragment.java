package com.ma.pingan.comprehensive.ui.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ma.pingan.comprehensive.R;
import com.ma.pingan.comprehensive.base.BaseFragment;
import com.ma.pingan.comprehensive.bean.FindBean;
import com.ma.pingan.comprehensive.dagger.component.AppComponent;
import com.ma.pingan.comprehensive.ui.activity.TopCategoryListActivity;
import com.ma.pingan.comprehensive.ui.adapter.FindAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReadFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener {


    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private FindAdapter mAdapter;
    private List<FindBean> mList = new ArrayList<>();

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_read;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void attachView() {

    }

    @Override
    public void initDatas() {
        mList.clear();
        mList.add(new FindBean("排行榜", R.drawable.home_find_rank));
        mList.add(new FindBean("主题书单", R.drawable.home_find_topic));
        mList.add(new FindBean("分类", R.drawable.home_find_category));
        mList.add(new FindBean("官方QQ群", R.drawable.home_find_listen));
    }

    @Override
    public void configViews() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new FindAdapter(R.layout.item_find, mList);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (position){
            case 0:
                //TopRankActivity.startActivity(activity);
                startActivity(new Intent(activity, TopCategoryListActivity.class));
                break;
            case 1:
                //SubjectBookListActivity.startActivity(activity);
                startActivity(new Intent(activity, TopCategoryListActivity.class));
                break;
            case 2:
                startActivity(new Intent(activity, TopCategoryListActivity.class));
                break;
            case 3:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://jq.qq.com/?_wv=1027&k=46qbql8")));
                break;
            default:
                break;
        }
    }
}
