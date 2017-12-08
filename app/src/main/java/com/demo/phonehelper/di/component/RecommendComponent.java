package com.demo.phonehelper.di.component;

import com.demo.phonehelper.di.Fragmentcope;
import com.demo.phonehelper.di.module.RecommendModule;
import com.demo.phonehelper.ui.fragment.RecommendFragment;

import dagger.Component;

/**
 * Created by Administrator on 2017/12/5.
 *
 */
@Fragmentcope
@Component(modules = RecommendModule.class,dependencies = AppComponent.class)
public interface RecommendComponent {


    void inject(RecommendFragment fragment);
}
