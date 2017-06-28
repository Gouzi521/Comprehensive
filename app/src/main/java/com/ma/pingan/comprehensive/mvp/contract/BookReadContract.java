package com.ma.pingan.comprehensive.mvp.contract;



import com.ma.pingan.comprehensive.base.BaseContract;
import com.ma.pingan.comprehensive.bean.BookMixAToc;
import com.ma.pingan.comprehensive.bean.ChapterRead;

import java.util.List;

/**
 * Created by mapingan
 * on 2017/6/27 0027.
 */

public interface BookReadContract {

    interface View extends BaseContract.BaseView {
        void showBookToc(List<BookMixAToc.mixToc.Chapters> list);

        void showChapterRead(ChapterRead.Chapter data, int chapter);

        void netError(int chapter);//添加网络处理异常接口
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getBookMixAToc(String bookId, String view);

        void getChapterRead(String url, int chapter);
    }
}
