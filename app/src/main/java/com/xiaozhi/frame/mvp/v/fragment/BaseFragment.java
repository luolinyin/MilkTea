package com.xiaozhi.frame.mvp.v.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.xiaozhi.frame.mvp.m.HttpRequestManage;
import com.xiaozhi.frame.mvp.v.activity.BaseActivity;


/**
 * Created by lenovo on 2016/12/8.
 */

public abstract class BaseFragment extends BaseListennerFragment {

    public Context context = null;
    public BaseActivity activity;
    public ImageLoader mImageLoader;

    /**
     * 请求列表管理器
     */
    private HttpRequestManage requestManage = null;

    // --修改--
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();

        requestManage = new HttpRequestManage((BaseActivity) context);
        mImageLoader = ImageLoader.getInstance();

        if (baseFragmentView == null) {
            initBaseViewFragment(inflater, container, savedInstanceState);
        }

        return baseFragmentView;
    }


    public void onPause() {

        super.onPause();
    }

    @Override
    public void onStop() {

  
        super.onStop();
    }

    // 删除控件
    @Override
    public void onDestroy() {

        mImageLoader.clearMemoryCache();

        ViewGroup viewGroup = (ViewGroup) baseFragmentView.getParent();
        if (viewGroup != null) {
            if (baseFragmentView != null) {
                viewGroup.removeView(baseFragmentView);
            }
        }
        super.onDestroy();
    }

    protected void intentToActivity(Class<? extends Activity> targetActivity) {
        Intent intent = new Intent(context, targetActivity);
        startActivity(intent);
    }

    /*
        * 每个activity都有一个请求管理器
        * */
    public HttpRequestManage getRequestManage() {
        return requestManage;
    }


}
