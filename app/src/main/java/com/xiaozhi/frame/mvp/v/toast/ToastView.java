package com.xiaozhi.frame.mvp.v.toast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.xiaozhi.frame.main.R;

import java.util.Timer;
import java.util.TimerTask;


public class ToastView {

    public static Toast mToast;
    private int time;
    private Timer timer;

    // public ToastView(Context context, String text)

	/*
     * 调用该方法获取自定义Toast。 Context 上下文 String 显示的文本
	 */

    public ToastView(Context context, String text) {
        LayoutInflater inflater = LayoutInflater.from(context);
        // 定义View
        View view = inflater.inflate(R.layout.toast_view, null);
        TextView t = (TextView) view.findViewById(R.id.toast_text);
        t.setText(text);

        cancel();

        mToast = new Toast(context);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setView(view);
    }

    public ToastView(Context context, String text, int gravity, int xOffset, int yOffset) {
        LayoutInflater inflater = LayoutInflater.from(context);
        // 定义View
        View view = inflater.inflate(R.layout.toast_view, null);
        TextView t = (TextView) view.findViewById(R.id.toast_text);
        t.setText(text);
        cancel();
        mToast = new Toast(context);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setGravity(gravity, xOffset, yOffset);
        mToast.setView(view);
    }

    public ToastView(Context context, String text, int duration) {
        LayoutInflater inflater = LayoutInflater.from(context);
        // 定义View
        View view = inflater.inflate(R.layout.toast_view, null);
        TextView t = (TextView) view.findViewById(R.id.toast_text);
        t.setText(text);
        cancel();
        mToast = new Toast(context);
        setDuration(duration);
        mToast.setView(view);
    }

    public ToastView(Context context, String text, int duration, int gravity, int xOffset, int yOffset) {
        LayoutInflater inflater = LayoutInflater.from(context);
        // 定义View
        View view = inflater.inflate(R.layout.toast_view, null);
        TextView t = (TextView) view.findViewById(R.id.toast_text);
        t.setText(text);
        cancel();
        mToast = new Toast(context);
        mToast.setGravity(gravity, xOffset, yOffset);
        setDuration(duration);
        mToast.setView(view);
    }

    // 设置toast显示位置
    public void setGravity(int gravity, int xOffset, int yOffset) {
        // toast.setGravity(Gravity.CENTER, 0, 0); // 居中显示
        mToast.setGravity(gravity, xOffset, yOffset);
    }

    // 设置toast显示时间
    public void setDuration(int duration) {
        mToast.setDuration(duration);
    }

    // 设置toast显示时间(自定义时间)
    public void setLongTime(int duration) {
        time = duration;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                if (time - 1000 >= 0) {
                    show();
                    time = time - 1000;
                } else {
                    timer.cancel();
                }
            }
        }, 0, 1000);
    }

    public void show() {
        if (mToast != null) {
            mToast.show();
        }
    }

    public static void cancel() {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
    }

}
