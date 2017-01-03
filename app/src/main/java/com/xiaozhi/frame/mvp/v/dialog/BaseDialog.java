package com.xiaozhi.frame.mvp.v.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.xiaozhi.frame.main.R;


/*
 *小智工具包 自定义dialog
 *
 */
public abstract class BaseDialog extends Dialog {

	public static final int OUT_TO = -1;

	private Context context;
	private BaseDialog mDialog;

	private View mRootView;

	private Window window = null;
	private ImageLoader mImageLoader;

	private boolean isInit=false;

	public BaseDialog(Context context) {
		super(context);
		this.context = context;
		// 去标题
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		mDialog = this;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		init();
	}

	/**
	 * @param
	 *  	 ，设置弹出View
	 */
	protected abstract View setDialogContentView();

	/**
	 * 初始化View
	 * 
	 * @param
	 */
	protected abstract void initView();

	public void init() {
		mRootView = setDialogContentView();
		if (mRootView == null) {
			// new ToastView(context, "视图丢失").show();
			throw new NullPointerException("试图丢失");
			// throw new RuntimeException("试图丢失");
			// throw new IllegalArgumentException("试图丢失");
		}
		setContentView(mRootView);

		if (window == null)
			window = getWindow();

		window.setBackgroundDrawableResource(R.color.base_dailog_widow_bg); // 设置对话框背景为透明

		window.setWindowAnimations(R.style.dialog_anim_style);
		setWindowDispalay(0.8, OUT_TO);// 宽度设置为屏幕的0.8
		initView();
		initListener();
		isInit=true;
	}

	protected abstract void initListener();

	/*
	 * 配置dialog的宽高比例
	 */
	public void setWindowDispalay(double wPercent, double hPercent) {
		if (window == null)
			window = getWindow();
		WindowManager.LayoutParams lp = window.getAttributes();
		DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
		if (wPercent != OUT_TO) {
			lp.width = (int) (d.widthPixels * wPercent);
		}

		if (hPercent != OUT_TO) {
			lp.height = (int) (d.heightPixels * hPercent);
		}
		window.setAttributes(lp);
	}

	/*
	 * 配置dialog的弹出位置 例如：setDialogGravity（Gravity.TOP）;
	 */
	public void setDialogGravity(int gravity) {
		if (window == null)
			window = getWindow();

		window.setGravity(gravity);
	}

	public boolean getIsFinishInit(){
		return isInit;
	}
}
