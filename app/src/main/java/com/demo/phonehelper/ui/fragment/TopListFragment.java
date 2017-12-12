package com.demo.phonehelper.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.demo.phonehelper.R;
import com.demo.phonehelper.bean.AppInfo;
import com.demo.phonehelper.bean.PageBean;
import com.demo.phonehelper.di.component.AppComponent;

import com.demo.phonehelper.di.component.DaggerAppInfoComponent;
import com.demo.phonehelper.di.module.AppInfoModule;
import com.demo.phonehelper.presenter.AppInfoPresenter;
import com.demo.phonehelper.presenter.contract.AppInfoContract;
import com.demo.phonehelper.ui.adapter.AppInfoAdapter;
import com.demo.phonehelper.ui.decoration.DividerItemDecoration;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopListFragment extends BaseAppInfoFragment{


    @Override
    int type() {
        return AppInfoPresenter.TOP_LIST;
    }

    @Override
    AppInfoAdapter buildAdapter() {
        return AppInfoAdapter.builder().showPosition(true).showBrief(false).showCategory(true).build();
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerAppInfoComponent.builder().appComponent(appComponent).appInfoModule(new AppInfoModule(this))
                .build().injectTopListFragment(this);
    }


}
