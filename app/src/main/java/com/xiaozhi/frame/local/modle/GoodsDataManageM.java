package com.xiaozhi.frame.local.modle;

import android.content.Context;

import com.xiaozhi.frame.mvp.m.BaseModel;
import com.xiaozhi.frame.mvp.m.HttpRemoteService;
import com.xiaozhi.frame.mvp.m.HttpRequestParameter;
import com.xiaozhi.frame.mvp.m.callbackmanage.RequestCallBack;
import com.xiaozhi.frame.mvp.v.activity.BaseActivity;
import com.xiaozhi.frame.urlkey.GoodsKey;
import com.xiaozhi.frame.urlkey.SignKey;

import java.util.ArrayList;

/**
 * Created by Fynner on 2017/1/2.
 */

public class GoodsDataManageM extends BaseModel {
    private BaseActivity baseActivity;

    public GoodsDataManageM(BaseActivity baseActivity, RequestCallBack requestCallBack) {
        super(baseActivity, requestCallBack);
        this.baseActivity=baseActivity;
    }

    /**
     * 获取商品数据
     */
    public void obtainGoodsData(String action) {
        ArrayList<HttpRequestParameter> parameters = new ArrayList<HttpRequestParameter>();
        parameters=setUserInfo(parameters);
        parameters.add(new HttpRequestParameter("Act",action));
        HttpRemoteService.getInstentce().invoke(baseActivity, GoodsKey.GOODS_QUERY_KEY, parameters, getRequestCallBack(), action, false);
    }
}
