package com.xiaozhi.frame.main.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaozhi.frame.main.R;
import com.xiaozhi.frame.mvp.v.view.BaseView;

/**
 * Created by Fynner on 2017/1/1.
 * 账户信息视图
 */

public class MenberLevelView extends BaseView {
    private Context context;
    private View view;

    private TextView menber_right_level_name;

    public MenberLevelView(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public View initGroupView() {
        view = View.inflate(context, R.layout.view_menber_level, null);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }

    @Override
    public void initView() {
        menber_right_level_name = (TextView) view.findViewById(R.id.menber_right_level_name);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListenner() {

    }

    public void setCatalogTitle(String menuName) {
        if (menuName == null) {
            menuName = "";
        }
        menber_right_level_name.setText(menuName);
    }
}
