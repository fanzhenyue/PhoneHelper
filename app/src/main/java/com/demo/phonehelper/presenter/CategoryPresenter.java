package com.demo.phonehelper.presenter;

import com.demo.phonehelper.bean.BaseBean;
import com.demo.phonehelper.bean.Category;
import com.demo.phonehelper.common.rx.RxHttpResponseCompat;
import com.demo.phonehelper.common.rx.subsriber.ProgressSubscriber;
import com.demo.phonehelper.presenter.contract.CategoryContract;

import java.util.List;

import javax.inject.Inject;

import rx.functions.Action1;

/**
 * Created by Administrator on 2017/12/14.
 */

public class CategoryPresenter extends BasePresenter<CategoryContract.ICategoryModel,CategoryContract.CategoryView> {



    @Inject
    public CategoryPresenter(CategoryContract.ICategoryModel iCategoryModel, CategoryContract.CategoryView categoryView) {
        super(iCategoryModel, categoryView);
    }

    public void getALlCategory(){
        mModel.getCategories().compose(RxHttpResponseCompat.<List<Category>>compatResult())
                .subscribe(new ProgressSubscriber<List<Category>>(mContext,mView) {
                    @Override
                    public void onNext(List<Category> categories) {
                        mView.showData(categories);
                    }
                });
    }
}
