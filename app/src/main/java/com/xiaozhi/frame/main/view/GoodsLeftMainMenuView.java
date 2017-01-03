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
public class GoodsLeftMainMenuView extends BaseView {
    private View view;
    private Context context;
    private GoodsLeftMainMenuView goodsLeftMainMenuView;

    private TextView goods_left_main_menu;
    private String mainName;

    private OnLeftMainMenuListenner onLeftMainMenuListenner;

    public GoodsLeftMainMenuView(Context context, String mainName, OnLeftMainMenuListenner onLeftMainMenuListenner) {
        super(context);
        this.context = context;
        this.mainName = mainName;
        this.onLeftMainMenuListenner = onLeftMainMenuListenner;
        goodsLeftMainMenuView = this;
    }

    @Override
    public View initGroupView() {
        view = View.inflate(context, R.layout.view_goods_left_main_menu, null);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 150));
        return view;
    }

    @Override
    public void initView() {
        goods_left_main_menu = (TextView) view.findViewById(R.id.goods_left_main_menu);
    }

    @Override
    public void initData() {
        if (mainName == null) {
            goods_left_main_menu.setText("");
        } else {
            goods_left_main_menu.setText(mainName);
        }
    }

    @Override
    public void initListenner() {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onLeftMainMenuListenner != null) {
                    onLeftMainMenuListenner.onMainGroupViewClick(goodsLeftMainMenuView);

                }
            }
        });

    }

    public interface OnLeftMainMenuListenner {
        void onMainGroupViewClick(BaseView mainMenuView);
    }


}
