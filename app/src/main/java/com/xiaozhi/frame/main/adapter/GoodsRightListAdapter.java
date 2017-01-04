package com.xiaozhi.frame.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.xiaozhi.frame.main.R;
import com.xiaozhi.frame.tool.imageloader.LoaderImageNet;

import java.util.ArrayList;

/**
 * 商品列表适配器
 * Created by lenovo on 2016/12/9.
 */
public class GoodsRightListAdapter extends RecyclerView.Adapter {

    public static final int GRID = 0;

    public static final int LIST = 1;

    private ArrayList<Object> data;

    private Context context;

    private LoaderImageNet loaderImageNet;

    /**
     * 列表模式
     */
    private int mode = GRID;

    public GoodsRightListAdapter(Context context, ArrayList<Object> data) {
        this.data = new ArrayList<>();
        if (data != null) {
            this.data.addAll(data);
        }
        this.context = context;
        loaderImageNet = new LoaderImageNet(R.color.load_default_color, R.color.load_default_color, true, false, new FadeInBitmapDisplayer(300));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mode == GRID) {
            if (viewType == 0) {
                return new GoodsRightListHolder(LayoutInflater.from(context).inflate(R.layout.view_goods_right_scan_code, parent, false));
            }
            return new GoodsRightListHolder(LayoutInflater.from(context).inflate(R.layout.view_goods_right_grid_item, parent, false));
        } else {
            return new GoodsRightListHolder(LayoutInflater.from(context).inflate(R.layout.view_goods_right_list_item, parent, false));
        }
    }

    @Override
    public int getItemCount() {
        if (mode == GRID) {
            return data.size() + 1;
        } else {
            return data.size();
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof GoodsRightListHolder) {
            GoodsRightListHolder goodsRightListHolder = (GoodsRightListHolder) holder;
            if (mode == GRID) {
                if (position != 0) {
                    Object item = data.get(position - 1);
                    setupSameData(goodsRightListHolder, item);
                }
            } else {
                Object item = data.get(position);
                setupSameData(goodsRightListHolder, item);
                goodsRightListHolder.goods_right_item_sale.setText("325");
                goodsRightListHolder.goods_right_item_stock.setText("50");
                goodsRightListHolder.goods_right_item_type.setText("排类");
            }
        }
    }

    /**
     * 设置相同的数据
     */
    private void setupSameData(GoodsRightListHolder goodsRightListHolder, Object item) {
        goodsRightListHolder.goods_right_item_name.setText("澳洲小牛排");
        goodsRightListHolder.goods_right_item_code.setText("4521362");
        goodsRightListHolder.goods_right_item_price.setText("68.00");
        // TODO 测试链接
        String testUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1483507275678&di=72b9d3d13c1551099a511f6ac8f91f1f&imgtype=0&src=http%3A%2F%2Fimages4.c-ctrip.com%2Ftarget%2Ffd%2Ftuangou%2Fg2%2FM07%2F38%2FC9%2FCghzf1Wtk6CANhFIAAD8EHlvu-k722_600_400_s.jpg";
        loaderImageNet.loaderImage(testUrl, goodsRightListHolder.goods_right_item_img);//设置图片
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void notifyData(ArrayList<Object> data) {
        this.data.clear();
        if (data != null) {
            this.data.addAll(data);
        }
        notifyDataSetChanged();
    }

    public void setMode(int mode) {
        this.mode = mode;
        notifyDataSetChanged();
    }

    public int getMode() {
        return mode;
    }

    private class GoodsRightListHolder extends RecyclerView.ViewHolder {
        ImageView goods_right_item_img;
        TextView goods_right_item_name;
        TextView goods_right_item_code;
        TextView goods_right_item_price;
        TextView goods_right_item_sale;
        TextView goods_right_item_stock;
        TextView goods_right_item_type;


        GoodsRightListHolder(View itemView) {
            super(itemView);
            if (mode == LIST) {
                goods_right_item_sale = (TextView) itemView.findViewById(R.id.goods_right_item_sale);
                goods_right_item_stock = (TextView) itemView.findViewById(R.id.goods_right_item_stock);
                goods_right_item_type = (TextView) itemView.findViewById(R.id.goods_right_item_type);
            }
            goods_right_item_img = (ImageView) itemView.findViewById(R.id.goods_right_item_img);
            goods_right_item_name = (TextView) itemView.findViewById(R.id.goods_right_item_name);
            goods_right_item_code = (TextView) itemView.findViewById(R.id.goods_right_item_code);
            goods_right_item_price = (TextView) itemView.findViewById(R.id.goods_right_item_price);
        }
    }
}
