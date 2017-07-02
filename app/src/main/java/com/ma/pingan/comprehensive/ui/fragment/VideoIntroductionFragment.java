package com.ma.pingan.comprehensive.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ma.pingan.comprehensive.R;
import com.ma.pingan.comprehensive.base.BaseFragment;
import com.ma.pingan.comprehensive.bilientity.VideoDetailsInfo;
import com.ma.pingan.comprehensive.dagger.component.AppComponent;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoIntroductionFragment extends BaseFragment {

    @BindView(R.id.tv_title)
    TextView mTitleText;

    @BindView(R.id.tv_play_time)
    TextView mPlayTimeText;

    @BindView(R.id.tv_review_count)
    TextView mReviewCountText;

    @BindView(R.id.tv_description)
    TextView mDescText;


    @BindView(R.id.share_num)
    TextView mShareNum;

    @BindView(R.id.coin_num)
    TextView mCoinNum;

    @BindView(R.id.fav_num)
    TextView mFavNum;


    @BindView(R.id.recycle)
    RecyclerView mRecyclerView;

    @BindView(R.id.layout_video_related)
    RelativeLayout mVideoRelatedLayout;

    private int av;

    private VideoDetailsInfo.DataBean mVideoDetailsInfo;
    public VideoIntroductionFragment() {
        // Required empty public constructor
    }


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_video_introduction;
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

}
