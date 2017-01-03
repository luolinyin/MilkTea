package com.xiaozhi.frame.mvp.v.activity;

import android.os.Bundle;

/**
 * Created by lenovo on 2016/11/18.
 * 该类操作全局所有activity中的Data
 */


public abstract class BaseDataActivity extends BaseViewActivity {

    public String TAG;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        TAG = this.getClass().getSimpleName();
        super.onCreate(savedInstanceState);
        initData();
    }

    public abstract void initData();

}
