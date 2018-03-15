package com.android.yongnongpda.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.yongnongpda.R;
import com.android.yongnongpda.application.MyApp;
import com.android.yongnongpda.bean.CodeBean;
import com.android.yongnongpda.scan.ScanResultReceiver;
import com.android.yongnongpda.utils.AssistHelper;
import com.android.yongnongpda.utils.CodeHelper;
import com.winsafe.mylibrary.scan.Scanner;
import com.winsafe.mylibrary.view.AppBaseActivity;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by shijie.yang on 2018/2/27.
 */

public class DeleteCodeActivity extends AppBaseActivity {
    @BindView(R.id.etCode)
    EditText etCode;
    @BindView(R.id.btnDelete)
    Button btnDelete;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case ScanResultReceiver.READ_RESULT:
                    //sacn result
                    String result = CodeHelper.resultCode(message.obj.toString());
                    //code rule
                    if (!CodeHelper.codeRules(DeleteCodeActivity.this, result)) return false;

                    etCode.setText(result);
                    MyApp.playSound(0);

                    break;

                default:
                    break;

            }
            return false;
        }
    });

    @Override
    protected int activityLayoutId() {
        return R.layout.activity_delete_code;
    }

    @Override
    protected void initView() {
        setHeader("删除条码", true, false, 0, "", null);

    }

    @Override
    protected void setListener() {

    }

    @OnClick({R.id.btnDelete})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDelete:
                if (CodeHelper.queryUserCode(etCode.getText().toString())) {
                    Toast.makeText(this, "没有此条码记录!", Toast.LENGTH_SHORT).show();
                    break;
                }

                if (CodeHelper.deleteUserCode(etCode.getText().toString())) {
                    Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "删除失败", Toast.LENGTH_SHORT).show();
                }

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
    }

    @Override
    protected void onPause() {
        super.onPause();
        Scanner.unregisterBroadcastReceiver();
    }

}
