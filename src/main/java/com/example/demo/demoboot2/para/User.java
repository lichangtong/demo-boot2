package com.example.demo.demoboot2.para;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Auther: lichangtong
 * @Date: 2019-02-13 15:49
 * @Description:
 */
public class User implements Serializable {
    private Long id;
    @NotBlank(message = "{user.name.notBlank}")
    private String uname;
    @NotBlank(message = "{user.name.notBlank}")
    private String pwd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
