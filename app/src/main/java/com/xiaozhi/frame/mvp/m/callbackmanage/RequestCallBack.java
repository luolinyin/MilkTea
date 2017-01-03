package com.xiaozhi.frame.mvp.m.callbackmanage;

/**
 * Created by Fynner on 2016/11/29.
 */

public interface RequestCallBack {
    public void onSuccess(String response, String callBackAction);
    public void onFail(String failSt, String callBackAction);
    public void onCookieExpired();

}
