package com.xiaozhi.frame.main.activity;

import android.content.Context;
import android.view.View;

import com.xiaozhi.frame.main.R;
import com.xiaozhi.frame.mvp.v.activity.BaseActivity;

/**
 * Created by Fynner on 2017/1/4.
 */

public class TActivity extends BaseActivity {
    private View view;
    private Context context;

    @Override
    public View initView() {
        context = this;
        view = View.inflate(context, R.layout.activity_t, null);
        return view;
    }

    @Override
    public void initData() {
        setTitle("测试数据界面");
    }

    @Override
    public void initListenner() {

    }


}
