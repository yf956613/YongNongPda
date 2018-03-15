package com.android.yongnongpda.utils;

import android.content.Context;
import android.widget.Toast;

import com.android.yongnongpda.R;
import com.android.yongnongpda.application.MyApp;
import com.android.yongnongpda.bean.CodeBean;
import com.android.yongnongpda.bean.db.ProductBean;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by shijie.yang on 2018/3/2.
 */

public class CodeHelper {
    //截取码
    public static String resultCode(String result) {
        String mCode;
        if (result.contains("=")) {
            mCode = result.contains("=") ? result.split("=")[1] : result;
        } else if (result.contains("/")) {
            int aa = result.lastIndexOf("/") + 1;
            mCode = result.substring(aa, result.length());
        } else {
            mCode = result;
        }
        return mCode;
    }

    public static boolean codeRules(Context context, String code) {

        if (code.length() != 23) {
            MyApp.playSound(1);
            Toast.makeText(context, AssistHelper.showString(context, R.string.toast_code_wl_error), Toast.LENGTH_SHORT).show();
            return false;
        }
        String flag = code.substring(0, 1);// Y 箱, L 托
        if (!flag.equals("Y") && !flag.equals("L")) {
            MyApp.playSound(1);
            Toast.makeText(context, AssistHelper.showString(context, R.string.toast_code_unit_error), Toast.LENGTH_SHORT).show();
            return false;
        }
//        String productNo = code.substring(12, 15);//产品规格
//        if (!queryProductNo().contains(productNo)) {
//            MyApp.playSound(1);
//            Toast.makeText(context, AssistHelper.showString(context, R.string.toast_code_unit_error), Toast.LENGTH_SHORT).show();
//            return false;
//        }
        return true;
    }

    /**
     * this code is scanned
     *
     * @param context 上下文
     */
    public static void codeScanned(Context context) {
        Toast.makeText(context, AssistHelper.showString(context, R.string.code_scanned), Toast.LENGTH_SHORT).show();
        MyApp.playSound(1);
    }

    /**
     * query productNo
     *
     * @return
     */
    public static String queryProductNo() {
        List<ProductBean> list = DataSupport.findAll(ProductBean.class);
        String pn = "";
        for (ProductBean bean : list) {
            pn += bean.getSpec() + ";";
        }
        return pn;
    }

    /**
     * query is scanned
     *
     * @param code
     * @return
     */
    public static boolean isScannedCode(String code) {
        List<CodeBean> list = DataSupport.where("userName=? and code=?",
                AssistHelper.getUserName(), code).find(CodeBean.class);
        if (list.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * query scanned num
     *
     * @param codeFlag
     * @param productId
     * @param sendOrganId
     * @param sendWarehouseId
     * @param receiveOrganId
     * @param receiveWarehouseId
     * @return
     */
    public static int getScannedNum(String codeFlag, String productId,
                                    String sendOrganId, String sendWarehouseId,
                                    String receiveOrganId, String receiveWarehouseId) {
        List<CodeBean> list = DataSupport.where("userName=? and codeFlag=? and productId=? and sendOrganId=? and sendWarehouseId=? and receiveOrganId=? and receiveWarehouseId=?",
                AssistHelper.getUserName(), codeFlag, productId,
                sendOrganId, sendWarehouseId, receiveOrganId, receiveWarehouseId).find(CodeBean.class);
        return list.size();
    }

    /**
     * 获取选择信息扫码列表
     *
     * @param productId
     * @param sendOrganId
     * @param sendWarehouseId
     * @param receiveOrganId
     * @param receiveWarehouseId
     * @return
     */
    public static List<CodeBean> getScannedList(String productId,
                                                String sendOrganId, String sendWarehouseId,
                                                String receiveOrganId, String receiveWarehouseId) {
        List<CodeBean> list = DataSupport.where("userName=? and productId=? and sendOrganId=? and sendWarehouseId=? and receiveOrganId=? and receiveWarehouseId=?",
                AssistHelper.getUserName(), productId,
                sendOrganId, sendWarehouseId, receiveOrganId, receiveWarehouseId).find(CodeBean.class);
        return list;
    }

    /**
     * 删除上传选择信息扫码列表
     *
     * @param productId
     * @param sendOrganId
     * @param sendWarehouseId
     * @param receiveOrganId
     * @param receiveWarehouseId
     */
    public static void deleteScannedList(String productId,
                                         String sendOrganId, String sendWarehouseId,
                                         String receiveOrganId, String receiveWarehouseId) {
        DataSupport.deleteAll(CodeBean.class, "userName=? and productId=? and sendOrganId=? and sendWarehouseId=? and receiveOrganId=? and receiveWarehouseId=?",
                AssistHelper.getUserName(), productId,
                sendOrganId, sendWarehouseId,
                receiveOrganId, receiveWarehouseId);
    }

    /**
     * 删除当前用户扫码
     */
    public static boolean isSuccessDeleteUserCode() {
        DataSupport.deleteAll(CodeBean.class, "userName=?", AssistHelper.getUserName());
        List<CodeBean> list = DataSupport.where("userName=?", AssistHelper.getUserName()).find(CodeBean.class);
        if (list.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param code
     * @return
     */

    public static boolean deleteUserCode(String code) {
        DataSupport.deleteAll(CodeBean.class, "userName=? and code=?", AssistHelper.getUserName(),code);
        List<CodeBean> list = DataSupport.where("userName=? and code=?", AssistHelper.getUserName(),code).find(CodeBean.class);
        if (list.size() == 0) {
            return true;
        }
        return false;
    }

    public static boolean queryUserCode(String code) {
        List<CodeBean> list = DataSupport.where("userName=? and code=?", AssistHelper.getUserName(),code).find(CodeBean.class);
        if (list.size() == 0) {
            return true;
        }
        return false;
    }


}
