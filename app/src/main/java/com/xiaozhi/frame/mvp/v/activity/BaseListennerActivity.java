package com.xiaozhi.frame.mvp.v.activity;

import android.os.Bundle;

/**
 * Created by lenovo on 2016/11/18.
 * 该类操作全局所有activity中的Listenner
 */

public abstract class BaseListennerActivity extends BaseDataActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListenner();
    }

    public abstract void initListenner();

}
