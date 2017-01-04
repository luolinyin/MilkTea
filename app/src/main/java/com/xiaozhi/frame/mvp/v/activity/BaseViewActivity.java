package com.xiaozhi.frame.mvp.v.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaozhi.frame.main.activity.MainActivity;
import com.xiaozhi.frame.mvp.v.autolayout.AutoFrameLayout;
import com.xiaozhi.frame.mvp.v.autolayout.AutoLayoutActivity;
import com.xiaozhi.frame.main.R;
import com.xiaozhi.frame.mvp.v.dialog.ReadDialog;
import com.xiaozhi.frame.tool.color.StatusBarUtils;
import com.xiaozhi.frame.tool.listenner.NoDoubleClickListener;


/**
 * Created by lenovo on 2016/11/18.
 * 该类操作全局所有activity中的View
 */
public abstract class BaseViewActivity extends AutoLayoutActivity {
    private Context context;
    private ReadDialog mReadDialog;

    //上下布局
    private LinearLayout base_top;
    private LinearLayout base_view_group;

    //标题
    private TextView base_title;

    //头部logo
    private LinearLayout base_logo;

    //去到主页
    private ImageView base_to_main;

    //搜索 (默认隐藏)
    private LinearLayout base_top_search_group;
    private EditText base_top_search;
    private ImageView base_top_search_bg;
    private int sreachImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mReadDialog = new ReadDialog(this);
        setContentView(R.layout.activity_base_view);
        base_view_group = (LinearLayout) findViewById(R.id.base_view_group);
        base_top = (LinearLayout) findViewById(R.id.base_top);
        View view = initView();
        view.setLayoutParams(new AutoFrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        base_view_group.addView(view);

        //logo
        base_logo = (LinearLayout) findViewById(R.id.base_logo);

        //标题
        base_title = (TextView) findViewById(R.id.base_title);

        //回到主页
        base_to_main = (ImageView) findViewById(R.id.base_to_main);

        //搜索
        base_top_search_group = (LinearLayout) findViewById(R.id.base_top_search_group);
        base_top_search = (EditText) findViewById(R.id.base_top_search);
        base_top_search_bg = (ImageView) findViewById(R.id.base_top_search_bg);

        initBaseListenner();

        setStatusBar();
    }

    private void initBaseListenner() {
        base_to_main.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    public abstract View initView();

    protected void setStatusBar() {
        StatusBarUtils.setColor(this, getResources().getColor(R.color.status_bar_color));
    }

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

    /**
     * 隐藏顶部全部布局
     */
    public void hideTopGroupView() {
        base_top.setVisibility(View.GONE);
    }

    /**
     * 显示顶部全部布局
     */
    public void showTopGroupView() {
        base_top.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏Logo
     */
    public void hideLogo() {
        base_logo.setVisibility(View.INVISIBLE);
    }

    /**
     * 显示Logo
     */
    public void showLogo() {
        base_logo.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏标题
     */
    public void hideTitle() {
        base_title.setVisibility(View.INVISIBLE);
    }

    /**
     * 显示标题
     */
    public void showTitle() {
        base_title.setVisibility(View.VISIBLE);
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        if (title == null) {
            title = "";
        }
        base_title.setText(title);
    }

    /**
     * 隐藏跳转主页
     */
    public void hideToHomeView() {
        base_to_main.setVisibility(View.INVISIBLE);
    }

    /**
     * 显示跳转主页
     */
    public void showToHomeView() {
        base_to_main.setVisibility(View.VISIBLE);
    }


    /**
     * 自定义跳转按钮
     *
     * @param click   点击事件
     * @param imageId 图片资源id
     */
    public void changeToHomeFuction(int imageId, View.OnClickListener click) {
        showToHomeView();
        if (click != null) {
            base_to_main.setOnClickListener(click);
        }

        if (imageId != 0) {
            base_to_main.setImageResource(imageId);
        }
    }

    public void changeToHomeFuction(int imageId) {
        changeToHomeFuction(imageId, null);
    }

    public void changeToHomeFuction(View.OnClickListener click) {
        changeToHomeFuction(0, click);
    }


    /**
     * 显示搜索功能
     */
    public void showSreach() {
        base_top_search_group.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏搜索功能
     */
    public void hideSreach() {
        base_top_search_group.setVisibility(View.INVISIBLE);

    }

    /**
     * 设置搜索文本
     *
     * @param s 搜索文本
     */
    public void setSeachText(String s) {
        if (s == null) {
            s = "";
        }
        base_top_search.setText(s);
    }

    /**
     * 清空搜索文本
     */
    public void clearSeachTaxt() {
        base_top_search.setText("");
    }

    /**
     * 设置搜索样式
     *
     * @param textWatcher     写入事件监听
     * @param onClickListener 点击事件监听
     * @param imageOnclick    图片点击事件监听
     * @param imageId         图片资源id
     */
    public void setSreachListenner(TextWatcher textWatcher, View.OnClickListener onClickListener, View.OnClickListener imageOnclick, int imageId) {
        showSreach();
        if (textWatcher != null) {
            base_top_search.addTextChangedListener(textWatcher);
        }

        if (onClickListener != null) {
            base_top_search.setOnClickListener(onClickListener);
        }

        if (imageOnclick != null) {
            base_top_search_bg.setOnClickListener(imageOnclick);
        }

        if (imageId != 0) {
            base_top_search_bg.setImageResource(imageId);
        }


    }

    /**
     * 设置搜索点击事件
     *
     * @param onClickListener
     */
    private void setSreachListenner(View.OnClickListener onClickListener) {
        setSreachListenner(null, onClickListener, null, 0);
    }

    private void setSreachImageListenner(View.OnClickListener onClickListener) {
        setSreachListenner(null, null, onClickListener, 0);
    }

    private void setSreachImageListenner(View.OnClickListener onClickListener, int sreachImage) {
        setSreachListenner(null, null, onClickListener, sreachImage);
    }

    /**
     * 设置搜索写入监听
     *
     * @param textWatcher
     */
    private void setSreachListenner(TextWatcher textWatcher) {
        setSreachListenner(textWatcher, null, null, 0);
    }

    /**
     * 设置搜索图片
     *
     * @param sreachImage
     */
    public void setSreachImage(int sreachImage) {
        setSreachListenner(null, null, null, sreachImage);
    }
}


