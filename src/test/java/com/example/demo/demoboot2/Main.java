package com.example.demo.demoboot2;

import com.google.inject.internal.util.Lists;

import java.util.List;

/**
 * @Auther: lichangtong
 * @Date: 2019-02-27 11:36
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
//        Test test = new Test();
//        test.setId(100);
//        Test test1 = new Test();
//        List<Test> testList = Lists.newArrayList();
//        testList.add(test1);
//        test.setItems(testList);
//        System.out.println(test.toString());
        String path="/dddd/ddssdf,/sdfs/dddd";
        System.out.println(path.indexOf("/dddd/ddssdf"));
        System.out.println(path.indexOf("/sdfs/dddd"));
    }
}
