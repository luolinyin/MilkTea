package com.xiaozhi.frame.mvp.m;

import android.os.Handler;


import com.xiaozhi.frame.configuration.Configuration;
import com.xiaozhi.frame.mvp.m.callbackmanage.HttpRequestCallBack;
import com.xiaozhi.frame.mvp.m.cookiemanage.CookieManage;
import com.xiaozhi.frame.mvp.m.urlmanage.UrlData;
import com.xiaozhi.frame.mvp.m.urlmanage.UrlSplice;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 * Created by Fynner on 2016/11/19.
 */

public class HttpRequeste implements Runnable {

    private static final String REQUEST_GET = "get";
    private static final String REQUEST_POST = "post";

    private UrlData urlData;
    private String url;
    private ArrayList<HttpRequestParameter> parameters;

    private HttpRequestCallBack callBack;
    private Handler handler;

    private HttpUriRequest request = null;
    private HttpResponse response = null;
    private DefaultHttpClient httpClient;
    private String strResponse;

    private HashMap<String, String> headers;//请求头
    private static final String ACCEPT_CHARSET = "Accept-Charset";
    private static final String USER_AGENT = "User-Agent";
    private static final String ACCEPT_ENCODING = "Accept-Encoding";

    private CookieManage cookieManage;

    private HttpRequeste httpRequeste;

    //终止请求回调
    public boolean isCallBack = true;

    public HttpRequeste(UrlData urlData, ArrayList<HttpRequestParameter> parameters, HttpRequestCallBack callBack) {

        //初始化处理数据
        this.urlData = urlData;
        this.parameters = parameters;
        this.callBack = callBack;

        url = this.urlData.getUrl();
        headers = new HashMap<String, String>();

        handler = new Handler();

        if (httpClient == null) {
            httpClient = new DefaultHttpClient();
        }

        if (Configuration.IS_COOKIE) {
            if (cookieManage == null) {
                cookieManage = new CookieManage();
            }
        }
        httpRequeste = this;
    }

    @Override
    public void run() {

        String newUrl;//拼接后的url

        if (urlData.getNetType().equals(REQUEST_GET)) {
            newUrl = UrlSplice.spliceUrlGet(url, parameters);//拼接url
            request = new HttpGet(newUrl);
        } else if (urlData.getNetType().equals(REQUEST_POST)) {
            request = new HttpPost(url);

            List<BasicNameValuePair> basicParameters = UrlSplice.spliceUrlPost(parameters);
            try {
                ((HttpPost) request).setEntity(new UrlEncodedFormEntity(basicParameters, HTTP.UTF_8));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        } else {
            return;
        }
        request.getParams().setParameter(
                CoreConnectionPNames.CONNECTION_TIMEOUT, 30000);
        request.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 30000);
        //添加相关请求头信息
        setHttpHeaders(request);
        //添加cookie
        if (Configuration.IS_COOKIE) {
            httpClient = cookieManage.addCookie(httpClient);
        }
        //发送请求
        try {
            response = httpClient.execute(request);
        } catch (IOException e) {
            handleHttpfail("网络异常");
        }
        //请求回调数据处理。
        if (response != null) {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                if (callBack == null) return;//说明不需要回调
                strResponse = manageResponse();
            } else {
                handleHttpfail("网络异常:" + statusCode);
                return;
            }

            //预处理数据（1.错误异常数据。2.cookie过期处理回调）

            //请求成功回调
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (isCallBack) {
                        callBack.httpSuccess(httpRequeste, strResponse);
                    }

                }
            });

            //保存cookie
            if (Configuration.IS_COOKIE) {
                cookieManage.saveCookie(httpClient);
            }

        } else {
            handleHttpfail("网络异常:");
            return;
        }

    }

    private String manageResponse() {

        final ByteArrayOutputStream content = new ByteArrayOutputStream();

        String responseSt = "";
        try {
            // 根据是否支持gzip  并且解壓
            if ((response.getEntity().getContentEncoding() != null)
                    && (response.getEntity().getContentEncoding()
                    .getValue() != null) && response.getEntity().getContentEncoding()
                    .getValue().contains("gzip")) {

                final InputStream in;
                in = response.getEntity().getContent();
                final InputStream is = new GZIPInputStream(in);
                responseSt = HttpRequeste.inputStreamToString(is);
                is.close();
                //base64解密


            } else {
                response.getEntity().writeTo(content);
                responseSt = new String(content.toByteArray(), "UTF-8").trim();
            }
        } catch (IOException e) {
            handleHttpfail("网络异常:");
        }

        return responseSt;
    }


    private static String inputStreamToString(final InputStream is) throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = -1;
        while ((i = is.read()) != -1) {
            baos.write(i);
        }
        return baos.toString();
    }

    private void handleHttpfail(final String failSt) {
        if (callBack != null) {
            if (isCallBack) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.httpFail(httpRequeste, failSt);
                    }
                });
            }


        }
    }


    private void setHttpHeaders(final HttpUriRequest httpMessage) {
        headers.clear();
        headers.put(ACCEPT_CHARSET, "UTF-8,*");
        headers.put(USER_AGENT, "Young Heart Android App ");
        headers.put(ACCEPT_ENCODING, "gzip");//gzip压缩

        if ((httpMessage != null) && (headers != null)) {
            for (final Map.Entry<String, String> entry : headers.entrySet())
                if (entry.getKey() != null) {
                    httpMessage.addHeader(entry.getKey(), entry.getValue());
                }
        }
    }

    /*接口 獲取該請求躰*/
    public HttpUriRequest getUriRequest() {

        return request;
    }

}

