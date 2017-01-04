package com.xiaozhi.frame.main.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaozhi.frame.main.R;
import com.xiaozhi.frame.mvp.v.view.BaseView;

/**
 * Created by Fynner on 2016/12/31.
 */
public class GoodsLeftItemMenuView extends BaseView {
    private View view;
    private Context context;
    //设置监听回调
    private GoodsLeftItemMenuView leftItemMenuView;
    private OnLeftItemMenuListenner onLeftItemMenuListenner;


    private TextView goods_left_item_menu;
    private String itemName;

    public GoodsLeftItemMenuView(Context context, String itemName, OnLeftItemMenuListenner onLeftItemMenuListenner) {
        super(context);
        this.context = context;
        this.itemName = itemName;

        //设置监听回调
        leftItemMenuView = this;
        this.onLeftItemMenuListenner = onLeftItemMenuListenner;
    }

    @Override
    public View initGroupView() {
        view = View.inflate(context, R.layout.view_goods_left_item_menu, null);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 75));
        return view;
    }

    @Override
    public void initView() {
        goods_left_item_menu = (TextView) view.findViewById(R.id.goods_left_item_menu);
    }

    @Override
    public void initData() {
        if (itemName == null) {
            goods_left_item_menu.setText("");
        } else {
            goods_left_item_menu.setText(itemName);
        }

    }

    @Override
    public void initListenner() {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onLeftItemMenuListenner != null) {
                    onLeftItemMenuListenner.onItemGroupViewClick(leftItemMenuView);
                }
            }
        });
    }

    /**
     * 显示布局
     */
    public void hindView() {
        view.setVisibility(View.GONE);
    }

    /**
     * 隐藏布局
     */
    public void showView() {
        view.setVisibility(View.VISIBLE);
    }

    public interface OnLeftItemMenuListenner {
        void onItemGroupViewClick(BaseView itemMenuView);
    }

}
