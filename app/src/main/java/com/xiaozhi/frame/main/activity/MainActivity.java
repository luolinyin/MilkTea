package com.xiaozhi.frame.main.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.xiaozhi.frame.main.R;
import com.xiaozhi.frame.main.view.MainGoalAttainmentView;
import com.xiaozhi.frame.main.view.MainReportFormView;
import com.xiaozhi.frame.main.view.MainSalesQuotaView;
import com.xiaozhi.frame.mvp.v.activity.BaseActivity;
import com.xiaozhi.frame.mvp.v.view.BaseView;
import com.xiaozhi.frame.tool.listenner.NoDoubleClickListener;

public class MainActivity extends BaseActivity {
    private View view;

    private Context context;

    private LinearLayout main_cashier;//收银
    private LinearLayout main_goods;//商品
    private LinearLayout main_member;//会员
    private LinearLayout main_report_form;//报表
    private LinearLayout main_return_goods;//退货
    private LinearLayout main_transfer;//交接
    private LinearLayout main_set_up;//设置
    private LinearLayout main_back;//退出

    //分析视图
    private LinearLayout main_analysis_diagram_view_group;
    private BaseView currentAnalysisView;

    //销售报表
    private LinearLayout main_sales_quota_table;
    private MainSalesQuotaView mainSalesQuotaView;

    //目標報表
    private LinearLayout main_goal_attainment_table;
    private MainGoalAttainmentView mainGoalAttainmentView;
    //報表
    /*private LinearLayout main_report_form_table;
    private MainReportFormView mainReportFormView;*/


    @Override
    public View initView() {
        context = this;
        view = View.inflate(context, R.layout.activity_main, null);
        if (!this.isTaskRoot()) { // 判断该Activity是不是任务空间的源Activity，“非”也就是说是被系统重新实例化出来
            // 如果你就放在launcher Activity中话，这里可以直接return了
            Intent mainIntent = getIntent();
            String action = mainIntent.getAction();
            if (mainIntent.hasCategory(Intent.CATEGORY_LAUNCHER)
                    && action.equals(Intent.ACTION_MAIN)) {
                finish();
                return view;// finish()之后该活动会继续执行后面的代码，你可以logCat验证，加return避免可能的exception
            }
        }

        //收银
        main_cashier = (LinearLayout) view.findViewById(R.id.main_cashier);
        //商品
        main_goods = (LinearLayout) view.findViewById(R.id.main_goods);
        //会员
        main_member = (LinearLayout) view.findViewById(R.id.main_member);
         //报表
        main_report_form = (LinearLayout) view.findViewById(R.id.main_report_form);
        //退货
        main_return_goods = (LinearLayout) view.findViewById(R.id.main_return_goods);
        //交接
        main_transfer = (LinearLayout) view.findViewById(R.id.main_transfer);
        //设置
        main_set_up = (LinearLayout) view.findViewById(R.id.main_set_up);
        //退出
        main_back = (LinearLayout) view.findViewById(R.id.main_back);

        //分析视图容器
        main_analysis_diagram_view_group = (LinearLayout) view.findViewById(R.id.main_analysis_diagram_view_group);

        //销售报表
        main_sales_quota_table = (LinearLayout) view.findViewById(R.id.main_sales_quota_table);
        mainSalesQuotaView = new MainSalesQuotaView(context);

        //目標达成报表
        main_goal_attainment_table = (LinearLayout) view.findViewById(R.id.main_goal_attainment_table);
        mainGoalAttainmentView = new MainGoalAttainmentView(context);

        //报表
        /*main_report_form_table = (LinearLayout) view.findViewById(R.id.main_report_form_table);
        mainReportFormView = new MainReportFormView(context);*/
        return view;

    }


    @Override
    public void initData() {
        //设置标题信息
        setTitle("主页");
        hideToHomeView();
        main_analysis_diagram_view_group.removeAllViews();
        main_analysis_diagram_view_group.addView(mainSalesQuotaView.getRootView());
        currentAnalysisView = mainSalesQuotaView;
    }


    @Override
    public void initListenner() {
        //收银
        main_cashier.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                toCashierActivity();
                finish();
            }
        });
        //商品管理
        main_goods.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                toGoodsActivity();
                finish();
            }
        });
        //会员
        main_member.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                toMemberActivity();
                finish();
            }


        });
        //报表
        main_report_form.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                toReportFormActivity();
                finish();
            }


        });

        //退款
        main_return_goods.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                toReturnGoodsActivity();
                finish();
            }
        });
        //交接
        main_transfer.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                toTransferActivity();

            }


        });
        //设置
        main_set_up.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                toSetActivity();
                finish();
            }
        });

        //退出
        main_back.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                finish();
            }
        });

        //销售达成报表
        main_sales_quota_table.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                if (currentAnalysisView != mainSalesQuotaView) {
                    main_analysis_diagram_view_group.removeAllViews();
                    main_analysis_diagram_view_group.addView(mainSalesQuotaView.getRootView());
                    currentAnalysisView = mainSalesQuotaView;
                }

            }
        });

        //目标达成报表
        main_goal_attainment_table.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                if (currentAnalysisView!=mainGoalAttainmentView){
                    main_analysis_diagram_view_group.removeAllViews();
                    main_analysis_diagram_view_group.addView(mainGoalAttainmentView.getRootView());
                    currentAnalysisView=mainGoalAttainmentView;
                }

            }
        });
       /* //报表
        main_report_form_table.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                if (currentAnalysisView != mainReportFormView) {
                    main_analysis_diagram_view_group.removeAllViews();
                    main_analysis_diagram_view_group.addView(mainReportFormView.getRootView());
                    currentAnalysisView=mainReportFormView;
                }

            }
        });*/

    }

    private void toTransferActivity() {
        Intent intent = new Intent();
        intent.setClass(context, TransferActivity.class);
        startActivity(intent);
    }

    private void toSetActivity() {
        Intent intent = new Intent();
        intent.setClass(context, SetActivity.class);
        startActivity(intent);
    }

    private void toSignActivity() {
        Intent intent = new Intent();
        intent.setClass(context, SignActivity.class);
        startActivity(intent);
    }

    private void toReturnGoodsActivity() {
        Intent intent = new Intent();
        intent.setClass(context, ReturnGoodsActivity.class);
        startActivity(intent);
    }

    private void toMemberActivity() {
        Intent intent = new Intent();
        intent.setClass(context, MenberActivity.class);
        startActivity(intent);
    }

    private  void toReportFormActivity(){
        Intent intent = new Intent();
        intent.setClass(context, ReportFormActivity.class);
        startActivity(intent);
    }
    private void toCashierActivity() {
        Intent intent = new Intent();
        intent.setClass(context, CashierActivity.class);
        startActivity(intent);

    }

    private void toGoodsActivity() {
        Intent intent = new Intent();
        intent.setClass(context, GoodsActivity.class);
        startActivity(intent);

    }


}
