package com.demo.phonehelper.presenter;

import com.demo.phonehelper.bean.AppInfo;
import com.demo.phonehelper.common.rx.RxHttpResponseCompat;
import com.demo.phonehelper.common.rx.subsriber.ProgressSubscriber;
import com.demo.phonehelper.data.AppInfoModel;
import com.demo.phonehelper.presenter.contract.AppInfoContract;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/12/18.
 */

public class AppDetailPresenter extends BasePresenter<AppInfoModel,AppInfoContract.AppDetailView> {

    @Inject
    public AppDetailPresenter(AppInfoModel appInfoModel, AppInfoContract.AppDetailView appDetailView) {
        super(appInfoModel, appDetailView);
    }

    public void getAppDetail(int id){

        mModel.getAppDetail(id).compose(RxHttpResponseCompat.<AppInfo>compatResult())
                .subscribe(new ProgressSubscriber<AppInfo>(mContext,mView) {
                    @Override
                    public void onNext(AppInfo appInfo) {
                        mView.showAppDetail(appInfo);
                    }
                });
    }
}
