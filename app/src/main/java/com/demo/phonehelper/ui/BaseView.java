package com.demo.phonehelper.ui;

/**
 * Created by Administrator on 2017/12/5.
 */

public interface BaseView {
    //加载中
    void showLoading();
    //显示错误
    void showError(String msg);
    //加载结束
    void dismissLoading();


}
