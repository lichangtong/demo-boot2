package com.example.demo.demoboot2.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

/**
 * @Auther: lichangtong
 * @Date: 2019-02-20 13:26
 * @Description:
 */
@Component
@ConfigurationProperties("spring.redis.jedis.pool")
public class RedisConfig {

    // Redis数据库索引（默认为0）
    private Integer database = 9;
    // Redis服务器地址
    private String host = "localhost";
    // Redis服务器连接端口
    private Integer port = 6379;
    // Redis服务器连接密码（默认为空）
    private String password = "epwk";
    // 连接池最大连接数（使用负值表示没有限制）
    private Integer maxActive = 1024;
    // 连接池最大阻塞等待时间（使用负值表示没有限制）
    private Integer maxWait = 10000;
    //资源池中最大连接数 默认值8 建议值
    private Integer maxTotal;
    // 连接池中的最大空闲连接
    private Integer maxIdle = 200;
    // 连接池中的最小空闲连接
    private Integer minIdle = 0;
    // 连接超时时间（毫秒）
    private Integer timeout = 10000;
    //redis配置结束
    private Boolean blockWhenExhausted = true;
    private Boolean testOnBorrow = true;
    private Boolean testOnReturn = true;
    private long maxWaitMillis = 3000;

    public Integer getDatabase() {
        return database;
    }

    public void setDatabase(Integer database) {
        this.database = database;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(Integer maxActive) {
        this.maxActive = maxActive;
    }

    public Integer getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(Integer maxWait) {
        this.maxWait = maxWait;
    }

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
    }

    public Integer getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    public Integer getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Boolean getBlockWhenExhausted() {
        return blockWhenExhausted;
    }

    public void setBlockWhenExhausted(Boolean blockWhenExhausted) {
        this.blockWhenExhausted = blockWhenExhausted;
    }

    public long getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public Boolean getTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(Boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public Boolean getTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(Boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public void setMaxWaitMillis(long maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    @Bean
    private JedisPool initJedisPool() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxIdle(this.maxIdle);
        config.setMaxTotal(this.maxTotal);
        config.setBlockWhenExhausted(this.blockWhenExhausted);
        config.setMinIdle(this.minIdle);
        config.setMaxWaitMillis(this.maxWaitMillis);
        config.setTestOnBorrow(this.testOnBorrow);
        config.setTestOnReturn(this.testOnReturn);

        return new JedisPool(config, this.host, this.port, this.timeout, this.password,database);
    }
}
