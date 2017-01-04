package com.xiaozhi.frame.main.p;


import com.xiaozhi.frame.local.manage.UserDataLocalManage;
import com.xiaozhi.frame.local.userdata.CzyData;
import com.xiaozhi.frame.local.userdata.UserData;
import com.xiaozhi.frame.memory.UserInfoMemory;
import com.xiaozhi.frame.mvp.v.activity.BaseActivity;
import com.xiaozhi.frame.main.activity.SignActivity;
import com.xiaozhi.frame.mvp.v.toast.ToastView;
import com.xiaozhi.frame.tool.SharedPrefernces.SharedPreferencesUtil;

/**
 * Created by lenovo on 2016/11/30.
 */
public class SignActivityP {

    private SharedPreferencesUtil sharedPreferencesUtil;
    private UserDataLocalManage userDataLocalManage;
    private BaseActivity baseActivity;


    public SignActivityP(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
        userDataLocalManage = UserDataLocalManage.getuserDataManage();
        initData();
    }

    private void initData() {
        sharedPreferencesUtil = new SharedPreferencesUtil(baseActivity, UserInfoMemory.USER_INFO);
    }


    /**
     * @param shopId
     * @param czyId
     * @param password
     */
    public void login(String shopId, String czyId, String password) {
        if ("".equals(shopId)) {
            new ToastView(baseActivity, "请填写店铺帐号").show();
            return;
        }
        if ("".equals(czyId)) {
            new ToastView(baseActivity, "请员工帐号").show();
            return;
        }
        if ("".equals(password)) {
            new ToastView(baseActivity, "请填写密码").show();
            return;
        }

        UserData userData = new UserData();
        userData.shopId = shopId;
        CzyData czyData = new CzyData();
        czyData.czyId = czyId;
        czyData.czyPasswork = password;
        userData.czyDatas.add(czyData);

        userDataLocalManage.loginUserData(baseActivity, userData, new UserDataLocalManage.OnUserDataLoginSuccessListenner() {
            @Override
            public void userDataLoginSuccessListenner(String shopId, CzyData czyData, String action) {
                saveUserInfo(shopId, czyData);
                ((SignActivity) baseActivity).toMainActivity();
            }
        });


    }

    /**
     * 缓存用户数据
     *
     * @param shopId
     * @param czyData
     */
    private void saveUserInfo(String shopId, CzyData czyData) {
        String czyId = czyData.czyId;
        String flag = czyData.flag;
        String realname = czyData.realname;
        String czyPasswork = czyData.czyPasswork;
        String czyPassworkKey = czyData.czyPassworkKey;
        String czType = czyData.czType;

        sharedPreferencesUtil.setString(UserInfoMemory.SHOP_ID, shopId);
        sharedPreferencesUtil.setString(UserInfoMemory.CZY_ID, czyId);
        sharedPreferencesUtil.setString(UserInfoMemory.FLAG, flag);
        sharedPreferencesUtil.setString(UserInfoMemory.REAL_NAME, realname);
        sharedPreferencesUtil.setString(UserInfoMemory.CZY_PASSWORD, czyPasswork);
        sharedPreferencesUtil.setString(UserInfoMemory.CZY_PASSWORD_KEY, czyPassworkKey);
        sharedPreferencesUtil.setString(UserInfoMemory.CZ_TYPE, czType);

        sharedPreferencesUtil.setBoolean(UserInfoMemory.IS_LOGIN, true);

        sharedPreferencesUtil.editorCommit();
    }

    /**
     * 曾经是否登录过
     *
     * @return
     */
    public boolean isLogin() {
        return sharedPreferencesUtil.getBoolean(UserInfoMemory.IS_LOGIN, false);
    }

    /**
     * 获取店铺id
     *
     * @return
     */
    public String getShopId() {
        return sharedPreferencesUtil.getString(UserInfoMemory.SHOP_ID, "");
    }

    /**
     * 获取员工id
     *
     * @return
     */
    public String getCzyId() {
        return sharedPreferencesUtil.getString(UserInfoMemory.CZY_ID, "");
    }
}
