package com.xiaozhi.frame.local.P;

import com.xiaozhi.frame.local.manage.GoodsDataLocalManage;
import com.xiaozhi.frame.mvp.m.callbackmanage.RequestCallBack;
import com.xiaozhi.frame.mvp.v.activity.BaseActivity;

/**
 * Created by Fynner on 2017/1/4.
 * 一次性使用辅助类。
 */

public class GoodsDataNetP implements RequestCallBack {
    private BaseActivity baseActivity;
    private GoodsDataLocalManage.OnGoodsDataLocalListenner onGoodsDataLocalListenner;

    public GoodsDataNetP(BaseActivity baseActivity, GoodsDataLocalManage.OnGoodsDataLocalListenner onGoodsDataLocalListenner) {
        this.baseActivity = baseActivity;
        this.onGoodsDataLocalListenner = onGoodsDataLocalListenner;
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


    public void obtainNetGoodsData() {
        

    }
}
