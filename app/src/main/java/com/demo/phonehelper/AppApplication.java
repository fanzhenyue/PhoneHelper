package com.demo.phonehelper;

import android.app.Application;
import android.content.Context;

import com.demo.phonehelper.di.component.AppComponent;
import com.demo.phonehelper.di.component.DaggerAppComponent;
import com.demo.phonehelper.di.module.AppModule;
import com.demo.phonehelper.di.module.HttpModule;

/**
 *
 * Created by Administrator on 2017/12/5.
 */

public class AppApplication extends Application {

    private AppComponent mAppComponent;

    public static AppApplication get(Context context){
        return (AppApplication) context.getApplicationContext();
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent =  DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .httpModule(new HttpModule()).build();
    }
}
