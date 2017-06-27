package com.ma.pingan.comprehensive.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ma.pingan.comprehensive.R;
import com.ma.pingan.comprehensive.base.BaseActivity;
import com.ma.pingan.comprehensive.base.Constant;
import com.ma.pingan.comprehensive.bean.BookDetail;
import com.ma.pingan.comprehensive.bean.HotReview;
import com.ma.pingan.comprehensive.bean.Recommend;
import com.ma.pingan.comprehensive.bean.RecommendBookList;
import com.ma.pingan.comprehensive.dagger.component.AppComponent;
import com.ma.pingan.comprehensive.dagger.component.DaggerActivityComponent;
import com.ma.pingan.comprehensive.mvp.contract.BookDetailContract;
import com.ma.pingan.comprehensive.mvp.presenter.BookDetailPresenter;
import com.ma.pingan.comprehensive.ui.adapter.HotReviewAdapter;
import com.ma.pingan.comprehensive.ui.adapter.RecommendBookListAdapter;
import com.ma.pingan.comprehensive.utils.FormatUtils;
import com.ma.pingan.comprehensive.widget.DrawableCenterButton;
import com.ma.pingan.comprehensive.widget.TagColor;
import com.ma.pingan.comprehensive.widget.TagGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookDetailActivity extends BaseActivity implements BookDetailContract.View {
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
    RecyclerView mRvRecommendBoookList;
    @BindView(R.id.common_toolbar)
    Toolbar commonToolbar;


    private String bookId;
    private HotReviewAdapter mHotReviewAdapter;
    private List<HotReview.Reviews> mHotReviewList = new ArrayList<>();

    private RecommendBookListAdapter mRecommendBookListAdapter;
    private List<RecommendBookList.RecommendBook> mRecommendBookList = new ArrayList<>();

    private List<String> tagList = new ArrayList<>();
    private int times = 0;
    private Recommend.RecommendBooks recommendBooks;
    @Inject
    BookDetailPresenter mPresenter;

    public static void startActivity(Context context, String bookId) {
        context.startActivity(new Intent(context, BookDetailActivity.class)
                .putExtra(INTENT_BOOK_ID, bookId));

    }

    @Override
    protected void configViews() {
        mRvHotReview.setHasFixedSize(true);
        mRvHotReview.setLayoutManager(new LinearLayoutManager(this));
        mHotReviewAdapter = new HotReviewAdapter(R.layout.item_book_detai_hot_review_list, mHotReviewList);
        mRvHotReview.setAdapter(mHotReviewAdapter);

        mRvRecommendBoookList.setHasFixedSize(true);
        mRvRecommendBoookList.setLayoutManager(new LinearLayoutManager(this));
        mRecommendBookListAdapter = new RecommendBookListAdapter(R.layout.item_book_detail_recommend_book_list, mRecommendBookList);
        mRvRecommendBoookList.setAdapter(mRecommendBookListAdapter);

        mTagGroup.setOnTagClickListener(new TagGroup.OnTagClickListener() {
            @Override
            public void onTagClick(String tag) {
//                startActivity(new Intent(BookDetailActivity.this, BooksByTagActivity.class)
//                        .putExtra("tag", tag));
                Toast.makeText(BookDetailActivity.this, tag, Toast.LENGTH_SHORT).show();
            }
        });

        mPresenter.attachView(this);
        mPresenter.getBookDetail(bookId);
        mPresenter.getHotReview(bookId);
        mPresenter.getRecommendBookList(bookId, "3");
    }

    @Override
    protected void initToolBar() {
        mCommonToolbar.setNavigationIcon(R.drawable.ab_back);
        mCommonToolbar.setTitle(R.string.book_detail);
    }

    @Override
    protected void initData() {

        bookId = getIntent().getStringExtra(INTENT_BOOK_ID);
    }

    @Override
    protected void initInjector(AppComponent appComponent) {
        DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_book_detail;
    }


    @Override
    public void showError() {

    }

    @Override
    public void complete() {


    }

    @Override
    public void showBookDetail(BookDetail data) {
        Glide.with(this)
                .load(Constant.IMG_BASE_URL + data.cover)
                .placeholder(R.drawable.cover_default)
                // .transform(new GlideRoundTransform(mContext))
                .into(mIvBookCover);

        mTvBookTitle.setText(data.title);
        mTvAuthor.setText(String.format(getString(R.string.book_detail_author), data.author));
        mTvCatgory.setText(String.format(getString(R.string.book_detail_category), data.cat));
        mTvWordCount.setText(FormatUtils.formatWordCount(data.wordCount));
        mTvLatelyUpdate.setText(FormatUtils.getDescriptionTimeFromDateString(data.updated));
        mTvLatelyFollower.setText(String.valueOf(data.latelyFollower));
        mTvRetentionRatio.setText(TextUtils.isEmpty(data.retentionRatio) ?
                "-" : String.format(getString(R.string.book_detail_retention_ratio),
                data.retentionRatio));
        mTvSerializeWordCount.setText(data.serializeWordCount < 0 ? "-" :
                String.valueOf(data.serializeWordCount));

        tagList.clear();
        tagList.addAll(data.tags);
        times = 0;
        showHotWord();

        mTvlongIntro.setText(data.longIntro);
        mTvCommunity.setText(String.format(getString(R.string.book_detail_community), data.title));
        mTvHelpfulYes.setText(String.format(getString(R.string.book_detail_post_count), data.postCount));

        recommendBooks = new Recommend.RecommendBooks();
        recommendBooks.title = data.title;
        recommendBooks._id = data._id;
        recommendBooks.cover = data.cover;
        recommendBooks.lastChapter = data.lastChapter;
        recommendBooks.updated = data.updated;

        // refreshCollectionIcon();
    }

//
//    /**
//     * 刷新收藏图标
//     */
//    private void refreshCollectionIcon() {
//        if (CollectionsManager.getInstance().isCollected(recommendBooks._id)) {
//            initCollection(false);
//        } else {
//            initCollection(true);
//        }
//    }

    /**
     * 每次显示8个
     */
    private void showHotWord() {
        int start, end;
        if (times < tagList.size() && times + 8 <= tagList.size()) {
            start = times;
            end = times + 8;
        } else if (times < tagList.size() - 1 && times + 8 > tagList.size()) {
            start = times;
            end = tagList.size() - 1;
        } else {
            start = 0;
            end = tagList.size() > 8 ? 8 : tagList.size();
        }
        times = end;
        if (end - start > 0) {
            List<String> batch = tagList.subList(start, end);
            List<TagColor> colors = TagColor.getRandomColors(batch.size());
            mTagGroup.setTags(colors, (String[]) batch.toArray(new String[batch.size()]));
        }
    }

    @Override
    public void showHotReview(List<HotReview.Reviews> list) {
        mHotReviewList.clear();
        mHotReviewList.addAll(list);
        mHotReviewAdapter.notifyDataSetChanged();
    }

    @Override
    public void showRecommendBookList(List<RecommendBookList.RecommendBook> list) {
        if (!list.isEmpty()) {
            mTvRecommendBookList.setVisibility(View.VISIBLE);
            mRecommendBookList.clear();
            mRecommendBookList.addAll(list);
            mRecommendBookListAdapter.notifyDataSetChanged();
        }
    }



    @OnClick(R.id.tvBookListAuthor)
    public void onViewClicked() {
        String author = mTvAuthor.getText().toString().replaceAll(" ", "");
        SearchByAuthorActivity.startActivity(this, author);
    }


//    @OnClick(R.id.btnRead)
//    public void onViewClicked() {
//        if (recommendBooks == null) return;
//        ReadActivity.startActivity(this, recommendBooks);
//    }
}
