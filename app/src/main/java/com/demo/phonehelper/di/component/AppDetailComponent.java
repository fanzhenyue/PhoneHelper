package com.demo.phonehelper.di.component;

import com.demo.phonehelper.di.FragmentScope;
import com.demo.phonehelper.di.module.AppDetailModule;
import com.demo.phonehelper.di.module.AppInfoModule;
import com.demo.phonehelper.ui.activity.AppDetailActivity;
import com.demo.phonehelper.ui.fragment.AppDetailFragment;
import com.demo.phonehelper.ui.fragment.CategoryAppFragment;
import com.demo.phonehelper.ui.fragment.GamesFragment;
import com.demo.phonehelper.ui.fragment.TopListFragment;

import dagger.Component;

/**
 *
 * Created by Administrator on 2017/12/12.
 */

@FragmentScope
@Component(modules = AppDetailModule.class,dependencies = AppComponent.class)
public interface AppDetailComponent {

    void inject(AppDetailFragment fragment);
//    void inject(AppDetailActivity activity);


}
