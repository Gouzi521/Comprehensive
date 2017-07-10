package com.ma.pingan.comprehensive.ui.fragment;


import android.content.ComponentName;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ma.pingan.comprehensive.R;
import com.ma.pingan.comprehensive.music.Music;
import com.ma.pingan.comprehensive.music.service.OnPlayerEventListener;
import com.ma.pingan.comprehensive.music.service.PlayService;
import com.ma.pingan.comprehensive.utils.AppCache;

/**
 * A simple {@link Fragment} subclass.
 */
public class PictureFragment extends Fragment implements OnPlayerEventListener {


    private View rootView;
    private AudioManager mAudioManager;
    private ComponentName mRemoteReceiver;

    public PictureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_picture, container, false);
//        getPlayService().setOnPlayEventListener(this);
//        registerReceiver();
//        onChange(getPlayService().getPlayingMusic());

        return rootView;
    }

    private void registerReceiver() {


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

    }

    @Override
    public void onBufferingUpdate(int percent) {

    }

    @Override
    public void onChange(Music music) {

    }

    @Override
    public void onPlayerPause() {

    }

    @Override
    public void onPlayerResume() {

    }

    @Override
    public void onTimer(long remain) {

    }

    @Override
    public void onMusicListUpdate() {

    }
}
