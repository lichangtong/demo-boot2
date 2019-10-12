package com.example.demo.demoboot2.hash;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Auther: lichangtong
 * @Date: 2019-09-20 15:08
 * @Description:
 */
public class CacheManage {
    private List<CacheNode> cacheNodeList;

    private long MAX_CIRCLE = (2L << 32) - 1;

    private Long hash(String nodeName) {

        System.out.println("CODE:"+nodeName.hashCode()+"   MAX_CIRCLE:"+MAX_CIRCLE+" = "+(MAX_CIRCLE & nodeName.hashCode()));
//        System.out.println("MAX_CIRCLE:"+((2L << 32) - 1));
        return MAX_CIRCLE & nodeName.hashCode();
    }

    /**
     * 获取节点所在的下标
     *
     * @param hash
     * @return
     */
    private int getNodeIndex(Long hash) {
        int index = cacheNodeList.size();
        for (int i = 0; i < cacheNodeList.size(); i++) {
            if (hash <= hash(cacheNodeList.get(i).getCacheName())) {
                index = i;
                break;
            }
        }
        return index;
    }

    private void printList() {
        for (CacheNode cacheNode : cacheNodeList) {
//            log.info("cachenode: {}", cacheNode);
            System.out.println("cachenode: {}"+ JSON.toJSONString(cacheNode));
        }
    }


    /**
     * 初始化带有虚拟节点的节点链表
     *
     * @param size
     * @param virtualSize
     */
    public void initVitualNode(int size, int virtualSize) {
        cacheNodeList = new ArrayList<>();
//        log.info("mx: {}", MAX_CIRCLE);
        for (int i = 0; i < size; i++) {


            for (int j = 0; j < virtualSize; j++) {
                CacheNode cacheNode = new CacheNode();
                cacheNode.setCacheName("" + i + "_" + j + i + j + "_node_" + i + "_" + j);
                cacheNode.setCacheIP("192.168.1.10" + i);
                Long hashValue = hash(cacheNode.getCacheName());
                cacheNode.setHashValue(hashValue);
                int index = getNodeIndex(hashValue);
                if (index == cacheNodeList.size()) {
                    cacheNodeList.add(cacheNode);
                } else {
                    cacheNodeList.add(index, cacheNode);
                }
            }

        }

        printList();
    }

    /**
     * 初始化节点列表
     *
     * @param size
     */
    public void initCacheNode(int size) {
        cacheNodeList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            CacheNode cacheNode = new CacheNode();
            cacheNode.setCacheName("node_" + i);
            cacheNode.setCacheIP("192.168.1.10" + i);
            Long hashValue = hash(cacheNode.getCacheName());
            cacheNode.setHashValue(hashValue);
            int index = getNodeIndex(hashValue);
            if (index == 0) {
                cacheNodeList.add(cacheNode);
            } else {
                cacheNodeList.add(index, cacheNode);
            }

//            printList();
        }

        printList();
    }

    /**
     * 存数据
     *
     * @param data
     */
    public void putData(String data) {
        Long hashValue = hash(data);
        int index = getNodeIndex(hashValue);
        if (index == cacheNodeList.size()) {
            index = 0;
        }
//        log.info("data:{}[{}] put into ====>{}", data, hashValue, cacheNodeList.get(index));
    }

    public static void main(String[] args) {
        CacheManage c = new CacheManage();
//        c.initCacheNode(10);
////        System.out.println(c.hash("lisi"));
////        System.out.println(c.hash("wangwu"));
////        System.out.println(c.hash("tianqi"));
////        System.out.println(c.hash("lichangtong"));
////
////
////        System.out.println(c.cacheNodeList.get(c.getNodeIndex(c.hash("lichangtong"))).getCacheIP());
////        System.out.println(c.MAX_CIRCLE+"---");
//        System.out.println(c.hash("lichangtong"));

        System.out.println(3&5);
    }
}
