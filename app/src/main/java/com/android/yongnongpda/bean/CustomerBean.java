package com.android.yongnongpda.bean;

/**
 * Created by shijie.yang on 2018/2/28.
 */

public class CustomerBean {
    private String CID;//客商编号
    private String CName;//客商名称
    private String CAddress;//客商地址
    private String LID;//联系人编号
    private String LName;//联系人名称
    private String LPhone;//联系人手机

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public String getCName() {
        return CName;
    }

    public void setCName(String CName) {
        this.CName = CName;
    }

    public String getCAddress() {
        return CAddress;
    }

    public void setCAddress(String CAddress) {
        this.CAddress = CAddress;
    }

    public String getLID() {
        return LID;
    }

    public void setLID(String LID) {
        this.LID = LID;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getLPhone() {
        return LPhone;
    }

    public void setLPhone(String LPhone) {
        this.LPhone = LPhone;
    }
}
