package com.demo.phonehelper.presenter;

import com.demo.phonehelper.bean.LoginBean;
import com.demo.phonehelper.common.rx.RxHttpResponseCompat;
import com.demo.phonehelper.common.rx.subsriber.ErrorHandlerSubscriber;
import com.demo.phonehelper.common.util.VerificationUtils;
import com.demo.phonehelper.presenter.contract.LoginContract;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/12/12.
 */

public class LoginPresenter extends BasePresenter<LoginContract.ILoginModel,LoginContract.LoginView> {


    @Inject
    public LoginPresenter(LoginContract.ILoginModel iLoginModel, LoginContract.LoginView loginView) {
        super(iLoginModel, loginView);
    }

    public void login (String phone,String psd){
        if ( !VerificationUtils.matcherPhoneNum(phone)){
            mView.checkPhoneError();
            return;
        }
        else {
            mView.checkPhoneSuccess();
        }
      if ( VerificationUtils.matcherPassword(psd)){
            mView.checkPasswordError();
            return;
        }
        mModel.login(phone, psd).compose(RxHttpResponseCompat.<LoginBean>compatResult())
                .subscribe(new ErrorHandlerSubscriber<LoginBean>(mContext) {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        mView.loginSuccess(loginBean);
                    }
                });

    }
}
