package com.demo.dagger2;

/**
 * Created by Administrator on 2017/12/5.
 */

public class UserManager {

    private ApiService mApiService;
    private UserStore mUserStore;

    public UserManager(ApiService mApiService, UserStore mUserStore) {
        this.mApiService = mApiService;
        this.mUserStore = mUserStore;
    }



    public void register() {
        mApiService.register();
        mUserStore.register();
    }
}
