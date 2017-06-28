package com.ma.pingan.comprehensive.mvp.contract;

import com.ma.pingan.comprehensive.base.BaseContract;
import com.ma.pingan.comprehensive.bean.BookLists;

import java.util.List;

/**
 * Created by mapingan
 * on 2017/6/28 0028.
 */

public interface SubjectFragmentContract {

    interface View extends BaseContract.BaseView {
        void showBookList(List<BookLists.BookListsBean> bookLists, boolean isRefresh);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getBookLists(String duration, String sort, int start, int limit, String tag, String gender);
    }
}
