package com.android.yongnongpda.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.android.yongnongpda.MainActivity;
import com.android.yongnongpda.R;
import com.android.yongnongpda.application.MyApp;
import com.android.yongnongpda.bean.CustomerBean;
import com.android.yongnongpda.bean.LogBean;
import com.android.yongnongpda.confing.AppConfig;
import com.android.yongnongpda.utils.AssistHelper;
import com.android.yongnongpda.utils.CommonListAdapter;
import com.android.yongnongpda.utils.OkHttpHelper;
import com.android.yongnongpda.utils.ViewHolder;
import com.winsafe.mylibrary.view.AppBaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case AppConfig.HANDLER_MESSAGE:
                    //无上传日志信息！
                     Log.i("TAG",msg.obj.toString());

                    break;
                default:
                    break;
            }
            return false;
        }
    });
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

        Map<String, String> map = new HashMap<>();
        map.put(AppConfig.USERNAME, AssistHelper.getUserName());
        map.put(AppConfig.BILLSORT, "18");
        OkHttpHelper.postData(LogUploadActivity.this, AppConfig.URL_LOG_LIST, map, handler, "加载中···");

//        LogBean bean;
//        for (int i = 0; i < 10; i++) {
//            bean = new LogBean();
//            bean.setTime("001" + i);
//            bean.setSuccess(i+"");
//            bean.setFailt("10");
//            list.add(bean);
//        }
//        recyclerView.setAdapter(new CommonListAdapter<LogBean>(this, R.layout.activity_customer_item, list) {
//            @Override
//            public void convert(ViewHolder holder, LogBean logBean) {
//                holder.setText(R.id.tvCNum, logBean.getTime());
//                holder.setText(R.id.tvName, logBean.getSuccess());
//                holder.setText(R.id.tvAddress, logBean.getFailt());
//            }
//        });
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onClick(View view) {

    }

}
