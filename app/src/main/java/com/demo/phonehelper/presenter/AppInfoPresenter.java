package com.demo.phonehelper.presenter;

import com.demo.phonehelper.bean.AppInfo;
import com.demo.phonehelper.bean.BaseBean;
import com.demo.phonehelper.bean.PageBean;
import com.demo.phonehelper.common.rx.RxHttpResponseCompat;
import com.demo.phonehelper.common.rx.subsriber.ErrorHandlerSubscriber;
import com.demo.phonehelper.common.rx.subsriber.ProgressSubscriber;
import com.demo.phonehelper.data.AppInfoModel;
import com.demo.phonehelper.presenter.contract.AppInfoContract;
import com.demo.phonehelper.ui.fragment.ProgressFragment;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

/**
 *
 * Created by Administrator on 2017/12/12.
 */

public class AppInfoPresenter extends BasePresenter<AppInfoModel,AppInfoContract.AppInfoView> {

    public static final int TOP_LIST =1;
    public static final int GAME =2;

    @Inject
    public AppInfoPresenter(AppInfoModel appInfoModel, AppInfoContract.AppInfoView topListView) {
        super(appInfoModel, topListView);
    }

    public  void requestData(int type,int page){

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

        Observable observable = getObservable(type,page);
//        mModel.topList(page)
        observable
                .compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(subscriber);
    }

    private Observable<BaseBean<PageBean<AppInfo>>> getObservable(int type,int page){
        switch (type){
            case TOP_LIST:
                return  mModel.topList(page);

            case GAME:
                return  mModel.games(page);

            default:
                return Observable.empty();

        }

    }
}
