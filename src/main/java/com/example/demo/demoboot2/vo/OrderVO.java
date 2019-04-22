package com.example.demo.demoboot2.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Auther: lichangtong
 * @Date: 2019-03-19 14:28
 * @Description:
 */
public class OrderVO  implements Serializable{
    private Long id;
    private String orderNo;
    private String productCode;
    private String productName;
    private BigDecimal price;
    private List<OrderChildVO> childList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<OrderChildVO> getChildList() {
        return childList;
    }

    public void setChildList(List<OrderChildVO> childList) {
        this.childList = childList;
    }

    @Override
    public String toString() {
        return "OrderVO{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", childList=" + childList +
                '}';
    }
}
