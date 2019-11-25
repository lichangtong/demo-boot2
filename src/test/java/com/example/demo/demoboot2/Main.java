package com.example.demo.demoboot2;

import com.google.inject.internal.util.Lists;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
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
//        String path="/dddd/ddssdf,/sdfs/dddd";
//        System.out.println(path.indexOf("/dddd/ddssdf"));
//        System.out.println(path.indexOf("/sdfs/dddd"));
        System.out.println(Math.round(-1.5));
        System.out.println(Math.round(1.5));
        System.out.println(Files.exists(Paths.get("E:\\test\\abc.txt"),new LinkOption[]{LinkOption.NOFOLLOW_LINKS}));
        try {
            Files.createDirectory(Paths.get("E:\\test\\123"));
        } catch (IOException e) {
            e.printStackTrace();
        }

//        Thread
    }
}
