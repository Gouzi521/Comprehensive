package com.ma.pingan.comprehensive.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ma.pingan.comprehensive.ComprehensiveApplication;
import com.ma.pingan.comprehensive.dagger.component.AppComponent;
import com.ma.pingan.comprehensive.widget.LoadingPage;

import java.lang.reflect.Constructor;

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

    protected BaseQuickAdapter<T2,BaseViewHolder> mAdapter;

    public LoadingPage mLoadingPage;

    private boolean mIsVisible = false;     // fragment是否显示了

    private boolean isPrepared = false;

    private boolean isFirst = true; //只加载一次界面

    protected View parentView;
    protected FragmentActivity activity;
    protected Context mContext;
    protected LayoutInflater inflater;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        parentView = inflater.inflate(getLayoutResId(), container, false);
        activity = getSupportActivity();
        mContext = activity;
        this.inflater = inflater;
        return parentView;

    }

    public FragmentActivity getSupportActivity() {
        return super.getActivity();
    }

    protected View getParentView() {
        return parentView;
    }
    protected LayoutInflater getLayoutInflater() {
        return inflater;
    }

    private Class<? extends BaseQuickAdapter<T2,BaseViewHolder>> clazz;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initAdapter( clazz);

        setupActivityComponent(ComprehensiveApplication.getsInstance().getAppComponent());
        if (mLoadingPage==null){
            mLoadingPage=new LoadingPage(getContext()) {
                @Override
                protected void initView() {
                    if (isFirst){
                        BaseRvFragment.this.parentView=this.contentView;
                        ButterKnife.bind(this, contentView);
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
                    return BaseRvFragment.this.getLayoutResId();
                }
            };

        }
        isPrepared=true;
        loadBaseData();

    }

    protected void initAdapter(Class<? extends BaseQuickAdapter<T2,BaseViewHolder>> clazz) {
        mAdapter = (BaseQuickAdapter<T2, BaseViewHolder>) createInstance(clazz);

    }

    public Object createInstance(Class<?> cls) {
        Object obj;
        try {
            Constructor c1 = cls.getDeclaredConstructor(Context.class);
            c1.setAccessible(true);
            obj = c1.newInstance(mContext);
        } catch (Exception e) {
            obj = null;
        }
        return obj;
    }
    /**
     * 在这里实现Fragment数据的缓加载.
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {//fragment可见
            mIsVisible = true;
            onVisible();
        } else {//fragment不可见
            mIsVisible = false;
            onInvisible();
        }
    }


    protected void onInvisible() {
    }

    /**
     * 显示时加载数据,需要这样的使用
     * 注意声明 isPrepared，先初始化
     * 生命周期会先执行 setUserVisibleHint 再执行onActivityCreated
     * 在 onActivityCreated 之后第一次显示加载数据，只加载一次
     */
    protected void onVisible() {
        if (isFirst) {
            setupActivityComponent(ComprehensiveApplication.getsInstance().getAppComponent());
            if (mPresenter!=null){
                mPresenter.attachView(this);}
        }
        loadBaseData();//根据获取的数据来调用showView()切换界面
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
    public abstract
    @LayoutRes
    int getLayoutResId();

    /**
     * 3
     * 子类关于View的操作(如setAdapter)都必须在这里面，会因为页面状态不为成功，而binding还没创建就引用而导致空指针。
     * loadData()和initView只执行一次，如果有一些请求需要二次的不要放到loadData()里面。
     */
    protected abstract void initView();


    /**
     * dagger2注入
     */
    protected abstract void setupActivityComponent(AppComponent appComponent);


}
