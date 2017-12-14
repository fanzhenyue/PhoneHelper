package com.demo.phonehelper.di.component;

import com.demo.phonehelper.di.FragmentScope;
import com.demo.phonehelper.di.module.CategoryModule;
import com.demo.phonehelper.di.module.LoginModule;
import com.demo.phonehelper.ui.activity.LoginActivity;
import com.demo.phonehelper.ui.fragment.CategoryFragment;

import dagger.Component;

/**
 *
 * Created by Administrator on 2017/12/12.
 */

@FragmentScope
@Component(modules = CategoryModule.class,dependencies = AppComponent.class)
public interface CategoryComponent {

    void inject(CategoryFragment fragment);




}
