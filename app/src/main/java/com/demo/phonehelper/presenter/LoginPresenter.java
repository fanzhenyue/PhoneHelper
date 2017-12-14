package com.demo.phonehelper.presenter;

import android.util.Log;

import com.demo.phonehelper.bean.LoginBean;
import com.demo.phonehelper.common.Constant;
import com.demo.phonehelper.common.rx.RxHttpResponseCompat;
import com.demo.phonehelper.common.rx.subsriber.ErrorHandlerSubscriber;
import com.demo.phonehelper.common.util.ACache;
import com.demo.phonehelper.common.util.VerificationUtils;
import com.demo.phonehelper.presenter.contract.LoginContract;
import com.hwangjr.rxbus.RxBus;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/12/12.
 */

public class LoginPresenter extends BasePresenter<LoginContract.ILoginModel, LoginContract.LoginView> {


    @Inject
    public LoginPresenter(LoginContract.ILoginModel iLoginModel, LoginContract.LoginView loginView) {
        super(iLoginModel, loginView);
    }

    public void login(String phone, String psd) {

        Log.d("LoginPresenter", "phone=" + phone);
        Log.d("LoginPresenter", "pwd=" + psd);
        if (!VerificationUtils.matcherPhoneNum(phone)) {
            mView.checkPhoneError();
            return;
        } else {
            mView.checkPhoneSuccess();
        }
        if (!VerificationUtils.matcherPassword(psd)) {
            mView.checkPasswordError();
            return;
        }
        mModel.login(phone, psd).compose(RxHttpResponseCompat.<LoginBean>compatResult())
                .subscribe(new ErrorHandlerSubscriber<LoginBean>(mContext) {
                    @Override
                    public void onStart() {
                        mView.showLoading();
                    }

                    @Override
                    public void onCompleted() {
                        mView.dismissLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.dismissLoading();
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        mView.loginSuccess(loginBean);
                        saveUser(loginBean);


                        RxBus.get().post(loginBean);
                    }
                });

    }

    private void saveUser(LoginBean bean) {
        ACache aCache = ACache.get(mContext);

        aCache.put(Constant.TOKEN, bean.getToken());
        aCache.put(Constant.USER, bean.getToken());
    }


}
