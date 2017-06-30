package com.ma.pingan.comprehensive.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ma.pingan.comprehensive.R;
import com.ma.pingan.comprehensive.api.Api;
import com.ma.pingan.comprehensive.base.BaseActivity;
import com.ma.pingan.comprehensive.base.Constant;
import com.ma.pingan.comprehensive.bilientity.VideoDetailsInfo;
import com.ma.pingan.comprehensive.dagger.component.AppComponent;
import com.ma.pingan.comprehensive.dagger.component.DaggerActivityComponent;
import com.ma.pingan.comprehensive.ui.fragment.VideoCommentFragment;
import com.ma.pingan.comprehensive.ui.fragment.VideoIntroductionFragment;
import com.ma.pingan.comprehensive.utils.DisplayUtil;
import com.ma.pingan.comprehensive.utils.SystemBarHelper;
import com.ma.pingan.comprehensive.utils.UrlHelper;
import com.ma.pingan.comprehensive.widget.AppBarStateChangeEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;

public class VideoDetailsActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    @BindView(R.id.video_preview)
    ImageView mVideoPreview;

    @BindView(R.id.tablayout)
    TabLayout mTabLayout;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @BindView(R.id.fab)
    FloatingActionButton mFAB;

    @BindView(R.id.app_bar_layout)
    AppBarLayout mAppBarLayout;

    @BindView(R.id.tv_player)
    TextView mTvPlayer;

    @BindView(R.id.tv_av)
    TextView mAvText;

    private List<Fragment> fragments = new ArrayList<>();

    private List<String> titles = new ArrayList<>();

    private int av;

    private String imgUrl;

    private VideoDetailsInfo.DataBean mVideoDetailsInfo;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_details;
    }

    @Override
    protected void initToolBar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        //设置还没收缩时状态下字体颜色
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);
        //设置收缩后Toolbar上字体的颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);

        //设置StatusBar透明
        SystemBarHelper.immersiveStatusBar(this);
        SystemBarHelper.setHeightAndPadding(this, mToolbar);

        mAvText.setText("av" + av);
    }

    @Override
    protected void initInjector(AppComponent appComponent) {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            av = intent.getIntExtra(Constant.EXTRA_AV, -1);
            imgUrl = intent.getStringExtra(Constant.EXTRA_IMG_URL);
        }

        loadData();
        Glide.with(VideoDetailsActivity.this)
                .load(UrlHelper.getClearVideoPreviewUrl(imgUrl))
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.bili_default_image_tv)
                .dontAnimate()
                .into(mVideoPreview);
    }

    private void loadData() {
        Api.getInstance(new OkHttpClient())
                .getVideoDetails(av)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(videoDetailsInfo -> {
                    mVideoDetailsInfo = videoDetailsInfo.getData();
                    finishTask();
                }, throwable -> {
                    mFAB.setClickable(false);
                    mFAB.setBackgroundTintList(ColorStateList.valueOf(
                            getResources().getColor(R.color.gray_20)));
                });



    }

    private void finishTask() {
        mFAB.setClickable(true);
        mFAB.setBackgroundTintList(
                ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
        mCollapsingToolbarLayout.setTitle("");

        if (TextUtils.isEmpty(imgUrl)) {
            Glide.with(VideoDetailsActivity.this)
                    .load(mVideoDetailsInfo)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.bili_default_image_tv)
                    .dontAnimate()
                    .into(mVideoPreview);
        }

        VideoIntroductionFragment mVideoIntroductionFragment=new VideoIntroductionFragment();
        VideoCommentFragment mVideoCommentFragment=new VideoCommentFragment();
        fragments.add(mVideoIntroductionFragment);
        fragments.add(mVideoCommentFragment);
        setPagerTitle(String.valueOf(mVideoDetailsInfo.getStat().getReply()));
    }

    private void setPagerTitle(String num) {

        titles.add("简介");
        titles.add("评论" + "(" + num + ")");

        VideoDetailsPagerAdapter mAdapter = new VideoDetailsPagerAdapter(getSupportFragmentManager(),
                fragments, titles);

        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(2);
        mTabLayout.setupWithViewPager(mViewPager);

        measureTabLayoutTextWidth(0);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }


            @Override
            public void onPageSelected(int position) {

                measureTabLayoutTextWidth(position);
            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void measureTabLayoutTextWidth(int position) {

//        String title = titles.get(position);
//        TextView titleView = mTabLayout.get
//        TextPaint paint = titleView.getPaint();
//        float textWidth = paint.measureText(title);
//        mSlidingTabLayout.setIndicatorWidth(textWidth / 3);
    }
    @Override
    protected void configViews() {


        mFAB.setClickable(false);
        mFAB.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray_20)));
        mFAB.setTranslationY(-getResources().getDimension(R.dimen.floating_action_button_size_half));
        mFAB.setOnClickListener(v -> {
           //调整到播放界面
        });
        mAppBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) ->
            setViewsTranslation(verticalOffset));

        mAppBarLayout.addOnOffsetChangedListener(new AppBarStateChangeEvent() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state, int verticalOffset) {

                if (state == State.EXPANDED) {
                    //展开状态
                    mTvPlayer.setVisibility(View.GONE);
                    mAvText.setVisibility(View.VISIBLE);
                    mToolbar.setContentInsetsRelative(DisplayUtil.dp2px(VideoDetailsActivity.this, 15), 0);
                } else if (state == State.COLLAPSED) {
                    //折叠状态
                    mTvPlayer.setVisibility(View.VISIBLE);
                    mAvText.setVisibility(View.GONE);
                    mToolbar.setContentInsetsRelative(DisplayUtil.dp2px(VideoDetailsActivity.this, 150), 0);
                } else {
                    mTvPlayer.setVisibility(View.GONE);
                    mAvText.setVisibility(View.VISIBLE);
                    mToolbar.setContentInsetsRelative(DisplayUtil.dp2px(VideoDetailsActivity.this, 15), 0);
                }
            }
        });
    }

    private void setViewsTranslation(int target) {
        mFAB.setTranslationY(target);
        if (target == 0) {
            showFAB();
        } else if (target < 0) {
            hideFAB();
        }
    }

    private void showFAB() {

        mFAB.animate().scaleX(1f).scaleY(1f)
                .setInterpolator(new OvershootInterpolator())
                .start();

        mFAB.setClickable(true);
    }

    private void hideFAB() {

        mFAB.animate().scaleX(0f).scaleY(0f)
                .setInterpolator(new AccelerateInterpolator())
                .start();

        mFAB.setClickable(false);
    }

    public static void launch(Activity activity, int aid, String imgUrl) {

        Intent intent = new Intent(activity, VideoDetailsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constant.EXTRA_AV, aid);
        intent.putExtra(Constant.EXTRA_IMG_URL, imgUrl);
        activity.startActivity(intent);
    }

    public static class VideoDetailsPagerAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> fragments;

        private List<String> titles;


        VideoDetailsPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {

            super(fm);
            this.fragments = fragments;
            this.titles = titles;
        }


        @Override
        public Fragment getItem(int position) {

            return fragments.get(position);
        }


        @Override
        public int getCount() {

            return fragments.size();
        }


        @Override
        public CharSequence getPageTitle(int position) {

            return titles.get(position);
        }
    }
}
