package com.example.demo.demoboot2.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Auther: lichangtong
 * @Date: 2019-03-20 11:19
 * @Description:
 */
public class ProductVO implements Serializable {
    private Long id;
    private String code;
    private String name;
    private String describe;
    private BigDecimal officialCharge;
    private BigDecimal serviceCharge;
    private BigDecimal serviceChargeVip;
    private Long parent;
    List<ProductVO> childList;

    @Override
    public String toString() {
        return "ProductVO{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                ", officialCharge=" + officialCharge +
                ", serviceCharge=" + serviceCharge +
                ", serviceCharge_vip=" + serviceChargeVip +
                ", parent=" + parent +
                ", childList=" + childList +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public BigDecimal getOfficialCharge() {
        return officialCharge;
    }

    public void setOfficialCharge(BigDecimal officialCharge) {
        this.officialCharge = officialCharge;
    }

    public BigDecimal getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(BigDecimal serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public BigDecimal getServiceChargeVip() {
        return serviceChargeVip;
    }

    public void setServiceChargeVip(BigDecimal serviceCharge_vip) {
        this.serviceChargeVip = serviceCharge_vip;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public List<ProductVO> getChildList() {
        return childList;
    }

    public void setChildList(List<ProductVO> childList) {
        this.childList = childList;
    }
}
