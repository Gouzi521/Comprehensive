package com.ma.pingan.comprehensive.mvp.contract;

import com.ma.pingan.comprehensive.base.BaseContract;

import com.ma.pingan.comprehensive.bilientity.RegionRecommendInfo;

import java.util.List;

/**
 * Created by PingAn
 * on 2017/6/29 0029
 */

public interface RegionTypeRecommendContract {

    interface View extends BaseContract.BaseView{
        void showRegion(List<RegionRecommendInfo> list);

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void getRegionList(int id);
    }
}
