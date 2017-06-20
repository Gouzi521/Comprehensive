package com.ma.pingan.comprehensive.dagger.module;

import com.ma.pingan.comprehensive.api.Api;
import com.ma.pingan.comprehensive.api.support.HeaderInterceptor;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by mapingan
 * on 2017/6/20 0020.
 */
@Module
public class ApiModule {

    @Provides
    public OkHttpClient provideOkHttpClient(){

        OkHttpClient.Builder builder=new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true) // 失败重发
                .addInterceptor(new HeaderInterceptor());
                //.addInterceptor(logging);
        return builder.build();
    }

    @Provides
    protected Api provideApiService(OkHttpClient okHttpClient){
        return Api.getInstance(okHttpClient);
    }
}
