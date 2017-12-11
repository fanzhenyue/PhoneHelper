package com.demo.phonehelper.presenter;

import android.Manifest;
import android.app.Activity;

import com.demo.phonehelper.bean.AppInfo;
import com.demo.phonehelper.bean.BaseBean;
import com.demo.phonehelper.bean.IndexBean;
import com.demo.phonehelper.bean.PageBean;
import com.demo.phonehelper.common.rx.RxErrorHandler;
import com.demo.phonehelper.common.rx.RxHttpResponseCompat;
import com.demo.phonehelper.common.rx.subsriber.ErrorHandlerSubscriber;
import com.demo.phonehelper.common.rx.subsriber.ProgressDialogSubscriber;
import com.demo.phonehelper.common.rx.subsriber.ProgressSubscriber;
import com.demo.phonehelper.data.RecommendModel;
import com.demo.phonehelper.presenter.contract.RecommendContract;
import com.tbruyelle.rxpermissions.RxPermissions;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/12/5.
 *
 */

public class RecommendPresenter extends BasePresenter<RecommendModel, RecommendContract.View> {

//    private RecommendModel mModel;
//
//    private RecommendContract.View mView;


    @Inject
    public RecommendPresenter(RecommendModel model, RecommendContract.View view) {
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

//    public RecommendPresenter(RecommendContract.View view,RecommendModel model) {
//        this.mView = view;
//        mModel = model;
//    }

    //通过对view和model的指挥，不具体实现逻辑，具体逻辑交给model处理

    public void requestDatas() {

        mModel.inex().compose(RxHttpResponseCompat.<IndexBean>compatResult())
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
