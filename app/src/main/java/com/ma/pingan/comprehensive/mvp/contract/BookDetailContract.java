package com.ma.pingan.comprehensive.mvp.contract;

import com.ma.pingan.comprehensive.bean.BookDetail;
import com.ma.pingan.comprehensive.bean.HotReview;
import com.ma.pingan.comprehensive.bean.RecommendBookList;

import java.util.List;

/**
 * Created by PingAn
 * on 2017/6/22 0022
 */

public interface BookDetailContract {

    interface View extends BaseContract.BaseView, com.ma.pingan.comprehensive.base.BaseContract.BaseView {
        void showBookDetail(BookDetail data);

        void showHotReview(List<HotReview.Reviews> list);

        void showRecommendBookList(List<RecommendBookList.RecommendBook> list);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getBookDetail(String bookId);

        void getHotReview(String book);

        void getRecommendBookList(String bookId, String limit);
    }

}
