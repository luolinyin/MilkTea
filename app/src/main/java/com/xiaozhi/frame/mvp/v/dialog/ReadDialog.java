package com.xiaozhi.frame.mvp.v.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;

import com.xiaozhi.frame.main.R;

/**
 * Created by Fynner on 2016/12/30.
 */

public class ReadDialog extends BaseDialog {
    private Context context;
    private View view;

    public ReadDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public View setDialogContentView() {
        return view = View.inflate(context, R.layout.dialog_read, null);
    }

    @Override
    public void initView() {
        setDialogGravity(Gravity.CENTER);
        setCanceledOnTouchOutside(false);


    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
