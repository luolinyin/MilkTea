package com.xiaozhi.frame.mvp.m.urlmanage;

import android.app.Activity;
import android.content.res.XmlResourceParser;


import com.xiaozhi.frame.configuration.Configuration;
import com.xiaozhi.frame.main.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Fynner on 2016/11/19.
 * url配置处理
 */

public class UrlConfigureManage {
    private static ArrayList<UrlData> urlDatas;

    private static void makeUrlDataFromeXml(final Activity activity) {
        urlDatas = new ArrayList<UrlData>();  //这个位置不判空，目的是为了urldatas部分类被回收丢失，重新生成一个urlDatas。
        final XmlResourceParser xmlResourceParser = activity.getApplicationContext().getResources().getXml(R.xml.url_configuration);
        //开始进行xml解析操作
        int eventCode;
        try {
            eventCode = xmlResourceParser.getEventType();
            while (eventCode != XmlPullParser.END_DOCUMENT) {

                switch (eventCode) {
                    case XmlPullParser.START_DOCUMENT:

                        break;
                    case XmlPullParser.START_TAG:
                        if ("Node".equals(xmlResourceParser.getName())) {
                            UrlData urlData = new UrlData();
                            urlData.setKey(xmlResourceParser.getAttributeValue(null, "Key"));
                            urlData.setCacheTime(Long.parseLong(xmlResourceParser.getAttributeValue(null, "CacheTime")));
                            urlData.setMockClass(xmlResourceParser.getAttributeValue(null, "MockClass"));
                            urlData.setNetType(xmlResourceParser.getAttributeValue(null, "NetType"));
                            urlData.setUrl(Configuration.URL + xmlResourceParser.getAttributeValue(null, "Url"));//在配置文件URL com.xiaozhi.frame.configuration.Configuration配置
                            urlDatas.add(urlData);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                    default:
                        break;
                }
                eventCode = xmlResourceParser.next();
            }

        } catch (final XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            xmlResourceParser.close();
        }

        xmlResourceParser.close();
    }

    public static UrlData mateUrl(Activity activity,String key){
        if (urlDatas==null|| urlDatas.isEmpty()){
            makeUrlDataFromeXml(activity);
        }
            for (UrlData urlData: urlDatas){
                if (urlData.getKey().equals(key)){
                    return urlData;
                }
            }

      return null;
    }



}
