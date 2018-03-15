package com.android.yongnongpda.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.yongnongpda.R;
import com.android.yongnongpda.activity.LoginActivity;
import com.android.yongnongpda.application.MyApp;
import com.android.yongnongpda.confing.AppConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.io.File;
import java.util.Map;

import okhttp3.Call;

/**
 * 网络请求
 * Created by shijie.yang on 2017/7/18.
 */

public class OkHttpHelper {
    public Context context;
    protected static Dialog dialog;

    public OkHttpHelper(Context context) {
        this.context = context;
    }

    /**
     * @param context
     * @param url        地址
     * @param map        传递参数
     * @param handler
     * @param loadingTip 加载提示文本
     */
    public static void post(final Context context, final String url, Map<String, String> map,
                                 final Handler handler,final int mWhat, String loadingTip) {
        startDialogProgress(context, loadingTip);
        OkHttpUtils.post()
                .url(url)
                .params(map)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        stopDialogProgress();
                        Toast.makeText(context, context.getResources().getString(R.string.network_wifi_low), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Call call, String s) {
                        stopDialogProgress();
                        Log.i("TAG", s);
                        String returnCode = s.substring(11, 12);
                        String returnMsg = s.substring(s.lastIndexOf("=") + 1);
                        //Log.i("TAG", returnCode + returnMsg);
                        if (AppConfig.RETURN_SUCCESS.equals(returnCode)) {
                            Message message = new Message();
                            message.what = mWhat;
                            handler.sendMessage(message);
                        } else {
                            Toast.makeText(context, returnMsg, Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    /**
     * @param context
     * @param url        地址
     * @param map        传递参数
     * @param handler
     * @param loadingTip 加载提示文本
     */
    public static void postData(final Context context, final String url, Map<String, String> map,
                                final Handler handler, String loadingTip) {
        startDialogProgress(context, loadingTip);
        OkHttpUtils.post()
                .url(url)
                .params(map)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        stopDialogProgress();
                        Toast.makeText(context, context.getResources().getString(R.string.network_wifi_low), Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onResponse(Call call, String s) {
                        stopDialogProgress();
                        Message message = new Message();
                        message.what = AppConfig.HANDLER_MESSAGE;
                        message.obj = s;
                        handler.sendMessage(message);
                    }
                });
    }


    /**
     * 文件上传
     *
     * @param context
     * @param url
     * @param map
     * @param filename
     * @param files
     * @param handler
     * @param loadingTip
     */
    public static void postFiles(final Context context, String url, Map<String, String> map,
                                 String name, String filename, File files,
                                 final Handler handler, String loadingTip) {
        startDialogProgress(context, loadingTip);
        OkHttpUtils.post()
                .url(url)
                .params(map)
                .addFile(name, filename, files)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        stopDialogProgress();
                        Toast.makeText(context, context.getResources().getString(R.string.network_wifi_low), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Call call, String s) {
                        stopDialogProgress();
                        //当前返回数据出现异常
                        if (s.contains("DOCTYPE")) {
                            Toast.makeText(context, context.getResources().getString(R.string.toast_contants), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        try {
                            JSONObject JSONObject = new JSONObject(s);
                            String code = JSONObject.getString("returnCode");
                            String msg = JSONObject.getString("returnMsg");
                            if (AppConfig.RETURN_SUCCESS.equals(code)) {

                                MyApp.DATAJSONObject = s;
                                Message message = new Message();
                                message.what = AppConfig.HANDLER_MESSAGE;
                                handler.sendMessage(message);
                            } else {
                                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

    }

    /**
     * 加载动画
     *
     * @param context
     * @param tip
     */
    protected static void startDialogProgress(Context context, String tip) {
        if (dialog == null) {
            dialog = new Dialog(context, com.winsafe.mylibrary.R.style.Son_dialog);
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(com.winsafe.mylibrary.R.layout.dialog_loading, (ViewGroup) null);
            TextView tvTip = (TextView) view.findViewById(com.winsafe.mylibrary.R.id.tvTip);
            tvTip.setText(tip);
            dialog.setContentView(view);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }

    }

    /*
     关闭动画
     */
    protected static void stopDialogProgress() {
        if (dialog != null) {
            dialog.dismiss();
            dialog.cancel();
            dialog = null;
        }
    }

}
