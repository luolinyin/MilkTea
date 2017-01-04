package com.xiaozhi.frame.local.P;

import android.os.Handler;

import com.google.gson.Gson;
import com.xiaozhi.frame.action.SignAction;
import com.xiaozhi.frame.local.manage.LocalDataManage;
import com.xiaozhi.frame.local.manage.UserDataLocalManage;
import com.xiaozhi.frame.local.modle.UserDataManageM;
import com.xiaozhi.frame.local.userdata.CzyData;
import com.xiaozhi.frame.local.userdata.LoginReturnData;
import com.xiaozhi.frame.local.userdata.UserData;
import com.xiaozhi.frame.mvp.m.callbackmanage.RequestCallBack;
import com.xiaozhi.frame.mvp.v.activity.BaseActivity;
import com.xiaozhi.frame.mvp.v.toast.ToastView;
import com.xiaozhi.frame.tool.print.Print;

import java.util.ArrayList;

/**
 * Created by Fynner on 2017/1/4.
 * 一次请请求类
 */

public class LoginUserDataLocalP implements RequestCallBack {
    private BaseActivity baseActivity;
    private UserData userData;
    private UserDataLocalManage userDataLocalManage;
    private UserDataLocalManage.OnUserDataLoginSuccessListenner onUserDataLoginSuccessListenner;

    private UserDataManageM userDataNetManageM;

    private Handler handler;

    public LoginUserDataLocalP(UserDataLocalManage userDataLocalManage, BaseActivity baseActivity, UserData userData, UserDataLocalManage.OnUserDataLoginSuccessListenner onUserDataLoginSuccessListenner) {
        handler = new Handler();
        this.userDataLocalManage = userDataLocalManage;
        this.baseActivity = baseActivity;
        this.userData = userData;
        this.onUserDataLoginSuccessListenner = onUserDataLoginSuccessListenner;
        userDataNetManageM = new UserDataManageM(baseActivity, this);
    }

    @Override
    public void onSuccess(String response, String callBackAction) {
        baseActivity.dismissReadDialog();

        if (SignAction.LOGIN_ACTION.equals(callBackAction)) {
            Gson gson = new Gson();
            LoginReturnData loginReturnData = (LoginReturnData) gson.fromJson(response, LoginReturnData.class);
            UserData netUserData = new UserData();
            if (loginReturnData != null) {

                netUserData.shopId = loginReturnData.shopid;
                CzyData czyData = new CzyData();
                czyData.czyId = loginReturnData.sessionUid;
                czyData.czyPassworkKey = loginReturnData.sessionKey;
                czyData.flag = loginReturnData.flag;
                czyData.czType = loginReturnData.zw;
                czyData.realname = loginReturnData.realname;
                czyData.czyPasswork = userData.czyDatas.get(0).czyPasswork;
                netUserData.czyDatas.add(czyData);
            }


            userDataLocalManage.loginSuccess(netUserData, onUserDataLoginSuccessListenner);
        }
    }

    @Override
    public void onFail(String failSt, String callBackAction) {
        baseActivity.dismissReadDialog();

        if (SignAction.LOGIN_ACTION.equals(callBackAction)) {
            userDataLocalManage.loginFail(baseActivity, userData, onUserDataLoginSuccessListenner);
        }
    }

    @Override
    public void onCookieExpired() {
        Print.println("xiaozhi4");
        baseActivity.dismissReadDialog();


    }

    /**
     * 登录
     */
    public void loginNet() {
        String action = SignAction.LOGIN_ACTION;

        String shopid = userData.shopId;
        String czyid = userData.czyDatas.get(0).czyId;
        String passwork = userData.czyDatas.get(0).czyPasswork;

        if ("".equals(shopid)) {
            new ToastView(baseActivity, "请填写店铺帐号").show();
            return;
        }

        if ("".equals(czyid)) {
            new ToastView(baseActivity, "请填写工号").show();
            return;
        }

        if ("".equals(passwork)) {
            new ToastView(baseActivity, "请填写密码").show();
            return;
        }

        handler.post(new Runnable() {
            @Override
            public void run() {
                baseActivity.showReadDialog();
            }
        });


        userDataNetManageM.login(shopid, czyid, passwork, action);
    }

    /**
     * 本地登录
     */
    public synchronized void loginLocal(ArrayList<UserData> userDatas) {
        if (userData == null) {
            new ToastView(baseActivity, "请完善资料").show();
            return;
        }


        String shopId = userData.shopId;
        CzyData czyData = userData.czyDatas.get(0);
        String czyId = czyData.czyId;
        String password = czyData.czyPasswork;

        UserData localUserData = new UserData();

        boolean hasShopId = false;
        boolean hasCzyId = false;
        boolean okPassword = false;
        for (UserData user : userDatas) {
            if (shopId.equals(user.shopId)) {
                hasShopId = true;
                for (CzyData czy : user.czyDatas) {
                    if (czy.czyId.equals(czyId)) {
                        hasCzyId = true;
                        if (czy.czyPasswork.equals(password)) {
                            okPassword = true;
                            localUserData.shopId = user.shopId;
                            localUserData.czyDatas.add(czy);
                        }
                    }
                }
            }
        }

        if (!hasShopId) {
            new ToastView(baseActivity, "网络异常，切换本地登录，店铺帐号不存在").show();
            return;
        }
        if (!hasCzyId) {
            new ToastView(baseActivity, "网络异常，切换本地登录，工号不存在").show();
            return;
        }
        if (!okPassword) {
            new ToastView(baseActivity, "网络异常，切换本地登录，密码错误").show();
            return;
        }

        if (onUserDataLoginSuccessListenner != null) {
            onUserDataLoginSuccessListenner.userDataLoginSuccessListenner(localUserData.shopId, localUserData.czyDatas.get(0), LocalDataManage.USER_DATA);
        }
        new ToastView(baseActivity, "网络异常，切换本地登录，登录成功").show();
    }
}
