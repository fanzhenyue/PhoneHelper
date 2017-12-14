package com.demo.phonehelper.ui.activity;

import android.Manifest;
import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.demo.phonehelper.R;
import com.demo.phonehelper.bean.User;
import com.demo.phonehelper.common.Constant;
import com.demo.phonehelper.common.imageloader.GlideCircleTransform;
import com.demo.phonehelper.common.imageloader.ImageLoader;
import com.demo.phonehelper.common.util.ACache;
import com.demo.phonehelper.common.util.PermissionUtil;
import com.demo.phonehelper.di.component.AppComponent;
import com.demo.phonehelper.ui.adapter.ViewPagerAdapter;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.mikepenz.iconics.IconicsDrawable;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

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

    private ImageView mUserHeadView;
    private TextView mTextUserName;

    private View headerView;



    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {
        //订阅事件的注册
        RxBus.get().register(this);


        PermissionUtil.requestPermission(this, Manifest.permission.READ_PHONE_STATE)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if(aBoolean){
                            initDrawerLayout();

                            initTabLayout();
                            initUser();
                        }else {
                            //------
                        }
                    }
                });
//        initDrawerLayout();
//
//        initTabLayout();



    }

    private void initTabLayout() {
        PagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);

        //设置viewpager和tablayout关联
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initDrawerLayout() {


        headerView = mNavigationView.getHeaderView(0);

        mUserHeadView  = (ImageView) findViewById(R.id.img_head_portrait);
//        mUserHeadView.setBackgroundResource(R.mipmap.bg_header);OnErrorNotImplementedException
        mTextUserName  = (TextView) findViewById(R.id.txt_username);
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
                    case R.id.menu_app_uninstall:
                        Toast.makeText(MainActivity.this,"点击消息",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.menu_logout:
                        logout();
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

    /**
     * 退出登陆
     */
    private void logout() {
        ACache aCache = ACache.get(this);

        aCache.put(Constant.TOKEN,"");
        aCache.put(Constant.USER,"");

        mUserHeadView.setImageResource(R.mipmap.bg_header);
        mTextUserName.setText(R.string.un_login);

        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });

        Toast.makeText(MainActivity.this,"您已退出登录",Toast.LENGTH_LONG).show();

    }

    @Subscribe
     public void getUser(User user){
       initUserHeadView(user);
     }
    private void initUserHeadView(User user) {
        Glide.with(this).load(user.getLogin_url()).transform(new GlideCircleTransform(this)).into(mUserHeadView);
        mTextUserName.setText(user.getUsername());
    }
     private void initUser(){
         Object objUser = ACache.get(this).getAsObject(Constant.USER);
         if(objUser==null){
             headerView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     startActivity(new Intent(MainActivity.this,LoginActivity.class));
                 }
             });
         }
         else {
             User user = (User) objUser;
             initUserHeadView(user);
         }
     }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }
}
