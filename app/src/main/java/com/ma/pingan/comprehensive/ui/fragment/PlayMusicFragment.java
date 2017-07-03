package com.ma.pingan.comprehensive.ui.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ma.pingan.comprehensive.R;
import com.ma.pingan.comprehensive.base.BaseMusicFragment;
import com.ma.pingan.comprehensive.dagger.component.AppComponent;
import com.ma.pingan.comprehensive.music.Music;
import com.ma.pingan.comprehensive.music.enums.PlayModeEnum;
import com.ma.pingan.comprehensive.ui.adapter.PlayPagerAdapter;
import com.ma.pingan.comprehensive.utils.CoverLoader;
import com.ma.pingan.comprehensive.utils.FileUtils;
import com.ma.pingan.comprehensive.utils.Preferences;
import com.ma.pingan.comprehensive.utils.SystemUtils;
import com.ma.pingan.comprehensive.utils.ToastUtils;
import com.ma.pingan.comprehensive.widget.AlbumCoverView;
import com.ma.pingan.comprehensive.widget.IndicatorLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.wcy.lrcview.LrcView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayMusicFragment extends BaseMusicFragment {

    @BindView(R.id.iv_play_page_bg)
    ImageView ivPlayingBg;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_artist)
    TextView tvArtist;
    @BindView(R.id.vp_play_page)
    ViewPager vpPlay;
    @BindView(R.id.il_indicator)
    IndicatorLayout ilIndicator;
    @BindView(R.id.tv_current_time)
    TextView tvCurrentTime;
    @BindView(R.id.sb_progress)
    SeekBar sbProgress;
    @BindView(R.id.tv_total_time)
    TextView tvTotalTime;
    @BindView(R.id.iv_mode)
    ImageView ivMode;
    @BindView(R.id.iv_prev)
    ImageView ivPrev;
    @BindView(R.id.iv_play)
    ImageView ivPlay;
    @BindView(R.id.iv_next)
    ImageView ivNext;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    Unbinder unbinder;

    private AlbumCoverView mAlbumCoverView;
    private LrcView mLrcViewSingle;
    private LrcView mLrcViewFull;
    private SeekBar sbVolume;
    private AudioManager mAudioManager;
    private List<View> mViewPagerContent;
    private int mLastProgress;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_play_music;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void attachView() {

    }


    @Override
    public void initDatas() {

        initViewPager();
        ilIndicator.create(mViewPagerContent.size());
        initPlayMode();
        onChange(getPlayService().getPlayingMusic());
    }

    public void onChange(Music music) {
        if (isAdded()) {
            onPlay(music);
        }

    }

    private void onPlay(Music music) {
        if (music == null) {
            return;
        }

        tvTitle.setText(music.getTitle());
        tvArtist.setText(music.getArtist());
        sbProgress.setProgress(0);
        sbProgress.setSecondaryProgress(0);
        sbProgress.setMax((int) music.getDuration());
        mLastProgress = 0;
        tvCurrentTime.setText(R.string.play_time_start);
        tvTotalTime.setText(formatTime(music.getDuration()));
        setCoverAndBg(music);
        setLrc(music);
        if (getPlayService().isPlaying() || getPlayService().isPreparing()) {
            ivPlay.setSelected(true);
            mAlbumCoverView.start();
        } else {
            ivPlay.setSelected(false);
            mAlbumCoverView.pause();
        }
    }

    private void setLrc(final Music music) {
        if (music.getType() == Music.Type.LOCAL) {
            String lrcPath = FileUtils.getLrcFilePath(music);
            if (!TextUtils.isEmpty(lrcPath)) {
                loadLrc(lrcPath);
            } else {
//                new SearchLrc(music.getArtist(), music.getTitle()) {
//                    @Override
//                    public void onPrepare() {
//                        // 设置tag防止歌词下载完成后已切换歌曲
//                        vpPlay.setTag(music);
//
//                        loadLrc("");
//                        setLrcLabel("正在搜索歌词");
//                    }
//
//                    @Override
//                    public void onExecuteSuccess(@NonNull String lrcPath) {
//                        if (vpPlay.getTag() != music) {
//                            return;
//                        }
//
//                        // 清除tag
//                        vpPlay.setTag(null);
//
//                        loadLrc(lrcPath);
//                        setLrcLabel("暂无歌词");
//                    }
//
//                    @Override
//                    public void onExecuteFail(Exception e) {
//                        if (vpPlay.getTag() != music) {
//                            return;
//                        }
//
//                        // 清除tag
//                        vpPlay.setTag(null);
//
//                        setLrcLabel("暂无歌词");
//                    }
//                }.execute();
            }
        } else {
            String lrcPath = FileUtils.getLrcDir() + FileUtils.getLrcFileName(music.getArtist(), music.getTitle());
            loadLrc(lrcPath);
        }
    }

    private void loadLrc(String path) {
        File file = new File(path);
        mLrcViewSingle.loadLrc(file);
        mLrcViewFull.loadLrc(file);
    }

    private void setLrcLabel(String label) {
        mLrcViewSingle.setLabel(label);
        mLrcViewFull.setLabel(label);
    }

    private void setCoverAndBg(Music music) {
        mAlbumCoverView.setCoverBitmap(CoverLoader.getInstance().loadRound(music));
        ivPlayingBg.setImageBitmap(CoverLoader.getInstance().loadBlur(music));
    }

    private void initPlayMode() {
        int mode = Preferences.getPlayMode();
        ivMode.setImageLevel(mode);
    }

    private void initViewPager() {
        View coverView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_play_page_cover, null);
        View lrcView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_play_page_lrc, null);
        mAlbumCoverView = (AlbumCoverView) coverView.findViewById(R.id.album_cover_view);
        mLrcViewSingle = (LrcView) coverView.findViewById(R.id.lrc_view_single);
        mLrcViewFull = (LrcView) lrcView.findViewById(R.id.lrc_view_full);
        sbVolume = (SeekBar) lrcView.findViewById(R.id.sb_volume);
        mAlbumCoverView.initNeedle(getPlayService().isPlaying());
        initVolume();

        mViewPagerContent = new ArrayList<>(2);
        mViewPagerContent.add(coverView);
        mViewPagerContent.add(lrcView);
        vpPlay.setAdapter(new PlayPagerAdapter(mViewPagerContent));
    }

    private void initVolume() {
        mAudioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
        sbVolume.setMax(mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        sbVolume.setProgress(mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
    }

    @Override
    public void configViews() {

    }


    public void onPublish(int progress) {

        if (isAdded()) {
            sbProgress.setProgress(progress);
            if (mLrcViewSingle.hasLrc()) {
                mLrcViewSingle.updateTime(progress);
                mLrcViewFull.updateTime(progress);
            }
            //更新当前播放时间
            if (progress - mLastProgress >= 1000) {
                tvCurrentTime.setText(formatTime(progress));
                mLastProgress = progress;
            }
        }
    }

    private String formatTime(long time) {
        return SystemUtils.formatTime("mm:ss", time);
    }

    public void onPlayerResume() {
        if (isAdded()) {
            ivPlay.setSelected(true);
            mAlbumCoverView.start();
        }
    }

    public void onPlayerPause() {
        if (isAdded()) {
            ivPlay.setSelected(false);
            mAlbumCoverView.pause();
        }
    }

    public void onBufferingUpdate(int percent) {

        if (isAdded()) {
            sbProgress.setSecondaryProgress(sbProgress.getMax() * 100 / percent);
        }
    }


    @OnClick({R.id.iv_back, R.id.iv_mode, R.id.iv_prev, R.id.iv_play, R.id.iv_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.iv_mode:
                switchPlayMode();
                break;
            case R.id.iv_prev:
                prev();
                break;
            case R.id.iv_play:
                play();
                break;
            case R.id.iv_next:
                next();
                break;
        }
    }

    private void onBackPressed() {
        getActivity().onBackPressed();
        ivBack.setEnabled(false);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ivBack.setEnabled(true);
            }
        }, 300);
    }

    private void switchPlayMode() {
        PlayModeEnum mode = PlayModeEnum.valueOf(Preferences.getPlayMode());
        switch (mode) {
            case LOOP:
                mode = PlayModeEnum.SHUFFLE;
                ToastUtils.showLongToast(R.string.mode_shuffle);
                break;
            case SHUFFLE:
                mode = PlayModeEnum.SINGLE;
                ToastUtils.showLongToast(R.string.mode_one);
                break;
            case SINGLE:
                mode = PlayModeEnum.LOOP;
                ToastUtils.showLongToast(R.string.mode_loop);
                break;
        }
        Preferences.savePlayMode(mode.value());
        initPlayMode();
    }

    private void play() {
        getPlayService().playPause();
    }

    private void next() {
        getPlayService().next();
    }

    private void prev() {
        getPlayService().prev();
    }

    private BroadcastReceiver mVolumeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            sbVolume.setProgress(mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
        }
    };

    @Override
    public void onDestroy() {
        getContext().unregisterReceiver(mVolumeReceiver);
        super.onDestroy();
    }
}
