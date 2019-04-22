package com.example.demo.demoboot2.form;

import java.io.Serializable;

/**
 * @Auther: lichangtong
 * @Date: 2019-03-20 14:42
 * @Description:
 */
public class ProductForm implements Serializable {

    private Long id;
    private String code;
    private Long parent;

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

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }
}
