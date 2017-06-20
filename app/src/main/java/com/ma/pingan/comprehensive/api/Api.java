package com.ma.pingan.comprehensive.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.ma.pingan.comprehensive.bean.Ranking;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mapingan
 * on 2017/6/20 0020.
 */

public class Api {

    public static Api instance;

    private ApiService service;

    public Api(OkHttpClient okHttpClient){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service=retrofit.create(ApiService.class);
    }

    public static Api getInstance(OkHttpClient okHttpClient){
        if (instance==null)
            instance=new Api(okHttpClient);
        return instance;
    }

    public Observable<Ranking> getRanking(String rankingId){
        return service.getRanking(rankingId);
    }
}
