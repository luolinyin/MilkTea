package com.xiaozhi.frame.local.modle;

import android.content.Context;

import com.xiaozhi.frame.action.SignAction;
import com.xiaozhi.frame.mvp.m.BaseModel;
import com.xiaozhi.frame.mvp.m.HttpRemoteService;
import com.xiaozhi.frame.mvp.m.HttpRequestParameter;
import com.xiaozhi.frame.mvp.m.callbackmanage.RequestCallBack;
import com.xiaozhi.frame.mvp.v.activity.BaseActivity;
import com.xiaozhi.frame.tool.print.Print;
import com.xiaozhi.frame.urlkey.SignKey;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/11/30.
 */

public class UserDataLocalManageM extends BaseModel {
    private BaseActivity baseActivity;

    public UserDataLocalManageM(BaseActivity baseActivity, RequestCallBack requestCallBack) {
        super(baseActivity, requestCallBack);

        this.baseActivity = baseActivity;
    }

    /**
     * 登录
     *
     * @param shopid   店铺帐号
     * @param czyid    工号
     * @param passwork 密码
     * @param action   操作
     */
    public void login(String shopid, String czyid, String passwork, String action) {

        ArrayList<HttpRequestParameter> parameters = new ArrayList<HttpRequestParameter>();
        parameters.add(new HttpRequestParameter("Act", action));
        parameters.add(new HttpRequestParameter("shopid", shopid));
        parameters.add(new HttpRequestParameter("czy", czyid));
        parameters.add(new HttpRequestParameter("password", passwork));

        HttpRemoteService.getInstentce().invoke(baseActivity, SignKey.LOGIN_KEY, parameters, getRequestCallBack(), action, false);
    }
}
