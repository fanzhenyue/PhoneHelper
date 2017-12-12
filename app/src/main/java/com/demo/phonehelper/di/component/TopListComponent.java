package com.demo.phonehelper.di.component;

import com.demo.phonehelper.di.FragmentScope;
import com.demo.phonehelper.di.module.RecommendModule;
import com.demo.phonehelper.di.module.TopListModule;
import com.demo.phonehelper.presenter.contract.AppInfoContract;
import com.demo.phonehelper.ui.fragment.TopListFragment;

import dagger.Component;

/**
 *
 * Created by Administrator on 2017/12/12.
 */

@FragmentScope
@Component(modules = TopListModule.class,dependencies = AppComponent.class)
public interface TopListComponent {

    void inject(TopListFragment fragment);

}
