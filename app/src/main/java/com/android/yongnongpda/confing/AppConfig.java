package com.android.yongnongpda.confing;

public class AppConfig {
    //保存的文件名称和路径
    public final static String ROOTFOLDER = "WinSafe_YongNong";
    public final static String APK_NAME = "YongNong";
    public static final String UPLOADBACKUPFOLDER = "UploadBackup";
    // 错误日志是否保存
    public final static boolean isLogSave = true;
    // SharedManager info
    public final static String SHARED_NAME = "cache_YongNong";

    //URL
//    public static String URL_BASE = "http://www.winttp.com/";//正试地址
    public static String URL_BASE = "http://192.168.20.205:8802/";//测试

    //登录
    public static final String URL_LOGIN = AppConfig.URL_BASE+"is/scanner/loginAction.do";
    //发货机构
    public static final String URL_SEND_ORGAN = "uploadController/getCompanyInfo.do";
    //收货机构
    public static final String URL_RECEIVE_ORGAN = "uploadController/getAllDealerInfo.do";
    //产品下载
    public static final String URL_PRODUCT = AppConfig.URL_BASE+"is/scanner/downloadProductAction.do";
    //上传日志
    public static final String URL_LOG_LIST = "uploadController/getUploadLog.do";
    //日志详情
    public static final String URL_LOG_INFO = "uploadController/getInfo.do";
    //修改密码
    public static final String URL_CHANGE_PWD = "uploadController/updPassword.do";
    //文件上传
    public static final String URL_LOADIDCODEFILE = "uploadController/upload.do";
    //版本更新
    public static final String CHECK_VERSION_URL = "http://d.winsafe.cn/apk/UpdateService.ashx?action={0}&appName={1}&appVer={2}&appType=android";

    //参数
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String ISSAVE_PASSWORD = "isSave";
    public static final String ACCESSTOKEN = "accessToken";
    //bundle
    public static final String BUNDLE_SEND_ORGAN_NAME = "sendOrganName";
    public static final String BUNDLE_SEND_ORGAN_CODE = "sendOrganCode";
    public static final String BUNDLE_RECEIVE_ORGAN_NAME = "rvOrganName";
    public static final String BUNDLE_RECEIVE_ORGAN_CODE = "rvOrganCode";
    public static final String BUNDLE_SEND_WAREHOSE_NAME = "sendWarehouseName";
    public static final String BUNDLE_SEND_WAREHOSE_CODE = "sendWarehouseCode";
    public static final String BUNDLE_RECEIVE_WAREHOSE_NAME = "receiveWarehouseName";
    public static final String BUNDLE_RECEIVE_WAREHOSE_CODE = "receiveWarehouseCode";
    public static final String BUNDLE_PRODUCTNO = "productNo";//产品规格
    public static final String BUNDLE_PRODUCTID = "productId";//产品id
    public static final String BUNDLE_PRODUCTNAME = "productName";//产品名称
    public static final String BUNDLE_BOX_NUM = "boxNum";//计划箱数量
    public static final String BUNDLE_TUO_NUM = "tuoNum";//计划托数量

    //备份文件（出库，退货）名称
    public static final String BACKUP_FILES_OUT = "_out.txt";
    //网络响应值
    public static final String RETURN_SUCCESS = "0";//成功
    public static final String RETURN_INVALID = "512";//无效
    public static final String RETURN_EXPIRE = "513";//过期
    public static final int HANDLER_MESSAGE = 200;

    //dialog  --->>  0出库上传 1出库删除  2更新 3退出
    public static final int DIALOG_OUT = 0;
    public static final int DIALOG_DELETE = 1;
    public static final int DIALOG_UPDATE = 2;
    public static final int DIALOG_EXIT = 3;
}
