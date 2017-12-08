package com.demo.phonehelper.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.phonehelper.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 * Created by Administrator on 2017/12/6.
 */

public class GuideFragment extends Fragment {


    public static final String IMG_ID = "IMG_ID";
    public static final String COLOR_ID = "COLOR_ID";
    public static final String TEXT_ID = "TEXT_ID";

    private View view;

    @BindView(R.id.imgView)
    ImageView mImgView;
    @BindView(R.id.rootView)
    LinearLayout mRootView;
    @BindView(R.id.text)
    TextView mText;

    /**静态的方法，保存三个资源Id
     *
     * @param imgResId 图片
     * @param bgColorResId 背景色
     * @param textResId 文字
     * @return fragment
     */
    public static GuideFragment newInstance(int imgResId,int bgColorResId,int textResId){

        GuideFragment fragment = new GuideFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(IMG_ID,imgResId);
        bundle.putInt(COLOR_ID,bgColorResId);
        bundle.putInt(TEXT_ID,textResId);

        fragment.setArguments(bundle);
        return fragment;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_guide,container,false);

        ButterKnife.bind(this,view);

        initData();

        return view;
    }

    private void initData() {
        Bundle arg = getArguments();

        //获取到 传入bundle数据
        int colorId = arg.getInt(COLOR_ID);
        int imgId = arg.getInt(IMG_ID);
        int textId = arg.getInt(TEXT_ID);

        //设置各自的资源

        mRootView.setBackgroundColor(getResources().getColor(colorId));
        mImgView.setImageResource(imgId);
        mText.setText(textId);

    }
}
