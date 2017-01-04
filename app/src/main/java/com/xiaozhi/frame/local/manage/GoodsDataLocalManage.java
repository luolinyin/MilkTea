package com.xiaozhi.frame.local.manage;

import android.content.Context;
import android.os.Handler;

import com.xiaozhi.frame.configuration.Configuration;
import com.xiaozhi.frame.configuration.PathManage;
import com.xiaozhi.frame.local.P.GoodsDataNetP;
import com.xiaozhi.frame.local.goodsdata.GoodsAllData;
import com.xiaozhi.frame.local.goodsdata.GoodsData;
import com.xiaozhi.frame.memory.UserInfoMemory;
import com.xiaozhi.frame.mvp.v.activity.BaseActivity;
import com.xiaozhi.frame.tool.SharedPrefernces.SharedPreferencesUtil;
import com.xiaozhi.frame.tool.file.FileManage;
import com.xiaozhi.frame.tool.net.NetWork;

import java.util.ArrayList;

/**
 * Created by Fynner on 2017/1/2.
 */

public class GoodsDataLocalManage extends LocalDataManage {
    private static volatile GoodsDataLocalManage goodsDatalocalManage;
    private static SharedPreferencesUtil sharedPreferencesUtil;
    private ArrayList<GoodsData> goodsDatas = null;

    private GoodsDataLocalManage() {

    }

    public static GoodsDataLocalManage getGoodsDataManage(Context context) {
        if (goodsDatalocalManage == null) {
            synchronized (GoodsDataLocalManage.class) {
                if (goodsDatalocalManage == null) {
                    goodsDatalocalManage = new GoodsDataLocalManage();
                }
            }
        }

        sharedPreferencesUtil = new SharedPreferencesUtil(context, UserInfoMemory.USER_INFO);
        return goodsDatalocalManage;
    }

    /**
     * 无网络情况调用 获取本地商品数据
     */
    private synchronized void obtainLocalGoodsData() {
        //无网络
        Object object = FileManage.restoreObject(PathManage.GOODS_DATA_PHAT + sharedPreferencesUtil.getString(UserInfoMemory.SHOP_ID));
        if (object != null) {
            goodsDatas = (ArrayList<GoodsData>) object;

        } else {
            goodsDatas = new ArrayList<GoodsData>();
        }
    }

    /**
     * 保存商品数据
     */
    public synchronized void saveGoodsDatas(final BaseActivity baseActivity) {
        if (goodsDatas == null) {
            getGoodsDatas(baseActivity, new OnGoodsDataLocalListenner() {
                @Override
                public void goodsDataLocalListenner(ArrayList<GoodsData> goodsDatas, String action) {
                    saveGoodsDatas(baseActivity);
                }
            });
        } else {
            FileManage.saveObject(PathManage.GOODS_DATA_PHAT + sharedPreferencesUtil.getString(UserInfoMemory.SHOP_ID), goodsDatas);//路径需要加上店铺帐号

        }
    }

    /**
     * 获取商品数据
     *
     * @param baseActivity
     * @param onGoodsDataLocalListenner 监听
     */
    public synchronized void getGoodsDatas(final BaseActivity baseActivity, OnGoodsDataLocalListenner onGoodsDataLocalListenner) {

        if (goodsDatas == null) {
            if (NetWork.isNetworkConnected(baseActivity)) {
                GoodsDataNetP goodsDataNetP = new GoodsDataNetP(baseActivity, goodsDatalocalManage, onGoodsDataLocalListenner);

                goodsDataNetP.obtainNetGoodsData();
                //有网络
                /*这里操作先判断是否有请求缓存数据，如果有，先将缓存数据扔上服务端，服务端返回成功后，再进行同步商品数据*/


                return;
            } else {
                obtainLocalGoodsData();
            }
        }

        if (onGoodsDataLocalListenner != null) {
            onGoodsDataLocalListenner.goodsDataLocalListenner(goodsDatas, LocalDataManage.GOODS_DATA);
        }
    }


    public synchronized boolean addGoodsData(final BaseActivity baseActivity, final GoodsData goodsData) {
        if (goodsData == null) {
            return false;
        }

        if (goodsDatas == null) {
            getGoodsDatas(baseActivity, new OnGoodsDataLocalListenner() {
                @Override
                public void goodsDataLocalListenner(ArrayList<GoodsData> goodsDatas, String action) {
                    addGoodsData(baseActivity, goodsData);
                }
            });
        } else {
            goodsDatas.add(goodsData);
        }


        return true;

    }

    /**
     * 向服务端同步商品数据成功
     *
     * @param baseActivity
     * @param goodsAllData              所有商品数据实体
     * @param onGoodsDataLocalListenner
     */
    public void obtainNetGoodsDataSuccess(BaseActivity baseActivity, GoodsAllData goodsAllData, OnGoodsDataLocalListenner onGoodsDataLocalListenner) {
        goodsDatas = goodsAllData.list;
        if (onGoodsDataLocalListenner != null) {
            onGoodsDataLocalListenner.goodsDataLocalListenner(goodsDatas, LocalDataManage.GOODS_DATA);
        }
        saveGoodsDatas(baseActivity);//保存
    }


    /**
     * 获取数据监听
     */
    public interface OnGoodsDataLocalListenner {
        void goodsDataLocalListenner(ArrayList<GoodsData> goodsDatas, String action);
    }


}
