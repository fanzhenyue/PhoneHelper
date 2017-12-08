package com.demo.phonehelper.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.demo.phonehelper.R;
import com.demo.phonehelper.common.Constant;
import com.demo.phonehelper.common.util.ACache;
import com.eftimoff.androipathview.PathView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity {

    @BindView(R.id.activity_welcome)
    LinearLayout mActivityWelcome;
    @BindView(R.id.pathView)
    ImageView mPathView;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            public void run() {
                jump();
            }
        }, 1000);
        AlphaAnimation animation = new AlphaAnimation(0.5f,1);
        animation.setDuration(1000);
        mPathView.startAnimation(animation);

    }

    private void jump() {

        String isShowGuide = ACache.get(this).getAsString(Constant.IS_SHOW_GUIDE);


        //第一次启动进入引导页面
        if(isShowGuide == null){
            startActivity(new Intent(this,GuideActivity.class));
        }else{
            startActivity(new Intent(this,MainActivity.class));
        }
        this.finish();
    }
}
