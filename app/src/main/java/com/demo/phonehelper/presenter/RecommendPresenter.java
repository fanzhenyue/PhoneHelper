package com.demo.phonehelper.presenter;

import com.demo.phonehelper.bean.IndexBean;
import com.demo.phonehelper.common.rx.RxHttpResponseCompat;
import com.demo.phonehelper.common.rx.subsriber.ProgressSubscriber;
import com.demo.phonehelper.data.AppInfoModel;
import com.demo.phonehelper.presenter.contract.AppInfoContract;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/12/5.
 *
 */

public class RecommendPresenter extends BasePresenter<AppInfoModel, AppInfoContract.View> {

//    private AppInfoModel mModel;
//
//    private AppInfoContract.View mView;


    @Inject
    public RecommendPresenter(AppInfoModel model, AppInfoContract.View view) {
        super(model, view);

    }

    /**
     * 封装的权限
     */
    /*public void requestPermission(){


        RxPermissions rxPermissions = new RxPermissions((Activity) mContext);

        rxPermissions.request(Manifest.permission.READ_PHONE_STATE).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                if(aBoolean){

                    mView.onRequestPermissionSuccess();
                }
                else{

                    mView.onRequestPermissionError();
                }
            }
        });

    }*/

//    public RecommendPresenter(AppInfoContract.View view,AppInfoModel model) {
//        this.mView = view;
//        mModel = model;
//    }

    //通过对view和model的指挥，不具体实现逻辑，具体逻辑交给model处理

    public void requestDatas() {

        mModel.index().compose(RxHttpResponseCompat.<IndexBean>compatResult())
                .subscribe(new ProgressSubscriber<IndexBean>(mContext,mView) {
                    @Override
                    public void onNext(IndexBean indexBean) {
                        mView.showResult(indexBean);
                    }
                });

       /* RxPermissions rxPermissions = new RxPermissions((Activity) mContext);

        rxPermissions.request(Manifest.permission.READ_PHONE_STATE)
                .flatMap(new Func1<Boolean, Observable<PageBean<AppInfo>>>() {
                    @Override
                    public Observable<PageBean<AppInfo>>call(Boolean aBoolean) {

                        if(aBoolean){

                            return  mModel.getApps().compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult());
                        }
                        else{

                            return Observable.empty();
                        }


                    }
                })
                .subscribe(new ProgressSubscriber<PageBean<AppInfo>>(mContext,mView) {
                    @Override
                    public void onNext(PageBean<AppInfo> appInfoPageBean) {
                        mView.showResult(appInfoPageBean.getDatas());
                    }
                });
*/

       /* mModel.getApps().
                compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(new ProgressSubscriber<PageBean<AppInfo>>(mContext,mView) {


                    @Override
                    public void onNext(PageBean<AppInfo> appInfoPageBean) {
                       mView.showResult(appInfoPageBean.getDatas());
                    }
                });*/


//        mView.showLoading();
//        mModel.getApps(new Callback<PageBean<AppInfo>>() {
//            @Override
//            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
//                if(response!=null){
//                    mView.showResult(response.body().getDatas());
//                }else{
//                    mView.showNoData();
//                }
//                mView.dismissLoading();
//            }
//
//            @Override
//            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {
//                mView.dismissLoading();
//                mView.showError(t.getMessage());
//            }
//        });
    }
}
