package com.demo.phonehelper.common.rx.subsriber;

import android.content.Context;

import com.demo.phonehelper.common.execption.BaseException;
import com.demo.phonehelper.common.util.ProgressDialogHandler;
import com.demo.phonehelper.ui.BaseView;

/**
 *
 * Created by Administrator on 2017/12/7.
 */

public abstract class ProgressSubscriber<T> extends ErrorHandlerSubscriber<T>
        implements ProgressDialogHandler.OnProgressCancelListener{



    private BaseView mView;

    public ProgressSubscriber(Context context, BaseView view) {
        super(context);
        this.mView = view;
    }

    protected boolean isShowProgress(){
        return  true;
    }

    @Override
    public void onCancelProgress() {
        unsubscribe();
    }

    @Override
    public void onStart() {

        if(isShowProgress()){
            mView.showLoading();
        }

    }

    @Override
    public void onCompleted() {
        mView.dismissLoading();

    }

    @Override
    public void onError(Throwable e) {
        BaseException exception = mRxErrorHandler.handleError(e);
        mView.showError(exception.getDisplayMessage());

    }
}
