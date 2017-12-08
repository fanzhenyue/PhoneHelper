package com.demo.phonehelper.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.demo.phonehelper.R;

import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 首页（推荐）最外层的adapter,多布局的
 * 三种类型的布局：banner icon apps games
 * Created by Administrator on 2017/12/8.
 */

public class IndexMutilAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    public static final int TYPE_BANNER = 1;
    public static final int TYPE_ICON = 2;
    public static final int TYPE_APPS = 3;
    public static final int TYPE_GAMES = 4;

    public LayoutInflater mLayoutInflater;
    public Context mContext;

    public IndexMutilAdapter(Context context) {
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {

        if (position==0){
            return TYPE_BANNER;
        }else if (position==1){
            return TYPE_ICON;
        }else if (position==2){
            return TYPE_APPS;
        }else if (position==3){
            return TYPE_GAMES;
        }
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==TYPE_BANNER){
            return new BannerViewHolder(mLayoutInflater.inflate(R.layout.template_banner,parent,false));
        }else if (viewType == TYPE_ICON) {

            return new NavIconViewHolder(mLayoutInflater.inflate(R.layout.template_nav_icon, parent, false));

        }
        else if(viewType==TYPE_APPS){

//            return  new AppViewHolder(mLayoutInflater.inflate(R.layout.template_recyleview_with_title, null, false),TYPE_APPS);
        }
        else if(viewType==TYPE_GAMES){

//            return  new AppViewHolder(mLayoutInflater.inflate(R.layout.template_recyleview_with_title, null, false),TYPE_GAMES);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class BannerViewHolder extends RecyclerView.ViewHolder{

        public BannerViewHolder(View itemView) {
            super(itemView);
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

    /* class AppViewHolder extends RecyclerView.ViewHolder {
        public AppViewHolder(View inflate, int typeApps) {
            super();
        }
    }*/
}
