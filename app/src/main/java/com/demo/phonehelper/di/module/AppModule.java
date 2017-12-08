package com.demo.phonehelper.di.module;

import android.app.Application;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 提供APP级别的module
 * Created by Administrator on 2017/12/5.
 */

@Module
public class AppModule {

    private Application mApplication;

    public AppModule(Application application) {
        this.mApplication = application;
    }

    @Provides
    @Singleton
    public Application provideApplication(){

        return mApplication;
    }
    //提供Gson
    @Provides
    @Singleton
    public Gson provideGson(){

        return new Gson();
    }

}
