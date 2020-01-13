package org.ct.seckill.redis;

public class MiaoshaUserKey extends BasePrefix {

    public MiaoshaUserKey(int expireSecond,String prefix) {
        super(expireSecond,prefix);
    }

    public static String token = "tk";
}
