package com.xiaozhi.frame.main.p;

import com.xiaozhi.frame.main.activity.GoodsActivity;
import com.xiaozhi.frame.mvp.m.callbackmanage.RequestCallBack;

/**
 * 商品界面P层
 * Created by tenac on 2017/1/4.
 */
public class GoodsActivityP implements RequestCallBack {

    private GoodsActivity goodsActivity;

    public GoodsActivityP(GoodsActivity goodsActivity) {
        this.goodsActivity = goodsActivity;
    }

    @Override
    public void onSuccess(String response, String callBackAction) {

    }

    @Override
    public void onFail(String failSt, String callBackAction) {

    }

    @Override
    public void onCookieExpired() {

    }
}
