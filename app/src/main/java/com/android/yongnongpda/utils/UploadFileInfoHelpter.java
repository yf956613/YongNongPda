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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shijie.yang on 2017/6/7.
 */

public class UploadFileInfoHelpter {
    /**
     * 根据用户账号和操作类型获取文件名字
     *
     * @return 返回文件名字
     */
    public static String getFileName() {
        String result = "";
        String UserName = AssistHelper.getUserName();
        String strDateTime = DateTimeHelper.formatDate("yyyyMMddHHmmss", new Date());
        result = String.format("%s_%s_%s.txt", UserName, strDateTime, "Out");

        return result;
    }


    /**
     * 备份要上传的文件
     *
     * @param context    上下文
     * @param fileName   文件名称
     * @param backUpName 备份文件名称
     */
    public static void backUpLoadFiles(Context context, String fileName, String backUpName) {
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
     *
     * @param context     上下文
     * @param fileName    文件名称
     * @param fileContent 文件内容
     */
    public static File makeFiles(Context context, String fileName, String fileContent) {
        FileHelper.writeFile(context, fileName, fileContent);
        File filePath = new File(context.getFilesDir() + "/" + fileName);
        return filePath;
    }

    /**
     * 上传的文件
     *
     * @param context
     * @param list
     * @return
     */
    public static String getUploadFilesContent(Context context, List<CodeBean> list) {
        StringBuffer buffer = new StringBuffer();
        //文本格式 title
     /*
     <billNo description="单据号" startIndex="0">14</billNo>
    <warehouseIn description="收货仓库编号" startIndex="14">5</warehouseIn>
    <produceDate description="生产日期" startIndex="19">6</produceDate>
    <batch description="批次" startIndex="25">2</batch>
    <productNo description="产品编号" startIndex="27">2</productNo>
    <unitFlag description="1位托或箱扫描标识" startIndex="29">1</unitFlag>
    <barcode description="条码" startIndex="30">23</barcode>
    <scanType description="扫描类型" startIndex="53">1</scanType>
    <scanDate description="扫描日期" startIndex="54">14</scanDate>
    <num description="数量" startIndex="68">3</num>
    <scanerNo description="采集器编号" startIndex="71">7</scanerNo>
    <warehouseOut description="发货仓库编号" startIndex="78">5</warehouseOut>
    <userNamerName description="用户名" startIndex="83">32</userName>
         */
        for (CodeBean map : list) {
            buffer.append("00001111111111");
            buffer.append(map.getReceiveOrganId()); //收货仓库编号
            buffer.append("000000");
            buffer.append("11");
            buffer.append(map.getProductId());//产品编号
            buffer.append(map.getCodeFlag());//1位托或箱扫描标识
            buffer.append(map.getCode());//条码
            buffer.append("1");//扫描类型
            buffer.append("00001111111111");//扫描日期
            buffer.append("100");//数量
            buffer.append("8888888");//采集器编号
            buffer.append(map.getSendWarehouseId());//发货仓库编号"
            buffer.append("Y0000000000000000000004111111111"); //用户名
            buffer.append("\r\n");
        }
        return buffer.toString();
    }

    /**
     * 文件上传
     *
     * @param context 上下文
     * @param list    码列表
     * @param handler handler
     */
    public static void uploadFiles(Context context, List<CodeBean> list, Handler handler) {
        //当前上传文件是否为空
        if (list.size() == 0) {
            MyDialog.showToast(context, context.getResources().getString(R.string.toast_idcode_has_no_scanned));
            return;
        }
        String fileName = getFileName();
        String fileContent = getUploadFilesContent(context, list);
        //生成文件
        File filePath = makeFiles(context, fileName, fileContent);
        if (!filePath.exists()) {
            MyDialog.showToast(context, context.getResources().getString(R.string.toast_file_not_exist));
            return;
        }
        //备份
        String backupName = AppConfig.BACKUP_FILES_OUT;
        backUpLoadFiles(context, fileName, backupName);

        //上传文件
        Map<String, String> map = new HashMap<>();
        map.put(AppConfig.USERNAME, AssistHelper.getUserName());
        map.put(AppConfig.BILLSORT, AppConfig.SCAN_OUT);//出库类型
        //上传文件参数
        String name = "idcodefile";
        OkHttpHelper.postFiles(context, AppConfig.URL_LOADIDCODEFILE, map, name, fileName, filePath, handler, "文件上传中···");
    }

}
