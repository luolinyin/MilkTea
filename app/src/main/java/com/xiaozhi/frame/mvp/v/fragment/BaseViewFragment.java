package com.xiaozhi.frame.mvp.v.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaozhi.frame.mvp.v.dialog.ReadDialog;

/**
 * Created by lenovo on 2016/12/8.
 */

public abstract class BaseViewFragment extends Fragment {

    public View baseFragmentView = null;

    public ReadDialog mReadDialog;

    public void initBaseViewFragment(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        baseFragmentView = intiView(inflater, container, savedInstanceState);
        mReadDialog = new ReadDialog(getContext());
    }


    public abstract View intiView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * 获取读取延迟dialog窗口
     *
     * @return
     */
    public ReadDialog getReadDialog() {
        return mReadDialog;
    }

    /**
     * 显示读取延迟dialog窗口
     */
    public void showReadDialog() {
        mReadDialog.show();
    }

    /**
     * 隐藏读取延迟dialog窗口
     */
    public void dismissReadDialog() {
        mReadDialog.dismiss();
    }
}
