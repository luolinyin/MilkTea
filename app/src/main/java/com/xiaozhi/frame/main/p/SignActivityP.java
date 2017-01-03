package com.xiaozhi.frame.main.p;

import android.widget.Toast;

import com.xiaozhi.frame.action.SignAction;
import com.xiaozhi.frame.memory.UserInfoMemory;
import com.xiaozhi.frame.mvp.m.callbackmanage.RequestCallBack;
import com.xiaozhi.frame.mvp.v.activity.BaseActivity;
import com.xiaozhi.frame.main.activity.SignActivity;
import com.xiaozhi.frame.main.modle.SignActivityM;
import com.xiaozhi.frame.tool.SharedPrefernces.SharedPreferencesUtil;

/**
 * Created by lenovo on 2016/11/30.
 */
public class SignActivityP implements RequestCallBack {

    private SharedPreferencesUtil sharedPreferencesUtil;
    private SignActivity activity;
    private SignActivityM signActivityM;

    public SignActivityP(BaseActivity activity) {
        this.activity = (SignActivity) activity;
        initData();
    }

    private void initData() {
        sharedPreferencesUtil = new SharedPreferencesUtil(activity, UserInfoMemory.USER_INFO);
        signActivityM = new SignActivityM(activity, this);
    }


    @Override
    public void onSuccess(String response, String callBackAction) {
        if (SignAction.LOGIN_ACTION.equals(callBackAction)) {

        }

    }


    @Override
    public void onFail(String failSt, String callBackAction) {
        if (SignAction.LOGIN_ACTION.equals(callBackAction)) {

        }

    }

    @Override
    public void onCookieExpired() {

    }

    /*开发接口 设置是否将帐号密码缓存*/
    public void setMindPw(boolean b) {
        sharedPreferencesUtil.setBoolean(UserInfoMemory.IS_MIND_PASSWORD, b);
        sharedPreferencesUtil.editorCommit();
    }

    public boolean getMindPw() {
        return sharedPreferencesUtil.getBoolean(UserInfoMemory.IS_MIND_PASSWORD, true);
    }

    public String getPassW() {
        return sharedPreferencesUtil.getString(UserInfoMemory.LOGIN_PASSWORD, "");
    }

    public void setPassW(String passW) {
        sharedPreferencesUtil.setString(UserInfoMemory.LOGIN_PASSWORD, passW);
        sharedPreferencesUtil.editorCommit();
    }

    public String getUserName() {
        return sharedPreferencesUtil.getString(UserInfoMemory.USER_NEME, "");
    }


    public void setUserName(String userName) {
        sharedPreferencesUtil.setString(UserInfoMemory.USER_NEME, userName);
        sharedPreferencesUtil.editorCommit();
    }

    public void requesteSign(String userName, String cyz, String passWork) {

        if (userName.equals("") || userName == null) {
            Toast toast = new Toast(activity);
            toast.makeText(activity, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (passWork.equals("") || passWork == null) {
            Toast toast = new Toast(activity);
            toast.makeText(activity, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        signActivityM.sign(userName, cyz, passWork);
    }
}
