package com.ma.pingan.comprehensive.mvp.contract;

/**
 * Created by mapingan
 * on 2017/6/22 0022.
 */

public interface BaseContract {
    interface BasePresenter<T> {

        void attachView(T view);

        void detachView();
    }

    interface BaseView {

        void showError();

        void complete();

    }
}
