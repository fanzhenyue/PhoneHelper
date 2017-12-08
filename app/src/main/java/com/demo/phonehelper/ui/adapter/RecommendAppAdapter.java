package com.demo.phonehelper.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.phonehelper.R;
import com.demo.phonehelper.bean.AppInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/12/4.
 *
 */

public class RecommendAppAdapter extends RecyclerView.Adapter<RecommendAppAdapter.ViewHolder>{

    private List<AppInfo> mDatas;

    private LayoutInflater inflater;

    private Context mContext;

    public RecommendAppAdapter(Context context,List<AppInfo> datas) {
        this.mDatas = datas;
        this.mContext = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.template_recomend_app,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        AppInfo appInfo = mDatas.get(position);

        //AppStore/06e44580748edef88470f224846d5f63319431b2a 小米服务器地址
        String baseImgUrl ="http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";
        Picasso.with(mContext).load(baseImgUrl+appInfo.getIcon()).into(holder.mIngIcon);

        holder.mTextTittle.setText(appInfo.getDisplayName());
        holder.mTextSize.setText(appInfo.getApkSize()/1024/1024+" MB");

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_icon)
        ImageView mIngIcon;
        @BindView(R.id.text_title)
        TextView mTextTittle;
        @BindView(R.id.text_size)
        TextView mTextSize;
        @BindView(R.id.btn_dl)
        Button mBtnDl;

        public ViewHolder(View itemView) {
            super(itemView);
            //绑定数据
            ButterKnife.bind(this,itemView);
        }


    }
}
