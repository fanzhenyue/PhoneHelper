package com.demo.phonehelper.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.demo.phonehelper.R;
import com.demo.phonehelper.bean.AppInfo;
import com.demo.phonehelper.bean.PageBean;
import com.demo.phonehelper.di.component.AppComponent;
import com.demo.phonehelper.di.component.DaggerTopListComponent;
import com.demo.phonehelper.di.module.TopListModule;
import com.demo.phonehelper.presenter.TopListPresenter;
import com.demo.phonehelper.presenter.contract.AppInfoContract;
import com.demo.phonehelper.ui.adapter.AppInfoAdapter;
import com.demo.phonehelper.ui.decoration.DividerItemDecoration;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopListFragment extends ProgressFragment<TopListPresenter>
        implements AppInfoContract.TopListView ,BaseQuickAdapter.RequestLoadMoreListener{

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private AppInfoAdapter mAdapter;

    int page = 0;

    @Override
    public int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    public void init() {
        mPresenter.getTopListApps(page);
        initRecyclerView();
    }

    private void initRecyclerView(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), android.support.v7.widget.DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
        //构建者
        mAdapter = AppInfoAdapter.builder().showPosition(true).showBrief(false).showCategory(true).build();

        mAdapter.setOnLoadMoreListener(this);//开源库的加载更多监听
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerTopListComponent.builder().appComponent(appComponent).topListModule(new TopListModule(this))
                .build().inject(this);
    }

    /**
     * 显示数据
     * @param appInfoPageBean AppInfo
     */
    @Override
    public void shoResult(PageBean<AppInfo> appInfoPageBean) {

        mAdapter.addData(appInfoPageBean.getDatas());
        //如果有加载更多，就让page++
        if (appInfoPageBean.isHasMore()){
            page++;
        }
        //根据返回的结果确定是否开启加载更多
        mAdapter.setEnableLoadMore(appInfoPageBean.isHasMore());
    }

    /**
     * 下拉加载更多完成时，调用这个方法
     */
    @Override
    public void onLoadCompleted() {
        mAdapter.loadMoreComplete();
    }

    /**
     * 下拉加载更多的实现方法
     * 如果有加载更多，就通过page加载更多数据
     */
    @Override
    public void onLoadMoreRequested() {
        mPresenter.getTopListApps(page);
    }
}
