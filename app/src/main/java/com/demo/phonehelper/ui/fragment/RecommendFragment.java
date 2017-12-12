package com.demo.phonehelper.ui.fragment;


import android.app.ProgressDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.demo.phonehelper.R;


import com.demo.phonehelper.bean.IndexBean;
import com.demo.phonehelper.di.component.AppComponent;
import com.demo.phonehelper.di.component.DaggerRecommendComponent;
import com.demo.phonehelper.di.module.RecommendModule;
import com.demo.phonehelper.presenter.RecommendPresenter;
import com.demo.phonehelper.presenter.contract.AppInfoContract;
import com.demo.phonehelper.ui.adapter.IndexMultiAdapter;

import javax.inject.Inject;

import butterknife.BindView;


/**
 * 推荐类
 */
public class RecommendFragment extends ProgressFragment<RecommendPresenter> implements AppInfoContract.View {
    private static final String TAG = "RecommendFragment";
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private IndexMultiAdapter mAdapter;

//    @Inject
//     AppInfoContract.Presenter mPresenter;

    @Inject
     ProgressDialog progressDialog;
   /* @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(, container, false);
        ButterKnife.bind(this,view);

//        progressDialog = new ProgressDialog(getActivity());
//        mPresenter = new RecommendPresenter(this);

//        DaggerRecommendComponent.builder()
//                .recommendModule(new RecommendModule(this)).build().inject(this);

        DaggerRecommendComponent.builder().appComponent(((AppApplication) getActivity().getApplication()).getAppComponent())
                .recommendModule(new RecommendModule(this)).build().inject(this);

        return view;
    }*/

    @Override
    public int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerRecommendComponent.builder().appComponent(appComponent)
                .recommendModule(new RecommendModule(this)).build().inject(this);
    }

    @Override
    public void init() {
//        mPresenter.requestPermission();
        initRecycleView();
        mPresenter.requestDatas();
    }

    @Override
    public void onEmptyViewClick() {
        mPresenter.requestDatas();
    }

    /* private void initData() {
           mPresenter.requestDatas();

           *//* HttpManager manager = new HttpManager();

        ApiService apiService = manager.getRetrofit(manager.getOkHttpClient()).create(ApiService.class);

        apiService.getApps("{'page':0}").enqueue(new Callback<PageBean<AppInfo>>() {
            @Override
            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
                List<AppInfo> appInfo = response.body().getDatas();
                initRecycleView(appInfo);
            }

            @Override
            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {

            }
        });*//*
    }
*/
    private void  initRecycleView(){
        //为RecycleView设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //为RecycleView设置分割线，（可以对DividerItemDecoration进行修改，自定义）
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.HORIZONTAL_LIST));

        //动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


    }

    @Override
    public void showResult(IndexBean indexBean) {
        mAdapter = new IndexMultiAdapter(getActivity());
        mAdapter.setData(indexBean);

        mRecyclerView.setAdapter(mAdapter);
    }
    /* @Override
    public void showResult(List<AppInfo> datas) {
        initRecycleView(datas);
    }*/



    /*@Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void dismissLoading() {
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }*/

//    @Override
//    public void showNoData() {
//        Toast.makeText(getActivity(),"暂时无数据",Toast.LENGTH_LONG).show();
//    }

 /*   @Override
    public void showError(String msg) {
        Toast.makeText(getActivity(),"服务器开小差"+msg,Toast.LENGTH_LONG).show();
    }*/

    @Override
    public void onRequestPermissionSuccess() {
        mPresenter.requestDatas();//同意授权后才可以调用数据
    }

    @Override
    public void onRequestPermissionError() {
        Toast.makeText(getActivity(),"你已拒绝授权",Toast.LENGTH_LONG).show();
    }
}
