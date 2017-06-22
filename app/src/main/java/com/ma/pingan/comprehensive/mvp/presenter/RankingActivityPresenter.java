package com.ma.pingan.comprehensive.mvp.presenter;

import com.ma.pingan.comprehensive.api.Api;
import com.ma.pingan.comprehensive.mvp.contract.RankingContract;

import javax.inject.Inject;

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
    public void getRankinhList() {
    }
}
