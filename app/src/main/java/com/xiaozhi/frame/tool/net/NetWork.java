package com.xiaozhi.frame.tool.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Fynner on 2017/1/2.
 */

public class NetWork {
    /**
     *      * 判断是否有网络连接
     *      * @param context
     *      * @return
     *      
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable()) { //判断网络连接是否打开
            return networkInfo.isConnected();
        }
        return false;
    }
}
