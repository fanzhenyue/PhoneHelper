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
    public static final int  CATEGORY=3;


    public static final int FEATURED=0;
    public static final int TOPLIST=1;
    public static final int NEWLIST=2;

    @Inject
    public AppInfoPresenter(AppInfoModel appInfoModel, AppInfoContract.AppInfoView topListView) {
        super(appInfoModel, topListView);
    }

    public void  request(int type,int page,int categoryId,int flagType){

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

        Observable observable = getObservable(type,page,categoryId,flagType);
//        mModel.topList(page)
        observable
                .compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(subscriber);
    }

    public void  requestData(int type,int page){

        request(type,page,0,0);

    }

    public void requestCategory(int categoryId,int page,int flagType){
        request(CATEGORY,page,categoryId,flagType);
    }

    private Observable<BaseBean<PageBean<AppInfo>>> getObservable(int type,int page ,int categoryId,int flagType){
        switch (type){
            case TOP_LIST:
                return  mModel.topList(page);

            case GAME:
                return  mModel.games(page);

            case CATEGORY:
                if (flagType==FEATURED){
                    return mModel.getFeaturedAppsByCategory(categoryId,page);
                }else  if (flagType==TOP_LIST){
                    return mModel.getTopListAppsByCategory(categoryId,page);
                }else if (flagType==NEWLIST){
                    return mModel.getNewListAppsByCategory(categoryId,page);
                }

            default:
                return Observable.empty();

        }

    }
}
