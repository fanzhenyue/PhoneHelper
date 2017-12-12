package com.demo.phonehelper.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.demo.phonehelper.ui.bean.FragmentInfo;
import com.demo.phonehelper.ui.fragment.CategoryFragment;
import com.demo.phonehelper.ui.fragment.GamesFragment;
import com.demo.phonehelper.ui.fragment.TopListFragment;
import com.demo.phonehelper.ui.fragment.RecommendFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 4个滑动标题适配器
 * Created by Administrator on 2017/12/4.
 *
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<FragmentInfo> mFragments = new ArrayList<>(4);
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        initFragments();
    }

    private void initFragments(){
        mFragments.add(new FragmentInfo("推荐",RecommendFragment.class));
        mFragments.add(new FragmentInfo("排行",TopListFragment.class));
        mFragments.add(new FragmentInfo("分类",CategoryFragment.class));
        mFragments.add(new FragmentInfo("游戏",GamesFragment.class));
    }
    @Override
    public Fragment getItem(int position) {

        try {
            return (Fragment) mFragments.get(position).getFragment().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
       /* Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new RecommendFragment();
                break;
             case 1:
                fragment = new TopListFragment();
                break;
             case 2:
                fragment = new GamesFragment();
                break;
             case 3:
                fragment = new CategoryFragment();
                break;

        }
        return mFragments.get(position);*/
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    //获取标题
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getTitle();
    }
}
