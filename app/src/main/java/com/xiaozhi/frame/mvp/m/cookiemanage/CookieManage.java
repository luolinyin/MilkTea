package com.xiaozhi.frame.mvp.m.cookiemanage;



import com.xiaozhi.frame.configuration.Configuration;
import com.xiaozhi.frame.tool.file.FileManage;

import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fynner on 2016/11/23.
 */
public class CookieManage {
    private String COOKIE_PATH = Configuration.COOKIE_PHAT;

    /**
     * cookie列表保存到本地
     *
     * @return
     */
    public synchronized void saveCookie(DefaultHttpClient httpClient) {
        // 获取本次访问的cookie
        final List<Cookie> cookies = httpClient.getCookieStore().getCookies();
        // 将普通cookie转换为可序列化的cookie
        List<SerializableCookie> serializableCookies = null;

        if ((cookies != null) && (cookies.size() > 0)) {
            serializableCookies = new ArrayList<SerializableCookie>();
            for (final Cookie c : cookies) {
                serializableCookies.add(new SerializableCookie(c));
            }
        }

        FileManage.saveObject(COOKIE_PATH, serializableCookies);
    }

    /**
     * 从本地获取cookie列表
     *
     * @return
     */
    public DefaultHttpClient addCookie(DefaultHttpClient httpClient) {
        List<SerializableCookie> cookieList = null;
        Object cookieObj = FileManage.restoreObject(COOKIE_PATH);

        if (cookieObj != null) {
            cookieList = (ArrayList<SerializableCookie>) cookieObj;
        }

        if ((cookieList != null) && (cookieList.size() > 0)) {
            final BasicCookieStore cs = new BasicCookieStore();
            cs.addCookies(cookieList.toArray(new Cookie[]{}));

            for (int i = 0; i < cookieList.size(); i++) {
            }
            httpClient.setCookieStore(cs);
        } else {
            httpClient.setCookieStore(null);
        }
        return httpClient;
    }
}
