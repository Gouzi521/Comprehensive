package com.ma.pingan.comprehensive.mvp.presenter;

import android.util.Log;

import com.ma.pingan.comprehensive.api.Api;
import com.ma.pingan.comprehensive.base.BasePresenter;
import com.ma.pingan.comprehensive.bean.CategoryList;
import com.ma.pingan.comprehensive.mvp.contract.TopCategoryListContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by PingAn
 * on 2017/6/22 0022
 */

public class TopCategoryListPresenter extends BasePresenter<TopCategoryListContract.View> implements TopCategoryListContract.Presenter<TopCategoryListContract.View>{

    private Api api;

    public static final String TAG="TopCategoryList";

    @Inject
    public TopCategoryListPresenter(Api api) {
        this.api = api;
    }

    @Override
    public void getCategoryList() {
        api.getCategoryList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CategoryList>() {
                    @Override
                    public void accept(@NonNull CategoryList data) throws Exception {
                        if (data != null && mView != null) {
                            mView.showCategoryList(data);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e(TAG,"msg"+throwable.toString());
                    }
                });
    }
}
