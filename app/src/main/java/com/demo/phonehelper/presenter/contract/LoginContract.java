package com.demo.phonehelper.presenter.contract;

import com.demo.phonehelper.bean.BaseBean;
import com.demo.phonehelper.bean.LoginBean;
import com.demo.phonehelper.ui.BaseView;

import rx.Observable;

/**
 * Created by Administrator on 2017/12/12.
 */

public interface LoginContract {

    public interface ILoginModel{


        Observable<BaseBean<LoginBean>> login(String phone, String psd);
    }

    public interface LoginView extends BaseView{

        void checkPhoneError();
        void checkPasswordError();

        void checkPhoneSuccess();
        void loginSuccess(LoginBean bean);

    }
}
