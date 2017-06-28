package com.ma.pingan.comprehensive.mvp.contract;

import com.ma.pingan.comprehensive.base.BaseContract;
import com.ma.pingan.comprehensive.bean.BooksByTag;

import java.util.List;

/**
 * Created by mapingan
 * on 2017/6/28 0028.
 */

public interface SearchAuthorContract {

    interface View extends BaseContract.BaseView {
        void showSearchResultList(List<BooksByTag.TagBook> list);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getSearchResultList(String author);
    }

}
