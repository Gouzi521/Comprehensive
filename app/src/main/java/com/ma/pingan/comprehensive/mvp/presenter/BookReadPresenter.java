package com.ma.pingan.comprehensive.mvp.presenter;

import android.content.Context;

import com.ma.pingan.comprehensive.api.Api;
import com.ma.pingan.comprehensive.base.BasePresenter;
import com.ma.pingan.comprehensive.bean.BookMixAToc;
import com.ma.pingan.comprehensive.bean.ChapterRead;
import com.ma.pingan.comprehensive.mvp.contract.BookReadContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mapingan
 * on 2017/6/27 0027.
 */

public class BookReadPresenter extends BasePresenter<BookReadContract.View> implements BookReadContract.Presenter<BookReadContract.View>{


    private Api api;

    @Inject
    public BookReadPresenter(Api api) {

        this.api = api;
    }

    @Override
    public void getBookMixAToc(String bookId, String view) {

        api.getBookMixAToc(bookId,view)
                .map(new Function<BookMixAToc, BookMixAToc.mixToc>() {
                    @Override
                    public BookMixAToc.mixToc apply(@NonNull BookMixAToc data) throws Exception {
                        return data.mixToc;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BookMixAToc.mixToc>() {
                    @Override
                    public void accept(@NonNull BookMixAToc.mixToc data) throws Exception {
                        List<BookMixAToc.mixToc.Chapters> list = data.chapters;
                        if (list != null && !list.isEmpty() && mView != null) {
                            mView.showBookToc(list);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.netError(0);
                    }
                });
    }

    @Override
    public void getChapterRead(String url, final int chapter) {

        api.getChapterRead(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ChapterRead>() {
                    @Override
                    public void accept(@NonNull ChapterRead data) throws Exception {
                        if (data.chapter != null && mView != null) {
                            mView.showChapterRead(data.chapter, chapter);
                        } else {
                            mView.netError(chapter);
                        }
                    }
                });
    }
}
