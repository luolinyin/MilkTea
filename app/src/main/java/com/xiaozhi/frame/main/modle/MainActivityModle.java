package com.xiaozhi.frame.main.modle;

import android.content.Context;

import com.xiaozhi.frame.mvp.m.BaseModel;
import com.xiaozhi.frame.mvp.m.HttpRemoteService;
import com.xiaozhi.frame.mvp.m.HttpRequestParameter;
import com.xiaozhi.frame.mvp.m.callbackmanage.RequestCallBack;
import com.xiaozhi.frame.main.activity.MainActivity;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/11/30.
 */
public class MainActivityModle extends BaseModel {
    public MainActivityModle(Context context, RequestCallBack requestCallBack) {
        super(context, requestCallBack);

    }

    public void getNetWork() {
        HttpRemoteService.getInstentce().invoke((MainActivity) context, "card_detail", new ArrayList<HttpRequestParameter>(), getRequestCallBack(),"", false);
    }
}
