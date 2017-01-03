package com.xiaozhi.frame.mvp.v.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by lenovo on 2016/12/8.
 */

public abstract class BaseDataFragment extends BaseViewFragment{
    public String TAG;

    public void initBaseViewFragment(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        TAG=this.getClass().getSimpleName();
        super.initBaseViewFragment(inflater,container,savedInstanceState);
        initData();

    }
    public abstract void initData();
}
