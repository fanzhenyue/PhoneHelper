package com.demo.phonehelper.presenter.contract;

import com.demo.phonehelper.bean.AppInfo;
import com.demo.phonehelper.bean.IndexBean;
import com.demo.phonehelper.bean.PageBean;
import com.demo.phonehelper.presenter.BasePresenter;
import com.demo.phonehelper.ui.BaseView;

import java.util.List;

/**
 * 推荐的业务接口，MVP中的层
 * Created by Administrator on 2017/12/5.
 *
 */

public interface AppInfoContract {

    interface View extends BaseView{

        //显示结果
        void showResult(IndexBean indexBean);

        //没有数据
//        void showNoData();
        //出错接口
//        void showError(String msg);

        void onRequestPermissionSuccess();
        void onRequestPermissionError();

    }

    interface AppInfoView extends BaseView{

        void shoResult(PageBean<AppInfo> appInfoPageBean);

        void onLoadCompleted();
    }



}
