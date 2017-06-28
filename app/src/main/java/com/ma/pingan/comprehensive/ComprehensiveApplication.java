package com.ma.pingan.comprehensive;

import android.app.Application;
import android.content.Context;


import com.ma.pingan.comprehensive.dagger.component.AppComponent;

import com.ma.pingan.comprehensive.dagger.component.DaggerAppComponent;
import com.ma.pingan.comprehensive.dagger.module.ApiModule;
import com.ma.pingan.comprehensive.dagger.module.AppModule;
import com.ma.pingan.comprehensive.utils.AppUtils;
import com.ma.pingan.comprehensive.utils.SharedPreferencesUtil;

/**
 * Created by mapingan
 * on 2017/6/20 0020.
 */

public class ComprehensiveApplication extends Application{

    private static ComprehensiveApplication mInstance;

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
        initCompoent();
        AppUtils.init(this);
        initPrefs();
    }


    public static ComprehensiveApplication getsInstance(){
        return mInstance;
    }

    private void initCompoent() {

        appComponent= DaggerAppComponent.builder()
                .apiModule(new ApiModule())
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    /**
     * 初始化SharedPreference
     */
    protected void initPrefs() {
        SharedPreferencesUtil.init(getApplicationContext(), getPackageName() + "_preference", Context.MODE_MULTI_PROCESS);
    }
}
