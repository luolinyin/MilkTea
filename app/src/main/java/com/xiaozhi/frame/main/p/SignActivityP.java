package com.xiaozhi.frame.main.p;

import android.text.Editable;
import android.widget.Toast;

import com.xiaozhi.frame.action.SignAction;
import com.xiaozhi.frame.memory.UserInfoMemory;
import com.xiaozhi.frame.mvp.m.callbackmanage.RequestCallBack;
import com.xiaozhi.frame.mvp.v.activity.BaseActivity;
import com.xiaozhi.frame.main.activity.SignActivity;
import com.xiaozhi.frame.main.modle.SignActivityM;
import com.xiaozhi.frame.mvp.v.toast.ToastView;
import com.xiaozhi.frame.tool.SharedPrefernces.SharedPreferencesUtil;

/**
 * Created by lenovo on 2016/11/30.
 */
public class SignActivityP {

    private SharedPreferencesUtil sharedPreferencesUtil;
    private SignActivity activity;


    public SignActivityP(BaseActivity activity) {
        this.activity = (SignActivity) activity;
        initData();
    }

    private void initData() {
        sharedPreferencesUtil = new SharedPreferencesUtil(activity, UserInfoMemory.USER_INFO);
    }


    /**
     * @param shopId
     * @param czyId
     * @param password
     */
    public void login(String shopId, String czyId, String password) {
        if ("".equals(shopId)) {
            new ToastView(activity, "请填写店铺帐号").show();
            return;
        }
        if ("".equals(czyId)) {
            new ToastView(activity, "请员工帐号").show();
            return;
        }
        if ("".equals(password)) {
            new ToastView(activity, "请填写密码").show();
            return;
        }


    }
}
