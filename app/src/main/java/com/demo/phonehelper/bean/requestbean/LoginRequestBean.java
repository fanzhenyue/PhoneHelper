package com.demo.phonehelper.bean.requestbean;

/**
 * Created by Administrator on 2017/12/8.
 */

public class LoginRequestBean {

    private String phone;
    private String password;

    public LoginRequestBean(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }
}
