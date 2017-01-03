package com.xiaozhi.frame.mvp.m;


import com.xiaozhi.frame.mvp.m.callbackmanage.RequestCallBack;
import com.xiaozhi.frame.mvp.m.callbackmanage.RequestCallBackManage;
import com.xiaozhi.frame.mvp.m.threadmanage.DefaultThreadPool;
import com.xiaozhi.frame.mvp.m.urlmanage.UrlConfigureManage;
import com.xiaozhi.frame.mvp.m.urlmanage.UrlData;
import com.xiaozhi.frame.mvp.v.activity.BaseActivity;
import com.xiaozhi.frame.mvp.v.fragment.BaseFragment;

import java.util.ArrayList;

/**
 * Created by Fynner on 2016/11/29.
 */

public class HttpRemoteService {
    private static HttpRemoteService service;

    private HttpRemoteService() {

    }

    public static synchronized HttpRemoteService getInstentce() {
        if (HttpRemoteService.service == null) {
            service = new HttpRemoteService();
        }
        return service;
    }

    public void invoke(BaseActivity baseActivity, String urlKey, ArrayList<HttpRequestParameter> parameters, RequestCallBack callBack, String callBackAction, boolean forceUpdate) {

        UrlData urlData = UrlConfigureManage.mateUrl(baseActivity, urlKey);
        if (forceUpdate) {
            urlData.setCacheTime(0);
        }

        RequestCallBackManage requestCallBackManage = new RequestCallBackManage(baseActivity, callBack, callBackAction);
        HttpRequeste requeste = baseActivity.getRequestManage().createRequest(urlData, parameters, requestCallBackManage);
        DefaultThreadPool.getInstance().execute(requeste);
    }

    public void invoke(BaseFragment baseFragment, String urlKey, ArrayList<HttpRequestParameter> parameters, RequestCallBack callBack, String callBackAction, boolean forceUpdate) {

        UrlData urlData = UrlConfigureManage.mateUrl(baseFragment.getActivity(), urlKey);
        if (forceUpdate) {
            urlData.setCacheTime(0);
        }

        RequestCallBackManage requestCallBackManage = new RequestCallBackManage(baseFragment, callBack, callBackAction);
        HttpRequeste requeste = baseFragment.getRequestManage().createRequest(urlData, parameters, requestCallBackManage);
        DefaultThreadPool.getInstance().execute(requeste);
    }
}
