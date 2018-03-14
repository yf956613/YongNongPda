package com.android.yongnongpda.bean.db;

import org.litepal.crud.DataSupport;

/**
 * Created by shijie.yang on 2018/2/28.
 */

public class WarehouseBean extends DataSupport {

    //      "enCode": "123456",
//       "name": "aaaa默认仓库",
//      "code": "31a1cc84-62b2-4103-87f3-04e2cb2b2237",
//      "companyCode": "bedf20c3-fbfe-4f52-95de-2b080caa0bc2"
    private int id;
    private String enCode;
    private String name;
    private String code;
    private String companyCode;
    private String warehouseId;
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnCode() {
        return enCode;
    }

    public void setEnCode(String enCode) {
        this.enCode = enCode;
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

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }
}
