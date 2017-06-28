package com.ma.pingan.comprehensive.mvp.contract;

import com.ma.pingan.comprehensive.base.BaseContract;
import com.ma.pingan.comprehensive.bean.BooksByCats;

/**
 * Created by mapingan
 * on 2017/6/22 0022.
 */

public interface SubCategoryFragmentContract {
    interface View extends BaseContract.BaseView {
        void showCategoryList(BooksByCats data, boolean isRefresh);
    }

    interface Presenter  {
        void getCategoryList(String gender, String major, String minor, String type, int start, int limit);
    }
}
