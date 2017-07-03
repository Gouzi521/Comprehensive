package com.ma.pingan.comprehensive.base;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.ma.pingan.comprehensive.music.service.PlayService;
import com.ma.pingan.comprehensive.utils.AppCache;
import com.ma.pingan.comprehensive.utils.permission.PermissionReq;

/**
 * Created by mapingan
 * on 2017/7/3 0003.
 */

public abstract class BaseMusicFragment extends BaseFragment {

    protected Handler mHandler = new Handler(Looper.getMainLooper());


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionReq.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    protected PlayService getPlayService() {
        PlayService playService = AppCache.getPlayService();
        if (playService == null) {
            throw new NullPointerException("play service is null");
        }
        return playService;
    }
}
