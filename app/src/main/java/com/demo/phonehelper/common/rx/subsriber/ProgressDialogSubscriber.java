package com.demo.phonehelper.common.rx.subsriber;

import android.app.ProgressDialog;
import android.content.Context;

import com.demo.phonehelper.common.util.ProgressDialogHandler;

/**
 * 定义一个ProgressDialog的subscribe人，做统一处理
 * Created by Administrator on 2017/12/7.
 */

public abstract class ProgressDialogSubscriber<T> extends ErrorHandlerSubscriber<T>
        implements ProgressDialogHandler.OnProgressCancelListener{




    private ProgressDialogHandler mProgressDialogHandler;


    public ProgressDialogSubscriber(Context context) {
        super(context);

        mProgressDialogHandler = new ProgressDialogHandler(mContext,true,this);
    }

    protected boolean isShowProgressDialog(){
        return  true;
    }

    @Override
    public void onCancelProgress() {
        unsubscribe();
    }

    @Override
    public void onStart() {

        if(isShowProgressDialog()){
            this.mProgressDialogHandler.showProgressDialog();
        }

    }

    @Override
    public void onCompleted() {



        if(isShowProgressDialog()){
            this.mProgressDialogHandler.dismissProgressDialog();
        }
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);

        if(isShowProgressDialog()){
            this.mProgressDialogHandler.dismissProgressDialog();
        }

    }
}
