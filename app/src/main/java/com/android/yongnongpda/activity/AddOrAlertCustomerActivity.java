package com.android.yongnongpda.activity;

import android.view.View;

import com.android.yongnongpda.R;
import com.winsafe.mylibrary.view.AppBaseActivity;

/**
 * Created by shijie.yang on 2018/2/28.
 */

public class AddOrAlertCustomerActivity extends AppBaseActivity {
    @Override
    protected int activityLayoutId() {
        return R.layout.activity_addoralert_customer;
    }

    @Override
    protected void initView() {
        setHeader("客商新增", true, false, 0, "", null);

    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onClick(View view) {

    }
}
