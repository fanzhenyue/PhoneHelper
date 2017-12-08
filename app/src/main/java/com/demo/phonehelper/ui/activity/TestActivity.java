package com.demo.phonehelper.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.demo.phonehelper.R;

public class TestActivity extends AppCompatActivity {

    /**
     * common
     * 包含util、config、constant等通用包和类
     *
     * data
     * 包含model，api,db，perf,网络接口实现等
     * 其中公开一个DataRepository提供业务接口相关数据
     *
     * di
     * 依赖注入相关的类
     * 根据dagger2风格，一般有module和component模块
     *
     * presenter
     * 里面根据业务模块划分
     *
     * ui
     * 包括UI层所有东西，activity、fragmen、widget、dialog、adapter等，根据需求不同分包方式有出入
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }
}
