package com.xywei.redission.redission;


import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * redisson配置
 *
 * @author xywei
 */
@Configuration
public class RedissonManager {


    @Value("${spring.redis.host:localhost}")
    private String host;

    @Value("${spring.redis.port:6379}")
    private String port;

    @Bean
    public RedissonClient initRedisson() {
        RedissonClient redissonClient;
        Config config = new Config();
        config.setCodec(new org.redisson.client.codec.StringCodec());
        config.useSingleServer()
                .setAddress("redis://" + host + ":" + port)
                .setConnectionMinimumIdleSize(50)
                .setConnectionPoolSize(200);
        redissonClient = Redisson.create(config);
        return redissonClient;
    }
}