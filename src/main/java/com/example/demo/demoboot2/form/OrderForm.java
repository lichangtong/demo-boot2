package com.example.demo.demoboot2.form;

import java.io.Serializable;

/**
 * @Auther: lichangtong
 * @Date: 2019-03-20 14:58
 * @Description:
 */
public class OrderForm implements Serializable {
    private String orderNo;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
