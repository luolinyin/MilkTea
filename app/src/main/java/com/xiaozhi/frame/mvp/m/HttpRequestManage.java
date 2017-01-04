package com.xiaozhi.frame.mvp.m;

import android.content.Context;


import com.xiaozhi.frame.mvp.m.callbackmanage.HttpRequestCallBack;
import com.xiaozhi.frame.mvp.m.threadmanage.DefaultThreadPool;
import com.xiaozhi.frame.mvp.m.urlmanage.UrlData;
import com.xiaozhi.frame.mvp.v.activity.BaseActivity;
import com.xiaozhi.frame.tool.print.Print;

import java.util.ArrayList;

/**
 * Created by Fynner on 2016/11/29.
 * Activity 請求request管理。
 */

public class HttpRequestManage {
    private BaseActivity activity = null;
    private Context context;
    private ArrayList<HttpRequeste> requestesList;

    public HttpRequestManage(Context context) {
        this.context = context;
        DefaultThreadPool.getInstance().confirmPoolSize(this.context);//創建設置綫程池大小
        this.activity = (BaseActivity) context;
        requestesList = new ArrayList<HttpRequeste>();
    }

    /**
     * 添加Request到列表
     */
    public void addRequest(HttpRequeste request) {
        if (requestesList == null) {
            requestesList = new ArrayList<HttpRequeste>();
        }
        requestesList.add(request);
    }

    public HttpRequeste createRequest(UrlData urlData,
                                      ArrayList<HttpRequestParameter> params,
                                      HttpRequestCallBack requestCallback) {
        HttpRequeste requeste = new HttpRequeste(urlData, params, requestCallback);

        addRequest(requeste);

        return requeste;
    }

    public void remoRequest(HttpRequeste request) {

        requestesList.remove(request);
    }


    /**
     * 取消网络请求
     */
    public void cancelRequest() {
        if ((requestesList != null) && (requestesList.size() > 0)) {

            for (HttpRequeste request : requestesList) {

                if (request.getUriRequest() != null) {
                    try {
                        request.getUriRequest().abort();//终止请求
                        DefaultThreadPool.getInstance().removeTaskFromQueue(request);//删除请求任务线程
                        requestesList.remove(request);
                        request.isCallBack = false;
                    } catch (final UnsupportedOperationException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        DefaultThreadPool.getInstance().confirmPoolSize(this.context);//刪除完畢重置綫程池大小
    }

}
