package com.ma.pingan.comprehensive.base;

/**
 * Created by mapingan
 * on 2017/6/19 0019.
 */

public interface IBaseView {
    /**
     * 显示加载动画
     */
    void showLoading();
    /**
     * 隐藏加载
     */
    void hideLoading();
    /**
     * 显示网络错误
     * @param onRetryListener 点击监听
     */
    void showNetError();
    /**
     * 完成刷新, 新增控制刷新
     */
    void finishRefresh();
}
