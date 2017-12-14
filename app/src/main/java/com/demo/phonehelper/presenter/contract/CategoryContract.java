package com.demo.phonehelper.presenter.contract;

import com.demo.phonehelper.bean.BaseBean;
import com.demo.phonehelper.bean.Category;
import com.demo.phonehelper.ui.BaseView;

import java.util.List;

import rx.Observable;

/**
 *
 * Created by Administrator on 2017/12/14.
 */

public interface CategoryContract {

    interface ICategoryModel{
        Observable<BaseBean<List<Category>>> getCategories();
    }

    interface  CategoryView extends BaseView{
        void showData(List<Category> categories);
    }
}
