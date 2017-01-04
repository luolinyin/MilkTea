package com.xiaozhi.frame.main.activity;

import android.content.Context;
import android.view.View;

import com.xiaozhi.frame.main.R;
import com.xiaozhi.frame.mvp.v.activity.BaseActivity;

/**
 * Created by Fynner on 2017/1/3.
 * 报表页面
 */

public class ReportFormActivity extends BaseActivity {
    private Context context;
    private View view;

    @Override
    public View initView() {
        context = this;
        view = View.inflate(context, R.layout.activity_report_form, null);
        return view;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListenner() {

    }


}
