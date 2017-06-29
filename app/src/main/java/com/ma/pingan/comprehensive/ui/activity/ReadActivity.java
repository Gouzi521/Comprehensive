package com.ma.pingan.comprehensive.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.ListPopupWindow;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ma.pingan.comprehensive.R;
import com.ma.pingan.comprehensive.base.BaseActivity;
import com.ma.pingan.comprehensive.base.Constant;
import com.ma.pingan.comprehensive.bean.BookMixAToc;
import com.ma.pingan.comprehensive.bean.ChapterRead;
import com.ma.pingan.comprehensive.bean.Recommend;
import com.ma.pingan.comprehensive.dagger.component.AppComponent;
import com.ma.pingan.comprehensive.dagger.component.DaggerActivityComponent;
import com.ma.pingan.comprehensive.manger.CacheManager;
import com.ma.pingan.comprehensive.mvp.contract.BookReadContract;
import com.ma.pingan.comprehensive.mvp.presenter.BookReadPresenter;
import com.ma.pingan.comprehensive.ui.adapter.TocListAdapter;
import com.ma.pingan.comprehensive.utils.LogUtils;
import com.ma.pingan.comprehensive.utils.SharedPreferencesUtil;
import com.ma.pingan.comprehensive.widget.readview.BaseReadView;
import com.ma.pingan.comprehensive.widget.readview.OnReadStateChangeListener;
import com.ma.pingan.comprehensive.widget.readview.OverlappedWidget;
import com.ma.pingan.comprehensive.widget.readview.PageWidget;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReadActivity extends BaseActivity implements BookReadContract.View {

    @BindView(R.id.flReadWidget)
    FrameLayout flReadWidget;
    @BindView(R.id.ivBack)
    ImageView mIvBack;
    @BindView(R.id.tvBookReadTocTitle)
    TextView tvBookReadTocTitle;
    @BindView(R.id.tvBookReadReading)
    TextView mTvBookReadReading;
    @BindView(R.id.tvBookReadCommunity)
    TextView mTvBookReadCommunity;
    @BindView(R.id.tvBookReadIntroduce)
    TextView mTvBookReadIntroduce;
    @BindView(R.id.tvBookReadSource)
    TextView mTvBookReadSource;
    @BindView(R.id.llBookReadTop)
    LinearLayout mLlBookReadTop;
    @BindView(R.id.tvDownloadProgress)
    TextView mTvDownloadProgress;
    @BindView(R.id.ivBrightnessMinus)
    ImageView ivBrightnessMinus;
    @BindView(R.id.seekbarLightness)
    SeekBar seekbarLightness;
    @BindView(R.id.ivBrightnessPlus)
    ImageView ivBrightnessPlus;
    @BindView(R.id.tvFontsizeMinus)
    TextView mTvFontsizeMinus;
    @BindView(R.id.seekbarFontSize)
    SeekBar seekbarFontSize;
    @BindView(R.id.tvFontsizePlus)
    TextView mTvFontsizePlus;
    @BindView(R.id.cbVolume)
    CheckBox cbVolume;
    @BindView(R.id.cbAutoBrightness)
    CheckBox cbAutoBrightness;
    @BindView(R.id.gvTheme)
    GridView gvTheme;
    @BindView(R.id.rlReadAaSet)
    LinearLayout rlReadAaSet;
    @BindView(R.id.tvAddMark)
    TextView mTvAddMark;
    @BindView(R.id.tvClear)
    TextView mTvClear;
    @BindView(R.id.lvMark)
    ListView lvMark;
    @BindView(R.id.rlReadMark)
    LinearLayout rlReadMark;
    @BindView(R.id.tvBookReadMode)
    TextView mTvBookReadMode;
    @BindView(R.id.tvBookReadSettings)
    TextView mTvBookReadSettings;
    @BindView(R.id.tvBookReadDownload)
    TextView mTvBookReadDownload;
    @BindView(R.id.tvBookMark)
    TextView mTvBookMark;
    @BindView(R.id.tvBookReadToc)
    TextView mTvBookReadToc;
    @BindView(R.id.llBookReadBottom)
    LinearLayout mLlBookReadBottom;
    @BindView(R.id.rlBookReadRoot)
    RelativeLayout rlBookReadRoot;

    private Receiver receiver = new Receiver();
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    private IntentFilter intentFilter = new IntentFilter();
    @Inject
    BookReadPresenter mPresenter;

    private BaseReadView mPageWidget;
    public static final String INTENT_BEAN = "recommendBooksBean";
    public static final String INTENT_SD = "isFromSD";
    private Recommend.RecommendBooks recommendBooks;
    private String bookId;
    private boolean isAutoLightness = false; // 记录其他页面是否自动调整亮度
    private boolean isFromSD = false;
   // private TocListAdapter mTocListAdapter;
    private int curTheme = -1;
    private List<BookMixAToc.mixToc.Chapters> mChapterList = new ArrayList<>();
    /**
     * 是否开始阅读章节
     **/
    private boolean startRead = false;

    private View decodeView;
    //添加收藏需要，所以跳转的时候传递整个实体类
    public static void startActivity(Context context, Recommend.RecommendBooks recommendBooks) {
        startActivity(context, recommendBooks, false);
    }

    public static void startActivity(Context context, Recommend.RecommendBooks recommendBooks, boolean isFromSD) {
        context.startActivity(new Intent(context, ReadActivity.class)
                .putExtra(INTENT_BEAN, recommendBooks)
                .putExtra(INTENT_SD, false));
    }
    private ListPopupWindow mTocListPopupWindow;

    private int currentChapter = 1;

    @Override
    protected void configViews() {
       // hideStatusBar();
        decodeView = getWindow().getDecorView();
        initTocList();
        initPagerWidget();
        mPresenter.attachView(this);
        mPresenter.getBookMixAToc(bookId, "chapters");
    }

    private void initPagerWidget() {
        if (SharedPreferencesUtil.getInstance().getInt(Constant.FLIP_STYLE, 0) == 0) {
            mPageWidget = new PageWidget(this, bookId, mChapterList, new ReadListener());
        } else {
            mPageWidget = new OverlappedWidget(this, bookId, mChapterList, new ReadListener());
        }
        registerReceiver(receiver, intentFilter);
        if (SharedPreferencesUtil.getInstance().getBoolean(Constant.ISNIGHT, false)) {
            mPageWidget.setTextColor(ContextCompat.getColor(this, R.color.chapter_content_night),
                    ContextCompat.getColor(this, R.color.chapter_title_night));
        }
        flReadWidget.removeAllViews();
        flReadWidget.addView(mPageWidget);
    }

    private class ReadListener implements OnReadStateChangeListener {
        @Override
        public void onChapterChanged(int chapter) {
            LogUtils.i("onChapterChanged:" + chapter);
            currentChapter = chapter;
           // mTocListAdapter.setCurrentChapter(currentChapter);
            // 加载前一节 与 后三节
            for (int i = chapter - 1; i <= chapter + 3 && i <= mChapterList.size(); i++) {
                if (i > 0 && i != chapter
                        && CacheManager.getInstance().getChapterFile(bookId, i) == null) {
                    mPresenter.getChapterRead(mChapterList.get(i - 1).link, i);
                }
            }
        }

        @Override
        public void onPageChanged(int chapter, int page) {
            LogUtils.i("onPageChanged:" + chapter + "-" + page);
        }

        @Override
        public void onLoadChapterFailure(int chapter) {
            LogUtils.i("onLoadChapterFailure:" + chapter);
            startRead = false;
            if (CacheManager.getInstance().getChapterFile(bookId, chapter) == null)
                mPresenter.getChapterRead(mChapterList.get(chapter - 1).link, chapter);
        }

        @Override
        public void onCenterClick() {
            LogUtils.i("onCenterClick");
          //  toggleReadBar();
        }

        @Override
        public void onFlip() {
            hideReadBar();
        }
    }

    private synchronized void hideReadBar() {
        gone(mTvDownloadProgress, mLlBookReadBottom, mLlBookReadTop, rlReadAaSet, rlReadMark);
        hideStatusBar();
        decodeView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
    }



    class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (mPageWidget != null) {
                if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
                    int level = intent.getIntExtra("level", 0);
                    mPageWidget.setBattery(100 - level);
                } else if (Intent.ACTION_TIME_TICK.equals(intent.getAction())) {
                    mPageWidget.setTime(sdf.format(new Date()));
                }
            }
        }
    }


    private void hideStatusBar() {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
//        if(statusBarView != null){
//            statusBarView.setBackgroundColor(Color.TRANSPARENT);
//        }
    }

    private void initTocList() {
//        mTocListAdapter = new TocListAdapter(R.layout.item_book_read_toc_list,mChapterList, bookId, currentChapter);
//        mTocListPopupWindow = new ListPopupWindow(this);
//        mTocListPopupWindow.setAdapter(mTocListAdapter);
//        mTocListPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//        mTocListPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
//        mTocListPopupWindow.setAnchorView(mLlBookReadTop);
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initData() {
        recommendBooks = (Recommend.RecommendBooks) getIntent().getSerializableExtra(INTENT_BEAN);
        bookId = recommendBooks._id;
        isFromSD = getIntent().getBooleanExtra(INTENT_SD, false);
        tvBookReadTocTitle.setText(recommendBooks.title);
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        intentFilter.addAction(Intent.ACTION_TIME_TICK);
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

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
      //  statusBarColor = ContextCompat.getColor(this, R.color.reader_menu_bg_color);
        return R.layout.activity_read;
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    //加载章节列表
    @Override
    public void showBookToc(List<BookMixAToc.mixToc.Chapters> list) {
        mChapterList.clear();
        mChapterList.addAll(list);

        readCurrentChapter();
    }

    private void readCurrentChapter() {
        mPresenter.getChapterRead(mChapterList.get(currentChapter - 1).link, currentChapter);
    }

    @Override
    public void showChapterRead(ChapterRead.Chapter data, int chapter) {
        if (!startRead) {
            startRead = true;
            currentChapter = chapter;
            if (!mPageWidget.isPrepared) {
                mPageWidget.init(curTheme);
            } else {
                mPageWidget.jumpToChapter(currentChapter);
            }
            //  hideDialog();
        }
    }

    @Override
    public void netError(int chapter) {

    }



    @OnClick(R.id.tvBookReadToc)
    public void onViewClicked() {
        gone(rlReadAaSet, rlReadMark);
        if (!mTocListPopupWindow.isShowing()) {
            visible(tvBookReadTocTitle);
            gone(mTvBookReadReading, mTvBookReadCommunity, mTvBookReadIntroduce);
            mTocListPopupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
            mTocListPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            mTocListPopupWindow.show();
            mTocListPopupWindow.setSelection(currentChapter - 1);
            mTocListPopupWindow.getListView().setFastScrollEnabled(true);
        }
    }


    protected void gone(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.GONE);
                }
            }
        }
    }

    protected void visible(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.VISIBLE);
                }
            }
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
