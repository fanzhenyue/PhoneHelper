package com.demo.phonehelper.ui.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.demo.phonehelper.R;
import com.demo.phonehelper.bean.Banner;
import com.demo.phonehelper.bean.IndexBean;

import com.demo.phonehelper.common.imageloader.ImageLoader;
import com.demo.phonehelper.ui.decoration.DividerItemDecoration;
import com.demo.phonehelper.ui.widget.BannerLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 首页（推荐）最外层的adapter,多布局的
 * 三种类型的布局：banner icon apps games
 * Created by Administrator on 2017/12/8.
 */

public class IndexMultiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {


    public static final int TYPE_BANNER = 1;
    public static final int TYPE_ICON = 2;
    public static final int TYPE_APPS = 3;
    public static final int TYPE_GAMES = 4;

    public LayoutInflater mLayoutInflater;
    public Context mContext;
    private IndexBean mIndexBean;

    public IndexMultiAdapter(Context context) {
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setData(IndexBean indexBean) {
        mIndexBean = indexBean;
    }


    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return TYPE_BANNER;
        } else if (position == 1) {
            return TYPE_ICON;
        } else if (position == 2) {
            return TYPE_APPS;
        } else if (position == 3) {
            return TYPE_GAMES;
        }
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_BANNER) {
            return new BannerViewHolder(mLayoutInflater.inflate(R.layout.template_banner, parent, false));
        } else if (viewType == TYPE_ICON) {

            return new NavIconViewHolder(mLayoutInflater.inflate(R.layout.template_nav_icon, parent, false));

        } else if (viewType == TYPE_APPS) {

            return new AppViewHolder(mLayoutInflater.inflate(R.layout.template_recyleview_with_title, null, false), TYPE_APPS);
        } else if (viewType == TYPE_GAMES) {

            return  new AppViewHolder(mLayoutInflater.inflate(R.layout.template_recyleview_with_title, null, false),TYPE_GAMES);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (position == 0) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            List<Banner> banners = mIndexBean.getBanners();
            List<String> urls = new ArrayList<>();
            for (Banner banner : banners) {
                urls.add(banner.getThumbnail());
            }
            bannerViewHolder.mBanner.setViewUrls(urls);

            //banner的点击事件
            bannerViewHolder.mBanner.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
                @Override
                public void onItemClick(int position) {

                }
            });
        } else if (position == 1) {
            NavIconViewHolder navIconViewHolder = (NavIconViewHolder) holder;

            navIconViewHolder.mLayoutHotApp.setOnClickListener(this);
            navIconViewHolder.mLayoutHotGame.setOnClickListener(this);
            navIconViewHolder.mLayoutHotSubject.setOnClickListener(this);
        }
        else {
            AppViewHolder viewHolder = (AppViewHolder) holder;

            AppInfoAdapter appInfoAdapter = AppInfoAdapter.builder().showPosition(false).showCategory(false).showBrief(true).build();
            if (viewHolder.type==TYPE_APPS){
                viewHolder.mText.setText(R.string.hot_app);
                appInfoAdapter.addData(mIndexBean.getRecommendApps());
            }
            else {
                viewHolder.mText.setText(R.string.hot_game);
                appInfoAdapter.addData(mIndexBean.getRecommendGames());
            }
            viewHolder.mRecyclerView.setAdapter(appInfoAdapter);

            // TODO recycler点击事件
            viewHolder.mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
                @Override
                public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                }
            });


        }

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public void onClick(View v) {

    }

    class BannerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.banner)
        BannerLayout mBanner;

        public BannerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mBanner.setImageLoader(new ImageLoader());
        }
    }

    /**
     * imageloader类，通过glide封装实现图片加载
     */
    class ImageLoader implements BannerLayout.ImageLoader {

        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            com.demo.phonehelper.common.imageloader.ImageLoader.load(path, imageView);
        }
    }


    class NavIconViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.layout_hot_app)
        LinearLayout mLayoutHotApp;
        @BindView(R.id.layout_hot_game)
        LinearLayout mLayoutHotGame;
        @BindView(R.id.layout_hot_subject)
        LinearLayout mLayoutHotSubject;

        NavIconViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    class AppViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text)
        TextView mText;
        @BindView(R.id.recycler_view)
        RecyclerView mRecyclerView;
        int type;

        public AppViewHolder(View itemView, int type) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.type = type;

            initRecyclerView();
        }
        private void initRecyclerView() {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext){
                @Override
                public boolean canScrollVertically() {
                    return false;//垂直方向不允许滚动
                }
            });
            DividerItemDecoration itemDecoration = new DividerItemDecoration(mContext, android.support.v7.widget.DividerItemDecoration.VERTICAL);
            mRecyclerView.addItemDecoration(itemDecoration);
        }

    }




}
