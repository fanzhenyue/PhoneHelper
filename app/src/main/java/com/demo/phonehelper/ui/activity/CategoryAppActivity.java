package com.demo.phonehelper.ui.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.demo.phonehelper.R;
import com.demo.phonehelper.bean.Category;
import com.demo.phonehelper.common.Constant;
import com.demo.phonehelper.di.component.AppComponent;
import com.demo.phonehelper.ui.adapter.CategoryAppViewPagerAdapter;
import com.demo.phonehelper.ui.adapter.ViewPagerAdapter;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/14.
 */

public class CategoryAppActivity extends BaseActivity {

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    private Category category;

    @Override
    public int setLayout() {
        return R.layout.activity_category_app;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {
        getData();
        initTabLayout();

    }

    private void getData(){

        Intent intent = getIntent();

         category = (Category) intent.getSerializableExtra(Constant.CATEGORY);
    }

    private void initTabLayout() {

        mToolBar.setNavigationIcon(R.drawable.ic_back_arrow);
        mToolBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        CategoryAppViewPagerAdapter adapter = new CategoryAppViewPagerAdapter(getSupportFragmentManager(),category.getId());
        mViewPager.setAdapter(adapter);

        //设置viewpager和tablayout关联
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
