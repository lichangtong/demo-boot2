package com.example.demo.demoboot2.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.demoboot2.Result.ApiResult;
import com.example.demo.demoboot2.dao.UserMapper;
import com.example.demo.demoboot2.para.User;
import com.example.demo.demoboot2.utils.RedisUtil;
import com.google.inject.internal.util.Lists;
import com.google.inject.internal.util.Maps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: lichangtong
 * @Date: 2019-02-13 10:19
 * @Description:
 */
@RestController
public class HelloController {
    private Logger logger = LogManager.getLogger(HelloController.class);
    /**
     *
     */
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("hello")
    public String hello() {


//        int i = 1 / 1;
//        System.out.println(i);

        redisUtil.set("lisi", "李四");
        System.out.println(redisUtil.get("lisi"));
        redisUtil.setList("lichangtong", "1", "2", "3", "4");
        System.out.println(redisUtil.getListString("lichangtong"));
        System.out.println(redisUtil.existKey("lichangtong"));
        List<Object> lists = Lists.newArrayList();
        Map<String, Object> map = Maps.newHashMap();
        map.put("zhangsan","张三");
        map.put("wangwu","王武");
        lists.add(map);
        Map<String, Object> map2 = Maps.newHashMap();
        map2.put("zhangsan99999","张三00000");
        map2.put("wangwu9999999","王武00000");
        lists.add(map2);

        Map<String, Object> map3 = Maps.newHashMap();
        map3.put("map3",map2);
        redisUtil.setList("lisiObject", lists);
        System.out.println(JSON.toJSONString(redisUtil.getListObject("lisiObject",Map.class)));

        redisUtil.setHash("listHash","lisi","李四");
        System.out.println(redisUtil.getHash("listHash","lisi"));

        redisUtil.setHashMap("lisiHashMap3",map3);
//
        System.out.println(JSON.toJSONString(redisUtil.getMapEntity("lisiHashMap3",Map.class)));

        return "Hello World!!!";

    }

    @RequestMapping("hai")
    public String sayHai() throws Exception {
        throw new Exception("参数错误");
    }

    @RequestMapping("adduser")
    public ApiResult addUser(@RequestBody @Validated User user) {

        System.out.println(user.getPwd() + "===" + user.getUname());
        userMapper.addUser(user);
        return null;
    }
}
