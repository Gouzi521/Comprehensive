package com.ma.pingan.comprehensive.ui.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.view.ViewPager;

import com.ma.pingan.comprehensive.R;
import com.ma.pingan.comprehensive.api.Api;
import com.ma.pingan.comprehensive.base.BaseActivity;
import com.ma.pingan.comprehensive.bean.Ranking;
import com.ma.pingan.comprehensive.dagger.component.AppComponent;
import com.ma.pingan.comprehensive.widget.RVPIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 小说排行榜
 */
public class RankingActivity extends BaseActivity {


    @BindView(R.id.indicatorSub)
    RVPIndicator mIndicatorSub;
    @BindView(R.id.viewpagerSub)
    ViewPager mViewpagerSub;

    Api api;

    @Override
    protected void initData() {

        api.getRanking("")
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        //showLoading();加载空视图
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Ranking>() {
                    @Override
                    public void accept(@NonNull Ranking ranking) throws Exception {

                    }
                });


    }

    @Override
    protected void initInjector(AppComponent appComponent) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ranking;
    }


}
