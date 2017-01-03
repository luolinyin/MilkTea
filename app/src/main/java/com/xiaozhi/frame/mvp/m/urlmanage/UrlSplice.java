package com.xiaozhi.frame.mvp.m.urlmanage;


import com.xiaozhi.frame.mvp.m.HttpRequestParameter;

import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fynner on 2016/11/21.
 * 该类负责url拼接工作
 */

public class UrlSplice {

    public static String spliceUrlGet(String url, ArrayList<HttpRequestParameter> parameters) {
        if (url == null || url.equals("")) {
            return url;
        }

        StringBuffer sbParameter = new StringBuffer();

        if (parameters != null && !parameters.isEmpty()) {
            parameters = sortKey(parameters);

            for (final HttpRequestParameter p : parameters) {
                try {
                    if (sbParameter.length() == 0) {
                        sbParameter.append(p.getKey() + "="
                                + URLEncoder.encode(p.getValue(), "UTF-8"));
                    } else {
                        sbParameter.append("&" + p.getKey() + "="
                                + URLEncoder.encode(p.getValue(), "UTF-8"));
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            return url + "?" + sbParameter.toString();
        }
        return url;
    }

    public static List<BasicNameValuePair> spliceUrlPost(ArrayList<HttpRequestParameter> parameters) {
        List<BasicNameValuePair> basicParameters=new ArrayList<BasicNameValuePair>();
        for (HttpRequestParameter parameter: parameters){
            basicParameters.add(new BasicNameValuePair(parameter.getKey(),parameter.getValue()));
        }
        return basicParameters;
    }

    private static ArrayList<HttpRequestParameter> sortKey(ArrayList<HttpRequestParameter> parameters) {
        ArrayList<HttpRequestParameter> p = parameters;
        int lenght = p.size();
        for (int i = 0; i < lenght - 1; i++) {
            for (int j = i + 1; j < lenght; j++) {
                if (p.get(i).compareTo(p.get(j)) > 0) {

                    String key = p.get(i).getKey();
                    String value = p.get(i).getValue();

                    p.get(i).setKey(p.get(j).getKey());
                    p.get(i).setValue(p.get(j).getValue());
                    p.get(j).setKey(key);
                    p.get(j).setValue(value);
                }
            }

        }
        return p;
    }



}
