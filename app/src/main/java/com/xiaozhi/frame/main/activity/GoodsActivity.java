package com.xiaozhi.frame.main.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.xiaozhi.frame.main.R;
import com.xiaozhi.frame.main.been.goodsbeen.GoodsItemMenuData;
import com.xiaozhi.frame.main.been.goodsbeen.GoodsMainMenuData;
import com.xiaozhi.frame.main.been.goodsbeen.GoodsMenuData;
import com.xiaozhi.frame.main.p.GoodsActivityP;
import com.xiaozhi.frame.main.view.GoodsCatalogView;
import com.xiaozhi.frame.main.view.GoodsLeftItemMenuView;
import com.xiaozhi.frame.main.view.GoodsLeftMainMenuView;
import com.xiaozhi.frame.main.view.GoodsLeftMenuView;
import com.xiaozhi.frame.main.view.GoodsRightView;
import com.xiaozhi.frame.main.view.GoodsSetCatalogView;
import com.xiaozhi.frame.main.view.GoodsSetDiscountView;
import com.xiaozhi.frame.main.view.GoodsSetPracticeView;
import com.xiaozhi.frame.mvp.v.activity.BaseActivity;
import com.xiaozhi.frame.mvp.v.view.BaseView;

import java.util.ArrayList;


/**
 * 商品管理
 * Created by Fynner on 2016/12/30.
 * 开发思路 将左右两个View当成v层，将该activity当成c层，左右两个v对应创建两个p层，p层与对应m层进行数据交流，该页面采用mvvm模式构架
 */
public class GoodsActivity extends BaseActivity {

    private GoodsActivityP goodsActivityP;

    private View view;
    private Context context;

    //菜单数据
    private GoodsMenuData goodsMenuData;

    //左边view
    private LinearLayout goods_left_menu;
    private GoodsLeftMenuView goodsLeftMenuView;
    private BaseView currentLeftView;

    //目录
    private GoodsMainMenuData catalogMainMenu;

    //右边view
    private LinearLayout goods_right_group;
    private GoodsRightView goodsRightView;
    private BaseView currentRightView;


    //右边目录商品显示界面
    private GoodsCatalogView goodsCatalogView;

    @Override
    public View initView() {

        context = this;

        view = View.inflate(context, R.layout.activity_goods, null);

        //左边view
        goods_left_menu = (LinearLayout) view.findViewById(R.id.goods_left_menu);

        //右边view
        goods_right_group = (LinearLayout) view.findViewById(R.id.goods_right_group);

        return view;
    }

    @Override
    public void initData() {
        setTitle("商品管理");
        showSreach();
        addMenuInfo();

        goodsActivityP = new GoodsActivityP(this);

        //设置左侧view数据
        goodsLeftMenuView = new GoodsLeftMenuView(context, goodsMenuData);
        goods_left_menu.addView(goodsLeftMenuView.getRootView());

        //设置右侧view数据
        goodsRightView = new GoodsRightView(context);
        goods_right_group.addView(goodsRightView.getRootView());

        // 默认显示全部
        goodsMenuData.controlLeftViewVisible(catalogMainMenu);
        GoodsItemMenuData catalogItem1 = catalogMainMenu.getItemMenus().get(0);
        goodsRightView.changeRightView(catalogItem1.getRightView());
        currentLeftView = catalogItem1.getLeftView();
        currentRightView = catalogItem1.getRightView();
    }

