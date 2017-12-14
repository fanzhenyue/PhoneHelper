package com.demo.phonehelper.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.demo.phonehelper.R;
import com.demo.phonehelper.di.component.AppComponent;

import butterknife.BindView;

/**
 * 
 * Created by Administrator on 2017/12/14.
 */

public class AppDetailActivity extends BaseActivity {


    @BindView(R.id.imgView)
    ImageView mImageView;

    @Override
    public int setLayout() {
        return R.layout.activity_app_detail;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {
        View view = mApplication.getView();

        Bitmap bitmap =  getViewImageCache(view);

        if (bitmap!=null){
            mImageView.setImageBitmap(bitmap);
        }
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
}
