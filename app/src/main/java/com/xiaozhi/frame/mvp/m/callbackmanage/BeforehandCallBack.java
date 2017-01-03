package com.xiaozhi.frame.mvp.m.callbackmanage;

/**
 * Created by Fynner on 2016/12/7.
 * 预处理返回体
 * 该项目暂不使用
 */

 class BeforehandCallBack {
    private boolean error;
    private int errorType;	//404为Cookie失效
    private String errorMessage;
    private String result;

    protected boolean isError() {
        return error;
    }

    protected String getErrorMessage() {
        return errorMessage;
    }

    protected int getErrorType() {
        return errorType;
    }

    protected String getResult() {
        return result;
    }
}
