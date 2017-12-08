package com.demo.phonehelper.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/6.
 */

public class GuideFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;

    public void setmFragments(List<Fragment> fragments) {
        if(fragments==null){
            mFragments = new ArrayList<>();
        }else
        mFragments = fragments;
    }

    public GuideFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
