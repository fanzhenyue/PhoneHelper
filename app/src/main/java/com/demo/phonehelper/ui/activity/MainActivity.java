package com.demo.phonehelper.ui.activity;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.phonehelper.R;
import com.demo.phonehelper.di.component.AppComponent;
import com.demo.phonehelper.ui.adapter.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    @BindView(R.id.drawLayout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.navigation_view)
    NavigationView  mNavigationView;

    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
     @BindView(R.id.view_pager)

    ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;


    private View headerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {

        initDrawerLayout();

        initTabLayout();

    }

    private void initTabLayout() {
        PagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);

        //设置viewpager和tablayout关联
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initDrawerLayout() {


        headerView = mNavigationView.getHeaderView(0);
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"headerView clicked",Toast.LENGTH_LONG).show();
            }
        });
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_app_update:
                        Toast.makeText(MainActivity.this,"点击应用更新",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.menu_message:
                        Toast.makeText(MainActivity.this,"点击消息",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.menu_setting:
                        Toast.makeText(MainActivity.this,"点击设置",Toast.LENGTH_LONG).show();
                        break;

                }
                return false;
            }
        });
        //初始化menu
        mToolbar.inflateMenu(R.menu.toolbar_menu);
        ActionBarDrawerToggle drawerToggle =new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,R.string.open,R.string.close);
        drawerToggle.syncState();
        mDrawerLayout.addDrawerListener(drawerToggle);

    }
}
