package com.xiaozhi.frame.mvp.v.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.xiaozhi.frame.mvp.m.HttpRequestManage;


/**
 * Created by lenovo on 2016/11/18.
 * 继承BaseActivity
 * 运行循序为View -》Data-》Listenner
 * 注意实现抽象方法的时候需要将抽象initView 需要先使用setContentView 获取对应的xml界面
 */

public abstract class BaseActivity extends BaseListennerActivity {
    private HttpRequestManage requestManage = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestManage = new HttpRequestManage(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onRestart() {
        super.onRestart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {

        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();


           }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

    /*
    * 每个activity都有一个请求管理器
    * */
    public HttpRequestManage getRequestManage() {
        return requestManage;
    }

    /**
     * 简单的页面跳转封装（如果需要传递参数，则要自己编写代码）
     *
     * @param targetActivity -目标页面
     */
    protected void intentToActivity(Class<? extends Activity> targetActivity) {
        Intent intent = new Intent(this, targetActivity);
        startActivity(intent);
    }

    public void finishActivity(){


        finish();
    }

}
