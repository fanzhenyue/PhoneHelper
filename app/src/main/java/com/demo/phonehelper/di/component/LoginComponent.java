package com.demo.phonehelper.di.component;

import com.demo.phonehelper.data.http.LoginModel;
import com.demo.phonehelper.di.FragmentScope;
import com.demo.phonehelper.di.module.AppInfoModule;
import com.demo.phonehelper.di.module.LoginModule;
import com.demo.phonehelper.ui.activity.LoginActivity;
import com.demo.phonehelper.ui.fragment.GamesFragment;
import com.demo.phonehelper.ui.fragment.TopListFragment;

import dagger.Component;

/**
 *
 * Created by Administrator on 2017/12/12.
 */

@FragmentScope
@Component(modules = LoginModule.class,dependencies = AppComponent.class)
public interface LoginComponent {

    void inject(LoginActivity activity);




}
