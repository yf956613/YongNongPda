package com.android.yongnongpda.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.android.yongnongpda.bean.CodeBean;
import com.android.yongnongpda.confing.AppConfig;

import org.litepal.crud.DataSupport;

/**
 * Created by shijie.yang on 2018/2/28.
 */

public class DialogHelper {
    /**
     * @param context
     * @param msg
     * @param flag  0出库上传 1出库删除  2更新 3退出
     */
    public static void mDialog(final Context context, String msg, final int flag) {
        final Dialog dialog = new AlertDialog.Builder(context)

                .setTitle("提示：")
                .setMessage(msg)
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        if (flag== AppConfig.DIALOG_OUT) {

                        }else if (flag==AppConfig.DIALOG_DELETE) {
                            if (CodeHelper.isSuccessDeleteUserCode()) {
                                Toast.makeText(context, "删除成功!", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context, "删除失败!", Toast.LENGTH_SHORT).show();
                            }
                        }else if (flag==AppConfig.DIALOG_UPDATE) {

                        }else if (flag==AppConfig.DIALOG_EXIT) {

                        }
                        dialog.dismiss();
                    }
                }).create();


        dialog.setCancelable(true);
        dialog.show();
    }


}
