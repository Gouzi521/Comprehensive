package com.ma.pingan.comprehensive.dagger.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mapingan
 * on 2017/6/20 0020.
 */
@Module
public class AppModule {

    private Context context;

    public AppModule(Context context){
        this.context=context;
    }

    @Provides
    public Context provideContext(){
        return context;
    }
}
