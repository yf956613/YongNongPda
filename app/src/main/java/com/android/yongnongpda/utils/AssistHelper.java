package com.android.yongnongpda.utils;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.yongnongpda.application.MyApp;
import com.android.yongnongpda.confing.AppConfig;


/**
 * 用户辅助类
 * Created by shijie.yang on 2017/6/7.
 */

public class AssistHelper {
    private Context context;

    AssistHelper(Context context) {
        this.context = context;
    }

    //获取用户名
    public static String getUserName() {
//        return MyApp.shared.getValueByKey(AppConfig.USERNAME);
        return "admin";
    }

    //获取密码
    public static String getPassword() {
        return MyApp.shared.getValueByKey(AppConfig.PASSWORD);
    }

    //获取accessToken
    public static String getAccessToken() {
        return MyApp.shared.getValueByKey(AppConfig.ACCESSTOKEN);
    }

    //获取密码
    public static String isSavePassword() {
        return MyApp.shared.getValueByKey(AppConfig.ISSAVE_PASSWORD);
    }

    //显示String文字提示信息
    public static String showString(Context context, int str) {
        return context.getResources().getString(str);
    }

    //手机验证
    public static boolean isMobileNO(String mobiles) {
        //"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        String telRegex = "[1][34578]\\d{9}";
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }


    //出库退货传参
    public static Bundle bundleString(Bundle bundle, String a, String b, String c,
                                    String d, String e, String f, String g, String h,
                           String l, String m, String n, int x, int y) {

        bundle.putString(AppConfig.BUNDLE_SEND_ORGAN_NAME, a);//发货机构name
        bundle.putString(AppConfig.BUNDLE_SEND_ORGAN_CODE, b);//发货机构code
        bundle.putString(AppConfig.BUNDLE_RECEIVE_ORGAN_NAME, c);//收货机构name
        bundle.putString(AppConfig.BUNDLE_RECEIVE_ORGAN_CODE, d);//收货机构code

        bundle.putString(AppConfig.BUNDLE_SEND_WAREHOSE_NAME, e);//发货仓库name
        bundle.putString(AppConfig.BUNDLE_SEND_WAREHOSE_CODE, f);//发货仓库code
        bundle.putString(AppConfig.BUNDLE_RECEIVE_WAREHOSE_NAME, g);//收货仓库name
        bundle.putString(AppConfig.BUNDLE_RECEIVE_WAREHOSE_CODE, h);//收货仓库code

        bundle.putString(AppConfig.BUNDLE_PRODUCTNO, l);//产品规格
        bundle.putString(AppConfig.BUNDLE_PRODUCTID, m);//产品id
        bundle.putString(AppConfig.BUNDLE_PRODUCTNAME, n);//产品名称
        bundle.putInt(AppConfig.BUNDLE_BOX_NUM, x);//计划箱数量
        bundle.putInt(AppConfig.BUNDLE_TUO_NUM, y);//计划托数量
        return bundle;
    }

    //发货机构name
    public static String getSendOrganName(Bundle bundle) {
        return bundle.getString(AppConfig.BUNDLE_SEND_ORGAN_NAME);
    }

    //发货机构code
    public static String getSendOrganCode(Bundle bundle) {
        return bundle.getString(AppConfig.BUNDLE_SEND_ORGAN_CODE);
    }

    //收货机构name
    public static String getReceiveOrganName(Bundle bundle) {
        return bundle.getString(AppConfig.BUNDLE_RECEIVE_ORGAN_NAME);
    }

    //收货机构code
    public static String getReceiveOrganCode(Bundle bundle) {
        return bundle.getString(AppConfig.BUNDLE_RECEIVE_ORGAN_CODE);
    }

    //发货仓库name
    public static String getSendWarehouseName(Bundle bundle) {
        return bundle.getString(AppConfig.BUNDLE_SEND_WAREHOSE_NAME);
    }

    //发货仓库code
    public static String getSendWarehouseCode(Bundle bundle) {
        return bundle.getString(AppConfig.BUNDLE_SEND_WAREHOSE_CODE);
    }

    //收货仓库name
    public static String getReceiveWarehouseName(Bundle bundle) {
        return bundle.getString(AppConfig.BUNDLE_RECEIVE_WAREHOSE_NAME);
    }

    //收货仓库code
    public static String getReceiveWarehouseCode(Bundle bundle) {
        return bundle.getString(AppConfig.BUNDLE_RECEIVE_WAREHOSE_CODE);
    }

    //产品规格
    public static String getProductNo(Bundle bundle) {
        return bundle.getString(AppConfig.BUNDLE_PRODUCTNO);
    }

    //产品id
    public static String getProductId(Bundle bundle) {
        return bundle.getString(AppConfig.BUNDLE_PRODUCTID);
    }

    //产品名称
    public static String getProductName(Bundle bundle) {
        return bundle.getString(AppConfig.BUNDLE_PRODUCTNAME);
    }

    //计划箱数量
    public static int getBoxNum(Bundle bundle) {
        return bundle.getInt(AppConfig.BUNDLE_BOX_NUM);
    }

    //计划托数量
    public static int getTuoNum(Bundle bundle) {
        return bundle.getInt(AppConfig.BUNDLE_TUO_NUM);
    }
}
