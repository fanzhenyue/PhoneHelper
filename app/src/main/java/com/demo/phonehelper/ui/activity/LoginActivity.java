package com.demo.phonehelper.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.widget.Button;
import android.widget.Toast;

import com.demo.phonehelper.R;
import com.demo.phonehelper.common.util.DeviceUtils;
import com.demo.phonehelper.di.component.AppComponent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *
 * Created by Administrator on 2017/12/8.
 */

public class LoginActivity extends BaseActivity{

    private static final int READ_PHONE_STATE_CODE = 1000;
    @BindView(R.id.btn)
    Button mBtn;
    @Override
    public int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    public void init() {

    }


    @OnClick(R.id.btn)
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
    }
}
