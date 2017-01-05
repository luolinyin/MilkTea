package com.xiaozhi.frame.main.activity;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.xiaozhi.frame.main.R;
import com.xiaozhi.frame.main.been.setbeen.SetItemMenuData;
import com.xiaozhi.frame.main.been.setbeen.SetMainMenuData;
import com.xiaozhi.frame.main.been.setbeen.SetMenuData;
import com.xiaozhi.frame.main.view.SetConnectView;
import com.xiaozhi.frame.main.view.SetInformationView;
import com.xiaozhi.frame.main.view.SetLeftItemMenuView;
import com.xiaozhi.frame.main.view.SetLeftMainMenuView;
import com.xiaozhi.frame.main.view.SetLeftMenuView;
import com.xiaozhi.frame.main.view.SetPaymentView;
import com.xiaozhi.frame.main.view.SetRightView;
import com.xiaozhi.frame.main.view.SetSoftwareView;
import com.xiaozhi.frame.main.view.SetStaffView;
import com.xiaozhi.frame.main.view.SetTickeyView;
import com.xiaozhi.frame.mvp.v.activity.BaseActivity;
import com.xiaozhi.frame.mvp.v.view.BaseView;

import java.util.ArrayList;

/**
 * Created by Fynner on 2016/12/31.
 */
public class SetActivity extends BaseActivity {
    private View view;
    private Context context;

    //菜单数据
    private SetMenuData setMenuData;

    //左侧 菜单
    private LinearLayout set_left_menu;
    private SetLeftMenuView setLeftMenuView;
    private BaseView currentLeftView;

    //右侧
    private LinearLayout set_right_group;
    private SetRightView setRightView;


    @Override
    public View initView() {
        context = this;
        view = View.inflate(context, R.layout.activity_set, null);
        //左侧菜单
        set_left_menu = (LinearLayout) view.findViewById(R.id.set_left_menu);

        //右侧
        set_right_group = (LinearLayout) view.findViewById(R.id.set_right_group);

        return view;
    }


    @Override
    public void initData() {
        setTitle("设置");
        //初始化菜单数据
        addMenuInfo();
        //左侧菜单
        setLeftMenuView = new SetLeftMenuView(context, setMenuData);
        set_left_menu.addView(setLeftMenuView.getRootView());

        //右侧菜单
        setRightView = new SetRightView(context);
        set_right_group.addView(setRightView.getRootView());
    }

    @Override
    public void initListenner() {

    }

    //添加菜单数据
    private void addMenuInfo() {
        ArrayList<SetMainMenuData> menuDatas = new ArrayList<SetMainMenuData>();

        ArrayList<SetItemMenuData> shopDatas = new ArrayList<SetItemMenuData>();
        SetItemMenuData information = new SetItemMenuData("账户信息", new SetInformationView(context), new SetLeftItemMenuView(context, "账户信息", leftItemMenuListenner));
        SetItemMenuData staff = new SetItemMenuData("员工权限", new SetStaffView(context), new SetLeftItemMenuView(context, "员工权限", leftItemMenuListenner));
        shopDatas.add(information);
        shopDatas.add(staff);
        SetMainMenuData shopMenu = new SetMainMenuData("店铺设置", new SetLeftMainMenuView(context, "店铺设置", leftMainMenuListenner), shopDatas);
        menuDatas.add(shopMenu);

        ArrayList<SetItemMenuData> deviceConnDatas = new ArrayList<SetItemMenuData>();
        SetMainMenuData deviceConnMenu = new SetMainMenuData("设备连接", new SetLeftMainMenuView(context, "设备连接", leftMainMenuListenner), deviceConnDatas);
        menuDatas.add(deviceConnMenu);

        ArrayList<SetItemMenuData> setData = new ArrayList<SetItemMenuData>();
        SetItemMenuData printSetup = new SetItemMenuData("打印设置", new SetConnectView(context), new SetLeftItemMenuView(context, "打印设置", leftItemMenuListenner));
        SetItemMenuData cashPrinter = new SetItemMenuData("收银小票", new SetTickeyView(context), new SetLeftItemMenuView(context, "收银小票", leftItemMenuListenner));
        SetItemMenuData kitchenPrinter = new SetItemMenuData("厨房小票", new SetPaymentView(context), new SetLeftItemMenuView(context, "厨房小票", leftItemMenuListenner));
        SetItemMenuData labelPrinter = new SetItemMenuData("标签/条码", new SetSoftwareView(context), new SetLeftItemMenuView(context, "标签/条码", leftItemMenuListenner));
        setData.add(printSetup);
        setData.add(cashPrinter);
        setData.add(kitchenPrinter);
        setData.add(labelPrinter);
        SetMainMenuData setMenu = new SetMainMenuData("小票设置", new SetLeftMainMenuView(context, "小票设置", leftMainMenuListenner), setData);
        menuDatas.add(setMenu);

        ArrayList<SetItemMenuData> paymentDatas = new ArrayList<SetItemMenuData>();
        SetMainMenuData paymentMenu = new SetMainMenuData("支付方式", new SetLeftMainMenuView(context, "支付方式", leftMainMenuListenner), paymentDatas);
        menuDatas.add(paymentMenu);

        ArrayList<SetItemMenuData> softwareDownloadDatas = new ArrayList<SetItemMenuData>();
        SetMainMenuData softwareDownloadMenu = new SetMainMenuData("软件下载", new SetLeftMainMenuView(context, "软件下载", leftMainMenuListenner), softwareDownloadDatas);
        menuDatas.add(softwareDownloadMenu);

        setMenuData = new SetMenuData(menuDatas);
    }

    /**
     * 主菜单监听
     */
    private SetLeftMainMenuView.OnLeftMainMenuListenner leftMainMenuListenner = new SetLeftMainMenuView.OnLeftMainMenuListenner() {
        @Override
        public void onMainGroupViewClick(BaseView mainMenuView) {

            for (SetMainMenuData mainMenuData : setMenuData.getLeftSetMainMenuDatas()) {
                if (mainMenuData.getMainView() == mainMenuView) {
                    setMenuData.controlLeftViewVisible(mainMenuData);
                }
            }
        }
    };

    /**
     * 子菜单监听
     */
    private SetLeftItemMenuView.OnLeftItemMenuListenner leftItemMenuListenner = new SetLeftItemMenuView.OnLeftItemMenuListenner() {
        @Override
        public void onItemGroupViewClick(BaseView itemMenuView) {
            if (itemMenuView == currentLeftView) {
                return;
            }
            for (SetMainMenuData mainMenuData : setMenuData.getLeftSetMainMenuDatas()) {
                for (SetItemMenuData itemMenuData : mainMenuData.getItemMenus()) {
                    if (itemMenuData.getLeftView() == itemMenuView) {
                        setRightView.changeRightView(itemMenuData.getRightView());
                        currentLeftView = itemMenuView;
                    }
                }
            }

        }
    };

}