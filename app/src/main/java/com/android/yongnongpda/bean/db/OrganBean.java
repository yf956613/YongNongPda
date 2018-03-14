package com.android.yongnongpda.bean.db;

import org.litepal.crud.DataSupport;

/**
 * Created by shijie.yang on 2018/2/28.
 */

public class OrganBean extends DataSupport {
    //     "compantFullName": "公司名称",
//     "encode": "20160927",
//     "code": "5516ec3d-803a-4cc6-9415-9d99cc26fd3b
    private int id;
    private String type;
    private String name;
    private String code;
    private String encode;
    private String organId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEncode() {
        return encode;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId;
    }
}
