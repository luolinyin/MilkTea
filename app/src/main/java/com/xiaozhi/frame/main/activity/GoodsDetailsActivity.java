package com.xiaozhi.frame.main.activity;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.xiaozhi.frame.main.R;
import com.xiaozhi.frame.main.view.GoodsDetailsBodyPhotoView;
import com.xiaozhi.frame.main.view.GoodsDetailsLeftInfoView;
import com.xiaozhi.frame.main.view.GoodsDetailsRightView;
import com.xiaozhi.frame.mvp.v.activity.BaseActivity;
import com.xiaozhi.frame.mvp.v.toast.ToastView;
import com.xiaozhi.frame.tool.listenner.NoDoubleClickListener;

/**
 * 商品详情
 * Created by Fynner on 2016/12/30.
 */
public class GoodsDetailsActivity extends BaseActivity {
    private View view;
    private Context context;

    //商品信息
    private LinearLayout goods_details_left_info_group;
    private GoodsDetailsLeftInfoView goodsDetailsLeftInfoView;

    //商品图片处理
    private LinearLayout goods_details_body_photo_group;
    private GoodsDetailsBodyPhotoView goodsDetailsBodyPhotoView;

    //商品右菜单栏功能
    private LinearLayout goods_details_right_group;
    private GoodsDetailsRightView goodsDetailsRightView;

    @Override
    public View initView() {
        context = this;
        view = View.inflate(context, R.layout.activity_goods_details, null);
        //商品信息
        goods_details_left_info_group= (LinearLayout) view.findViewById(R.id.goods_details_left_info_group);
        goodsDetailsLeftInfoView=new GoodsDetailsLeftInfoView(context);
        goods_details_left_info_group.addView(goodsDetailsLeftInfoView.getRootView());

        //商品图片处理
        goods_details_body_photo_group= (LinearLayout) view.findViewById(R.id.goods_details_body_photo_group);
        goodsDetailsBodyPhotoView=new GoodsDetailsBodyPhotoView(context);
        goods_details_body_photo_group.addView(goodsDetailsBodyPhotoView.getRootView());

        //商品右菜单栏功能
        goods_details_right_group= (LinearLayout) view.findViewById(R.id.goods_details_right_group);
        goodsDetailsRightView=new GoodsDetailsRightView(context);
        goods_details_right_group.addView(goodsDetailsRightView.getRootView());

        return view;
    }
    @Override
    public void initListenner() {
        changeToHomeFuction(R.mipmap.ic_tip_mobile, new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                new ToastView(context, "更改了title事件").show();
            }
        });
    }

    @Override
    public void initData() {
        setTitle("Goods_Details");
    }

}
