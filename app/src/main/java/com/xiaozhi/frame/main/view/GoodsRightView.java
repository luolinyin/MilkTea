package com.xiaozhi.frame.main.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaozhi.frame.main.R;
import com.xiaozhi.frame.main.activity.GoodsActivity;
import com.xiaozhi.frame.mvp.v.view.BaseView;
import com.xiaozhi.frame.tool.listenner.NoDoubleClickListener;

/**
 * Created by Fynner on 2016/12/30.
 */

public class GoodsRightView extends BaseView {
    private View view;
    private Context context;
    private GoodsActivity goodsActivity;

    //商品详情入口
    private TextView goods_right_to_details;

    //视图容器
    private LinearLayout goods_right_view_group;

    public GoodsRightView(Context context) {
        super(context);
        this.context = context;
        if (context instanceof GoodsActivity) {
            goodsActivity = (GoodsActivity) context;
        }

    }

    @Override
    public View initGroupView() {
        view = View.inflate(context, R.layout.view_goods_right, null);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        return view;
    }

    @Override
    public void initView() {
        goods_right_to_details = (TextView) view.findViewById(R.id.goods_right_to_details);
        goods_right_view_group = (LinearLayout) view.findViewById(R.id.goods_right_view_group);
    }


    @Override
    public void initData() {

    }

    @Override
    public void initListenner() {
        goods_right_to_details.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                goodsActivity.toGoodsDetailsActivity();
            }
        });
    }

    /**
     * 切换视图
     */
    public void changeRightView(BaseView rightView) {
        goods_right_view_group.removeAllViews();
        goods_right_view_group.addView(rightView.getRootView());
    }
}
