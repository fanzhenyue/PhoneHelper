package com.demo.phonehelper.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.demo.phonehelper.R;
import com.demo.phonehelper.bean.AppInfo;
import com.demo.phonehelper.common.imageloader.ImageLoader;

import java.util.List;

/**
 *
 * Created by Administrator on 2017/12/11.
 */

public class AppInfoAdapter extends BaseQuickAdapter<AppInfo,BaseViewHolder> {


    private Builder mBuilder;

    public AppInfoAdapter(Builder builder) {
        super(builder.layoutId);
        this.mBuilder = builder;

        openLoadAnimation();//开启加载动画
    }
    //建造者模式
    public static Builder builder(){
        return new Builder();
    }

    @Override
    protected void convert(BaseViewHolder helper, AppInfo item) {
        String baseImgUrl ="http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";
        ImageLoader.load(baseImgUrl+item.getIcon(), (ImageView) helper.getView(R.id.img_app_icon));

        helper.setText(R.id.txt_app_name,item.getDisplayName())
                .setText(R.id.txt_brief,item.getBriefShow());

        TextView textViewPosition = helper.getView(R.id.txt_position);
        textViewPosition.setVisibility(mBuilder.isShowPosition ? View.VISIBLE : View.GONE);
        textViewPosition.setText(item.getPosition()+1+".");

         TextView textViewCategory = helper.getView(R.id.txt_category);
        textViewCategory.setVisibility(mBuilder.isShowCategory ? View.VISIBLE : View.GONE);
        textViewCategory.setText(item.getLevel1CategoryName());

         TextView textViewBrief = helper.getView(R.id.txt_brief);
        textViewBrief.setVisibility(mBuilder.isShowBrief ? View.VISIBLE : View.GONE);
        textViewBrief.setText(item.getBriefShow());




    }


    public static class Builder{
        //位置
        private boolean isShowPosition;
        //分类
        private boolean isShowCategory;
        //简介
        private boolean isShowBrief;
        //默认传入AppInfo的布局id
        private int layoutId = R.layout.template_appinfo;

        public Builder showPosition(boolean b){
            this.isShowPosition = b;
            return this;
        }
        public Builder showCategory(boolean b){
            this.isShowCategory = b;
            return this;
        }
        public Builder showBrief(boolean b){
            this.isShowBrief = b;
            return this;
        }

        public AppInfoAdapter build(){
            return  new AppInfoAdapter(this);
        }

        public Builder layout(int resId){
            this.layoutId = resId;
            return this;
        }

    }
}
