package com.example.demo.demoboot2.hash;

/**
 * @Auther: lichangtong
 * @Date: 2019-09-20 15:08
 * @Description:
 */
public class CacheNode {
    private String cacheName;
    private String cacheIP;
    private Long hashValue;

    public String getCacheName() {
        return cacheName;
    }

    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    public String getCacheIP() {
        return cacheIP;
    }

    public void setCacheIP(String cacheIP) {
        this.cacheIP = cacheIP;
    }

    public Long getHashValue() {
        return hashValue;
    }

    public void setHashValue(Long hashValue) {
        this.hashValue = hashValue;
    }
}
