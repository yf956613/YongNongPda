package com.android.yongnongpda.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.android.yongnongpda.R;
import com.android.yongnongpda.bean.CustomerBean;
import com.android.yongnongpda.bean.LogBean;
import com.android.yongnongpda.utils.CommonListAdapter;
import com.android.yongnongpda.utils.ViewHolder;
import com.winsafe.mylibrary.view.AppBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shijie.yang on 2018/2/27.
 */

public class LogUploadActivity extends AppBaseActivity {
    @BindView(R.id.btnQuery)
    Button btnQuery;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private List<LogBean> list = new ArrayList<>();
    @Override
    protected int activityLayoutId() {
        return R.layout.activity_log_upload;
    }

    @Override
    protected void initView() {
        setHeader("上传日志", true, false, 0, "", null);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        getData();
    }

    private void getData(){
        LogBean bean;
        for (int i = 0; i < 10; i++) {
            bean = new LogBean();
            bean.setTime("001" + i);
            bean.setSuccess(i+"");
            bean.setFailt("10");
            list.add(bean);
        }
        recyclerView.setAdapter(new CommonListAdapter<LogBean>(this, R.layout.activity_customer_item, list) {
            @Override
            public void convert(ViewHolder holder, LogBean logBean) {
                holder.setText(R.id.tvCNum, logBean.getTime());
                holder.setText(R.id.tvName, logBean.getSuccess());
                holder.setText(R.id.tvAddress, logBean.getFailt());
            }
        });
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onClick(View view) {

    }

}
