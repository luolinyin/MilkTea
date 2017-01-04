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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaozhi.frame.main.R;
import com.xiaozhi.frame.main.adapter.GoodsRightListAdapter;
import com.xiaozhi.frame.mvp.v.view.BaseView;
import com.xiaozhi.frame.tool.SharedPrefernces.SharedPreferencesUtil;
import com.xiaozhi.frame.tool.listenner.NoDoubleClickListener;

import java.util.ArrayList;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.xiaozhi.frame.main.adapter.GoodsRightListAdapter.GRID;
import static com.xiaozhi.frame.main.adapter.GoodsRightListAdapter.LIST;

/**
 * 商品列表视图
 * Created by Fynner on 2017/1/1.
 */
public class GoodsCatalogView extends BaseView {

    private static final String MODE = "mode";

    private Context context;

    private View view;

    /**
     * 用户偏好记录（记录列表模式）
     */
    private SharedPreferencesUtil sharedPreferencesUtil;

    /**
     * 模式切换按钮
     */
    private ImageView goods_right_recycle_mode;

    /**
     * 列表模式的标签栏
     */
    private View goods_right_tag_bar;

    /**
     * 排序选项
     */
    private LinearLayout goods_right_sale; // 销量
    private LinearLayout goods_right_stock;// 库存
    private TextView goods_right_sale_tv;
    private TextView goods_right_stock_tv;

    private boolean isMaxSales;
    private boolean isMinStock;

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
        // 排序
        goods_right_sale = (LinearLayout) view.findViewById(R.id.goods_right_sale);
        goods_right_stock = (LinearLayout) view.findViewById(R.id.goods_right_stock);
        goods_right_sale_tv = (TextView) view.findViewById(R.id.goods_right_sale_tv);
        goods_right_stock_tv = (TextView) view.findViewById(R.id.goods_right_stock_tv);
        // 标签栏
        goods_right_tag_bar = view.findViewById(R.id.goods_right_tag_bar);
        goods_right_recycle_mode = (ImageView) view.findViewById(R.id.goods_right_recycle_mode);
        // 商品列表
        goodsRecyclerView = (RecyclerView) view.findViewById(R.id.goods_right_recycle);
        gridLayoutManager = new GridLayoutManager(context, 5);
        listLayoutManager = new LinearLayoutManager(context);
        // 刷新列表商品
        goodsDataRefresh = (SwipeRefreshLayout) view.findViewById(R.id.home_swipe);
        goodsDataRefresh.setColorSchemeColors(Color.RED, Color.BLUE, Color.YELLOW, Color.BLUE);
    }

    @Override
    public void initData() {
        // TODO 测试数据
        data = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            data.add(null);
        }
        // 获取用户上次使用的模式
        sharedPreferencesUtil = new SharedPreferencesUtil(context, "user");
        int mode = sharedPreferencesUtil.getInt(MODE, GRID);
        // 构建列表适配器
        goodsRightListAdapter = new GoodsRightListAdapter(context, data);
        goodsRightListAdapter.setMode(mode);
        if (mode == GRID) {
            goodsRecyclerView.setLayoutManager(gridLayoutManager);
        } else {
            goodsRecyclerView.setLayoutManager(listLayoutManager);
            goods_right_tag_bar.setVisibility(VISIBLE);
        }
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
                    saveRecyclerMode(LIST);
                } else {
                    goods_right_tag_bar.setVisibility(GONE);
                    // 切换到网格模式
                    goodsRightListAdapter = new GoodsRightListAdapter(context, data);
                    goodsRecyclerView.setAdapter(goodsRightListAdapter);
                    goodsRecyclerView.setLayoutManager(gridLayoutManager);
                    // 设置图片
                    goods_right_recycle_mode.setImageResource(R.mipmap.goods_right_switch_list);
                    saveRecyclerMode(GRID);
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

        // 排序
        goods_right_sale.setOnClickListener(new NoDoubleClickListener() {

            @Override
            public void onNoDoubleClick(View v) {
//                goodsDataRefresh.setRefreshing(true);
                goods_right_stock_tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.goods_right_hand_default, 0);
                if (isMaxSales) {
                    goods_right_sale_tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.goods_right_hand_down, 0);
                    isMaxSales = false;
                    //小的排上面
                    new Thread() {

                        @Override
                        public void run() {
                            super.run();
//                            ((GoodsActivity)context).minSalesVolume(mGoodsDatas, mSearchGoodsDatas, handler);
                        }
                    }.start();
                } else {
                    goods_right_sale_tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.goods_right_hand_up, 0);
                    isMaxSales = true;
                    //大的排上面
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
//                            ((GoodsActivity)context).maxSalesVolume(mGoodsDatas, mSearchGoodsDatas, handler);
                        }
                    }.start();
                }
            }
        });

        // 库存
        goods_right_stock.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
//                goodsDataRefresh.setRefreshing(true);
                goods_right_sale_tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.goods_right_hand_default, 0);
                if (isMinStock) {
                    goods_right_stock_tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.goods_right_hand_down, 0);

                    isMinStock = false;
                    //小的排上面
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
//                            ((GoodsActivity)context).minStock(mGoodsDatas, mSearchGoodsDatas, handler);
                        }
                    }.start();
                } else {
                    goods_right_stock_tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.goods_right_hand_up, 0);
                    isMinStock = true;

                    //大的排上面
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
//                            ((GoodsActivity)context).maxStock(mGoodsDatas, mSearchGoodsDatas, handler);
                        }
                    }.start();
                }
            }
        });
    }

    /**
     * 保存用户当前使用的模式
     */
    private void saveRecyclerMode(int mode) {
        sharedPreferencesUtil.setInt(MODE, mode);
        sharedPreferencesUtil.editorCommit();
    }

    public void setCatalogTitle(String menuName) {
        if (menuName == null) {
            menuName = "";
        }
    }
}
