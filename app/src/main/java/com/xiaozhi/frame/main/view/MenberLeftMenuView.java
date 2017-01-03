package com.xiaozhi.frame.main.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xiaozhi.frame.main.R;
import com.xiaozhi.frame.main.been.goodsbeen.GoodsItemMenuData;
import com.xiaozhi.frame.main.been.goodsbeen.GoodsMainMenuData;
import com.xiaozhi.frame.main.been.menberbeen.MenberItemMenuData;
import com.xiaozhi.frame.main.been.menberbeen.MenberMainMenuData;
import com.xiaozhi.frame.main.been.menberbeen.MenberMenuData;
import com.xiaozhi.frame.mvp.v.view.BaseView;

import static com.xiaozhi.frame.main.R.id.goods_left_menu_view_group;

/**
 * Created by Fynner on 2016/12/30.
 */

public class MenberLeftMenuView extends BaseView {
    private Context context;
    private View view;

    //菜单数据
    private MenberMenuData menberMenuData;
    private LinearLayout menber_left_menu_view_group;

    public MenberLeftMenuView(Context context, MenberMenuData menberMenuData) {
        super(context);
        this.context = context;
        this.menberMenuData = menberMenuData;
    }

    @Override
    public View initGroupView() {
        view = View.inflate(context, R.layout.view_menber_left_menu, null);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }

    @Override
    public void initView() {
        menber_left_menu_view_group = (LinearLayout) view.findViewById(R.id.menber_left_menu_view_group);
    }

    @Override
    public void initData() {

        addMenuView();

    }

    //添加菜单视图
    private void addMenuView() {
        if (menberMenuData == null) {
            return;
        }

        menber_left_menu_view_group.removeAllViews();
        for (MenberMainMenuData mainMenuData : menberMenuData.getLeftMenberMainMenuDatas()) {
            menber_left_menu_view_group.addView(mainMenuData.getMainView().getRootView());
            for (MenberItemMenuData goodsItemMenuData : mainMenuData.getItemMenus()) {
                menber_left_menu_view_group.addView(goodsItemMenuData.getLeftView().getRootView());
            }
        }
    }

    @Override
    public void initListenner() {

    }
}
