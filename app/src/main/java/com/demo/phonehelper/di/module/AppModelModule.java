package com.demo.phonehelper.di.module;

import com.demo.phonehelper.data.AppInfoModel;
import com.demo.phonehelper.data.http.ApiService;
import com.demo.phonehelper.presenter.contract.AppInfoContract;

import dagger.Module;
import dagger.Provides;

/**
 *
 * Created by Administrator on 2017/12/18.
 */
@Module
public class AppModelModule {

    @Provides
    public AppInfoModel provideModel(ApiService apiService){
        return new AppInfoModel(apiService);
    }
}
