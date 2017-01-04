package com.xiaozhi.frame.main.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.xiaozhi.frame.mvp.v.activity.BaseActivity;
import com.xiaozhi.frame.main.R;
import com.xiaozhi.frame.main.p.SignActivityP;
import com.xiaozhi.frame.mvp.v.toast.ToastView;
import com.xiaozhi.frame.tool.listenner.NoDoubleClickListener;

/**
 * Created by Fynner on 2016/11/30.
 */
public class SignActivity extends BaseActivity {
    private View view;
    private Context context;

    private final int Sign_PERMISSIONS_REQUEST_READ_CONTACTS = 1001;

    private SignActivityP signActivityP;

    private EditText sign_shop_id;
    private EditText sign_czy_id;
    private EditText sign_password;

    //登录
    private TextView sign_login;

    @Override
    public View initView() {
        context = this;
        view = View.inflate(context, R.layout.activity_sign, null);
        if (!this.isTaskRoot()) { // 判断该Activity是不是任务空间的源Activity，“非”也就是说是被系统重新实例化出来
            // 如果你就放在launcher Activity中话，这里可以直接return了
            Intent mainIntent = getIntent();
            String action = mainIntent.getAction();
            if (mainIntent.hasCategory(Intent.CATEGORY_LAUNCHER)
                    && action.equals(Intent.ACTION_MAIN)) {
                finish();
                return view;// finish()之后该活动会继续执行后面的代码，你可以logCat验证，加return避免可能的exception
            }
        }

        //判断sdk版本
        int sdkVersion;
        try {
            sdkVersion = Integer.valueOf(android.os.Build.VERSION.SDK);
        } catch (NumberFormatException e) {
            sdkVersion = 0;
        }

        if (sdkVersion > 22) {
            //判断是否授权了调用相册权限
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, Sign_PERMISSIONS_REQUEST_READ_CONTACTS
                );
            }
        }

        //登录
        sign_login = (TextView) view.findViewById(R.id.sign_login);
        sign_shop_id = (EditText) view.findViewById(R.id.sign_shop_id);
        sign_czy_id = (EditText) view.findViewById(R.id.sign_czy_id);
        sign_password = (EditText) view.findViewById(R.id.sign_password);

        return view;
    }

    @Override
    public void initData() {
        setTitle("登录");
        hideToHomeView();

        signActivityP = new SignActivityP(this);

        if (signActivityP.isLogin()) {
            sign_shop_id.setText(signActivityP.getShopId());
            sign_czy_id.setText(signActivityP.getCzyId());
        }


    }

    @Override
    public void initListenner() {
        //登录
        sign_login.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                login();
            }
        });

    }

    private void login() {
        if (sign_shop_id == null || sign_shop_id.getText() == null) {
            new ToastView(context, "请填写店铺帐号").show();
            return;
        }

        if (sign_czy_id == null || sign_czy_id.getText() == null) {
            new ToastView(context, "请员工帐号").show();
            return;
        }

        if (sign_password == null || sign_password.getText() == null) {
            new ToastView(context, "请填写密码").show();
            return;
        }

        signActivityP.login(sign_shop_id.getText().toString(), sign_czy_id.getText().toString(), sign_password.getText().toString());
    }


    /**
     * 主界面入口
     */
    public void toMainActivity() {
        Intent intent = new Intent();
        intent.setClass(context, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Sign_PERMISSIONS_REQUEST_READ_CONTACTS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    new ToastView(this, "没有权限退出页面").show();
                    finish();
                }
            }
        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


}
