package com.demo.phonehelper.data;

import com.demo.phonehelper.bean.AppInfo;
import com.demo.phonehelper.bean.BaseBean;
import com.demo.phonehelper.bean.PageBean;
import com.demo.phonehelper.data.http.ApiService;
import com.demo.phonehelper.data.http.HttpManager;

import retrofit2.Callback;
import rx.Observable;

/**
 * 排行的逻辑业务处理
 * Created by Administrator on 2017/12/5.
 */

public class RecommendModel {

    private ApiService mApiService;

    public RecommendModel(ApiService apiService) {
        this.mApiService = apiService;
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getApps(){

//        HttpManager manager = new HttpManager();
//
//        ApiService apiService = manager.getRetrofit(manager.getOkHttpClient()).create(ApiService.class);

        return  mApiService.getApps("{'page':0}");
    }
}
