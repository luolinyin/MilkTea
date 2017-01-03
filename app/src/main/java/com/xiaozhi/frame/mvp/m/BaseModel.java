package com.xiaozhi.frame.mvp.m;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import com.xiaozhi.frame.memory.UserInfoMemory;
import com.xiaozhi.frame.mvp.m.callbackmanage.RequestCallBack;
import com.xiaozhi.frame.mvp.v.fragment.BaseFragment;
import com.xiaozhi.frame.tool.SharedPrefernces.SharedPreferencesUtil;
import com.xiaozhi.frame.tool.print.Print;

import java.util.ArrayList;

/**
 * Created by Fynner on 2016/11/19.
 */
public abstract class BaseModel {

    public Context context;
    public SharedPreferencesUtil mSharedPreferencesUtil;
    private RequestCallBack requestCallBack;

    public BaseModel(Context context, RequestCallBack requestCallBack) {
        this.context = context;
        this.requestCallBack = requestCallBack;
        mSharedPreferencesUtil=new SharedPreferencesUtil(context, UserInfoMemory.USER_INFO);
    }

    public BaseModel(BaseFragment baseFragment, RequestCallBack requestCallBack) {
        this.context = baseFragment.getActivity();
        this.requestCallBack = requestCallBack;
        mSharedPreferencesUtil=new SharedPreferencesUtil(context, UserInfoMemory.USER_INFO);
    }

    public RequestCallBack getRequestCallBack() {
        return requestCallBack;
    }

//    // 添加手机信息
//    public void addUserPhoneInfoParams(ArrayList<HttpRequestParameter> params,
//                                       Context context) {
//       long currentTimeMillis = HttpRequests.getServerTimeMillis() / 1000;
//        params.add(new HttpRequestParameter("CPT", "android")); // 手机类型
//        params.add(new HttpRequestParameter("version", UserPhoneInfo
//                .getChePinVersion(context)));// 应用版本号
//        params.add(new HttpRequestParameter("system", UserPhoneInfo.getVersion())); // 获取手机系统版本号
//        params.add(new HttpRequestParameter("device", "android")); // 装置
//        params.add(new HttpRequestParameter("access_time", currentTimeMillis + ""));
//        params.add(new HttpRequestParameter("ip_address", UserPhoneInfo
//                .getLocalIpAddress(context)));// ip
//        params.add(new HttpRequestParameter("signature", UserPhoneInfo
//                .getSignature(context, currentTimeMillis)));// 拼接加密
//    }

    public ArrayList<HttpRequestParameter> setUserInfo(ArrayList<HttpRequestParameter> parameters){
        if (parameters==null){
            parameters = new ArrayList<HttpRequestParameter>();
        }
        parameters.add(new HttpRequestParameter("sessionUid",mSharedPreferencesUtil.getString(UserInfoMemory.USER_NEME)));
        parameters.add(new HttpRequestParameter("shopid",mSharedPreferencesUtil.getString(UserInfoMemory.USER_NEME)));
        parameters.add(new HttpRequestParameter("czy",mSharedPreferencesUtil.getString(UserInfoMemory.CZY_NAME)));
        parameters.add(new HttpRequestParameter("sessionKey",mSharedPreferencesUtil.getString(UserInfoMemory.CZY_PW)));

        Print.println(mSharedPreferencesUtil.getString(UserInfoMemory.CZY_PW));

        return parameters;
    }



    /**
     * 根据路径生成一个Bitmap
     *
     * @param path
     * @param w    指定宽
     * @param h    指定高
     * @return
     */
    public static Bitmap convertToBitmap(String path, int w, int h) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        // 设置为ture只获取图片大小
        opts.inJustDecodeBounds = true;
        // 返回为空
        BitmapFactory.decodeFile(path, opts);
        int width = opts.outWidth;
        int height = opts.outHeight;
        int inSampleSize = 1;
        if (height > w || width > h) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) h);
            } else {
                inSampleSize = Math.round((float) width / (float) w);
            }
        }
        opts.inSampleSize = inSampleSize;
        opts.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(path, opts);
    }



}
