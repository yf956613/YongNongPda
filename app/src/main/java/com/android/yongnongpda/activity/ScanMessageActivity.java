package com.android.yongnongpda.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.yongnongpda.R;
import com.android.yongnongpda.application.MyApp;
import com.android.yongnongpda.bean.CodeBean;
import com.android.yongnongpda.scan.ScanResultReceiver;
import com.android.yongnongpda.utils.AssistHelper;
import com.android.yongnongpda.utils.CodeHelper;
import com.android.yongnongpda.utils.UploadFileInfoHelpter;
import com.winsafe.mylibrary.scan.Scanner;
import com.winsafe.mylibrary.utils.DateTimeHelper;
import com.winsafe.mylibrary.view.AppBaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by shijie.yang on 2018/2/28.
 */

public class ScanMessageActivity extends AppBaseActivity {
    @BindView(R.id.tvSendOrgan)
    TextView tvSendOrgan;
    @BindView(R.id.tvSendWarehouse)
    TextView tvSendWarehouse;
    @BindView(R.id.tvReceiveOrgan)
    TextView tvReceiveOrgan;
    @BindView(R.id.tvReceiveWarehouse)
    TextView tvReceiveWarehouse;
    @BindView(R.id.tvProduct)
    TextView tvProduct;
    @BindView(R.id.tvPlanNum)
    TextView tvPlanNum;
    @BindView(R.id.tvBoxNum)
    TextView tvBoxNum;
    @BindView(R.id.tvTuoNum)
    TextView tvTuoNum;
    @BindView(R.id.btnUpload)
    Button btnUpload;
    @BindView(R.id.etCode)
    EditText etCode;

    private Bundle bundle;
    private String productId = "", sendOrganId = "", receiveOrganId = "", sendWarehouseId = "", receiveWarehouseId = "";
    private int planBoxNum, planTuoNum; //计划数量
    private int scannedBoxNum = 0, scannedTuoNum = 0;//已扫数量

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case ScanResultReceiver.READ_RESULT:
                    String result = CodeHelper.resultCode(message.obj.toString());
                    //code rule
                    if (!CodeHelper.codeRules(ScanMessageActivity.this, result)) return false;
                    if (CodeHelper.isScannedCode(result)) {

                        if (result.substring(0, 1).equals("Y")) {
                            ++scannedBoxNum;
                        } else {
                            ++scannedTuoNum;
                        }
                        if (scannedBoxNum > planBoxNum || scannedTuoNum > planTuoNum) {
                            MyApp.playSound(1);
                            Toast.makeText(ScanMessageActivity.this, "已超过计划扫描数量", Toast.LENGTH_SHORT).show();
                            break;
                        }

                        tvBoxNum.setText(scannedBoxNum + "");
                        tvTuoNum.setText(scannedTuoNum + "");
                        etCode.setText(result);
                        saveCode(result);
                        MyApp.playSound(0);
                    } else {
                        CodeHelper.codeScanned(ScanMessageActivity.this);
                        break;
                    }
                    break;

                default:
                    break;

            }
            return false;
        }
    });

    @Override
    protected int activityLayoutId() {
        return R.layout.activity_scan_message;
    }

    @Override
    protected void initView() {
        setHeader("扫描信息", true, false, 0, "", null);
        bundle = getIntent().getExtras();
        productId = AssistHelper.getProductId(bundle);
        sendOrganId = AssistHelper.getSendOrganCode(bundle);
        receiveOrganId = AssistHelper.getReceiveOrganCode(bundle);
        sendWarehouseId = AssistHelper.getSendWarehouseCode(bundle);
        receiveWarehouseId = AssistHelper.getReceiveWarehouseCode(bundle);

        planBoxNum = AssistHelper.getBoxNum(bundle);//计划箱
        planTuoNum = AssistHelper.getTuoNum(bundle);//计划托

        tvSendOrgan.setText(AssistHelper.getSendOrganName(bundle));
        tvSendWarehouse.setText(AssistHelper.getSendWarehouseName(bundle));
        tvReceiveOrgan.setText(AssistHelper.getReceiveOrganName(bundle));
        tvReceiveWarehouse.setText(AssistHelper.getReceiveWarehouseName(bundle));
        tvProduct.setText(AssistHelper.getProductName(bundle));
        tvPlanNum.setText(planBoxNum + "  箱   " + planTuoNum + "  托");
    }

    //num
    private void showScannedNum() {
        scannedBoxNum = CodeHelper.getScannedNum("Y", productId, sendOrganId, sendWarehouseId, receiveOrganId, receiveWarehouseId);
        scannedTuoNum = CodeHelper.getScannedNum("L", productId, sendOrganId, sendWarehouseId, receiveOrganId, receiveWarehouseId);

        tvBoxNum.setText(scannedBoxNum + "");
        tvTuoNum.setText(scannedTuoNum + "");
    }

    //save code
    private void saveCode(String code) {
        String date = DateTimeHelper.getCurrentDateTimeString();
        CodeBean bean = new CodeBean();
        bean.setCode(code);
        bean.setCodeFlag(code.substring(0, 1));
        bean.setTime(date);
        bean.setType("1");//出库
        bean.setProductId(productId);
        bean.setSendOrganId(sendOrganId);
        bean.setSendWarehouseId(sendWarehouseId);
        bean.setReceiveOrganId(receiveOrganId);
        bean.setReceiveWarehouseId(receiveWarehouseId);
        bean.setUserName(AssistHelper.getUserName());
        bean.save();
    }

    @Override
    protected void setListener() {

    }

    @OnClick({R.id.btnUpload})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnUpload:
                List<CodeBean> list = CodeHelper.getScannedList(productId, sendOrganId, sendWarehouseId, receiveOrganId, receiveWarehouseId);
                UploadFileInfoHelpter.uploadFiles(ScanMessageActivity.this, list, null);

              //  CodeHelper.deleteScannedList(productId, sendOrganId, sendWarehouseId, receiveOrganId, receiveWarehouseId);
                break;

            default:
                break;

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Scanner.mHandler = handler;//iData 95
        Scanner.registerBroadcastReceiver();

        showScannedNum();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Scanner.unregisterBroadcastReceiver();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_F9 && event.getRepeatCount() == 0) {
            Scanner.startScanning();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_F9) {
            Scanner.stopScanning();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

}
