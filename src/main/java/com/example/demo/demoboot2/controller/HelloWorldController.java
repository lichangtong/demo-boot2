package com.example.demo.demoboot2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: lichangtong
 * @Date: 2019-02-14 10:04
 * @Description:
 */
@RestController
public class HelloWorldController {
    @RequestMapping("/hello2")
    public String index() {
        return "Hello World";
    }
}
