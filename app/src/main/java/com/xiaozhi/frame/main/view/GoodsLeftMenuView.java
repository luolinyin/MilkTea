package com.xiaozhi.frame.main.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xiaozhi.frame.main.R;
import com.xiaozhi.frame.main.been.goodsbeen.GoodsItemMenuData;
import com.xiaozhi.frame.main.been.goodsbeen.GoodsMainMenuData;
import com.xiaozhi.frame.main.been.goodsbeen.GoodsMenuData;
import com.xiaozhi.frame.main.been.setbeen.SetItemMenuData;
import com.xiaozhi.frame.main.been.setbeen.SetMainMenuData;
import com.xiaozhi.frame.mvp.v.view.BaseView;

/**
 * Created by Fynner on 2016/12/30.
 */

public class GoodsLeftMenuView extends BaseView {
    private Context context;
    private View view;

    //左侧视图数据
    private GoodsMenuData goodsMenuData;
    private LinearLayout goods_left_menu_view_group;

    public GoodsLeftMenuView(Context context, GoodsMenuData goodsMenuData) {
        super(context);
        this.context = context;
        this.goodsMenuData = goodsMenuData;
    }

    @Override
    public View initGroupView() {
        view = View.inflate(context, R.layout.view_goods_left_menu, null);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }

    @Override
    public void initView() {
        goods_left_menu_view_group = (LinearLayout) view.findViewById(R.id.goods_left_menu_view_group);
    }

    @Override
    public void initData() {

        addMenuView();

    }

    //添加菜单视图
    private void addMenuView() {
        if (goodsMenuData == null) {
            return;
        }

        goods_left_menu_view_group.removeAllViews();
        for (GoodsMainMenuData goodsMainMenuData : goodsMenuData.getLeftGoodsMainMenuDatas()) {
            goods_left_menu_view_group.addView(goodsMainMenuData.getMainView().getRootView());
            for (GoodsItemMenuData goodsItemMenuData : goodsMainMenuData.getItemMenus()) {
                goods_left_menu_view_group.addView(goodsItemMenuData.getLeftView().getRootView());
            }
        }
    }

    @Override
    public void initListenner() {

    }
}
