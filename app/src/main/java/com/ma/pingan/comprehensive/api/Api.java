package com.ma.pingan.comprehensive.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.ma.pingan.comprehensive.base.Constant;
import com.ma.pingan.comprehensive.bean.BookDetail;
import com.ma.pingan.comprehensive.bean.BookListDetail;
import com.ma.pingan.comprehensive.bean.BookLists;
import com.ma.pingan.comprehensive.bean.BookMixAToc;
import com.ma.pingan.comprehensive.bean.BooksByCats;
import com.ma.pingan.comprehensive.bean.BooksByTag;
import com.ma.pingan.comprehensive.bean.CategoryList;
import com.ma.pingan.comprehensive.bean.CategoryListLv2;
import com.ma.pingan.comprehensive.bean.ChapterRead;
import com.ma.pingan.comprehensive.bean.HotReview;
import com.ma.pingan.comprehensive.bean.Ranking;
import com.ma.pingan.comprehensive.bean.RecommendBookList;

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

    public Api(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Constant.API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
              //  .client(okHttpClient)
                .build();

        service=retrofit.create(ApiService.class);
    }

    public static Api getInstance(OkHttpClient okHttpClient){
        if (instance==null)
            instance=new Api();
        return instance;
    }

    public Observable<BookListDetail> getBookListDetail(String bookListId) {
        return service.getBookListDetail(bookListId);
    }
    public Observable<BookLists> getBookLists(String duration, String sort, String start, String limit, String tag, String gender) {
        return service.getBookLists(duration, sort, start, limit, tag, gender);
    }

    public Observable<Ranking> getRanking(String rankingId){
        return service.getRanking(rankingId);
    }

    public  Observable<BooksByCats> getBooksByCats(String gender, String type, String major, String minor, int start, @Query("limit") int limit) {
        return service.getBooksByCats(gender, type, major, minor, start, limit);
    }

    public  Observable<CategoryList> getCategoryList() {
        return service.getCategoryList();
    }
    public  Observable<CategoryListLv2> getCategoryListLv2() {
        return service.getCategoryListLv2();
    }

    public Observable<RecommendBookList> getRecommendBookList(String bookId, String limit) {
        return service.getRecommendBookList(bookId, limit);
    }

    public Observable<HotReview> getHotReview(String book) {
        return service.getHotReview(book);
    }

    public Observable<BookDetail> getBookDetail(String bookId) {
        return service.getBookDetail(bookId);
    }

    public Observable<BookMixAToc> getBookMixAToc(String bookId, String view) {
        return service.getBookMixAToc(bookId, view);
    }

    public synchronized Observable<ChapterRead> getChapterRead(String url) {
        return service.getChapterRead(url);
    }

    public Observable<BooksByTag> searchBooksByAuthor(String author) {
        return service.searchBooksByAuthor(author);
    }
}
