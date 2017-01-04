package com.xiaozhi.frame.local.manage;

import android.content.Context;

import com.xiaozhi.frame.configuration.PathManage;
import com.xiaozhi.frame.local.P.LoginUserDataLocalP;
import com.xiaozhi.frame.local.goodsdata.GoodsData;
import com.xiaozhi.frame.local.userdata.CzyData;
import com.xiaozhi.frame.local.userdata.UserData;
import com.xiaozhi.frame.mvp.v.activity.BaseActivity;
import com.xiaozhi.frame.tool.file.FileManage;
import com.xiaozhi.frame.tool.net.NetWork;

import java.util.ArrayList;

/**
 * Created by Fynner on 2017/1/3.
 */

public class UserDataLocalManage extends LocalDataManage {
    private static volatile UserDataLocalManage userDatalocalManage;
    private ArrayList<UserData> userDatas;

    private UserDataLocalManage() {

    }

    public static UserDataLocalManage getuserDataManage() {
        if (userDatalocalManage == null) {
            synchronized (UserDataLocalManage.class) {
                if (userDatalocalManage == null) {
                    userDatalocalManage = new UserDataLocalManage();
                }
            }
        }
        return userDatalocalManage;
    }

    /**
     * 用户登录接口
     *
     * @param baseActivity                    传入Activity必须继承BaseActivity才可以调用
     * @param userData                        将相应的信息进行封装，传入该方法，将自动处理
     * @param onUserDataLoginSuccessListenner 登录信息处理完成后回调此方法，做相呼应的操作。
     */
    public synchronized void loginUserData(BaseActivity baseActivity, UserData userData, OnUserDataLoginSuccessListenner onUserDataLoginSuccessListenner) {
        obtainLocalUserData();
        if (NetWork.isNetworkConnected(baseActivity)) {
            LoginUserDataLocalP loginUserDataLocalP = new LoginUserDataLocalP(userDatalocalManage, baseActivity, userData, onUserDataLoginSuccessListenner);
            loginUserDataLocalP.loginNet();
        } else {
            LoginUserDataLocalP loginUserDataLocalP = new LoginUserDataLocalP(userDatalocalManage, baseActivity, userData, onUserDataLoginSuccessListenner);
            loginUserDataLocalP.loginLocal(userDatas);

        }
    }

    /**
     * 获取本地用户数据
     */
    private synchronized void obtainLocalUserData() {

        if (userDatas == null) {
            Object object = FileManage.restoreObject(PathManage.USER_PHAT);//路径需要加上店铺帐号
            if (object != null) {
                userDatas = (ArrayList<UserData>) object;
            } else {
                userDatas = new ArrayList<UserData>();
            }
        }
    }

    /**
     * 请求网路失败
     *
     * @param baseActivity
     * @param userData
     * @param onUserDataLoginSuccessListenner
     */
    public synchronized void loginFail(BaseActivity baseActivity, UserData userData, OnUserDataLoginSuccessListenner onUserDataLoginSuccessListenner) {
        LoginUserDataLocalP loginUserDataLocalP = new LoginUserDataLocalP(userDatalocalManage, baseActivity, userData, onUserDataLoginSuccessListenner);
        loginUserDataLocalP.loginLocal(userDatas);
    }

    /**
     * 请求网络成功获取数据
     *
     * @param netUserData
     * @param onUserDataLoginSuccessListenner
     */
    public synchronized void loginSuccess(UserData netUserData, OnUserDataLoginSuccessListenner onUserDataLoginSuccessListenner) {

        if (onUserDataLoginSuccessListenner != null) {
            onUserDataLoginSuccessListenner.userDataLoginSuccessListenner(netUserData.shopId, netUserData.czyDatas.get(0), LocalDataManage.USER_DATA);
        }

        addUserData(netUserData);
    }


    /**
     * 添加用户信息
     *
     * @param netUserData 员工对象
     */
    private void addUserData(UserData netUserData) {

        boolean hasShopId = false;

        String shopId = netUserData.shopId;
        String czyId = netUserData.czyDatas.get(0).czyId;

        for (UserData user : userDatas) {
            if (user.shopId.equals(shopId)) {
                hasShopId = true;
                for (CzyData czy : user.czyDatas) {
                    if (czyId.equals(czy.czyId)) {
                        user.czyDatas.remove(czy);
                    }
                }
                user.czyDatas.add(netUserData.czyDatas.get(0));
            }
        }

        if (!hasShopId) {
            userDatas.add(netUserData);
        }

        saveUserDatas();
    }

    /**
     * 本地保存用户数据到文件夹
     */
    private synchronized void saveUserDatas() {

        if (userDatas == null) {
            userDatas = new ArrayList<UserData>();
        }
        FileManage.saveObject(PathManage.USER_PHAT, userDatas);
    }


    /**
     * 登录获取获取数据监听
     */
    public interface OnUserDataLoginSuccessListenner {
        /**
         * @param shopId  店铺id
         * @param czyData 员工信息
         * @param action  操作类型
         */
        void userDataLoginSuccessListenner(String shopId, CzyData czyData, String action);
    }

}
