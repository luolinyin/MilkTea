package com.xiaozhi.frame.local.P;

import android.os.Handler;

import com.google.gson.Gson;
import com.xiaozhi.frame.action.GoodsAction;
import com.xiaozhi.frame.local.goodsdata.GoodsAllData;
import com.xiaozhi.frame.local.goodsdata.GoodsData;
import com.xiaozhi.frame.local.manage.GoodsDataLocalManage;
import com.xiaozhi.frame.local.modle.GoodsDataManageM;
import com.xiaozhi.frame.mvp.m.callbackmanage.RequestCallBack;
import com.xiaozhi.frame.mvp.v.activity.BaseActivity;

/**
 * Created by Fynner on 2017/1/4.
 * 一次性使用辅助类。
 */

public class GoodsDataNetP implements RequestCallBack {
    private BaseActivity baseActivity;

    private GoodsDataLocalManage goodsDatalocalManage;
    private GoodsDataLocalManage.OnGoodsDataLocalListenner onGoodsDataLocalListenner;
    private GoodsDataManageM goodsDataManageM;

    private Handler handle;

    public GoodsDataNetP(BaseActivity baseActivity,GoodsDataLocalManage goodsDatalocalManage, GoodsDataLocalManage.OnGoodsDataLocalListenner onGoodsDataLocalListenner) {
        this.baseActivity = baseActivity;
        this.goodsDatalocalManage=goodsDatalocalManage;
        this.onGoodsDataLocalListenner = onGoodsDataLocalListenner;
        goodsDataManageM = new GoodsDataManageM(baseActivity, this);
        handle = new Handler();
    }

    @Override
    public void onSuccess(String response, String callBackAction) {
        baseActivity.dismissReadDialog();
        switch (callBackAction) {
            case GoodsAction.GOODS_QUERY_ACTION:
                Gson gson = new Gson();
                GoodsAllData goodsDatas = (GoodsAllData) gson.fromJson(response, GoodsAllData.class);
                goodsDatalocalManage.obtainNetGoodsDataSuccess(baseActivity,goodsDatas,onGoodsDataLocalListenner);

                break;
        }
    }

    @Override
    public void onFail(String failSt, String callBackAction) {
        baseActivity.dismissReadDialog();
        switch (callBackAction) {
            case GoodsAction.GOODS_QUERY_ACTION:
                goodsDatalocalManage.obtainNetGoodsDataFail(baseActivity,onGoodsDataLocalListenner);

                break;
        }
    }

    @Override
    public void onCookieExpired() {
        baseActivity.dismissReadDialog();
    }


    public void obtainNetGoodsData() {
        handle.post(new Runnable() {
            @Override
            public void run() {
                baseActivity.showReadDialog();
            }
        });
        goodsDataManageM.obtainGoodsData(GoodsAction.GOODS_QUERY_ACTION);
    }
}
