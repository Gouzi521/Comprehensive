package com.ma.pingan.comprehensive.mvp.presenter;

import android.nfc.Tag;
import android.util.Log;

import com.ma.pingan.comprehensive.api.Api;
import com.ma.pingan.comprehensive.base.BasePresenter;
import com.ma.pingan.comprehensive.bilientity.RegionRecommendInfo;
import com.ma.pingan.comprehensive.mvp.contract.RegionTypeRecommendContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by PingAn
 * on 2017/6/29 0029
 */

public class RegionTypeRecommendPresenter extends BasePresenter<RegionTypeRecommendContract.View> implements RegionTypeRecommendContract.Presenter<RegionTypeRecommendContract.View> {

    public static final String TAG="RegionTypeRecommend";

    private Api api;

    @Inject
    public RegionTypeRecommendPresenter(Api api) {
        this.api = api;
    }

    @Override
    public void getRegionList(int id) {
        api.getRegionRecommends(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RegionRecommendInfo>() {
                    @Override
                    public void accept(@NonNull RegionRecommendInfo dataBean) throws Exception {
                        Log.e(TAG,dataBean.getData().getRecommend().size()+"");
                        Log.e(TAG,dataBean.getData().getDynamic().size()+"");
                        Log.e(TAG,dataBean.getData().getNewX().size()+"");
                        List<RegionRecommendInfo> data=new ArrayList<RegionRecommendInfo>();
                        data.add(dataBean);
                        mView.showRegion(data);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e(TAG,throwable.toString());
                    }
                });

    }


}
