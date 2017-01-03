package com.xiaozhi.frame.main.modle;

import android.content.Context;

import com.xiaozhi.frame.action.SignAction;
import com.xiaozhi.frame.mvp.m.BaseModel;
import com.xiaozhi.frame.mvp.m.HttpRemoteService;
import com.xiaozhi.frame.mvp.m.HttpRequestParameter;
import com.xiaozhi.frame.mvp.m.callbackmanage.RequestCallBack;
import com.xiaozhi.frame.mvp.v.activity.BaseActivity;
import com.xiaozhi.frame.urlkey.SignKey;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/11/30.
 */

public class SignActivityM extends BaseModel {

    public SignActivityM(Context context, RequestCallBack requestCallBack) {
        super(context, requestCallBack);
    }

    public void sign(String loginname,String syz ,String password) {

        ArrayList<HttpRequestParameter> parameters = new ArrayList<HttpRequestParameter>();
        parameters.add(new HttpRequestParameter("Act", SignAction.LOGIN_ACTION));
        parameters.add(new HttpRequestParameter("uid", loginname));
        parameters.add(new HttpRequestParameter("czy", syz));
        parameters.add(new HttpRequestParameter("password", password));
        HttpRemoteService.getInstentce().invoke((BaseActivity) context, "Login", parameters, getRequestCallBack(), SignAction.LOGIN_ACTION, false);
    }
}
