package com.demo.phonehelper.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.demo.phonehelper.R;
import com.demo.phonehelper.bean.Category;
import com.demo.phonehelper.common.Constant;
import com.demo.phonehelper.di.component.AppComponent;
import com.demo.phonehelper.di.component.DaggerCategoryComponent;
import com.demo.phonehelper.di.module.CategoryModule;
import com.demo.phonehelper.presenter.CategoryPresenter;
import com.demo.phonehelper.presenter.contract.CategoryContract;
import com.demo.phonehelper.ui.activity.CategoryAppActivity;
import com.demo.phonehelper.ui.adapter.CategoryAdapter;
import com.demo.phonehelper.ui.decoration.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends ProgressFragment<CategoryPresenter> implements CategoryContract.CategoryView{

    @BindView(R.id.recycler_view)
    RecyclerView mRecycleView;

    private CategoryAdapter mAdapter;

    @Override
    public int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    public void init() {
        initRecyclerView();

        mPresenter.getALlCategory();
    }

    private void initRecyclerView() {
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()) );

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST);

        mRecycleView.addItemDecoration(itemDecoration);
        mAdapter = new CategoryAdapter();

        mRecycleView.setAdapter(mAdapter);

        mRecycleView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                Intent intent = new Intent(getActivity(), CategoryAppActivity.class);

                intent.putExtra(Constant.CATEGORY,mAdapter.getData().get(position));

                startActivity(intent);
            }
        });

    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerCategoryComponent.builder().appComponent(appComponent).categoryModule(new CategoryModule(this))
                .build().inject(this);
    }


    @Override
    public void showData(List<Category> categories) {
        mAdapter.addData(categories);
    }
}
