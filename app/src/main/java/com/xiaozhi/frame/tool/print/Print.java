package com.xiaozhi.frame.tool.print;

import com.xiaozhi.frame.configuration.Configuration;

/**
 * Created by lenovo on 2016/11/18.
 * 打印共有类
 * 通过Configuration配置是否进行打印
 * */

public class Print {
    public static  void println(String printString){
        if (Configuration.IS_PRINT){
            System.out.println(printString);
        }
    }

}
