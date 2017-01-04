package com.xiaozhi.frame.local.goodsdata;

import com.xiaozhi.frame.main.been.goodsbeen.GoodsMenuData;

import java.util.ArrayList;

/**
 * Created by Fynner on 2017/1/2.
 * <p>
 * bh	978
 * dat	2016-08-31 10:37:01.0
 * directory
 * fid	e83bc59bd858be93
 * guid	14cae85e90692495
 * imgs
 * name	西冷牛排
 * num	0
 * pic	http://img01.poso2o.com//20160831/ee8977b978e0922a/ee8977b978e0922a_222_222.jpg
 * price	198.00
 * remark	微辣,中辣,重辣
 * taocan
 * uid	13016079579
 * unit	份
 */

public class GoodsData {
    //网络获取的数据
    public String bh; //货号
    public String dat;//商品创建时间
    public String directory;  //目录名
    public String fid;//目录id

    public String guid;//商品id
    public ArrayList<GoodsImagesData> imgs;//图片组
    public String name; //商品名
    public String num;//库存
    public String pic;//主图
    public String price; //商品价格
    public String remark;//描述

    public ArrayList<GoodsTaocanData> taocan;//套餐
    public String uid;  //用户shopid
    public String unit;  //数量单位
//-------------------------------------------

    public String salesnum; //卖出数量


}
