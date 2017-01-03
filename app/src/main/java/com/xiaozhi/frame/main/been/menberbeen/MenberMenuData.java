package com.xiaozhi.frame.main.been.menberbeen;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Fynner on 2016/12/31.
 * 每一个菜单都有内部都有一个tag 使用tag 标识某一项目的数据  控制
 */
public class MenberMenuData implements Serializable {
    private ArrayList<MenberMainMenuData> leftMenberMainMenuDatas;

    public MenberMenuData(ArrayList<MenberMainMenuData> leftSetMainMenuDatas) {
        setLeftSetMainMenuDatas(leftSetMainMenuDatas);
    }

    public void setLeftSetMainMenuDatas(ArrayList<MenberMainMenuData> leftSetMainMenuDatas) {

        if (leftSetMainMenuDatas != null) {
            this.leftMenberMainMenuDatas = leftSetMainMenuDatas;
        } else {
            this.leftMenberMainMenuDatas = new ArrayList<MenberMainMenuData>();
        }

        for (int i = 0; i < this.leftMenberMainMenuDatas.size(); i++) {
            this.leftMenberMainMenuDatas.get(i).setTab(i);
        }
    }

    public ArrayList<MenberMainMenuData> getLeftMenberMainMenuDatas() {
        return leftMenberMainMenuDatas;
    }

    /**
     * 控制视图
     */
    public void controlLeftViewVisible(MenberMainMenuData setMainMenuData) {
        for (MenberMainMenuData mainMenuData : leftMenberMainMenuDatas) {
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
