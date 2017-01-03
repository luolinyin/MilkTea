package com.xiaozhi.frame.mvp.m.callbackmanage;


import com.xiaozhi.frame.mvp.m.HttpRequeste;

/**
 * Created by Fynner on 2016/11/21.
 * HttpRequests的回调接口。
 */

public interface  HttpRequestCallBack {
    public void httpSuccess(HttpRequeste httpRequeste, String response);
    public void httpFail(HttpRequeste httpRequeste, String failSt);


}
