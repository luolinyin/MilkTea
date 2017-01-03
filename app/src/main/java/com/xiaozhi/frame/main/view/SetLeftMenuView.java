package com.xiaozhi.frame.main.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xiaozhi.frame.main.R;
import com.xiaozhi.frame.main.been.setbeen.SetItemMenuData;
import com.xiaozhi.frame.main.been.setbeen.SetMainMenuData;
import com.xiaozhi.frame.main.been.setbeen.SetMenuData;
import com.xiaozhi.frame.mvp.v.view.BaseView;

/**
 * Created by Fynner on 2016/12/30.
 */
public class SetLeftMenuView extends BaseView {
    private Context context;
    private View view;

    //菜单数据加载
    private LinearLayout left_set_menu_group;
    private SetMenuData setMenuData;

    public SetLeftMenuView(Context context, SetMenuData setMenuData) {
        super(context);
        this.context = context;
        this.setMenuData = setMenuData;
    }

    @Override
    public View initGroupView() {
        view = View.inflate(context, R.layout.view_set_left_menu, null);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }

    @Override
    public void initView() {
        left_set_menu_group = (LinearLayout) view.findViewById(R.id.left_set_menu_group);
    }

    @Override
    public void initData() {
        addMenuView();

    }

    //添加菜单视图
    private void addMenuView() {
        if (setMenuData == null) {
            return;
        }

        left_set_menu_group.removeAllViews();
        for (SetMainMenuData setMainMenuData : setMenuData.getLeftSetMainMenuDatas()) {
            left_set_menu_group.addView(setMainMenuData.getMainView().getRootView());
            for (SetItemMenuData setItemMenuData : setMainMenuData.getItemMenus()) {
                left_set_menu_group.addView(setItemMenuData.getLeftView().getRootView());
            }
        }
    }

    @Override
    public void initListenner() {


    }



}
