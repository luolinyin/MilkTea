package com.xiaozhi.frame.main.activity;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.xiaozhi.frame.main.R;
import com.xiaozhi.frame.main.view.CashierLeftView;
import com.xiaozhi.frame.main.view.CashierRightView;
import com.xiaozhi.frame.mvp.v.activity.BaseActivity;

/**
 * Created by Fynner on 2016/12/30.
 */

public class CashierActivity extends BaseActivity {
    private View view;
    private Context context;

    //左边收银
    private LinearLayout cashier_left_group;
    private CashierLeftView cashierLeftView;

    //右边暂时内容
    private LinearLayout cashier_right_group;
    private CashierRightView cashierRightView;

    @Override
    public View initView() {
        context = this;
        view = View.inflate(context, R.layout.activity_cashier, null);
        //左边收银
        cashier_left_group = (LinearLayout) view.findViewById(R.id.cashier_left_group);
        cashierLeftView = new CashierLeftView(context);
        cashier_left_group.addView(cashierLeftView.getRootView());

        //右边暂时内容
        cashier_right_group = (LinearLayout) view.findViewById(R.id.cashier_right_group);
        cashierRightView = new CashierRightView(context);
        cashier_right_group.addView(cashierRightView.getRootView());

        return view;
    }

    @Override
    public void initData() {
        hideTopGroupView();
    }
    @Override
    public void initListenner() {

    }




}
