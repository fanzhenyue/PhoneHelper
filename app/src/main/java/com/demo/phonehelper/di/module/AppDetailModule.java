package com.demo.phonehelper.di.module;

import com.demo.phonehelper.data.AppInfoModel;
import com.demo.phonehelper.data.http.ApiService;
import com.demo.phonehelper.data.http.CategoryModel;
import com.demo.phonehelper.presenter.contract.AppInfoContract;
import com.demo.phonehelper.presenter.contract.CategoryContract;

import dagger.Module;
import dagger.Provides;

/**
 *
 * Created by Administrator on 2017/12/12.
 */
@Module(includes = {AppModelModule.class})
public class AppDetailModule {

    private AppInfoContract.AppDetailView mView;

    public AppDetailModule(AppInfoContract.AppDetailView view) {
        this.mView = view;
    }


    @Provides
    public AppInfoContract.AppDetailView  provideView(){
        return mView;
    }






}
