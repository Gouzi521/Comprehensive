package com.ma.pingan.comprehensive.dagger.component;

import android.content.Context;

import com.ma.pingan.comprehensive.api.Api;
import com.ma.pingan.comprehensive.dagger.module.ApiModule;
import com.ma.pingan.comprehensive.dagger.module.AppModule;

import dagger.Component;

/**
 * Created by mapingan
 * on 2017/6/20 0020.
 */

@Component (modules = {AppModule.class,ApiModule.class})
public interface AppComponent {

    Context getContext();

    Api getApi();
}
