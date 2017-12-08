package com.demo.phonehelper.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.demo.phonehelper.ui.BaseView;

/**
 *
 * Created by Administrator on 2017/12/5.
 *
 */

public class BasePresenter<M,V extends BaseView> {

    protected Context mContext;

    protected M mModel;

    protected V mView;

    public BasePresenter(M m,V v){
        this.mModel = m;
        this.mView = v;

        initContext();
    }

    private void initContext(){
        if(mView instanceof Fragment){
            mContext  = ((Fragment) mView).getActivity();
        }else {
            mContext = (Activity) mView;
        }
    }

}
