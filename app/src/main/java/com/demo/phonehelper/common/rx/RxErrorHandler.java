package com.demo.phonehelper.common.rx;

import android.content.Context;
import android.widget.Toast;

import com.demo.phonehelper.common.execption.ApiException;
import com.demo.phonehelper.common.execption.BaseException;
import com.demo.phonehelper.common.execption.ErrorMessageFactory;

import org.json.JSONException;

import java.net.SocketException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by Administrator on 2017/12/7.
 */

public class RxErrorHandler {

    private Context mContext;
    public RxErrorHandler(Context context){
        this.mContext = context;
    }

    public BaseException handleError(Throwable e){
        BaseException exception =  new BaseException();

        if (e instanceof ApiException){
            exception.setCode(((ApiException)e).getCode());
        }else if(e instanceof SocketException){
            exception.setCode(BaseException.SOCKET_ERROR);
        }else if(e instanceof SocketTimeoutException){
            exception.setCode(BaseException.SOCKET_TIMEOUT_ERROR);
        }else if(e instanceof HttpException){
            exception.setCode(BaseException.HTTP_ERROR);
        }else if(e instanceof JSONException){
            exception.setCode(BaseException.JSON_ERROR);
        }
        else {
            exception.setCode(BaseException.UNKNOW_ERROR);
        }

        exception.setDisplayMessage(ErrorMessageFactory.create(mContext,exception.getCode()));

        return exception;
    }

    public void showErrorMessage(BaseException e){

        Toast.makeText(mContext,e.getDisplayMessage(),Toast.LENGTH_LONG).show();
    }
}
