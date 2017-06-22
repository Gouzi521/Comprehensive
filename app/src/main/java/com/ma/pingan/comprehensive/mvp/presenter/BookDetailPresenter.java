package com.ma.pingan.comprehensive.mvp.presenter;

import com.ma.pingan.comprehensive.api.Api;
import com.ma.pingan.comprehensive.base.BasePresenter;
import com.ma.pingan.comprehensive.bean.BookDetail;
import com.ma.pingan.comprehensive.bean.HotReview;
import com.ma.pingan.comprehensive.bean.RecommendBookList;
import com.ma.pingan.comprehensive.mvp.contract.BookDetailContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by PingAn
 * on 2017/6/22 0022
 */

public class BookDetailPresenter extends BasePresenter<BookDetailContract.View> implements BookDetailContract.Presenter<BookDetailContract.View>{


    private Api api;

    private static final String TAG = "BookDetailPresenter";


    @Inject
    public BookDetailPresenter(Api api) {
        this.api = api;
    }

    @Override
    public void getBookDetail(String bookId) {

        api.getBookDetail(bookId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BookDetail>() {
                    @Override
                    public void accept(@NonNull BookDetail data) throws Exception {
                        if (data != null && mView != null) {
                            mView.showBookDetail(data);
                        }
                    }
                });
    }

    @Override
    public void getHotReview(String book) {

        api.getHotReview(book)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HotReview>() {
                    @Override
                    public void accept(@NonNull HotReview data) throws Exception {

                        List<HotReview.Reviews> list = data.reviews;
                        if (list != null && !list.isEmpty() && mView != null) {
                            mView.showHotReview(list);
                        }
                    }
                });
    }

    @Override
    public void getRecommendBookList(String bookId, String limit) {

        api.getRecommendBookList(bookId, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RecommendBookList>() {
                    @Override
                    public void accept(@NonNull RecommendBookList data) throws Exception {
                        List<RecommendBookList.RecommendBook> list = data.booklists;
                        if (list != null && !list.isEmpty() && mView != null) {
                            mView.showRecommendBookList(list);
                        }
                    }
                });
    }
}
