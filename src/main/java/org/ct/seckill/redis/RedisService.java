package org.ct.seckill.redis;

import com.alibaba.fastjson.JSON;
import org.ct.seckill.domain.MiaoshaUser;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


@Component
@Configuration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisService {

    @Autowired
    private JedisPool jedisPool;

    /**
     * 获取键值
     *
     * @param keyPrefix
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T get(KeyPrefix keyPrefix, String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = keyPrefix.getPrefix() + key;
            String str = jedis.get(realKey);
            return stringToBean(str, clazz);
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 字符串转换成bean对象格式
     * @param str
     * @param clazz
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    private <T> T stringToBean(String str, Class<T> clazz) {
        if (str == null || str.length() <= 0 || clazz == null) {
            return null;
        } else if (clazz == String.class) {
            return (T) str;
        } else if (clazz == int.class || clazz == Integer.class) {
            T t = (T) Integer.valueOf(str);
            return t;
        } else if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(str);
        } else
            return JSON.parseObject(str, clazz);
    }

    /**
     * 设置键值
     *
     * @param keyPrefix
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public <T> boolean set(KeyPrefix keyPrefix, String key, T value) {
        Jedis jedis = null;
        try {
            jedis = this.jedisPool.getResource();
            String realKey = keyPrefix.getPrefix() + key;
            String str = beanToString(value);
            if (str == null || str.length() <= 0) {
                return false;
            } else {
                int seconds = keyPrefix.expireSecond();
                if (seconds <= 0) {
                    jedis.set(realKey, str);
                } else {
                    jedis.setex(realKey, seconds, str);
                }

                return true;
            }

        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * bean对象转换成字符串格式
     * @param value
     * @param <T>
     * @return
     */
    private <T> String beanToString(T value) {
        if (value == null) {
            return null;
        } else if (value.getClass() == String.class) {
            return value.toString();
        } else if (value.getClass() == int.class || value.getClass() == Integer.class) {
            return "" + value;
        } else if (value.getClass() == long.class || value.getClass() == Long.class) {
            return "" + value;
        } else
            return JSON.toJSONString(value);
    }

    private void returnToPool(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    /**
     * 判断键值是否存在
     *
     * @param keyPrefix
     * @param key
     * @param <T>
     * @return
     */
    public <T> boolean exists(KeyPrefix keyPrefix, String key) {
        Jedis jedis = null;
        try {
            jedis = this.jedisPool.getResource();
            String realKey = keyPrefix.getPrefix() + key;
            return jedis.exists(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 增加一个键值
     * @param keyPrefix
     * @param key
     * @param <T>
     * @return
     */
    public <T> Long incr(KeyPrefix keyPrefix, String key) {
        Jedis jedis = null;
        try {
            jedis = this.jedisPool.getResource();
            String realKey = keyPrefix.getPrefix() + key;
            return jedis.incr(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 删除一个键值
     * @param keyPrefix
     * @param key
     * @param <T>
     * @return
     */
    public <T> Long decr(KeyPrefix keyPrefix, String key) {
        Jedis jedis = null;
        try {
            jedis = this.jedisPool.getResource();
            String realKey = keyPrefix.getPrefix() + key;
            return jedis.decr(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

}
