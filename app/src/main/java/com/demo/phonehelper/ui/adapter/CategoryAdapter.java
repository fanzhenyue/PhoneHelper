package com.demo.phonehelper.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.demo.phonehelper.R;
import com.demo.phonehelper.bean.Category;
import com.demo.phonehelper.common.Constant;
import com.demo.phonehelper.common.imageloader.ImageLoader;

/**
 *
 * Created by Administrator on 2017/12/14.
 */

public class CategoryAdapter  extends BaseQuickAdapter<Category ,BaseViewHolder> {

    public CategoryAdapter() {
        super(R.layout.template_category);
    }


    @Override
    protected void convert(BaseViewHolder helper, Category item) {
        helper.setText(R.id.text_name,item.getName());

        ImageLoader.load(Constant.BASE_IMG_URL+item.getIcon(), (ImageView) helper.getView(R.id.img_icon));
    }
}
