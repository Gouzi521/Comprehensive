package com.ma.pingan.comprehensive.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ma.pingan.comprehensive.widget.LoadingPage;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by mapingan
 * on 2017/6/28 0028.
 */

public abstract class BaseRvFragment  <T1 extends BaseContract.BasePresenter,T2> extends Fragment{

    @Inject
    protected  T1 mPresenter;
    @Inject
    protected BaseQuickAdapter mAdapter;


    /**
     * [此方法不可再重写]
     */

    public LoadingPage mLoadingPage;

    private boolean mIsVisible = false;     // fragment是否显示了

    private boolean isPrepared = false;

    private boolean isFirst = true; //只加载一次界面
    protected View contentView;
    private Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mLoadingPage==null){
            mLoadingPage=new LoadingPage(getContext()) {
                @Override
                protected void initView() {
                    if (isFirst){
                        BaseRvFragment.this.contentView=this.contentView;
                        bind = ButterKnife.bind(BaseRvFragment.this, contentView);
                        BaseRvFragment.this.initView();
                        isFirst = false;
                    }
                }

                @Override
                protected void loadData() {
                    BaseRvFragment.this.loadData();
                }

                @Override
                protected int getLayoutId() {
                    return BaseRvFragment.this.getLayoutId();
                }
            };

        }
        isPrepared=true;
        loadBaseData();
        return mLoadingPage;
    }

    private void loadBaseData() {

        if (!mIsVisible || !isPrepared || !isFirst) {
            return;
        }
        loadData();
    }


    /**
     * 1
     * 根据网络获取的数据返回状态，每一个子类的获取网络返回的都不一样，所以要交给子类去完成
     */
    protected abstract void loadData();

    /**
     * 2
     * 网络请求成功在去加载布局
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 3
     * 子类关于View的操作(如setAdapter)都必须在这里面，会因为页面状态不为成功，而binding还没创建就引用而导致空指针。
     * loadData()和initView只执行一次，如果有一些请求需要二次的不要放到loadData()里面。
     */
    protected abstract void initView();

    /**
     * dagger2注入
     */
    protected abstract void initInject();



}
