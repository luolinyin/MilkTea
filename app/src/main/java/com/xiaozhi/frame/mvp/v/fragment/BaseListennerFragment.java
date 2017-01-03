package com.xiaozhi.frame.mvp.v.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by lenovo on 2016/12/8.
 */

public abstract class BaseListennerFragment extends BaseDataFragment{

    public void initBaseViewFragment(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        super.initBaseViewFragment(inflater,container,savedInstanceState);
        initListnner();
    }

    public abstract void initListnner();
}
