package com.ma.pingan.comprehensive.mvp.contract;

import com.ma.pingan.comprehensive.base.BaseContract;

/**
 * Created by mapingan
 * on 2017/6/20 0020.
 */

public interface RankingContract {

    interface View extends BaseContract.BaseView{
        void showRankingList();
    }

    interface Persenter<T> extends BaseContract.BasePresenter<T>{
        void getRankinhList();
    }
}
