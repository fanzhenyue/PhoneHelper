package com.demo.phonehelper.presenter;

import com.demo.phonehelper.bean.AppInfo;
import com.demo.phonehelper.bean.PageBean;
import com.demo.phonehelper.common.rx.RxHttpResponseCompat;
import com.demo.phonehelper.common.rx.subsriber.ErrorHandlerSubscriber;
import com.demo.phonehelper.common.rx.subsriber.ProgressSubscriber;
import com.demo.phonehelper.data.AppInfoModel;
import com.demo.phonehelper.presenter.contract.AppInfoContract;
import com.demo.phonehelper.ui.fragment.ProgressFragment;

import javax.inject.Inject;

import rx.Subscriber;

/**
 *
 * Created by Administrator on 2017/12/12.
 */

public class TopListPresenter extends BasePresenter<AppInfoModel,AppInfoContract.TopListView> {


    @Inject
    public TopListPresenter(AppInfoModel appInfoModel, AppInfoContract.TopListView topListView) {
        super(appInfoModel, topListView);
    }

    public  void getTopListApps(int page){

        Subscriber subscriber = null;

        if (page==0){
            //第一页显示
            subscriber = new ProgressSubscriber<PageBean<AppInfo>>(mContext,mView) {
                @Override
                public void onNext(PageBean<AppInfo> appInfoPageBean) {
                    mView.shoResult(appInfoPageBean);
                }
            };
        }else {
            //下一页
            subscriber = new ErrorHandlerSubscriber<PageBean<AppInfo>>(mContext) {

                @Override
                public void onCompleted() {
                    mView.onLoadCompleted();
                }

                @Override
                public void onNext(PageBean<AppInfo> appInfoPageBean) {
                    mView.shoResult(appInfoPageBean);
                }
            };
        }


        mModel.topList(page)
                .compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(subscriber);
    }
}
