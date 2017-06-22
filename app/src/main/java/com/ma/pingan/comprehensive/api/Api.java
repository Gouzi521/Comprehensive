package com.ma.pingan.comprehensive.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.ma.pingan.comprehensive.base.Constant;
import com.ma.pingan.comprehensive.bean.BooksByCats;
import com.ma.pingan.comprehensive.bean.CategoryList;
import com.ma.pingan.comprehensive.bean.CategoryListLv2;
import com.ma.pingan.comprehensive.bean.Ranking;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;

/**
 * Created by mapingan
 * on 2017/6/20 0020.
 */

public class Api {

    public static Api instance;

    private static ApiService service;

    public Api(OkHttpClient okHttpClient){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Constant.API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
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

    public static Observable<BooksByCats> getBooksByCats(String gender, String type, String major, String minor, int start, @Query("limit") int limit) {
        return service.getBooksByCats(gender, type, major, minor, start, limit);
    }

    public  Observable<CategoryList> getCategoryList() {
        return service.getCategoryList();
    }
    public static Observable<CategoryListLv2> getCategoryListLv2() {
        return service.getCategoryListLv2();
    }
}
