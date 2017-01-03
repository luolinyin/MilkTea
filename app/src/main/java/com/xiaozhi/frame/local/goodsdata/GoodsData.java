package com.xiaozhi.frame.local.goodsdata;

import com.xiaozhi.frame.main.been.goodsbeen.GoodsMenuData;

import java.util.ArrayList;

/**
 * Created by Fynner on 2017/1/2.
 */

public class GoodsData {
    public String bh; //货号
    public String dat;//商品创建时间
    public String fid;//目录id
    public String fprice;//成本价格
    public String guid;//商品id
    public String kcnum;//库存
    public String name; //商品名
    public ArrayList<GoodsMenuData> nums; //商品颜色 尺寸 库存 条码
    public String price; //商品价格
    public String salesnum; //卖出数量
    public String uid;  //用户id
    public String unit;
    public ArrayList<GoodsImagesData> imgs;//图片组
    public String directory;  //目录名
}
