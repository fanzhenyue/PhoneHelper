package com.demo.phonehelper.di.module;

import android.app.ProgressDialog;

import com.demo.phonehelper.data.RecommendModel;
import com.demo.phonehelper.data.http.ApiService;
import com.demo.phonehelper.presenter.RecommendPresenter;
import com.demo.phonehelper.presenter.contract.RecommendContract;
import com.demo.phonehelper.ui.fragment.RecommendFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/12/5.
 *
 */

@Module
public class RecommendModule {

    private RecommendContract.View mView;

    public RecommendModule(RecommendContract.View view) {
        this.mView = view;
    }


    @Provides
    public RecommendContract.View provideView(){
        return mView;
    }
    @Provides
    public RecommendModel provideModel(ApiService apiService){
        return new RecommendModel(apiService);
    }
    @Provides
    public ProgressDialog provideProgressDialog(RecommendContract.View view){
        return new ProgressDialog(((RecommendFragment)view).getActivity());
    }

}
