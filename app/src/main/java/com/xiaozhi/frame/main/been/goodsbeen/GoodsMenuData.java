package com.xiaozhi.frame.main.been.goodsbeen;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Fynner on 2016/12/31.
 * 每一个菜单都有内部都有一个tag 使用tag 标识某一项目的数据  控制
 */
public class GoodsMenuData implements Serializable {
    private ArrayList<GoodsMainMenuData> leftGoodsMainMenuDatas;

    public GoodsMenuData(ArrayList<GoodsMainMenuData> leftSetMainMenuDatas) {
        setLeftSetMainMenuDatas(leftSetMainMenuDatas);
    }

    public void setLeftSetMainMenuDatas(ArrayList<GoodsMainMenuData> leftSetMainMenuDatas) {

        if (leftSetMainMenuDatas != null) {
            this.leftGoodsMainMenuDatas = leftSetMainMenuDatas;
        } else {
            this.leftGoodsMainMenuDatas = new ArrayList<GoodsMainMenuData>();
        }

        for (int i = 0; i < this.leftGoodsMainMenuDatas.size(); i++) {
            this.leftGoodsMainMenuDatas.get(i).setTab(i);
        }
    }

    public ArrayList<GoodsMainMenuData> getLeftGoodsMainMenuDatas() {
        return leftGoodsMainMenuDatas;
    }

    /**
     * 控制视图
     */
    public void controlLeftViewVisible(GoodsMainMenuData setMainMenuData) {
        for (GoodsMainMenuData mainMenuData : leftGoodsMainMenuDatas) {
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
