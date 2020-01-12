package org.ct.seckill.redis;

/**
 * 设置唯一标识的redis存储的抽象接口
 */
public interface KeyPrefix {

    int expireSecond();

    String getPrefix();

}
