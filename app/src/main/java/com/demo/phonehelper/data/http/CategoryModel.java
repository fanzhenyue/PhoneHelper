package com.demo.phonehelper.data.http;

import com.demo.phonehelper.bean.BaseBean;
import com.demo.phonehelper.bean.Category;
import com.demo.phonehelper.presenter.contract.CategoryContract;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2017/12/14.
 */

public class CategoryModel implements CategoryContract.ICategoryModel {

    private ApiService mApiService;

    public CategoryModel(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<List<Category>>> getCategories() {
        return mApiService.getCategories();
    }
}
