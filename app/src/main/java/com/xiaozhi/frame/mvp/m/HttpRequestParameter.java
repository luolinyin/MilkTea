package com.xiaozhi.frame.mvp.m;

import java.io.Serializable;

/**
 * Created by Fynner on 2016/11/21.
 * Comparable接口对序列化对象进行排序。
 */

public class HttpRequestParameter implements Serializable ,Comparable<Object>{
    private static final Long serializationUID=1000000000000000001L;

    private String key;
    private String value;

    public HttpRequestParameter(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public int compareTo(Object another) {
        /*
        * 值比较
        * */
        HttpRequestParameter anotherParameter=(HttpRequestParameter)another;
        int compare=key.compareTo(anotherParameter.key);
        if (compare==0){
            compare=value.compareTo(value);
        }

        return compare;
    }

    @Override
    public boolean equals(Object another) {
        if (another==null)
            return false;
        if (this==another)
            return true;
        if (another instanceof HttpRequestParameter){
            HttpRequestParameter anotherParameter=(HttpRequestParameter) another;
            return key.equals(anotherParameter.key)&&value.equals(anotherParameter.value);
        }
        return false;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {

        return key;
    }

    public String getValue() {
        return value;
    }
}
