package com.ma.pingan.comprehensive.base;

/**
 * Created by mapingan
 * on 2017/6/20 0020.
 */

public interface BaseContract {

    interface BasePresenter<T>{

        void attachView(T view);

    }

    interface BaseView{
        void showError();

        void complete();
    }
}
