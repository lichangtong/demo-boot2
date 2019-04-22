package com.example.demo.demoboot2.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Auther: lichangtong
 * @Date: 2019-03-19 14:29
 * @Description:
 */
public class OrderChildVO implements Serializable {

        private Long id;
        private String orderNo;
        private String brandName;
        private BigDecimal price;
        private String created;
        private Integer state;

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

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
