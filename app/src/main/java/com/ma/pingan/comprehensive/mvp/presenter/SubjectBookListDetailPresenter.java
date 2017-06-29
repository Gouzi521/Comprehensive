package com.ma.pingan.comprehensive.mvp.presenter;

import com.ma.pingan.comprehensive.api.Api;
import com.ma.pingan.comprehensive.base.BasePresenter;
import com.ma.pingan.comprehensive.bean.BookListDetail;
import com.ma.pingan.comprehensive.mvp.contract.SubjectBookListDetailContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mapingan
 * on 2017/6/29 0029.
 */

public class SubjectBookListDetailPresenter extends BasePresenter<SubjectBookListDetailContract.View> implements SubjectBookListDetailContract.Presenter<SubjectBookListDetailContract.View>{


    private Api api;

    @Inject
    public SubjectBookListDetailPresenter(Api api) {
        this.api = api;
    }

    @Override
    public void getBookListDetail(String bookListId) {

        api.getBookListDetail(bookListId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BookListDetail>() {
                    @Override
                    public void accept(@NonNull BookListDetail bookListDetail) throws Exception {
                        mView.showBookListDetail(bookListDetail);
                    }
                });

    }
}
