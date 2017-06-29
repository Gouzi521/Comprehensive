package com.ma.pingan.comprehensive.mvp.presenter;

import android.util.Log;

import com.ma.pingan.comprehensive.api.Api;
import com.ma.pingan.comprehensive.base.BasePresenter;
import com.ma.pingan.comprehensive.bean.BookLists;
import com.ma.pingan.comprehensive.mvp.contract.SubjectFragmentContract;
import com.ma.pingan.comprehensive.utils.ToastUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mapingan
 * on 2017/6/28 0028.
 */

public class SubjectFragmentPresenter extends BasePresenter<SubjectFragmentContract.View> implements SubjectFragmentContract.Presenter<SubjectFragmentContract.View> {

    private Api api;

    @Inject
    public SubjectFragmentPresenter(Api api) {
        this.api = api;
    }

    @Override
    public void getBookLists(String duration, String sort, final int start, int limit, final String tag, String gender) {

        api.getBookLists(duration, sort, start + "", limit + "", "都市", "male")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BookLists>() {
                    @Override
                    public void accept(@NonNull BookLists tags) throws Exception {

                       mView.showBookList(tags.bookLists);
                        Log.e("tag",tags.bookLists.toString());
                        if (tags.bookLists == null || tags.bookLists.size() <= 0) {
                            ToastUtils.showSingleToast("暂无相关书单");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e("tagaa",throwable.toString());
                    }
                });
    }
}
