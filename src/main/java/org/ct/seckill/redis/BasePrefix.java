package org.ct.seckill.redis;

/**
 * 抽象方法，将接口模板具体化
 * 将获取key值方法具体化，方法调用时获取当前类名外加键值
 */
public abstract class BasePrefix implements KeyPrefix {

    private int expireSecond;

    private String prefix;

    public BasePrefix(String prefix) {
        this(0, prefix);
    }

    public BasePrefix(int expireSecond, String prefix) {
        this.expireSecond = expireSecond;
        this.prefix = prefix;
    }

    /**
     * 默认小于等于 0 为永不过期
     * @return
     */
    @Override
    public int expireSecond() {
        return expireSecond;
    }

    @Override
    public String getPrefix() {
        String str = getClass().getSimpleName();
        return str + ":" + prefix;
    }
}
