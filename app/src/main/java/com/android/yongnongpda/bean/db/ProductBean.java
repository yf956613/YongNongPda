package com.android.yongnongpda.bean.db;

import org.litepal.crud.DataSupport;

/**
 * Created by shijie.yang on 2018/2/28.
 */

public class ProductBean extends DataSupport {

    /**
     * isSetLabel : 0
     * unitList : [{"unitName":"中盒","quantity":"10","unitId":"6"},{"unitName":"箱","quantity":"100","unitId":"7"}]
     * name : 测试产品2018
     * basicPackageUnitName : 瓶
     * basicPackageUnit : 2
     * code : 1005
     */
    private int id;
    private String productId;
    private String isSetLabel;
    private String name;
    private String basicPackageUnitName;
    private String basicPackageUnit;
    private String spec;
    private String authEnticationCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsSetLabel() {
        return isSetLabel;
    }

    public void setIsSetLabel(String isSetLabel) {
        this.isSetLabel = isSetLabel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBasicPackageUnitName() {
        return basicPackageUnitName;
    }

    public void setBasicPackageUnitName(String basicPackageUnitName) {
        this.basicPackageUnitName = basicPackageUnitName;
    }

    public String getBasicPackageUnit() {
        return basicPackageUnit;
    }

    public void setBasicPackageUnit(String basicPackageUnit) {
        this.basicPackageUnit = basicPackageUnit;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getAuthEnticationCode() {
        return authEnticationCode;
    }

    public void setAuthEnticationCode(String authEnticationCode) {
        this.authEnticationCode = authEnticationCode;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
