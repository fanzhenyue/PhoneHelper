package com.demo.phonehelper.di.module;

import com.demo.phonehelper.data.AppInfoModel;
import com.demo.phonehelper.data.http.ApiService;
import com.demo.phonehelper.data.http.LoginModel;
import com.demo.phonehelper.presenter.contract.AppInfoContract;
import com.demo.phonehelper.presenter.contract.LoginContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/12/12.
 */
@Module
public class LoginModule {

    private LoginContract.LoginView mView;

    public LoginModule(LoginContract.LoginView view) {
        this.mView = view;
    }


    @Provides
    public LoginContract.LoginView  provideView(){
        return mView;
    }
    @Provides
    public LoginContract.ILoginModel provideModel(ApiService apiService){
        return new LoginModel(apiService);
    }



}
