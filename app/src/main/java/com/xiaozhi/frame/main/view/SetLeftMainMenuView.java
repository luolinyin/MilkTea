package com.xiaozhi.frame.main.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaozhi.frame.main.R;
import com.xiaozhi.frame.mvp.v.view.BaseView;

/**
 * Created by Fynner on 2016/12/31.
 * 主视图
 */
public class SetLeftMainMenuView extends BaseView {
    private View view;
    private Context context;
    private SetLeftMainMenuView setLeftMainMenuView;

    private TextView setleft_main_menu;
    private String mainName;

    private OnLeftMainMenuListenner onLeftMainMenuListenner;

    public SetLeftMainMenuView(Context context, String mainName, OnLeftMainMenuListenner onLeftMainMenuListenner) {
        super(context);
        this.context = context;
        this.mainName = mainName;
        this.onLeftMainMenuListenner = onLeftMainMenuListenner;
        setLeftMainMenuView = this;
    }

    @Override
    public View initGroupView() {
        view = View.inflate(context, R.layout.view_set_left_main_menu, null);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 150));
        return view;
    }

    @Override
    public void initView() {
        setleft_main_menu = (TextView) view.findViewById(R.id.setleft_main_menu);
    }

    @Override
    public void initData() {
        if (mainName == null) {
            setleft_main_menu.setText("");
        } else {
            setleft_main_menu.setText(mainName);
        }
    }

    @Override
    public void initListenner() {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onLeftMainMenuListenner != null) {
                    onLeftMainMenuListenner.onMainGroupViewClick(setLeftMainMenuView);

                }
            }
        });

    }

    public interface OnLeftMainMenuListenner {
        void onMainGroupViewClick(BaseView mainMenuView);
    }


}
