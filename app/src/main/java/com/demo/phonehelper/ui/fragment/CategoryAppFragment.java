package com.demo.phonehelper.ui.fragment;

import android.annotation.SuppressLint;

import com.demo.phonehelper.di.component.AppComponent;
import com.demo.phonehelper.di.component.DaggerAppInfoComponent;
import com.demo.phonehelper.di.component.DaggerCategoryComponent;
import com.demo.phonehelper.di.module.AppInfoModule;
import com.demo.phonehelper.ui.adapter.AppInfoAdapter;

/**
 * Created by Administrator on 2017/12/14.
 */

public class CategoryAppFragment extends BaseAppInfoFragment {


    private int categoryId;
    private int mFlagType;




    public CategoryAppFragment(int categoryId, int flagType) {
        this.categoryId = categoryId;
        this.mFlagType = flagType;
    }
//    public static CategoryAppFragment newInstance(int categoryId, int flagType){
//        return new CategoryAppFragment(categoryId,flagType);
//    }

    @Override
    public void init() {
        mPresenter.requestCategory(categoryId,page,mFlagType);
        initRecyclerView();
    }

    @Override
    int type() {
        return 0;
    }

    @Override
    AppInfoAdapter buildAdapter() {
        return AppInfoAdapter.builder().showPosition(false).showBrief(true).showCategory(false).build();
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerAppInfoComponent.builder().appComponent(appComponent).appInfoModule(new AppInfoModule(this))
                .build().injectCategoryAppFragment(this);
    }
}
