package com.example.demo.demoboot2.command;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Auther: lichangtong
 * @Date: 2019-02-14 10:13
 * @Description:
 */
@Component
@Order(2)
public class MyRunner2 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("The MyRunner2 start to initialize ...");
    }
}
