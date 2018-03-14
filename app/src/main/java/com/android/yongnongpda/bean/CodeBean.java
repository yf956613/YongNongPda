package com.android.yongnongpda.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by shijie.yang on 2018/2/28.
 */

public class CodeBean extends DataSupport {
    private int id;
    private String productId;
    private String productName;
    private String sendOrganId;
    private String sendWarehouseId;
    private String receiveOrganId;
    private String receiveWarehouseId;
    private String code;
    private String codeFlag;//Y 箱 L 托
    private String time;
    private String type;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCodeFlag() {
        return codeFlag;
    }

    public void setCodeFlag(String codeFlag) {
        this.codeFlag = codeFlag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSendOrganId() {
        return sendOrganId;
    }

    public void setSendOrganId(String sendOrganId) {
        this.sendOrganId = sendOrganId;
    }

    public String getSendWarehouseId() {
        return sendWarehouseId;
    }

    public void setSendWarehouseId(String sendWarehouseId) {
        this.sendWarehouseId = sendWarehouseId;
    }

    public String getReceiveOrganId() {
        return receiveOrganId;
    }

    public void setReceiveOrganId(String receiveOrganId) {
        this.receiveOrganId = receiveOrganId;
    }

    public String getReceiveWarehouseId() {
        return receiveWarehouseId;
    }

    public void setReceiveWarehouseId(String receiveWarehouseId) {
        this.receiveWarehouseId = receiveWarehouseId;
    }
}
