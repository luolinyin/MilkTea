package com.xiaozhi.frame.main.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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


    }

    @Override
    public void initListenner() {
        //登录
        sign_login.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
//                login();
                toMainActivity();
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

        signActivityP.login(sign_shop_id.getText().toString(),sign_czy_id.getText().toString(),sign_password.getText().toString());
    }


    /**
     * 主界面入口
     */
    private void toMainActivity() {
        Intent intent = new Intent();
        intent.setClass(context, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
