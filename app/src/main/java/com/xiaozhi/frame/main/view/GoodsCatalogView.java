package com.xiaozhi.frame.main.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xiaozhi.frame.main.R;
import com.xiaozhi.frame.main.adapter.GoodsRightListAdapter;
import com.xiaozhi.frame.mvp.v.view.BaseView;

import java.util.ArrayList;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.xiaozhi.frame.main.adapter.GoodsRightListAdapter.GRID;
import static com.xiaozhi.frame.main.adapter.GoodsRightListAdapter.LIST;

/**
 * Created by Fynner on 2017/1/1.
 * 账户信息视图
 */

public class GoodsCatalogView extends BaseView {

    private Context context;
    private View view;

    /**
     * 模式切换按钮
     */
    private ImageView goods_right_recycle_mode;

    /**
     * 列表模式的标签栏
     */
    private View goods_right_tag_bar;

    /**
     * 列表数据
     */
    private RecyclerView goodsRecyclerView;
    private RecyclerView.LayoutManager gridLayoutManager;
    private RecyclerView.LayoutManager listLayoutManager;
    private GoodsRightListAdapter goodsRightListAdapter;

    /**
     * 列表刷新数据
     */
    private SwipeRefreshLayout goodsDataRefresh;

    private ArrayList<Object> data;

    public GoodsCatalogView(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public View initGroupView() {
        view = View.inflate(context, R.layout.view_goods_catalog, null);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }

    @Override
    public void initView() {
        goods_right_tag_bar = view.findViewById(R.id.goods_right_tag_bar);
        goods_right_recycle_mode = (ImageView) view.findViewById(R.id.goods_right_recycle_mode);
        // 商品列表
        goodsRecyclerView = (RecyclerView) view.findViewById(R.id.goods_right_recycle);
        gridLayoutManager = new GridLayoutManager(context, 5);
        listLayoutManager = new LinearLayoutManager(context);
        goodsRecyclerView.setLayoutManager(gridLayoutManager);
        // 刷新列表商品
        goodsDataRefresh = (SwipeRefreshLayout) view.findViewById(R.id.home_swipe);
        goodsDataRefresh.setColorSchemeColors(Color.RED, Color.BLUE, Color.YELLOW, Color.BLUE);
    }

    @Override
    public void initData() {
        data = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            data.add(null);
        }
        goodsRightListAdapter = new GoodsRightListAdapter(context, data);
        goodsRecyclerView.setAdapter(goodsRightListAdapter);
    }

    @Override
    public void initListenner() {
        // 列表模式切换按钮
        goods_right_recycle_mode.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (goodsRightListAdapter.getMode() == GRID) {
                    // 切换到列表模式
                    goodsRightListAdapter = new GoodsRightListAdapter(context, data);
                    goodsRightListAdapter.setMode(LIST);
                    goodsRecyclerView.setAdapter(goodsRightListAdapter);
                    goodsRecyclerView.setLayoutManager(listLayoutManager);
                    // 设置图片
                    goods_right_recycle_mode.setImageResource(R.mipmap.goods_right_switch_grid);
                    goods_right_tag_bar.setVisibility(VISIBLE);
                } else {
                    goods_right_tag_bar.setVisibility(GONE);
                    // 切换到网格模式
                    goodsRightListAdapter = new GoodsRightListAdapter(context, data);
                    goodsRecyclerView.setAdapter(goodsRightListAdapter);
                    goodsRecyclerView.setLayoutManager(gridLayoutManager);
                    // 设置图片
                    goods_right_recycle_mode.setImageResource(R.mipmap.goods_right_switch_list);
                }
            }
        });
        // 刷新
        goodsDataRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                goodsDataRefresh.setRefreshing(false);
            }
        });
    }

    public void setCatalogTitle(String menuName) {
        if (menuName == null) {
            menuName = "";
        }

    }
}
