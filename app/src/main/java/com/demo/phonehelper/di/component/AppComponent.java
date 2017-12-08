package com.demo.phonehelper.di.component;

import android.app.Application;

import com.demo.phonehelper.common.rx.RxErrorHandler;
import com.demo.phonehelper.data.http.ApiService;
import com.demo.phonehelper.di.module.AppModule;
import com.demo.phonehelper.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 *
 * Created by Administrator on 2017/12/5.
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    public ApiService getApiService();

    public Application getApplication();

    public RxErrorHandler  getRxErrorHandler();
}
