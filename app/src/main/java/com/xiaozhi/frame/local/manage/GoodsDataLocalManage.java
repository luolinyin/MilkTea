package com.xiaozhi.frame.local.manage;

import android.content.Context;
import android.os.Handler;

import com.xiaozhi.frame.configuration.Configuration;
import com.xiaozhi.frame.local.goodsdata.GoodsData;
import com.xiaozhi.frame.mvp.v.activity.BaseActivity;
import com.xiaozhi.frame.tool.file.FileManage;
import com.xiaozhi.frame.tool.net.NetWork;

import java.util.ArrayList;

/**
 * Created by Fynner on 2017/1/2.
 */

public class GoodsDataLocalManage extends LocalDataManage {
    private static volatile GoodsDataLocalManage goodsDatalocalManage;
    private ArrayList<GoodsData> goodsDatas = null;


    private GoodsDataLocalManage() {
    }

    public static GoodsDataLocalManage getGoodsDataManage() {
        if (goodsDatalocalManage == null) {
            synchronized (GoodsDataLocalManage.class) {
                if (goodsDatalocalManage == null) {
                    goodsDatalocalManage = new GoodsDataLocalManage();
                }
            }
        }
        return goodsDatalocalManage;
    }

    /**
     * 保存商品数据
     *
     * @param context
     */
    public synchronized void saveGoodsDatas(Context context) {
        if (goodsDatas == null) {
            getGoodsDatas(context, new OnGoodsDataLocalListenner() {
                @Override
                public void goodsDataLocalListenner(ArrayList<GoodsData> goodsDatas, String action) {
                    FileManage.saveObject(Configuration.GOODS_DATA_PHAT, goodsDatas);//路径需要加上店铺帐号
                }
            });
        } else {
            FileManage.saveObject(Configuration.GOODS_DATA_PHAT, goodsDatas);//路径需要加上店铺帐号

        }
    }

    /**
     * 获取商品数据
     *
     * @param context                   上下文
     * @param onGoodsDataLocalListenner 监听
     */
    public synchronized void getGoodsDatas(final Context context, OnGoodsDataLocalListenner onGoodsDataLocalListenner) {

        if (goodsDatas == null) {
            if (NetWork.isNetworkConnected(context)) {

                //有网络
                /*这里操作先判断是否有请求缓存数据，如果有，先将缓存数据扔上服务端，服务端返回成功后，再进行同步商品数据*/

                //同步服务端商品数据

                return;
            } else {
                obtainLocalGoodsData();
            }
        }

        if (onGoodsDataLocalListenner != null) {
            onGoodsDataLocalListenner.goodsDataLocalListenner(goodsDatas, LocalDataManage.GOODS_DATA);
        }
    }

    public synchronized boolean addGoodsData(final Context context, final GoodsData goodsData) {
        if (goodsData == null) {
            return false;
        }

        if (goodsDatas == null) {
            getGoodsDatas(context, new OnGoodsDataLocalListenner() {
                @Override
                public void goodsDataLocalListenner(ArrayList<GoodsData> goodsDatas, String action) {
                    addGoodsData(context, goodsData);
                }
            });
        } else {
            goodsDatas.add(goodsData);
        }


        return true;

    }

    /**
     * 无网络情况调用 获取本地商品数据
     */
    private synchronized void obtainLocalGoodsData() {
        //无网络
        Object object = FileManage.restoreObject(Configuration.GOODS_DATA_PHAT);//路径需要加上店铺帐号
        if (object != null) {
            goodsDatas = (ArrayList<GoodsData>) object;

        } else {
            goodsDatas = new ArrayList<GoodsData>();
        }
    }


    /**
     * 获取数据监听
     */
    public interface OnGoodsDataLocalListenner {
        void goodsDataLocalListenner(ArrayList<GoodsData> goodsDatas, String action);
    }


}
