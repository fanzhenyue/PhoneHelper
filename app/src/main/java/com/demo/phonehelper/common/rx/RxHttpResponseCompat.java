package com.demo.phonehelper.common.rx;

import com.demo.phonehelper.bean.BaseBean;
import com.demo.phonehelper.common.execption.ApiException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * HTTP响应结果预处理
 * Created by Administrator on 2017/12/7.
 */

public class RxHttpResponseCompat {
    public static <T> Observable.Transformer<BaseBean<T>,T> compatResult(){

        return new Observable.Transformer<BaseBean<T>, T>() {
            @Override
            public Observable<T> call(Observable<BaseBean<T>> baseBeanObservable) {


                return baseBeanObservable.flatMap(new Func1<BaseBean<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(final BaseBean<T> tBaseBean) {

                        if(tBaseBean.success()){

                           return Observable.create(new Observable.OnSubscribe<T>() {
                                @Override
                                public void call(Subscriber<? super T> subscriber) {

                                    try{
                                        subscriber.onNext(tBaseBean.getData());
                                        subscriber.onCompleted();
                                    }catch(Exception e){
                                        subscriber.onError(e);
                                    }
                                }
                            });
                        }else {
                            //错误时返回异常
                           return Observable.error(new ApiException(tBaseBean.getStatus(),tBaseBean.getMessage()));
                        }

                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
            }
        };
    }



}
