package com.example.demo.demoboot2.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: lichangtong
 * @Date: 2019-11-27 13:23
 * @Description:
 */
public class IpHelpUtils {
    public static long ipToLong(String ip) {
        String[] ipArray = ip.split("\\.");
        List ipNums = new ArrayList();
        for (int i = 0; i < 4; ++i) {
            ipNums.add(Long.valueOf(Long.parseLong(ipArray[i].trim())));
        }
        long ZhongIPNumTotal = ((Long) ipNums.get(0)).longValue() * 256L * 256L * 256L
                + ((Long) ipNums.get(1)).longValue() * 256L * 256L + ((Long) ipNums.get(2)).longValue() * 256L
                + ((Long) ipNums.get(3)).longValue();

        return ZhongIPNumTotal;
    }

    public static void main(String[] args) {
        System.out.println(IpHelpUtils.ipToLong("218.6.78.18")+"");
    }
}
