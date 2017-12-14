package com.demo.phonehelper.common.rx.subsriber;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.demo.phonehelper.common.execption.ApiException;
import com.demo.phonehelper.common.execption.BaseException;
import com.demo.phonehelper.common.rx.RxErrorHandler;
import com.demo.phonehelper.ui.activity.LoginActivity;

import org.json.JSONException;

import java.net.SocketException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;

/**
 *
 * Created by Administrator on 2017/12/7.
 */

public abstract class ErrorHandlerSubscriber<T> extends DefaultSubscriber<T> {

    private static final String TAG = "ErrorHandlerSubscriber";


    protected RxErrorHandler mRxErrorHandler = null;
    protected Context mContext;

    public ErrorHandlerSubscriber(Context context) {
        this.mContext = context;

        mRxErrorHandler = new RxErrorHandler(mContext);
    }

    //rxjava只重写onError方法，定义这个抽象类，其他不需要
    @Override
    public void onError(Throwable e) {

       BaseException exception = mRxErrorHandler.handleError(e);

        if(exception ==null){
            e.printStackTrace();
            Log.d(TAG, "onError: "+e.getMessage());
        }else {
            mRxErrorHandler.showErrorMessage(exception);

            if (exception.getCode()== BaseException.ERROR_TOKEN){
                toLogin();
            }
        }
    }

    //token失效，直接跳转登陆界面
    private void toLogin() {
        Intent intent = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(intent);
    }
}
