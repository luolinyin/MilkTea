package com.xiaozhi.frame.manage;

import android.app.Activity;
import android.content.Context;

import java.util.List;

/**
 * Created by Fynner on 2016/11/18.
 * 1.针对Activity进行资源管理
 * 2.定位在栈的位置
 * 待完善
 */

public class ActivityManage {
    private static List<Context> activityList;

    /**
    * 方法描述
    * 添加activity standard、singleTop、singleTask、singleInstance五种模式
     * 1.standard 正常模式，新建activity，将该新activity放在栈顶
     * 2.singleTop 如果已经在栈顶就不会在新建实例。如果不存在栈顶则会新建一个实例和standard效果一样。
     * 3.singleTask 一个任务栈中如果已经存在实例则不会再创建，并且将在这个实例之上的实例通通扔出栈。如果没有存在该实例，则会创建新的实力 跟standard一样
     * 4.singleInstance 两个程序共享一个实例，例如A 栈 和被singleTask指定开启一个新的栈中 想要公用一个实例，那么A栈中的实例也可以用在新的栈中共用。
     */
    public static void addActivity(Context context){

        activityList.add(context);
    }


}
