package com.ma.pingan.comprehensive.mvp.presenter;

import com.ma.pingan.comprehensive.api.Api;
import com.ma.pingan.comprehensive.base.BaseContract;
import com.ma.pingan.comprehensive.base.IBasePresenter;
import com.ma.pingan.comprehensive.bean.Ranking;
import com.ma.pingan.comprehensive.mvp.contract.RankingContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mapingan
 * on 2017/6/20 0020.
 */

public class RankingActivityPresenter implements RankingContract.Persenter<RankingContract.View>{

    private Api api;

    @Inject
    public RankingActivityPresenter(Api api) {
        this.api = api;
    }

    @Override
    public void attachView(RankingContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void getRankinhList() {
    }
}
