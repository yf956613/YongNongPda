package com.android.yongnongpda.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.yongnongpda.MainActivity;
import com.android.yongnongpda.R;
import com.android.yongnongpda.application.MyApp;
import com.android.yongnongpda.confing.AppConfig;
import com.android.yongnongpda.utils.AssistHelper;
import com.android.yongnongpda.utils.OkHttpHelper;
import com.winsafe.mylibrary.utils.StringHelper;
import com.winsafe.mylibrary.view.AppBaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by shijie.yang on 2018/2/28.
 */

public class AddOrAlertCustomerActivity extends AppBaseActivity {
    @BindView(R.id.etCName)
    EditText etCName;
    @BindView(R.id.etCAddress)
    EditText etCAddress;
    @BindView(R.id.etLName)
    EditText etLName;
    @BindView(R.id.etLPhone)
    EditText etLPhone;
    @BindView(R.id.btnOperate)
    Button btnOperate;

    private Bundle bundle;
    private String type;
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
                    Toast.makeText(AddOrAlertCustomerActivity.this, "新增成功", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                default:
                    break;
            }
            return false;
        }
    });

    @Override
    protected int activityLayoutId() {
        return R.layout.activity_addoralert_customer;
    }

    @Override
    protected void initView() {
        bundle = getIntent().getExtras();
        type = bundle.getString("type");
        if ("2".equals(type)) {
            setHeader("客商新增", true, false, 0, "", null);
        } else if ("3".equals(type)) {
            setHeader("客商修改", true, false, 0, "", null);
            CID = bundle.getString("CID");
            CName = bundle.getString("CName");
            CAddress = bundle.getString("CAddress");
            LID = bundle.getString("LID");
            LName = bundle.getString("LName");
            LPhone = bundle.getString("LPhone");

            etCAddress.setText(CAddress);
            etCName.setText(CName);
            etLName.setText(LName);
            etLPhone.setText(LPhone);
        }
    }

    @Override
    protected void setListener() {

    }

    @OnClick({R.id.btnOperate})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnOperate:
                String cName = etCName.getText().toString().trim();
                String cAddress = etCAddress.getText().toString().trim();
                String lName = etLName.getText().toString().trim();
                String lPhone = etLPhone.getText().toString().trim();
                if (StringHelper.isNullOrEmpty(cName)) {
                    Toast.makeText(this, "请输入客商名称", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (StringHelper.isNullOrEmpty(cAddress)) {
                    Toast.makeText(this, "请输入地址", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (StringHelper.isNullOrEmpty(lName)) {
                    Toast.makeText(this, "请输入联系人", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (StringHelper.isNullOrEmpty(lPhone)) {
                    Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
                    return;
                }
                Map<String, String> map = new HashMap<>();
                map.put(AppConfig.USERNAME, AssistHelper.getUserName());
                if ("2".equals(type)) {//新增
                    map.put(AppConfig.TYPE, "2");
                    map.put("Cname", cName);
                    map.put("Cadress", cAddress);
                    map.put("Lname", lName);
                    map.put("Lphone", lPhone);
                    OkHttpHelper.post(this, AppConfig.URL_CUSTOMER, map, handler,AppConfig.HANDLER_MESSAGE, "新增中···");
                } else {
                    map.put(AppConfig.TYPE, "3");
                    map.put("CID", CID);
                    map.put("Cname", cName);
                    map.put("Cadress", cAddress);
                    map.put("LID", LID);
                    map.put("Lname", lName);
                    map.put("Lphone", lPhone);
                    OkHttpHelper.post(this, AppConfig.URL_CUSTOMER, map, handler,AppConfig.HANDLER_MESSAGE, "修改中···");
                }
                break;

            default:
                break;

        }
    }

}
