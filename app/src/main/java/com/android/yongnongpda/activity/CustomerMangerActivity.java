package com.android.yongnongpda.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.android.yongnongpda.R;
import com.android.yongnongpda.bean.CustomerBean;
import com.android.yongnongpda.utils.CommonListAdapter;
import com.android.yongnongpda.utils.ViewHolder;
import com.winsafe.mylibrary.view.AppBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by shijie.yang on 2018/2/28.
 */

public class CustomerMangerActivity extends AppBaseActivity {
    @BindView(R.id.btnQuery)
    Button btnQuery;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.btnAdd)
    Button btnAdd;
    @BindView(R.id.btnAlert)
    Button btnAlert;
    @BindView(R.id.btnDelete)
    Button btnDelete;

    private List<CustomerBean> list = new ArrayList<>();
    private int mPosition = -1;

    @Override
    protected int activityLayoutId() {
        return R.layout.activity_customer;
    }

    @Override
    protected void initView() {
        setHeader("客商维护", true, false, 0, "", null);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        getData();
    }

    private void getData() {
        CustomerBean bean;
        for (int i = 0; i < 10; i++) {
            bean = new CustomerBean();
            bean.setcNumber("001" + i);
            bean.setName("ysj");
            bean.setAddress("pudong");
            bean.setPhone("1598145622");
            list.add(bean);
        }
        recyclerView.setAdapter(new CommonListAdapter<CustomerBean>(this, R.layout.activity_customer_item, list) {
            @SuppressLint("ResourceAsColor")
            @Override
            public void convert(final ViewHolder holder, CustomerBean customerBean) {
                holder.setText(R.id.tvCNum, customerBean.getcNumber());
                holder.setText(R.id.tvName, customerBean.getName());
                holder.setText(R.id.tvAddress, customerBean.getAddress());
                final LinearLayout customerLayout = holder.getView(R.id.customerLayout);

                if (mPosition == holder.getAdapterPosition()) {
                    customerLayout.setBackgroundColor(getResources().getColor(R.color.header_title));
                } else {
                    customerLayout.setBackgroundColor(getResources().getColor(R.color.c_white));
                }
                customerLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        customerLayout.setBackgroundColor(getResources().getColor(R.color.header_title));
                        mPosition = holder.getAdapterPosition();
                        notifyDataSetChanged();
                    }
                });

            }
        });
    }


    @Override
    protected void setListener() {

    }

    @OnClick({R.id.btnAdd})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
                openActivity(CustomerMangerActivity.this, AddOrAlertCustomerActivity.class, false);
                break;

            default:
                break;

        }
    }


}