    private void addMenuInfo() {
        ArrayList<GoodsMainMenuData> menuDatas = new ArrayList<>();

        ArrayList<GoodsItemMenuData> catalogDatas = new ArrayList<>();

        goodsCatalogView = new GoodsCatalogView(context);
        GoodsItemMenuData catalogItem1 = new GoodsItemMenuData("全部", goodsCatalogView, new GoodsLeftItemMenuView(context, "全部", leftItemMenuListenner));
        GoodsItemMenuData catalogItem2 = new GoodsItemMenuData("牛排", goodsCatalogView, new GoodsLeftItemMenuView(context, "牛排", leftItemMenuListenner));
        GoodsItemMenuData catalogItem3 = new GoodsItemMenuData("薄饼", goodsCatalogView, new GoodsLeftItemMenuView(context, "薄饼", leftItemMenuListenner));
        catalogDatas.add(catalogItem1);
        catalogDatas.add(catalogItem2);
        catalogDatas.add(catalogItem3);
        catalogMainMenu = new GoodsMainMenuData("目录", new GoodsLeftMainMenuView(context, "目录", leftMainMenuListenner), catalogDatas);
        menuDatas.add(catalogMainMenu);

        ArrayList<GoodsItemMenuData> setData = new ArrayList<>();
        GoodsItemMenuData catalog = new GoodsItemMenuData("目录", new GoodsSetCatalogView(context), new GoodsLeftItemMenuView(context, "目录", leftItemMenuListenner));
        GoodsItemMenuData practice = new GoodsItemMenuData("做法", new GoodsSetPracticeView(context), new GoodsLeftItemMenuView(context, "做法", leftItemMenuListenner));
        GoodsItemMenuData discount = new GoodsItemMenuData("折扣", new GoodsSetDiscountView(context), new GoodsLeftItemMenuView(context, "折扣", leftItemMenuListenner));
        setData.add(catalog);
        setData.add(practice);
        setData.add(discount);
        GoodsMainMenuData setMenu = new GoodsMainMenuData("设置", new GoodsLeftMainMenuView(context, "设置", leftMainMenuListenner), setData);
        menuDatas.add(setMenu);

        goodsMenuData = new GoodsMenuData(menuDatas);
    }

    @Override
    public void initListenner() {
    }

    /**
     * 跳转商品详情
     */
    public void toGoodsDetailsActivity() {
        Intent intent = new Intent();
        intent.setClass(context, GoodsDetailsActivity.class);
        startActivity(intent);
    }

    /**
     * 主菜单监听
     */
    private GoodsLeftMainMenuView.OnLeftMainMenuListenner leftMainMenuListenner = new GoodsLeftMainMenuView.OnLeftMainMenuListenner() {

        @Override
        public void onMainGroupViewClick(BaseView mainMenuView) {
            for (GoodsMainMenuData mainMenuData : goodsMenuData.getLeftGoodsMainMenuDatas()) {
                if (mainMenuData.getMainView() == mainMenuView) {
                    goodsMenuData.controlLeftViewVisible(mainMenuData);
                }
            }
        }
    };

    /**
     * 子菜单监听
     */
    private GoodsLeftItemMenuView.OnLeftItemMenuListenner leftItemMenuListenner = new GoodsLeftItemMenuView.OnLeftItemMenuListenner() {

        @Override
        public void onItemGroupViewClick(BaseView itemMenuView) {
            if (itemMenuView == currentLeftView) {
                return;
            }

            for (GoodsMainMenuData mainMenuData : goodsMenuData.getLeftGoodsMainMenuDatas()) {
                for (GoodsItemMenuData itemMenuData : mainMenuData.getItemMenus()) {
                    if (itemMenuData.getLeftView() == itemMenuView) {
                        // 当右边视图为目录的情况右侧视图不跳转界面仅改变数据,如果右侧视图不是目录，点击目录的视图的情况下，先跳转再改变数据
                        if (itemMenuData.getRightView() == goodsCatalogView) {
                            if (currentRightView != goodsCatalogView) {
                                goodsRightView.changeRightView(itemMenuData.getRightView());
                            }
                            goodsCatalogView.setCatalogTitle(itemMenuData.getMenuName());
                        } else {
                            goodsRightView.changeRightView(itemMenuData.getRightView());
                        }
                        currentLeftView = itemMenuView;
                        currentRightView = itemMenuData.getRightView();
                    }
                }
            }
        }
    };
}
