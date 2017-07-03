package com.ma.pingan.comprehensive.ui.activity;

import android.content.ComponentName;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ma.pingan.comprehensive.R;
import com.ma.pingan.comprehensive.music.Music;
import com.ma.pingan.comprehensive.music.service.OnPlayerEventListener;
import com.ma.pingan.comprehensive.music.service.PlayService;
import com.ma.pingan.comprehensive.ui.fragment.PlayMusicFragment;
import com.ma.pingan.comprehensive.utils.AppCache;
import com.ma.pingan.comprehensive.utils.CoverLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MusicActivity extends AppCompatActivity implements OnPlayerEventListener {


    @BindView(R.id.iv_play_bar_cover)
    ImageView ivPlayBarCover;
    @BindView(R.id.tv_play_bar_title)
    TextView tvPlayBarTitle;
    @BindView(R.id.tv_play_bar_artist)
    TextView tvPlayBarArtist;
    @BindView(R.id.iv_play_bar_play)
    ImageView ivPlayBarPlay;
    @BindView(R.id.iv_play_bar_next)
    ImageView ivPlayBarNext;
    @BindView(R.id.pb_play_bar)
    ProgressBar mProgressBar;
    private PlayMusicFragment mPlayFragment;
    private AudioManager mAudioManager;
    private ComponentName mRemoteReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        ButterKnife.bind(this);
        getPlayService().setOnPlayEventListener(this);
        //  registerReceiver();
    }

    public PlayService getPlayService() {
        PlayService playService = AppCache.getPlayService();
        if (playService == null) {
            throw new NullPointerException("play service is null");
        }
        return playService;
    }

    @Override
    public void onPublish(int progress) {
        mProgressBar.setProgress(progress);
        if (mPlayFragment != null) {
            mPlayFragment.onPublish(progress);
        }
    }

    @Override
    public void onBufferingUpdate(int percent) {
        if (mPlayFragment != null) {
            mPlayFragment.onBufferingUpdate(percent);
        }
    }

    @Override
    public void onChange(Music music) {
        onPlay(music);
        if (mPlayFragment != null) {
            mPlayFragment.onChange(music);
        }
    }

    @Override
    public void onPlayerPause() {
        ivPlayBarPlay.setSelected(false);
        if (mPlayFragment != null) {
            mPlayFragment.onPlayerPause();
        }
    }

    @Override
    public void onPlayerResume() {
        ivPlayBarPlay.setSelected(true);
        if (mPlayFragment != null) {
            mPlayFragment.onPlayerResume();
        }
    }

    @Override
    public void onTimer(long remain) {
//        if (timerItem == null) {
//            timerItem = navigationView.getMenu().findItem(R.id.action_timer);
//        }
//        String title = getString(R.string.menu_timer);
//        timerItem.setTitle(remain == 0 ? title : SystemUtils.formatTime(title + "(mm:ss)", remain));
    }

    @Override
    public void onMusicListUpdate() {

    }


    public void onPlay(Music music) {
        if (music == null) {
            return;
        }

        Bitmap cover = CoverLoader.getInstance().loadThumbnail(music);
        ivPlayBarCover.setImageBitmap(cover);
        tvPlayBarTitle.setText(music.getTitle());
        tvPlayBarArtist.setText(music.getArtist());
        if (getPlayService().isPlaying() || getPlayService().isPreparing()) {
            ivPlayBarPlay.setSelected(true);
        } else {
            ivPlayBarPlay.setSelected(false);
        }
        mProgressBar.setMax((int) music.getDuration());
        mProgressBar.setProgress(0);

//        if (mLocalMusicFragment != null) {
//            mLocalMusicFragment.onItemPlay();
//        }
    }

    @OnClick({R.id.iv_play_bar_play, R.id.iv_play_bar_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_play_bar_play:
                play();
                break;
            case R.id.iv_play_bar_next:
                next();
                break;
        }
    }

    private void next() {
        getPlayService().next();
    }

    private void play() {
        getPlayService().playPause();
    }

    @Override
    protected void onDestroy() {
        if (mRemoteReceiver != null) {
            mAudioManager.unregisterMediaButtonEventReceiver(mRemoteReceiver);
        }
        PlayService service = AppCache.getPlayService();
        if (service != null) {
            service.setOnPlayEventListener(null);
        }
        super.onDestroy();
    }
}
