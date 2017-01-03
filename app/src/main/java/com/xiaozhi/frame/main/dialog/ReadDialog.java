package com.xiaozhi.frame.main.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;

import com.xiaozhi.frame.main.R;
import com.xiaozhi.frame.mvp.v.dialog.BaseDialog;

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
    protected View setDialogContentView() {
        return view = View.inflate(context, R.layout.dialog_read, null);
    }

    @Override
    protected void initView() {
        setDialogGravity(Gravity.CENTER);
        setCanceledOnTouchOutside(false);


    }

    @Override
    protected void initListener() {

    }
}
