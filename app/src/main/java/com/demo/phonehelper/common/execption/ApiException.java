package com.demo.phonehelper.common.execption;

/**
 * Created by Administrator on 2017/12/7.
 */

public class ApiException extends BaseException {




    public ApiException(int code, String displayMessage) {
        super(code, displayMessage);
    }
}
