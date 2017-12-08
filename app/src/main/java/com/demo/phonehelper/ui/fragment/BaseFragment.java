package com.demo.phonehelper.ui.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.phonehelper.AppApplication;
import com.demo.phonehelper.R;
import com.demo.phonehelper.di.component.AppComponent;
import com.demo.phonehelper.presenter.BasePresenter;
import com.demo.phonehelper.ui.BaseView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 *
 * Created by Administrator on 2017/12/6.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {

    private Unbinder mUnbinder;

    private AppApplication mApplication;

    private View mRootView;

    @Inject
    T mPresenter;

//    private ProgressDialog mProgressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRootView  = inflater.inflate(setLayout(), container, false);
         mUnbinder =ButterKnife.bind(this,mRootView);

        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mApplication = (AppApplication) getActivity().getApplication();
        setupActivityComponent(mApplication.getAppComponent());

        init();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        if(mUnbinder != Unbinder.EMPTY){
            mUnbinder.unbind();
        }
    }
    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void dismissLoading() {

    }


    //初始化id
    public abstract int setLayout();

    public abstract void setupActivityComponent(AppComponent appComponent);

    public abstract void init();


   /* @Override
    public void showLoading() {
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage("loading......");

        mProgressDialog.show();
    }

    @Override
    public void dismissLoading() {
        if(mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }*/


}
