package com.demo.phonehelper.di.module;

import android.app.ProgressDialog;

import com.demo.phonehelper.data.AppInfoModel;
import com.demo.phonehelper.data.http.ApiService;
import com.demo.phonehelper.presenter.contract.AppInfoContract;
import com.demo.phonehelper.ui.fragment.RecommendFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/12/12.
 */
@Module
public class AppInfoModule {

    private AppInfoContract.AppInfoView mView;

    public AppInfoModule(AppInfoContract.AppInfoView view) {
        this.mView = view;
    }


    @Provides
    public AppInfoContract.AppInfoView provideView(){
        return mView;
    }
    @Provides
    public AppInfoModel provideModel(ApiService apiService){
        return new AppInfoModel(apiService);
    }



}
