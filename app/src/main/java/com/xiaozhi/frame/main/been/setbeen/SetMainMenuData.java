package com.xiaozhi.frame.main.been.setbeen;

import android.view.View;

import com.xiaozhi.frame.mvp.v.view.BaseView;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Fynner on 2016/12/31.
 */

public class SetMainMenuData implements Serializable {
    private ArrayList<SetItemMenuData> itemMenus;
    private String mainName;
    private int Tab;

    private BaseView mainView;


    private boolean isItemVisible;

    public SetMainMenuData(String name, BaseView mainView, ArrayList<SetItemMenuData> itemMenus) {
        this.mainView = mainView;
        setName(name);
        setItemMenus(itemMenus);
        hideAllItemView();
    }

    public void setItemMenus(ArrayList<SetItemMenuData> itemMenus) {
        if (itemMenus != null) {
            this.itemMenus = itemMenus;
        } else {
            this.itemMenus = new ArrayList<SetItemMenuData>();
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


    public ArrayList<SetItemMenuData> getItemMenus() {
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
        for (SetItemMenuData setItemMenuData : itemMenus) {
            setItemMenuData.getLeftView().getRootView().setVisibility(View.GONE);
        }
    }

    /**
     * 显示所有item视图
     */
    public void visibleAllItemView() {
        isItemVisible = true;
        for (SetItemMenuData setItemMenuData : itemMenus) {
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
