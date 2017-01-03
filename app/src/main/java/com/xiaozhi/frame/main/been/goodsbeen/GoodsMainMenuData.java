package com.xiaozhi.frame.main.been.goodsbeen;

import android.view.View;

import com.xiaozhi.frame.mvp.v.view.BaseView;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Fynner on 2016/12/31.
 */

public class GoodsMainMenuData implements Serializable {
    private ArrayList<GoodsItemMenuData> itemMenus;
    private String mainName;
    private int Tab;

    private BaseView mainView;


    private boolean isItemVisible;

    public GoodsMainMenuData(String name, BaseView mainView, ArrayList<GoodsItemMenuData> itemMenus) {
        this.mainView = mainView;
        setName(name);
        setItemMenus(itemMenus);
        hideAllItemView();
    }

    public void setItemMenus(ArrayList<GoodsItemMenuData> itemMenus) {
        if (itemMenus != null) {
            this.itemMenus = itemMenus;
        } else {
            this.itemMenus = new ArrayList<GoodsItemMenuData>();
        }

        for (int i = 0; i < this.itemMenus.size(); i++) {
            itemMenus.get(i).setTag(i);
        }
    }

    public void setName(String name) {
        this.mainName = name;
    }

    protected void setTab(int tab) {
        Tab = tab;
    }


    public ArrayList<GoodsItemMenuData> getItemMenus() {
        return itemMenus;
    }

    public String getName() {
        return mainName;
    }

    public int getTab() {
        return Tab;
    }

    public BaseView getMainView() {
        return mainView;
    }

    public void setMainView(BaseView mainView) {
        if (mainView != null) {
            this.mainView = mainView;
        }
    }

    /**
     * 隐藏所有item视图
     */
    public void hideAllItemView() {
        isItemVisible = false;
        for (GoodsItemMenuData setItemMenuData : itemMenus) {
            setItemMenuData.getLeftView().getRootView().setVisibility(View.GONE);
        }
    }

    /**
     * 显示所有item视图
     */
    public void visibleAllItemView() {
        isItemVisible = true;
        for (GoodsItemMenuData setItemMenuData : itemMenus) {
            setItemMenuData.getLeftView().getRootView().setVisibility(View.VISIBLE);
        }
    }

    public void setItemVisible(boolean b) {
        isItemVisible = b;
    }

    public boolean isItemVisible() {

        return isItemVisible;
    }

}
