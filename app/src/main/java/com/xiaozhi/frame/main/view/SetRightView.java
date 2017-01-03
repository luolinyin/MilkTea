package com.xiaozhi.frame.main.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaozhi.frame.main.R;
import com.xiaozhi.frame.main.activity.GoodsActivity;
import com.xiaozhi.frame.main.activity.SetActivity;
import com.xiaozhi.frame.mvp.v.view.BaseView;
import com.xiaozhi.frame.tool.listenner.NoDoubleClickListener;

/**
 * Created by Fynner on 2016/12/30.
 */
public class SetRightView extends BaseView {
    private View view;
    private Context context;
    private SetActivity setActivity;

    //视图容器
    private LinearLayout set_right_view_group;


    public SetRightView(Context context) {
        super(context);
        this.context = context;
        if (context instanceof SetActivity) {
            setActivity = (SetActivity) context;
        }

    }

    @Override
    public View initGroupView() {
        view = View.inflate(context, R.layout.view_set_right, null);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        return view;
    }

    @Override
    public void initView() {

        set_right_view_group = (LinearLayout) view.findViewById(R.id.set_right_view_group);
    }


    @Override
    public void initData() {

    }

    @Override
    public void initListenner() {

    }

    /**
     * 切换视图
     */
    public void changeRightView(BaseView rightView) {
        set_right_view_group.removeAllViews();
        set_right_view_group.addView(rightView.getRootView());
    }
}
