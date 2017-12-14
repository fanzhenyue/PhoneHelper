package com.demo.phonehelper.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.demo.phonehelper.ui.bean.FragmentInfo;
import com.demo.phonehelper.ui.fragment.CategoryAppFragment;
import com.demo.phonehelper.ui.fragment.CategoryFragment;
import com.demo.phonehelper.ui.fragment.GamesFragment;
import com.demo.phonehelper.ui.fragment.RecommendFragment;
import com.demo.phonehelper.ui.fragment.TopListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 4个滑动标题适配器
 * Created by Administrator on 2017/12/4.
 *
 */

public class CategoryAppViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<String> titles = new ArrayList<>(3);
    private int categoryId;
    public CategoryAppViewPagerAdapter(FragmentManager fm,int categoryId) {
        super(fm);
        this.categoryId = categoryId;

        titles.add("精品");
        titles.add("排行");
        titles.add("新品");
    }


    @Override
    public Fragment getItem(int position) {
//        CategoryAppFragment.FEATURED = position;0,1,2
        return new  CategoryAppFragment(categoryId,position);

    }

    @Override
    public int getCount() {
        return titles.size();
    }

    //获取标题
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
