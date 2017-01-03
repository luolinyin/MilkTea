package com.xiaozhi.frame.mvp.m.callbackmanage;



import com.xiaozhi.frame.mvp.m.HttpRequeste;
import com.xiaozhi.frame.mvp.v.activity.BaseActivity;
import com.xiaozhi.frame.mvp.v.fragment.BaseFragment;
import com.xiaozhi.frame.tool.print.Print;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Created by Fynner on 2016/11/29.
 * 返回数据处理类，可以在这个类进行预处理
 */

public class RequestCallBackManage implements HttpRequestCallBack {
    private RequestCallBack requestCallBack = null;
    private BaseActivity baseActivity;
    private BaseFragment baseFragment;
    private String callBackAction;

    public RequestCallBackManage(BaseActivity baseActivity, RequestCallBack requestCallBack, String callBackAction) {
        this.requestCallBack = requestCallBack;
        this.baseActivity = baseActivity;
        if (callBackAction!=null&&!callBackAction.equals("")){
            this.callBackAction=callBackAction;
        }else {
            this.callBackAction="";
        }

    }
    public RequestCallBackManage(BaseFragment baseFragment, RequestCallBack requestCallBack, String callBackAction) {
        this.requestCallBack = requestCallBack;
        this.baseFragment = baseFragment;
        if (callBackAction!=null&&!callBackAction.equals("")){
            this.callBackAction=callBackAction;
        }else {
            this.callBackAction="";
        }

    }

    //请求服务器成功，拿到数据后回调
    @Override
    public void httpSuccess(HttpRequeste httpRequeste, String response ) {
        Print.println("zhi:"+response);
        if (baseActivity!=null){
            baseActivity.getRequestManage().remoRequest(httpRequeste);
        }else if (baseFragment!=null){
           baseFragment.getRequestManage().remoRequest(httpRequeste);
        }

        if (requestCallBack == null) return;
        //解析json数据
        try {
            JSONTokener jsonTokener = new JSONTokener(response);
            JSONObject json = (JSONObject) jsonTokener.nextValue();
            String code = json.getString("code");
            if (code.equals("success")) {
                JSONObject jsonObject = json.getJSONObject("data");

                requestCallBack.onSuccess(jsonObject.toString(),callBackAction);
            } else {
                String msg = json.getString("msg");
                //预处理后失败返回 目前也给返回数据结果
                requestCallBack.onFail(msg,callBackAction);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //请求服务器失败回调
    @Override
    public void httpFail(HttpRequeste httpRequeste,String failSt) {

        if (baseActivity!=null){
            baseActivity.getRequestManage().remoRequest(httpRequeste);
        }else if (baseFragment!=null){
            baseFragment.getRequestManage().remoRequest(httpRequeste);
        }

        requestCallBack.onFail(failSt,callBackAction);
    }

}
