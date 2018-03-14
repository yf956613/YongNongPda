package com.android.yongnongpda;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.yongnongpda.activity.BatchScanActivity;
import com.android.yongnongpda.activity.CustomerMangerActivity;
import com.android.yongnongpda.activity.DeleteCodeActivity;
import com.android.yongnongpda.activity.DownLoadDataActivity;
import com.android.yongnongpda.activity.LogUploadActivity;
import com.android.yongnongpda.activity.LoginActivity;
import com.android.yongnongpda.activity.LookFilesActivity;
import com.android.yongnongpda.confing.AppConfig;
import com.android.yongnongpda.utils.DialogHelper;
import com.winsafe.mylibrary.view.AppBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppBaseActivity {

    @BindView(R.id.btnScan)
    Button btnScan;
    @BindView(R.id.btnFileManger)
    Button btnFileManger;
    @BindView(R.id.btnSystemManger)
    Button btnSystemManger;

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 5:
                    stopDialogProgress();
                    DownLoadDataActivity.FLAG = 0;
                    Toast.makeText(MainActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
                    break;

            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected int activityLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        setHeader("主页", true, false, 0, "", null);


    }

    @Override
    protected void setListener() {

    }

    @OnClick({R.id.btnScan, R.id.btnFileManger, R.id.btnSystemManger})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnScan:
                scanDialog();
                break;
            case R.id.btnFileManger:
                fileDialog();
                break;
            case R.id.btnSystemManger:
                systemDialog();
                break;

            default:
                break;

        }

    }

    //scan
    private void scanDialog() {
        final String items[] = {"数据下载", "批量扫描", "删除条码", "上传日志"};
        final Dialog dialog = new AlertDialog.Builder(this)
                .setTitle("出库->")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (which == 0) {
                            startDialogProgress("下载中···");
                            DownLoadDataActivity.downLoadData(MainActivity.this,handler);

                        } else if (which == 1) {
                            openActivity(MainActivity.this, BatchScanActivity.class, false);
                        } else if (which == 2) {
                            openActivity(MainActivity.this, DeleteCodeActivity.class, false);
                        } else {
                            openActivity(MainActivity.this, LogUploadActivity.class, false);
                        }

                    }
                })
                .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();


        dialog.setCancelable(true);//返回键
        dialog.show();
    }

    //file
    private void fileDialog() {
        final String items[] = {"出库上传", "出库删除", "文件浏览"};
        final Dialog dialog = new AlertDialog.Builder(this)
                .setTitle("文件管理->")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (which == 0) {
                            DialogHelper.mDialog(MainActivity.this,"上传成功", AppConfig.DIALOG_OUT);
                        } else if (which == 1) {
                            DialogHelper.mDialog(MainActivity.this,"确定要删除吗？",AppConfig.DIALOG_DELETE);
                        } else {
                            openActivity(MainActivity.this, LookFilesActivity.class, false);
                        }
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();


        dialog.setCancelable(true);//返回键
        dialog.show();
    }

    //system
    private void systemDialog() {
        final String items[] = {"客商管理", "更新", "退出"};
        final Dialog dialog = new AlertDialog.Builder(this)
                .setTitle("文件管理->")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (which == 0) {
                            openActivity(MainActivity.this, CustomerMangerActivity.class, false);
                        } else if (which == 1) {
                            DialogHelper.mDialog(MainActivity.this,"检测到需要更新组件，是否立即更新", AppConfig.DIALOG_UPDATE);
                        } else {
                            DialogHelper.mDialog(MainActivity.this,"您确定要退出吗？", AppConfig.DIALOG_EXIT);
                        }
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();


        dialog.setCancelable(true);//返回键
        dialog.show();
    }


}
