package com.xiaozhi.frame.main.activity;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.xiaozhi.frame.main.R;
import com.xiaozhi.frame.main.been.goodsbeen.GoodsItemMenuData;
import com.xiaozhi.frame.main.been.goodsbeen.GoodsMainMenuData;
import com.xiaozhi.frame.main.been.goodsbeen.GoodsMenuData;
import com.xiaozhi.frame.main.been.menberbeen.MenberItemMenuData;
import com.xiaozhi.frame.main.been.menberbeen.MenberMainMenuData;
import com.xiaozhi.frame.main.been.menberbeen.MenberMenuData;
import com.xiaozhi.frame.main.view.GoodsCatalogView;
import com.xiaozhi.frame.main.view.GoodsLeftItemMenuView;
import com.xiaozhi.frame.main.view.GoodsLeftMainMenuView;
import com.xiaozhi.frame.main.view.GoodsSetDiscountView;
import com.xiaozhi.frame.main.view.GoodsSetPracticeView;
import com.xiaozhi.frame.main.view.MenberLeftItemMenuView;
import com.xiaozhi.frame.main.view.MenberLeftMainMenuView;
import com.xiaozhi.frame.main.view.MenberLeftMenuView;
import com.xiaozhi.frame.main.view.MenberLevelSetView;
import com.xiaozhi.frame.main.view.MenberLevelView;
import com.xiaozhi.frame.main.view.MenberRightView;
import com.xiaozhi.frame.mvp.v.activity.BaseActivity;
import com.xiaozhi.frame.mvp.v.view.BaseView;

import java.util.ArrayList;

/**
 * Created by Fynner on 2016/12/31.
 */

public class MenberActivity extends BaseActivity {
    private View view;
    private Context context;
    //菜单数据
    private MenberMenuData menberMenuData;

    //左侧菜单
    private LinearLayout menber_left_menu;
    private MenberLeftMenuView menberLeftMenuView;
    private BaseView currentLeftView;

    //右侧内容
    private LinearLayout menber_right_group;
    private MenberRightView menberRightView;
    private BaseView currentRightView;

    //级别视图
    private MenberLevelView menberLevelView;

    @Override
    public View initView() {
        context = this;
        view = View.inflate(context, R.layout.activity_menber, null);

        //左侧菜单
        menber_left_menu = (LinearLayout) view.findViewById(R.id.menber_left_menu);


        //右侧内容
        menber_right_group = (LinearLayout) view.findViewById(R.id.menber_right_group);

        return view;
    }

    @Override
    public void initData() {
        setTitle("Menber");
        showSreach();

        addMenuInfo();

        //左侧菜单数据
        menberLeftMenuView = new MenberLeftMenuView(context,menberMenuData);
        menber_left_menu.addView(menberLeftMenuView.getRootView());

        //右侧菜单数据
        menberRightView = new MenberRightView(context);
        menber_right_group.addView(menberRightView.getRootView());
    }

    @Override
    public void initListenner() {

    }

    private void addMenuInfo() {
        ArrayList<MenberMainMenuData> menuDatas = new ArrayList<MenberMainMenuData>();

        ArrayList<MenberItemMenuData> levelDatas = new ArrayList<MenberItemMenuData>();

        menberLevelView = new MenberLevelView(context);
        MenberItemMenuData levelItem1 = new MenberItemMenuData("全部", menberLevelView, new MenberLeftItemMenuView(context, "全部", leftItemMenuListenner));
        MenberItemMenuData levelItem2 = new MenberItemMenuData("VIP", menberLevelView, new MenberLeftItemMenuView(context, "VIP", leftItemMenuListenner));
        MenberItemMenuData levelItem3 = new MenberItemMenuData("白金", menberLevelView, new MenberLeftItemMenuView(context, "白金", leftItemMenuListenner));
        MenberItemMenuData levelItem4 = new MenberItemMenuData("钻石", menberLevelView, new MenberLeftItemMenuView(context, "钻石", leftItemMenuListenner));
        levelDatas.add(levelItem1);
        levelDatas.add(levelItem2);
        levelDatas.add(levelItem3);
        levelDatas.add(levelItem4);
        MenberMainMenuData levelMainMenu = new MenberMainMenuData("级别", new MenberLeftMainMenuView(context, "级别", leftMainMenuListenner), levelDatas);
        menuDatas.add(levelMainMenu);

        ArrayList<MenberItemMenuData> setData = new ArrayList<MenberItemMenuData>();
        MenberItemMenuData levelSet = new MenberItemMenuData("会员设置", new MenberLevelSetView(context), new MenberLeftItemMenuView(context, "会员设置", leftItemMenuListenner));
        setData.add(levelSet);

        MenberMainMenuData setMenu = new MenberMainMenuData("设置", new MenberLeftMainMenuView(context, "设置", leftMainMenuListenner), setData);
        menuDatas.add(setMenu);

        menberMenuData = new MenberMenuData(menuDatas);
    }

    /**
     * 主菜单监听
     */
    private MenberLeftMainMenuView.OnLeftMainMenuListenner leftMainMenuListenner = new MenberLeftMainMenuView.OnLeftMainMenuListenner() {
        @Override
        public void onMainGroupViewClick(BaseView mainMenuView) {

            for (MenberMainMenuData mainMenuData : menberMenuData.getLeftMenberMainMenuDatas()) {

                if (mainMenuData.getMainView() == mainMenuView) {
                    menberMenuData.controlLeftViewVisible(mainMenuData);
                }
            }
        }
    };

    /**
     * 子菜单监听
     */
    private MenberLeftItemMenuView.OnLeftItemMenuListenner leftItemMenuListenner = new MenberLeftItemMenuView.OnLeftItemMenuListenner() {
        @Override
        public void onItemGroupViewClick(BaseView itemMenuView) {
            if (itemMenuView == currentLeftView) {
                return;
            }

            for (MenberMainMenuData mainMenuData : menberMenuData.getLeftMenberMainMenuDatas()) {
                for (MenberItemMenuData itemMenuData : mainMenuData.getItemMenus()) {
                    if (itemMenuData.getLeftView() == itemMenuView) {
                        //当右边视图为目录的情况右侧视图不跳转界面仅改变数据,如果右侧视图不是目录，点击目录的视图的情况下，先跳转再改变数据
                        if (itemMenuData.getRightView() == menberLevelView) {
                            if (currentRightView != menberLevelView) {
                                menberRightView.changeRightView(itemMenuData.getRightView());
                            }
                            menberLevelView.setCatalogTitle(itemMenuData.getMenuName());
                        } else {
                            menberRightView.changeRightView(itemMenuData.getRightView());
                        }
                        currentLeftView = itemMenuView;
                        currentRightView = itemMenuData.getRightView();

                    }
                }
            }

        }
    };
}
