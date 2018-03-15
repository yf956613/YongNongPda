package com.android.yongnongpda.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.yongnongpda.MainActivity;
import com.android.yongnongpda.R;
import com.android.yongnongpda.application.MyApp;
import com.android.yongnongpda.bean.CustomerBean;
import com.android.yongnongpda.confing.AppConfig;
import com.android.yongnongpda.utils.AssistHelper;
import com.android.yongnongpda.utils.CodeHelper;
import com.android.yongnongpda.utils.CommonListAdapter;
import com.android.yongnongpda.utils.OkHttpHelper;
import com.android.yongnongpda.utils.ViewHolder;
import com.winsafe.mylibrary.view.AppBaseActivity;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by shijie.yang on 2018/2/28.
 */

public class CustomerMangerActivity extends AppBaseActivity {
    @BindView(R.id.btnQuery)
    Button btnQuery;
    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.btnAdd)
    Button btnAdd;
    @BindView(R.id.btnAlert)
    Button btnAlert;
    @BindView(R.id.btnDelete)
    Button btnDelete;

    private List<CustomerBean> list = new ArrayList<>();
    private List<CustomerBean> searchList = new ArrayList<>();
    private int mPosition = -1;

    private String CID = "";//客商编号
    private String CName = "";//客商名称
    private String CAddress = "";//客商地址
    private String LID = "";//联系人编号
    private String LName = "";//联系人名称
    private String LPhone = "";//联系人手机

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case AppConfig.HANDLER_MESSAGE:
                    String[] result = msg.obj.toString().split(";");
                    CustomerBean bean;
                    list.clear();
                    for (int i = 0; i < result.length; i++) {
                        String[] cData = result[i].split(",");
                        bean = new CustomerBean();
                        bean.setCID(cData[0]); //客商编号
                        bean.setCName(cData[1]);//客商名称
                        bean.setCAddress(cData[2]);//地址
                        bean.setLID(cData[3]);//联系人编号
                        bean.setLName(cData[4]);//联系人
                        if (cData.length > 5) {
                            bean.setLPhone(cData[5]);//电话
                        } else {
                            bean.setLPhone("");//电话
                        }
                        list.add(bean);
                    }
                    showList(list);
                    break;
                case AppConfig.HANDLER_MESSAGE_DELETE:
                    Toast.makeText(CustomerMangerActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    getData();
                    break;
                default:
                    break;
            }
            return false;
        }
    });

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
        mPosition = -1;
        Map<String, String> map = new HashMap<>();
        map.put(AppConfig.USERNAME, AssistHelper.getUserName());
        map.put(AppConfig.TYPE, "1");
        OkHttpHelper.postData(CustomerMangerActivity.this, AppConfig.URL_CUSTOMER, map, handler, "加载中···");
    }

    private void showList(List<CustomerBean> ls) {
        recyclerView.setAdapter(new CommonListAdapter<CustomerBean>(this, R.layout.activity_customer_item, ls) {
            @SuppressLint("ResourceAsColor")
            @Override
            public void convert(final ViewHolder holder, final CustomerBean customerBean) {
                holder.setText(R.id.tvCNum, customerBean.getCID());
                holder.setText(R.id.tvName, customerBean.getCName());
                holder.setText(R.id.tvAddress, customerBean.getCAddress());
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

                        CID = customerBean.getCID();
                        CName = customerBean.getCName();
                        CAddress = customerBean.getCAddress();
                        LID = customerBean.getLID();
                        LName = customerBean.getLName();
                        LPhone = customerBean.getLPhone();
                    }
                });

            }
        });
    }

    @Override
    protected void setListener() {

    }

    @OnClick({R.id.btnQuery,R.id.btnAdd, R.id.btnAlert, R.id.btnDelete})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnQuery://查询
                mPosition = -1;//初始化选中
                String mSearch=etSearch.getText().toString().trim();
                CustomerBean bean;
                searchList.clear();
                for (int i = 0; i <list.size() ; i++) {
                    bean=new CustomerBean();
                    if (list.get(i).getCID().contains(mSearch)) {
                        bean.setCID(list.get(i).getCID()); //客商编号
                        bean.setCName(list.get(i).getCName());//客商名称
                        bean.setCAddress(list.get(i).getCAddress());//地址
                        bean.setLID(list.get(i).getLID());//联系人编号
                        bean.setLName(list.get(i).getLName());//联系人
                        bean.setLPhone(list.get(i).getLPhone());//电话
                        searchList.add(bean);
                    }
                }
                showList(searchList);
                break;
            case R.id.btnAdd://新增
                Bundle bundle = new Bundle();
                bundle.putString("type", "2");
                openActivity(CustomerMangerActivity.this, AddOrAlertCustomerActivity.class, bundle, false);
                break;
            case R.id.btnAlert://修改
                Bundle bundles = new Bundle();
                bundles.putString("type", "3");
                setBundle(bundles);
                openActivity(CustomerMangerActivity.this, AddOrAlertCustomerActivity.class, bundles, false);
                break;
            case R.id.btnDelete://删除
                final Dialog dialog = new AlertDialog.Builder(this)
                        .setTitle("提示：")
                        .setMessage("确定要删除此客商信息吗?")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                Map<String, String> map = new HashMap<>();
                                map.put(AppConfig.USERNAME, AssistHelper.getUserName());
                                map.put(AppConfig.TYPE, "4");
                                map.put("CID", CID);
                                map.put("LID", LID);
                                OkHttpHelper.post(CustomerMangerActivity.this, AppConfig.URL_CUSTOMER, map, handler, AppConfig.HANDLER_MESSAGE_DELETE, "删除中···");
                                dialog.dismiss();
                            }
                        }).create();


                dialog.setCancelable(true);
                dialog.show();

                break;
            default:
                break;

        }
    }

    private Bundle setBundle(Bundle bundles) {
        bundles.putString("CID", CID);
        bundles.putString("CName", CName);
        bundles.putString("CAddress", CAddress);
        bundles.putString("LID", LID);
        bundles.putString("LName", LName);
        bundles.putString("LPhone", LPhone);
        return bundles;
    }

}
