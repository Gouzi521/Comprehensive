package com.ma.pingan.comprehensive.mvp.contract;

import com.ma.pingan.comprehensive.bean.CategoryList;

/**
 * Created by PingAn
 * on 2017/6/22 0022
 */

public interface TopCategoryListContract {
    interface View extends BaseContract.BaseView, com.ma.pingan.comprehensive.base.BaseContract.BaseView {
        void showCategoryList(CategoryList data);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getCategoryList();
    }
}
