package com.ma.pingan.comprehensive.base;

/**
 *
 * 原作者中是用于解绑rxjava1的，但是2不用解绑
 * Created by PingAn
 * on 2017/6/22 0022
 */

public class BasePresenter<T extends BaseContract.BaseView> implements BaseContract.BasePresenter<T>{
    protected T mView;
    @Override
    public void attachView(T view) {
        this.mView = view;
    }

}
