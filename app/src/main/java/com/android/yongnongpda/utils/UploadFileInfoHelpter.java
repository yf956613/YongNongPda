package com.android.yongnongpda.utils;

import android.content.Context;
import android.os.Handler;

import com.android.yongnongpda.R;
import com.android.yongnongpda.bean.CodeBean;
import com.android.yongnongpda.confing.AppConfig;
import com.winsafe.mylibrary.utils.CommonHelper;
import com.winsafe.mylibrary.utils.DateTimeHelper;
import com.winsafe.mylibrary.utils.FileHelper;
import com.winsafe.mylibrary.utils.MyDialog;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Created by shijie.yang on 2017/6/7.
 */

public class UploadFileInfoHelpter {
    /**
     * 根据用户账号和操作类型获取文件名字
     * @return 返回文件名字
     */
    public static String getFileName() {
        String result = "";
        String UserName=AssistHelper.getUserName();
        String strDateTime = DateTimeHelper.formatDate("yyyyMMddHHmmss", new Date());
        result = String.format("%s_%s_%s.txt", UserName, strDateTime, "Out");

        return result;
    }

    /**
     * 上传的文件
     * @param context
     * @param list
     * @return
     */
    public static String getUploadFilesContent(Context context, List<CodeBean> list) {
        StringBuffer buffer = new StringBuffer();
        //文本格式
        buffer.append("code"+",");
        buffer.append("codeFlag"+",");
        buffer.append("\r\n");
        for (CodeBean map : list) {
            buffer.append(map.getCode()+",");
            buffer.append(map.getCodeFlag());
            buffer.append("\r\n");
        }
        return buffer.toString();
    }



    /**
     * 备份要上传的文件
     * @param context 上下文
     * @param fileName 文件名称
     * @param backUpName 备份文件名称
     */
    public static void backUpLoadFiles(Context context, String fileName, String backUpName){
        if (!CommonHelper.getExternalStoragePath().equalsIgnoreCase("")) {
            String oldPath = context.getFilesDir() + "/" + fileName;
            String strDateTime = DateTimeHelper.formatDate("yyyyMMddHHmmss", new Date()) + backUpName;
            String newPath = CommonHelper.getExternalStoragePath()
                    + String.format("/%s/%s/", AppConfig.ROOTFOLDER, AppConfig.UPLOADBACKUPFOLDER) + strDateTime;
            FileHelper.copyFile(oldPath, newPath);
        }
    }

    /**
     * 生成文件内容
     * @param context 上下文
     * @param fileName 文件名称
     * @param fileContent 文件内容
     */
    public  static File makeFiles(Context context, String fileName, String fileContent){
        FileHelper.writeFile(context, fileName, fileContent);
        File filePath = new File(context.getFilesDir() + "/" + fileName);
        return filePath;
    }

    /**
     * 文件上传
     * @param context 上下文
     * @param list 码列表
     * @param handler handler
     */
    public static void uploadFiles(Context context, List<CodeBean> list ,Handler handler){
        //当前上传文件是否为空
        if (list.size() == 0) {
            MyDialog.showToast(context, context.getResources().getString(R.string.toast_idcode_has_no_scanned));
            return;
        }
        String fileName = getFileName();
        String fileContent = getUploadFilesContent(context,list);
        //生成文件
        File filePath=makeFiles(context,fileName,fileContent);
        if (!filePath.exists()) {
            MyDialog.showToast(context, context.getResources().getString(R.string.toast_file_not_exist));
            return;
        }
        //备份
        String backupName=AppConfig.BACKUP_FILES_OUT;
        backUpLoadFiles(context,fileName,backupName);


        //上传文件
//        Map<String, String> map=new HashMap<>();
//        map .put(AppConfig.MAP_TYPE, AppConfig.MAP_TYPE_NO) ;
//        map .put(AppConfig.ACCESSTOKEN,  AssistHelper.getAccessToken()) ;
//        //上传文件参数
//        String name="file";
//        OkHttpHelper.postFiles(context,AppConfig.URL_BASE+AppConfig.URL_LOADIDCODEFILE,map,name,fileName,filePath,handler,"文件上传中···");
    }

}
