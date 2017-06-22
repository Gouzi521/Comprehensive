package com.ma.pingan.comprehensive.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ma.pingan.comprehensive.R;
import com.ma.pingan.comprehensive.base.BaseActivity;
import com.ma.pingan.comprehensive.dagger.component.AppComponent;
import com.ma.pingan.comprehensive.widget.DrawableCenterButton;
import com.ma.pingan.comprehensive.widget.TagGroup;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookDetailActivity extends BaseActivity {
    public static String INTENT_BOOK_ID = "bookId";
    @BindView(R.id.ivBookCover)
    ImageView mIvBookCover;
    @BindView(R.id.tvBookListTitle)
    TextView mTvBookTitle;
    @BindView(R.id.tvBookListAuthor)
    TextView mTvAuthor;
    @BindView(R.id.tvCatgory)
    TextView mTvCatgory;
    @BindView(R.id.tvWordCount)
    TextView mTvWordCount;
    @BindView(R.id.tvLatelyUpdate)
    TextView mTvLatelyUpdate;
    @BindView(R.id.btnJoinCollection)
    DrawableCenterButton mBtnJoinCollection;
    @BindView(R.id.btnRead)
    DrawableCenterButton mBtnRead;
    @BindView(R.id.tvLatelyFollower)
    TextView mTvLatelyFollower;
    @BindView(R.id.tvRetentionRatio)
    TextView mTvRetentionRatio;
    @BindView(R.id.tvSerializeWordCount)
    TextView mTvSerializeWordCount;
    @BindView(R.id.tag_group)
    TagGroup mTagGroup;
    @BindView(R.id.tvlongIntro)
    TextView mTvlongIntro;
    @BindView(R.id.tvMoreReview)
    TextView mTvMoreReview;
    @BindView(R.id.rvHotReview)
    RecyclerView mRvHotReview;
    @BindView(R.id.tvCommunity)
    TextView mTvCommunity;
    @BindView(R.id.tvHelpfulYes)
    TextView mTvHelpfulYes;
    @BindView(R.id.rlCommunity)
    RelativeLayout mRlCommunity;
    @BindView(R.id.tvRecommendBookList)
    TextView mTvRecommendBookList;
    @BindView(R.id.rvRecommendBoookList)
    RecyclerView mTvRecommendBoookList;


    public static void startActivity(Context context, String bookId) {
        context.startActivity(new Intent(context, BookDetailActivity.class)
                .putExtra(INTENT_BOOK_ID, bookId));

    }

    @Override
    protected void configViews() {

    }

    @Override
    protected void initToolBar() {
        mCommonToolbar.setNavigationIcon(R.drawable.ab_back);
        mCommonToolbar.setTitle(R.string.book_detail);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initInjector(AppComponent appComponent) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_book_detail;
    }


}
