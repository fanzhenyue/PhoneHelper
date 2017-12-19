package com.demo.phonehelper.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.phonehelper.R;
import com.demo.phonehelper.bean.AppInfo;
import com.demo.phonehelper.common.Constant;
import com.demo.phonehelper.common.imageloader.ImageLoader;
import com.demo.phonehelper.common.util.DateUtil;
import com.demo.phonehelper.di.component.AppComponent;
import com.demo.phonehelper.di.component.DaggerAppDetailComponent;
import com.demo.phonehelper.di.module.AppDetailModule;
import com.demo.phonehelper.di.module.AppModelModule;
import com.demo.phonehelper.presenter.AppDetailPresenter;
import com.demo.phonehelper.presenter.contract.AppInfoContract;
import com.demo.phonehelper.ui.adapter.AppInfoAdapter;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * app详情，基于appDetailActivity

 * Created by Administrator on 2017/12/18.
 */
public class AppDetailFragment extends ProgressFragment<AppDetailPresenter> implements AppInfoContract.AppDetailView {

    private int mAppId;

    private LayoutInflater mInflater;

    private AppInfoAdapter mAdapter;

    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.tv_name)
    TextView mTextName;


    @BindView(R.id.view_gallery)
    LinearLayout mViewGallery;
    @BindView(R.id.view_introduction)
    ExpandableTextView mViewIntroduction;

    @BindView(R.id.tv_update_time)
    TextView mTxtUpdateTime;
    @BindView(R.id.tv_version)
    TextView mTxtVersion;
    @BindView(R.id.tv_apk_size)
    TextView mTxtApkSize;
    @BindView(R.id.tv_publisher)
    TextView mTxtPublisher;
    @BindView(R.id.recycler_view_same_dev)
    RecyclerView mRecyclerViewSameDev;
    @BindView(R.id.recycler_view_relate)
    RecyclerView mRecyclerViewRelate;


    public AppDetailFragment(int id) {
        this.mAppId = id;

    }

    @Override
    public int setLayout() {
        return R.layout.fragment_app_detail;
    }

    @Override
    public void init() {
        mPresenter.getAppDetail(mAppId);
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerAppDetailComponent.builder().appComponent(appComponent)
                .appDetailModule(new AppDetailModule(this))
                .appModelModule(new AppModelModule())
                .build().inject(this);
    }

    @Override
    public void showAppDetail(AppInfo appInfo) {
        showScreenShot(appInfo.getScreenshot());

        mViewIntroduction.setText(appInfo.getIntroduction());

        mTxtUpdateTime.setText(DateUtil.strToDate(appInfo.getUpdateTime()));
        mTxtApkSize.setText((appInfo.getApkSize() / 1014 / 1024) +"Mb");
        mTxtVersion.setText(appInfo.getVersionName());
        mTxtPublisher.setText(appInfo.getPublisherName());

         mAdapter = AppInfoAdapter.builder().layout(R.layout.template_appinfo2)
                .build();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        mRecyclerViewSameDev.setLayoutManager(layoutManager);

        mAdapter.addData(appInfo.getSameDevAppInfoList());
        mRecyclerViewSameDev.setAdapter(mAdapter);

        //////////////////////////////////////////////////////////////////////

        mAdapter = AppInfoAdapter.builder().layout(R.layout.template_appinfo2)
                .build();
        mRecyclerViewRelate.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        mAdapter.addData(appInfo.getRelateAppInfoList());
        mRecyclerViewRelate.setAdapter(mAdapter);


    }

    /**
     * 获取APP详情效果图
     * @param screenShot String
     */
    private void showScreenShot(String screenShot) {

        //分割
        List<String> urls = Arrays.asList(screenShot.split(","));

        //循环显示获取的图片
        for (String url : urls) {
            ImageView imageView = (ImageView) mInflater.inflate(R.layout.template_imageview, mViewGallery, false);
            ImageLoader.load(Constant.BASE_IMG_URL + url, imageView);
            mViewGallery.addView(imageView);
        }

    }


}
