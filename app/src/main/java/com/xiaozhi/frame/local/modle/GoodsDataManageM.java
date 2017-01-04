package com.xiaozhi.frame.local.modle;

import android.content.Context;

import com.xiaozhi.frame.mvp.m.BaseModel;
import com.xiaozhi.frame.mvp.m.callbackmanage.RequestCallBack;

/**
 * Created by Fynner on 2017/1/2.
 */

public class GoodsDataManageM extends BaseModel {

    public GoodsDataManageM(Context context, RequestCallBack requestCallBack) {
        super(context, requestCallBack);
    }

    /**
     * 获取商品数据
     */
    public void obtainGoodsData() {
    }
}
