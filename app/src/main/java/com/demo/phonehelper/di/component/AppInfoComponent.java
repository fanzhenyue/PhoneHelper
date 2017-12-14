package com.demo.phonehelper.di.component;

import com.demo.phonehelper.di.FragmentScope;
import com.demo.phonehelper.di.module.AppInfoModule;
import com.demo.phonehelper.ui.fragment.CategoryAppFragment;
import com.demo.phonehelper.ui.fragment.GamesFragment;
import com.demo.phonehelper.ui.fragment.TopListFragment;

import dagger.Component;

/**
 *
 * Created by Administrator on 2017/12/12.
 */

@FragmentScope
@Component(modules = AppInfoModule.class,dependencies = AppComponent.class)
public interface AppInfoComponent {

    void injectTopListFragment(TopListFragment fragment);

    void injectGamesFragment(GamesFragment fragment);

    void injectCategoryAppFragment(CategoryAppFragment fragment);

}
