package com.demo.phonehelper.di.module;

import android.app.ProgressDialog;

import com.demo.phonehelper.data.AppInfoModel;
import com.demo.phonehelper.data.http.ApiService;
import com.demo.phonehelper.presenter.contract.AppInfoContract;
import com.demo.phonehelper.ui.fragment.RecommendFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/12/5.
 *
 */

@Module
public class RecommendModule {

    private AppInfoContract.View mView;

    public RecommendModule(AppInfoContract.View view) {
        this.mView = view;
    }


    @Provides
    public AppInfoContract.View provideView(){
        return mView;
    }
    @Provides
    public AppInfoModel provideModel(ApiService apiService){
        return new AppInfoModel(apiService);
    }
    @Provides
    public ProgressDialog provideProgressDialog(AppInfoContract.View view){
        return new ProgressDialog(((RecommendFragment)view).getActivity());
    }

}
