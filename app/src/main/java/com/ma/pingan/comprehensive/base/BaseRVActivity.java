package com.ma.pingan.comprehensive.base;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ma.pingan.comprehensive.dagger.component.AppComponent;

import butterknife.BindView;

/**
 * Created by mapingan
 * on 2017/6/20 0020.
 */

public abstract class BaseRVActivity<T> extends BaseActivity{



    protected RecyclerView mRecyclerView;
    protected BaseQuickAdapter<T,BaseViewHolder> adapter;


    protected int start = 0;
    protected int limit = 20;

    @Override
    protected void initData() {

    }

    @Override
    protected void initInjector(AppComponent appComponent) {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }


    protected void initAdapter(Class<? extends BaseQuickAdapter<T,BaseViewHolder>> clazz,boolean refreshable, boolean loadmoreable){
        initAdapter(refreshable, loadmoreable);
    }

    protected void initAdapter(boolean refreshable, boolean loadmoreable){

    }
}
