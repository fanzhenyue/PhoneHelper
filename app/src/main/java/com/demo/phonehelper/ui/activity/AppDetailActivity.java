package com.demo.phonehelper.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.demo.phonehelper.R;
import com.demo.phonehelper.bean.AppInfo;
import com.demo.phonehelper.common.Constant;
import com.demo.phonehelper.common.util.DensityUtil;
import com.demo.phonehelper.di.component.AppComponent;
import com.demo.phonehelper.di.component.DaggerAppDetailComponent;
import com.demo.phonehelper.di.component.DaggerAppInfoComponent;
import com.demo.phonehelper.di.module.AppDetailModule;
import com.demo.phonehelper.di.module.AppModelModule;
import com.demo.phonehelper.presenter.AppDetailPresenter;
import com.demo.phonehelper.presenter.contract.AppInfoContract;
import com.demo.phonehelper.ui.fragment.AppDetailFragment;

import butterknife.BindView;

/**
 *详情页，每个item点击后的详情
 * Created by Administrator on 2017/12/14.
 */

public class AppDetailActivity extends BaseActivity<AppDetailPresenter>  {


    @BindView(R.id.view_content)
    FrameLayout mViewContent;

    private AppInfo mAppInfo;

    @Override
    public int setLayout() {
        return R.layout.activity_app_detail;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        /*DaggerAppDetailComponent.builder().appComponent(appComponent)
                .appDetailModule(new AppDetailModule(this))
                .appModelModule(new AppModelModule())
                .build().inject(this);*/
    }

    @Override
    public void init() {

        mAppInfo = (AppInfo) getIntent().getSerializableExtra(Constant.APP_INFO);
        Log.e("APP", "initFragment: "+mAppInfo.getId() +mAppInfo.getDisplayName());

        initFragment();

       /* View view = mApplication.getView();

        Bitmap bitmap =  getViewImageCache(view);

        if (bitmap!=null){
            mViewContent.setBackgroundDrawable(new BitmapDrawable(bitmap));
        }

        int[] location = new int[2];
        view.getLocationOnScreen(location);

        //获取view相对于屏幕左侧、顶部位置，坐标
        int left = location[0];
        int top = location[1];

        ViewGroup.MarginLayoutParams marginLayoutParams  = new ViewGroup.MarginLayoutParams(mViewContent.getLayoutParams());

        marginLayoutParams.topMargin = top- DensityUtil.getStatusBarH(this);//减去状态栏的高度，获取相对应的高度，不出现偏差
        marginLayoutParams.leftMargin = left;
        marginLayoutParams.width = view.getWidth();
        marginLayoutParams.height = view.getHeight();

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(marginLayoutParams);

        //将获取的参数设置到linearLayout中
        mViewContent.setLayoutParams(params);

        open();
*/
    }

    /**
     * view向下展开的动画
     */
    private  void  open(){

        //获取屏幕的高度
        int h = DensityUtil.getScreenH(this);

        ObjectAnimator animator = ObjectAnimator.ofFloat(mViewContent,"scaleY",1f,(float) h);

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                //动画开始设置背景颜色为白色
                mViewContent.setBackgroundColor(getResources().getColor(R.color.white));
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                initFragment();
            }
        });
        animator.setStartDelay(500);//设置东动画开始延时1秒
        animator.setDuration(10000);
        animator.start();


    }

    /**
     * 获取imageView缓存
     * @param view View
     * @return Bitmap
     */
    private Bitmap getViewImageCache(View view){

        view.setDrawingCacheEnabled(true);///打开
        view.buildDrawingCache();//绘制缓存

        Bitmap bitmap = view.getDrawingCache();

        if (bitmap==null)
            return null;

        bitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight());

        view.destroyDrawingCache();//销毁

        return bitmap;
    }

    /**
     * 初始化appDetailFragment
     */
    private void  initFragment(){
        AppDetailFragment fragment = new AppDetailFragment(mAppInfo.getId());

        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();

        transaction.add(R.id.view_content,fragment);
        transaction.commitAllowingStateLoss();
    }

}
