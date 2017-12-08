package com.demo.phonehelper.ui.activity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.demo.phonehelper.AppApplication;
import com.demo.phonehelper.di.component.AppComponent;
import com.demo.phonehelper.presenter.BasePresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 *
 * Created by Administrator on 2017/12/5.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {
    private Unbinder mUnbinder;

    private AppApplication mApplication;

    @Inject
    T mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(setLayout());
        mUnbinder = ButterKnife.bind(this);//绑定、、、、、、绑定、、、、、
        this.mApplication = (AppApplication) getApplication();

        setupActivityComponent(mApplication.getAppComponent());

        init();

    }

    //初始化id
    public abstract int setLayout();

    //dagger2注入
    public abstract void setupActivityComponent(AppComponent appComponent);

    public abstract void init();

    //跳转类的封装
    protected void startActivity(Class c){
        this.startActivity(new Intent(this,c));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(mUnbinder !=Unbinder.EMPTY){

            mUnbinder.unbind();
        }
    }




}
