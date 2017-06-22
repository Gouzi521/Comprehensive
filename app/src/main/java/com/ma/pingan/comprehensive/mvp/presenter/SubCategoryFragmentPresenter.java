package com.ma.pingan.comprehensive.mvp.presenter;

import com.ma.pingan.comprehensive.api.Api;
import com.ma.pingan.comprehensive.bean.BooksByCats;
import com.ma.pingan.comprehensive.mvp.contract.SubCategoryFragmentContract;
import com.ma.pingan.comprehensive.utils.StringUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mapingan
 * on 2017/6/22 0022.
 */

public class SubCategoryFragmentPresenter  implements SubCategoryFragmentContract.Presenter {

    private Api api;
    private SubCategoryFragmentContract.View view;


    @Inject
    public SubCategoryFragmentPresenter(Api api, SubCategoryFragmentContract.View view) {
        this.api = api;
        this.view = view;
    }

    @Override
    public void getCategoryList(String gender, String major, String minor, String type, final int start, int limit) {
        String key = StringUtils.creatAcacheKey("category-list", gender, type, major, minor, start, limit);

        api.getBooksByCats(gender, type, major, minor, start, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BooksByCats>() {
                    @Override
                    public void accept(@NonNull BooksByCats booksByCats) throws Exception {

                        view.showCategoryList(booksByCats,start == 0 ? true : false);
                    }
                });
    }
}
