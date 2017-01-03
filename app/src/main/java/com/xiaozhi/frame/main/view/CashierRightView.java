package com.xiaozhi.frame.main.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaozhi.frame.main.R;
import com.xiaozhi.frame.main.activity.CashierActivity;
import com.xiaozhi.frame.main.activity.GoodsActivity;
import com.xiaozhi.frame.main.activity.MainActivity;
import com.xiaozhi.frame.mvp.v.view.BaseView;
import com.xiaozhi.frame.tool.listenner.NoDoubleClickListener;

/**
 * Created by Fynner on 2016/12/30.
 */

public class CashierRightView extends BaseView {
    private View view;
    private Context context;
    private CashierActivity cashierActivity;

    //商品详情入口
    private TextView goods_right_to_details;

    public CashierRightView(Context context) {
        super(context);
        this.context = context;
        if (context instanceof CashierActivity) {
            cashierActivity = (CashierActivity) context;
        }

    }

    @Override
    public View initGroupView() {
        view = View.inflate(context, R.layout.view_cashier_right, null);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        return view;
    }

    @Override
    public void initView() {

    }


    @Override
    public void initData() {

    }

    @Override
    public void initListenner() {

    }
}
