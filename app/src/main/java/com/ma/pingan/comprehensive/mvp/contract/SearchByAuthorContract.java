package com.ma.pingan.comprehensive.mvp.contract;

import com.ma.pingan.comprehensive.bean.BooksByTag;

import java.util.List;

/**
 * Created by PingAn
 * on 2017/6/27 0027
 */

public interface SearchByAuthorContract {

    interface View extends BaseContract.BaseView, com.ma.pingan.comprehensive.base.BaseContract.BaseView {
        void showSearchResultList(List<BooksByTag.TagBook> list);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getSearchResultList(String author);
    }
}
