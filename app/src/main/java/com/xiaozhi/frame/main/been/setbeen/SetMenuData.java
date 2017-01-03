package com.xiaozhi.frame.main.been.setbeen;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Fynner on 2016/12/31.
 * 每一个菜单都有内部都有一个tag 使用tag 标识某一项目的数据  控制
 */
public class SetMenuData implements Serializable {
    private ArrayList<SetMainMenuData> leftSetMainMenuDatas;

    public SetMenuData(ArrayList<SetMainMenuData> leftSetMainMenuDatas) {
        setLeftSetMainMenuDatas(leftSetMainMenuDatas);
    }

    public void setLeftSetMainMenuDatas(ArrayList<SetMainMenuData> leftSetMainMenuDatas) {

        if (leftSetMainMenuDatas != null) {
            this.leftSetMainMenuDatas = leftSetMainMenuDatas;
        } else {
            this.leftSetMainMenuDatas = new ArrayList<SetMainMenuData>();
        }

        for (int i = 0; i < this.leftSetMainMenuDatas.size(); i++) {
            this.leftSetMainMenuDatas.get(i).setTab(i);
        }
    }

    public ArrayList<SetMainMenuData> getLeftSetMainMenuDatas() {
        return leftSetMainMenuDatas;
    }

    /**
     * 控制视图
     */
    public void controlLeftViewVisible(SetMainMenuData setMainMenuData) {
        for (SetMainMenuData mainMenuData : leftSetMainMenuDatas) {
            if (setMainMenuData == mainMenuData) {
                if (setMainMenuData.isItemVisible()) {
                    mainMenuData.hideAllItemView();
                } else {
                    mainMenuData.visibleAllItemView();
                }
            } else {
                mainMenuData.hideAllItemView();
            }
        }
    }


}
