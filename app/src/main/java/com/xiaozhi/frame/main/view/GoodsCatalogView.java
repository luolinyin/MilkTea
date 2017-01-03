package com.xiaozhi.frame.main.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaozhi.frame.main.R;
import com.xiaozhi.frame.mvp.v.view.BaseView;

/**
 * Created by Fynner on 2017/1/1.
 * 账户信息视图
 */

public class GoodsCatalogView extends BaseView {
    private Context context;
    private View view;

    private TextView goods_right_catalog_name;

    public GoodsCatalogView(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public View initGroupView() {
        view = View.inflate(context, R.layout.view_goods_catalog, null);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }

    @Override
    public void initView() {
        goods_right_catalog_name = (TextView) view.findViewById(R.id.goods_right_catalog_name);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListenner() {

    }

    public void setCatalogTitle(String menuName) {
        if (menuName == null) {
            menuName = "";
        }
        goods_right_catalog_name.setText(menuName);
    }
}
