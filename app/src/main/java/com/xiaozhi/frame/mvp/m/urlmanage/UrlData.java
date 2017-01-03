package com.xiaozhi.frame.mvp.m.urlmanage;

/**
 * Created by Fynner on 2016/11/19.
 * url数据封装对象
 */

public class UrlData {
    private long mCacheTime;   //缓存时间
    private String mKey;        //key url名称 一个key对应一个url
    private String mMockClass; //请求静态数据
    private String mNetType;    //请求方式 post get
    private String mUrl;        //请求url

    public void setCacheTime(long cacheTime) {
        this.mCacheTime = cacheTime;
    }

    public void setKey(String key) {
        this.mKey = key;
    }

    public void setMockClass(String mockClass) {
        this.mMockClass = mockClass;
    }

    public void setNetType(String netType) {
        this.mNetType = netType;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public long getCacheTime() {

        return mCacheTime;
    }

    public String getKey() {
        return mKey;
    }

    public String getMockClass() {
        return mMockClass;
    }

    public String getNetType() {
        return mNetType;
    }

    public String getUrl() {
        return mUrl;
    }
}
