package com.demo.phonehelper.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.demo.phonehelper.AppApplication;
import com.demo.phonehelper.R;
import com.demo.phonehelper.di.component.AppComponent;
import com.demo.phonehelper.presenter.BasePresenter;
import com.demo.phonehelper.presenter.contract.RecommendContract;
import com.demo.phonehelper.ui.BaseView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 改进后的基类，progressFragment
 * Created by Administrator on 2017/12/8.
 */

public abstract class ProgressFragment<T extends BasePresenter> extends Fragment implements BaseView {

    private FrameLayout mRootView;

    private View mViewProgress;
    private FrameLayout mViewContent;
    private View mViewEmpty;
    private Unbinder mUnbinder;
    private TextView mTextError;

    private AppApplication mApplication;

    @Inject
    T mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRootView  = (FrameLayout) inflater.inflate(R.layout.fragment_progress,container,false);

        mViewProgress = mRootView.findViewById(R.id.view_progress);
        mViewContent = (FrameLayout) mRootView.findViewById(R.id.view_content);
        mViewEmpty = mRootView.findViewById(R.id.view_empty);
        mTextError = (TextView) mRootView.findViewById(R.id.text_tip);

        mViewEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEmptyViewClick();
            }
        });

        return mRootView;
    }

    /**
     * 空布局点击方法，子类需要时可以调用
     */
    public void onEmptyViewClick() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mApplication = (AppApplication) getActivity().getApplication();
        setupActivityComponent(mApplication.getAppComponent());

        setRealContentView();

        init();
    }

    //创建一个真正的view，拿到需要加载的布局view_content
    private void setRealContentView() {
        View realContentView = LayoutInflater.from(getActivity()).inflate(setLayout(),mViewContent,true);
        mUnbinder = ButterKnife.bind(this,realContentView);
    }

    /**
     * 显示加载loading内容
     */
    public void showProgressView() {
        showView(R.id.view_progress);
    }

    /**
     * 显示正常加载后的布局内容
     */
    public void showContentView() {
        showView(R.id.view_content);
    }

    /**
     * 获取数据为空的布局
     */
   public void showEmptyView() {
        showView(R.id.view_empty);
    }

    /**
     * 获取数据为空的布局
     * @param resId int
     */

   public void showEmptyView(int resId) {
        showView(R.id.view_empty);
       mTextError.setText(resId);
    }
    /**
     * 获取数据为空的布局
     * @param msg 显示的信息
     */
   public void showEmptyView(String msg) {
        showView(R.id.view_empty);
       mTextError.setText(msg);
    }


    /**
     *
     * @param viewId rootID
     *   view的显示方法
     */

    public void showView(int viewId){
        for (int i = 0; i <mRootView.getChildCount() ; i++) {
            if(mRootView.getChildAt(i).getId() == viewId){
                mRootView.getChildAt(i).setVisibility(View.VISIBLE);
            }else {
                mRootView.getChildAt(i).setVisibility(View.GONE);
            }
        }
    }

    /**
     * 设置布局 初始化id
     * @return 布局资源
     */
    public abstract int setLayout();

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(mUnbinder != Unbinder.EMPTY){
            mUnbinder.unbind();
        }
    }

    @Override
    public void showLoading() {
        showProgressView();
    }

    @Override
    public void showError(String msg) {
        showEmptyView(msg);
    }

    @Override
    public void dismissLoading() {
        showContentView();
    }

    public abstract void init();

    public abstract void setupActivityComponent(AppComponent appComponent);
}
