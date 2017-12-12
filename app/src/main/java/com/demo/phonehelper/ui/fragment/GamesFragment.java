package com.demo.phonehelper.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.phonehelper.R;
import com.demo.phonehelper.di.component.AppComponent;
import com.demo.phonehelper.di.component.DaggerAppInfoComponent;
import com.demo.phonehelper.di.module.AppInfoModule;
import com.demo.phonehelper.presenter.AppInfoPresenter;
import com.demo.phonehelper.ui.adapter.AppInfoAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class GamesFragment extends BaseAppInfoFragment {



    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerAppInfoComponent.builder().appComponent(appComponent).appInfoModule(new AppInfoModule(this))
                .build().injectGamesFragment(this);
    }

    @Override
    int type() {
        return AppInfoPresenter.GAME;
    }

    @Override
    AppInfoAdapter buildAdapter() {
        return AppInfoAdapter.builder().showPosition(false).showBrief(true).showCategory(true).build();
    }
}
