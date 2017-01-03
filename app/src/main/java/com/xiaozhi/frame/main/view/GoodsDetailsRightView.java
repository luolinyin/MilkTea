package com.xiaozhi.frame.main.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.xiaozhi.frame.main.R;
import com.xiaozhi.frame.mvp.v.view.BaseView;

/**
 * Created by Fynner on 2016/12/30.
 */

public class GoodsDetailsRightView extends BaseView {
    private Context context;
    private View view;

    public GoodsDetailsRightView(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public View initGroupView() {
        view = View.inflate(context, R.layout.view_goods_details_right, null);
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
