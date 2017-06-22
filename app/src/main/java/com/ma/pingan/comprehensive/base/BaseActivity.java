package com.ma.pingan.comprehensive.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;

import com.ma.pingan.comprehensive.ComprehensiveApplication;
import com.ma.pingan.comprehensive.R;
import com.ma.pingan.comprehensive.dagger.component.AppComponent;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by mapingan
 * on 2017/6/20 0020.
 */

public  abstract class BaseActivity   extends AppCompatActivity  {

    public Toolbar mCommonToolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载布局
        setContentView(getLayoutId());
        //butterKnife绑定
        ButterKnife.bind(this);
        mCommonToolbar = ButterKnife.findById(this, R.id.common_toolbar);
        if (mCommonToolbar != null) {
            initToolBar();
            setSupportActionBar(mCommonToolbar);
        }
        //注入dagger
        initInjector(ComprehensiveApplication.getsInstance().getAppComponent());
        //数据初始化
        initData();
        configViews();
    }

    protected abstract void configViews();

    protected abstract void initToolBar();

    protected abstract void initData();

    protected abstract void initInjector(AppComponent appComponent);

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
