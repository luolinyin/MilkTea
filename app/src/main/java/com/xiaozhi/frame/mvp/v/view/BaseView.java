package com.xiaozhi.frame.mvp.v.view;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
/*
View基类 抽象类
		 创建多个View时使用View基类可以进行简单相同的控件的初始化
		 可以清晰写出数据与UI的代码。
		 
实现	initView填充视图  initData加载数据  抽象方法
		 
*/

public abstract class BaseView {

    public Context context;
    private View mRootView;

    /**
     * 注意继承后 先走了初始化控件  data
     *
     * @param context
     */
    public BaseView(Context context) {
        this.context = context;


    }

    private void init() {
        mRootView = initGroupView();

        setBaseUi();
        initView();
        initData();
        initListenner();
    }



    // 获得View。
    public View getRootView() {
        if (mRootView == null) {

            init();
        }
        return mRootView;
    }

    // 实例化布局
    public abstract View initGroupView();

    // 实现接口加载数据
    public abstract void initView();


    // 实现接口加载数据
    public abstract void initData();

    // 实现接口添加监听
    public abstract void initListenner();

    // 基类ui初始化
    public void setBaseUi() {

    }


}
