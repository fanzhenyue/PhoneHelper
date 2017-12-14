package com.demo.phonehelper.di.module;

import com.demo.phonehelper.data.http.ApiService;
import com.demo.phonehelper.data.http.CategoryModel;
import com.demo.phonehelper.data.http.LoginModel;
import com.demo.phonehelper.presenter.contract.CategoryContract;
import com.demo.phonehelper.presenter.contract.LoginContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/12/12.
 */
@Module
public class CategoryModule {

    private CategoryContract.CategoryView mView;

    public CategoryModule(CategoryContract.CategoryView view) {
        this.mView = view;
    }


    @Provides
    public CategoryContract.CategoryView  provideView(){
        return mView;
    }
    @Provides
    public CategoryContract.ICategoryModel provideModel(ApiService apiService){
        return new CategoryModel(apiService);
    }



}
