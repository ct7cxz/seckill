package org.ct.seckill.redis;

import io.swagger.models.auth.In;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "redis")
@Component
@Data
public class RedisConfig {

    private String host;
    private Integer port;
    private Integer timeout;
    private Integer maxActive;
    private Integer maxIdle;
    private Integer maxWait;

    /*@Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.post}")
    private String post;
    @Value("${spring.redis.timeout}")
    private String timeout;
    @Value("${spring.redis.jedis.pool.max-active}")
    private String maxActive;
    @Value("${spring.redis.jedis.pool.max-idle}")
    private String maxIdle;
    @Value("${spring.redis.jedis.pool.max-wait}")
    private String maxWait;
    */
}
