package com.demo.phonehelper.data.http;

import com.demo.phonehelper.bean.BaseBean;
import com.demo.phonehelper.bean.LoginBean;
import com.demo.phonehelper.bean.requestbean.LoginRequestBean;
import com.demo.phonehelper.presenter.contract.LoginContract;

import rx.Observable;

/**
 * Created by Administrator on 2017/12/12.
 */

public class LoginModel implements LoginContract.ILoginModel {


    private ApiService mApiService;
    public LoginModel(ApiService apiService) {

        this.mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<LoginBean>> login(String phone, String psd) {
        LoginRequestBean param = new LoginRequestBean();
        param.setEmail(phone);
        param.setPassword(psd);

        return mApiService.login(param);
    }
}
