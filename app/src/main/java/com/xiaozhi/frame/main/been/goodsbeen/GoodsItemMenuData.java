package com.xiaozhi.frame.main.been.goodsbeen;

import com.xiaozhi.frame.mvp.v.view.BaseView;

import java.io.Serializable;

/**
 *
 * Created by Fynner on 2016/12/31.
 */
public class GoodsItemMenuData implements Serializable {
    private String menuName;
    private int Tag;

    private BaseView rightView;
    private BaseView leftView;

    public GoodsItemMenuData(String menuName, BaseView rightView, BaseView leftView) {
        this.menuName = menuName;
        this.rightView = rightView;
        this.leftView = leftView;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    protected void setTag(int tag) {
        Tag = tag;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getTag() {
        return Tag;
    }

    public BaseView getRightView() {
        return rightView;
    }

    public void setRightView(BaseView view) {
        if (view != null) {
            this.rightView = view;
        }
    }

    public void setLeftView(BaseView leftView) {
        if (leftView != null) {
            this.leftView = leftView;
        }

    }

    public BaseView getLeftView() {
        return leftView;
    }
}
