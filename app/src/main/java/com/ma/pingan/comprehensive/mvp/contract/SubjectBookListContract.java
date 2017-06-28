package com.ma.pingan.comprehensive.mvp.contract;

import com.ma.pingan.comprehensive.base.BaseContract;

/**
 * Created by mapingan
 * on 2017/6/28 0028.
 */

public class SubjectBookListContract {

    interface View extends BaseContract.BaseView {
       // void showBookListTags(BookListTags data);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getBookListTags();
    }
}
