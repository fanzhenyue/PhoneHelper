package com.demo.phonehelper.ui.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.demo.phonehelper.R;
import com.demo.phonehelper.common.Constant;
import com.demo.phonehelper.common.util.ACache;
import com.demo.phonehelper.ui.adapter.GuideFragmentAdapter;
import com.demo.phonehelper.ui.fragment.GamesFragment;
import com.demo.phonehelper.ui.fragment.GuideFragment;
import com.demo.phonehelper.ui.widget.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    @BindView(R.id.view_pager)
    ViewPager  mViewPager;
    @BindView(R.id.btn_enter)
    Button mBtnEnter;
    @BindView(R.id.indicator)
    CircleIndicator mIndicator;
    @BindView(R.id.activity_guide)
    RelativeLayout mActivityGuide;



    private GuideFragmentAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {



        List<Fragment> fragments = new ArrayList<>();
        fragments.add(GuideFragment.newInstance(R.drawable.guide_1,R.color.color_bg_guide1,R.string.guide_1));
        fragments.add(GuideFragment.newInstance(R.drawable.guide_2,R.color.color_bg_guide2,R.string.guide_2));
        fragments.add(GuideFragment.newInstance(R.drawable.guide_3,R.color.color_bg_guide3,R.string.guide_3));

        mAdapter = new GuideFragmentAdapter(getSupportFragmentManager());
        mAdapter.setmFragments(fragments);

        mViewPager.setCurrentItem(0);
        mViewPager.setOffscreenPageLimit(mAdapter.getCount());
        mViewPager.setAdapter(mAdapter);

        mViewPager.addOnPageChangeListener(this);//添加监听

//                mViwPager.setPageTransformer();
        mIndicator.setViewPager(mViewPager);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //判断这个indicator滑动位置是否等于adapter数量，来控制按钮显示
        mBtnEnter.setVisibility((position == mAdapter.getCount()-1)? View.VISIBLE : View.GONE);
        mBtnEnter.setVisibility((position == mAdapter.getCount() - 1) ? View.VISIBLE : View.GONE);

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    @OnClick(R.id.btn_enter)
    public void onClick(){
        //当点击过后，将0保存到缓存，下一次启动就跳过引导activity
        ACache.get(this).put(Constant.IS_SHOW_GUIDE,"0");
        startActivity(new Intent(this,MainActivity.class));
        this.finish();
    }
}
