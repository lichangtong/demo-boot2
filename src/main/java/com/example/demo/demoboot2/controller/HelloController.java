package com.example.demo.demoboot2.controller;

import com.example.demo.demoboot2.Result.ApiResult;
import com.example.demo.demoboot2.dao.OrderMapper;
import com.example.demo.demoboot2.dao.ProductMapper;
import com.example.demo.demoboot2.dao.UserMapper;
import com.example.demo.demoboot2.form.OrderForm;
import com.example.demo.demoboot2.form.ProductForm;
import com.example.demo.demoboot2.para.User;
import com.example.demo.demoboot2.utils.RedisUtil;
import com.example.demo.demoboot2.vo.OrderVO;
import com.example.demo.demoboot2.vo.ProductVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

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
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ProductMapper productMapper;

    @RequestMapping(value = "queryOrder", method = RequestMethod.POST)
    public ApiResult queryOrderList(@RequestBody OrderForm order) {
        System.out.println(order.getOrderNo());
        ApiResult apiResult = new ApiResult();
        List<OrderVO> list = orderMapper.queryOrderList(order.getOrderNo());
        System.out.println(list);
        apiResult.setData(list);
        return apiResult;
    }

    @RequestMapping(value = "queryProduct", method = RequestMethod.POST)
    public ApiResult queryProduct(@RequestBody ProductForm form) {
        List<ProductVO> productVOList = productMapper.queryProductList(form);
        ApiResult apiResult = new ApiResult();
        apiResult.setData(productVOList);
        return apiResult;
    }

    @RequestMapping("hello")
    public String hello(HttpServletRequest request) {
        System.out.println(request.getHeaderNames());
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()){
            String headerName = enumeration.nextElement();
            System.out.println(headerName+":"+request.getHeader(headerName));

        }
//        int i = 1 / 1;
//        System.out.println(i);

//        redisUtil.set("lisi", "李四");
//        System.out.println(redisUtil.get("lisi"));
//        redisUtil.setList("lichangtong", "1", "2", "3", "4");
//        System.out.println(redisUtil.getListString("lichangtong"));
//        System.out.println(redisUtil.existKey("lichangtong"));
//        List<Object> lists = Lists.newArrayList();
//        Map<String, Object> map = Maps.newHashMap();
//        map.put("zhangsan", "张三");
//        map.put("wangwu", "王武");
//        lists.add(map);
//        Map<String, Object> map2 = Maps.newHashMap();
//        map2.put("zhangsan99999", "张三00000");
//        map2.put("wangwu9999999", "王武00000");
//        lists.add(map2);
//
//        Map<String, Object> map3 = Maps.newHashMap();
//        map3.put("map3", map2);
//        redisUtil.setList("lisiObject", lists);
//        System.out.println(JSON.toJSONString(redisUtil.getListObject("lisiObject", Map.class)));
//
//        redisUtil.setHash("listHash", "lisi", "李四");
//        System.out.println(redisUtil.getHash("listHash", "lisi"));
//
//        redisUtil.setHashMap("lisiHashMap3", map3);
////
//        System.out.println(JSON.toJSONString(redisUtil.getMapEntity("lisiHashMap3", Map.class)));


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
