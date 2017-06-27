package com.ma.pingan.comprehensive.mvp.presenter;

import com.ma.pingan.comprehensive.api.Api;
import com.ma.pingan.comprehensive.base.BasePresenter;
import com.ma.pingan.comprehensive.bean.BooksByTag;
import com.ma.pingan.comprehensive.mvp.contract.SearchByAuthorContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by PingAn
 * on 2017/6/27 0027
 */

public class SearchByAuthorPresenter extends BasePresenter<SearchByAuthorContract.View> implements SearchByAuthorContract.Presenter{

    private Api api;

    @Inject
    public SearchByAuthorPresenter(Api api) {
        this.api = api;
    }

    @Override
    public void getSearchResultList(String author) {

        api.searchBooksByAuthor(author)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BooksByTag>() {
                    @Override
                    public void accept(@NonNull BooksByTag booksByTag) throws Exception {
                        if (mView != null)
                            mView.showSearchResultList(booksByTag.books);
                    }
                });
    }
}
