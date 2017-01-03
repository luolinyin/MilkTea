package com.xiaozhi.frame.main.activity;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.xiaozhi.frame.main.R;
import com.xiaozhi.frame.main.view.CashierLeftView;
import com.xiaozhi.frame.main.view.ReturnGoodsLeftView;
import com.xiaozhi.frame.main.view.ReturnGoodsRightView;
import com.xiaozhi.frame.mvp.v.activity.BaseActivity;

/**
 * Created by Fynner on 2016/12/31.
 */

public class ReturnGoodsActivity extends BaseActivity {
    private Context context;
    private View view;

    //左侧
    private LinearLayout returngoods_left_group;
    private ReturnGoodsLeftView returnGoodsLeftView;

    //右侧
    private LinearLayout returngoods_right_group;
    private ReturnGoodsRightView returnGoodsRightView;

    @Override
    public View initView() {
        context = this;
        view = View.inflate(context, R.layout.activity_return_goods, null);
        //左侧
        returngoods_left_group = (LinearLayout) view.findViewById(R.id.returngoods_left_group);
        returnGoodsLeftView=new ReturnGoodsLeftView(context);
        returngoods_left_group.addView(returnGoodsLeftView.getRootView());

        //右侧
        returngoods_right_group = (LinearLayout) view.findViewById(R.id.returngoods_right_group);
        returnGoodsRightView=new ReturnGoodsRightView(context);
        returngoods_right_group.addView(returnGoodsRightView.getRootView());

        return view;
    }

    @Override
    public void initData() {
        setTitle("退货");
        showSreach();
    }

    @Override
    public void initListenner() {

    }


}
