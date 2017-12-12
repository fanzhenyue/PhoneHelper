package com.demo.phonehelper.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.demo.phonehelper.R;
import com.demo.phonehelper.bean.LoginBean;
import com.demo.phonehelper.common.util.DeviceUtils;
import com.demo.phonehelper.di.component.AppComponent;

import com.demo.phonehelper.di.component.DaggerLoginComponent;
import com.demo.phonehelper.di.module.LoginModule;
import com.demo.phonehelper.presenter.LoginPresenter;
import com.demo.phonehelper.presenter.contract.LoginContract;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

/**
 *
 * Created by Administrator on 2017/12/8.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.LoginView{


    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.et_phone)
    EditText mEtPhoneNum;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.view_phone)
    TextInputLayout mViewPhone;
     @BindView(R.id.view_password)
    TextInputLayout mPassword;
    @BindView(R.id.btn_login)
    Button mBtnLogin;


    @Override
    public int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerLoginComponent.builder().appComponent(appComponent).loginModule(new LoginModule(this))
                .build().inject(this);
    }

    @Override
    public void init() {
        initView();
    }

    private void initView() {

        Observable<CharSequence> obPhone = RxTextView.textChanges(mEtPhoneNum);
        Observable<CharSequence> obPassword = RxTextView.textChanges(mEtPassword);

        Observable.combineLatest(obPhone, obPassword, new Func2<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean call(CharSequence obPhone, CharSequence obPassword) {
                return isPhoneValid(obPhone.toString()) && isPasswordValid(obPassword.toString());
            }
        }).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                RxView.enabled(mBtnLogin).call(aBoolean);
            }
        });
        RxView.clicks(mBtnLogin).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                mPresenter.login(mEtPhoneNum.getText().toString().trim(),mEtPassword.getText().toString().trim());
            }
        });

    }



    private boolean isPhoneValid(String phone){
        return phone.length()==11;
    }
    private boolean isPasswordValid(String password){
        return password.length()>=6;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void dismissLoading() {

    }

    //手机号格式不正确
    @Override
    public void checkPhoneError() {
        mViewPhone.setError("手机号码格式不正确");
    }

    @Override
    public void checkPhoneSuccess() {
        mViewPhone.setError("");
        mViewPhone.setEnabled(false);
    }

    @Override
    public void checkPasswordError() {

    }

    @Override
    public void loginSuccess(LoginBean bean) {
        Toast.makeText(LoginActivity.this,"登陆成功！",Toast.LENGTH_LONG).show();
    }


   /* @OnClick(R.id.btn)
    public void onClick(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_PHONE_STATE},READ_PHONE_STATE_CODE);
        }

        else {
            // 已经授权
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==READ_PHONE_STATE_CODE){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){

                String imei  = DeviceUtils.getIMEI(this);
                Toast.makeText(LoginActivity.this,"imei="+imei,Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(LoginActivity.this,"用户拒绝授权",Toast.LENGTH_LONG).show();
            }
        }
    }*/
}
