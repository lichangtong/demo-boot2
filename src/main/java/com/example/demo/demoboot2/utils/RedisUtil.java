package com.example.demo.demoboot2.utils;

import com.alibaba.fastjson.JSON;
import com.google.inject.internal.util.Lists;
import com.google.inject.internal.util.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.*;


/**
 * @Auther: lichangtong
 * @Date: 2019-02-20 14:31
 * @Description:
 */
@Component
public class RedisUtil {
    private Logger logger = LogManager.getLogger(RedisUtil.class);
    @Autowired
    private JedisPool jedisPool;

    /**
     * 设置保存字符串
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        set(key, value, -1);
    }

    /**
     * 置保存字符串 及过期时间
     *
     * @param key
     * @param value
     * @param expire
     */
    public void set(String key, String value, int expire) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
            setExpire(key, expire);
        } catch (Exception e) {
            logger.error("KEY：" + key + " 保存出现错误");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 获取字符串
     *
     * @param key
     * @return
     */
    public String get(String key) {
        String value = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            logger.error("KEY：" + key + " 获取值时出现错误");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }

    public String getHash(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hget(key, field);
        } catch (Exception e) {
            logger.error("KEY：" + key + " 获取值时出现错误");
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Map<String, String> getMap(String key) {

        return getMapEntity(key, String.class);
    }

    public <V> Map<String, V> getMapEntity(String key, Class<V> entityClass) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Map<String, String> map = jedis.hgetAll(key);
            Set<Map.Entry<String, String>> entry = map.entrySet();
            Map<String, V> value = Maps.newHashMap();
            for (Iterator<Map.Entry<String, String>> ite = entry.iterator(); ite.hasNext(); ) {
                Map.Entry<String, String> kv = ite.next();
                value.put(kv.getKey(), JSON.parseObject(kv.getValue(), entityClass));
            }
            return value;
        } catch (Exception e) {
            logger.error("KEY：" + key + " 获取值时出现错误");
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 设置hash 信息
     *
     * @param key
     * @param filed
     * @param value
     */
    public void setHash(String key, String filed, String value) {
        this.setHash(key, filed, value, -1);
    }

    /**
     * 设置hash 信息及过期时间
     *
     * @param key
     * @param field
     * @param value
     * @param expire
     */
    public void setHash(String key, String field, String value, int expire) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.hset(key, field, value);
            setExpire(key, expire);
        } catch (Exception e) {
            logger.error("KEY：" + key + " 获取值时出现错误");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 设置过期时间
     *
     * @param key
     * @param expire
     */
    private void setExpire(String key, int expire) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (StringUtils.isNotBlank(key) && expire > 0) {
                jedis.expire(key, expire);
            }
        } catch (Exception e) {
            logger.error("KEY：" + key + " 设置过期时间时出现错误");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    /**
     * 设置hash 信息
     *
     * @param key
     * @param map
     */
    public void setHashMap(String key, Map<String, Object> map) {
        setHashMap(key, map, -1);
    }

    /**
     * 设置hash 数据及过期时间
     *
     * @param key
     * @param map
     * @param expire
     */
    public void setHashMap(String key, Map<String, Object> map, int expire) {

        Set<Map.Entry<String, Object>> entry = map.entrySet();
        Iterator<Map.Entry<String, Object>> ite = entry.iterator();
        while (ite.hasNext()) {
            Map.Entry<String, Object> kv = ite.next();
            if (kv.getValue() instanceof String) {
                setHash(key, kv.getKey(), String.valueOf(kv.getValue()));
            } else if (kv.getValue() instanceof List) {
                setHash(key, kv.getKey(), JSON.toJSONString(kv.getValue()));
            } else {
                setHash(key, kv.getKey(), JSON.toJSONString(kv.getValue()));
            }
        }
        setExpire(key, expire);

    }

    public void setList(String key, String... values) {
        setList(key, -1, values);
    }

    public void setList(String key, int expire, String... values) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            for (String value : values) {
                jedis.rpush(key, value);
            }
            setExpire(key, expire);
        } catch (Exception e) {
            logger.error("KEY：" + key + " 保存集合时出现错误");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public void setList(String key, List<Object> lists) {
        setList(key, lists, -1);
    }

    public void setList(String key, List<Object> lists, int expire) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            for (Object obj : lists) {
                if (obj instanceof String) {
                    jedis.rpush(key, String.valueOf(obj));
                } else {
                    jedis.rpush(key, JSON.toJSONString(obj));
                }
            }
            setExpire(key, expire);
        } catch (Exception e) {
            logger.error("KEY：" + key + " 保存集合时出现错误");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 获取集合 List<String></>
     *
     * @param key
     * @return
     */
    public List<String> getListString(String key) {
        return getListObject(key, String.class);
    }

    /**
     * 获取 List<T></T>
     *
     * @param key
     * @param entityClass
     * @param <T>
     * @return
     */
    public <T> List<T> getListObject(String key, Class<T> entityClass) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            List<String> listString = jedis.lrange(key, 0, -1);
            List<T> listT = Lists.newArrayList();
            for (String str : listString) {
                listT.add(JSON.parseObject(str, entityClass));
            }
            return listT;
        } catch (Exception e) {
            logger.error("KEY：" + key + " 获取集合时出现错误");
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 判断是否存在
     *
     * @param key
     * @return
     */
    public boolean existKey(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        } catch (Exception e) {
            logger.error("KEY：" + key + "判断是否存在时出现错误");
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 删除数据
     *
     * @param key
     */
    public void deleteKey(String key) {
        deleteKey(new String[]{key});
    }


    /**
     * 删除数据
     *
     * @param listKey
     */
    public void deleteKey(List<String> listKey) {
        for (String key : listKey) {
            deleteKey(key);
        }
    }

    /**
     * 删除数据
     *
     * @param keys
     */
    public void deleteKey(String... keys) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.del(keys);
        } catch (Exception e) {
            logger.error("KEY：" + JSON.toJSONString(keys) + "删除时出现错误");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * SET 添加
     *
     * @param key
     * @param values
     */
    public void addSet(String key, String... values) {
        addSet(key, -1, values);
    }

    /**
     * 获取Set
     *
     * @param key
     * @return
     */
    public Set<String> getSet(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.smembers(key);
        } catch (Exception e) {
            logger.error("KEY：" + key + "获取SET时出现错误");
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * SET 添加
     *
     * @param key
     * @param values
     */
    public void addSet(String key, int expire, String... values) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.sadd(key, values);
            setExpire(key, expire);
        } catch (Exception e) {
            logger.error("KEY：" + key + "添加SET时出现错误");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * ZSET 添加
     *
     * @param key
     * @param member
     * @param socre
     */
    public void addZSet(String key, String member, double socre) {
        addZSet(key, member, socre, -1);
    }

    /**
     * ZSET 添加
     *
     * @param key
     * @param member
     * @param socre
     */
    public void addZSet(String key, String member, double socre, int expire) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.zadd(key, socre, member);
            setExpire(key, expire);
        } catch (Exception e) {
            logger.error("KEY：" + key + "添加ZSET时出现错误");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * @param key
     */
    public Set<String> getZSet(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.zrange(key, 0, -1);
        } catch (Exception e) {
            logger.error("KEY：" + key + "获取ZSET时出现错误");
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

    }


    /**
     * 获取
     * Zset by score 值递减(从大到小)次序排列。
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Set<String> getZrangeByScore(String key, double min, double max) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.zrangeByScore(key, min, max);
        } catch (Exception e) {
            logger.error("KEY：" + key + "获取ZSET时出现错误");
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * score值递减(从大到小)次序排列。
     * @param key
     * @param max
     * @param min
     * @param entityClass
     * @param <T>
     * @return
     */
    public <T> List<T> zrevrangeByScore(String key, double max, double min,
                                        Class<T> entityClass) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Set<String> set = jedis.zrevrangeByScore(key, max, min);
            List<T> list = new ArrayList<T>();
            for (String str : set) {
                list.add(JSON.parseObject(str, entityClass));
            }
            return list;
        } catch (Exception e) {
            logger.error("KEY：" + key + "获取ZSET时出现错误");
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * score值递减(从大到小)次序排列。
     * @param key
     * @param max
     * @param min
     * @param offset
     * @param count
     * @param entityClass
     * @param <T>
     * @return
     */
    public <T> List<T> getZrevrangeByScore(String key, double max, double min,
                                           int offset, int count, Class<T> entityClass) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Set<String> set = jedis.zrevrangeByScore(key, max, min, offset, count);
            List<T> list = new ArrayList<T>();
            for (String str : set) {
                list.add(JSON.parseObject(str, entityClass));
            }
            return list;
        } catch (Exception e) {
            logger.error("KEY：" + key + "获取ZSET时出现错误");
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * ZSET 添加
     *
     * @param key
     * @param scoreMembers
     */

    public void addZSet(String key, Map<String, Double> scoreMembers) {
        addZSet(key, scoreMembers, -1);
    }

    /**
     * ZSET 添加
     *
     * @param key
     * @param scoreMembers
     * @param expire
     */
    public void addZSet(String key, Map<String, Double> scoreMembers, int expire) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.zadd(key, scoreMembers);
            setExpire(key, expire);
        } catch (Exception e) {
            logger.error("KEY：" + key + "添加ZSET时出现错误");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

}
