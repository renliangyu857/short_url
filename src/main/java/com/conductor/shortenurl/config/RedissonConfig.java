package com.conductor.shortenurl.config;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private String port;

    //添加redisson的bean
    @Bean
    public Redisson redisson() {
        Config config = new Config();
        //此示例是单机的，可以是主从、sentinel、集群等模式
        SingleServerConfig singleServerConfig = config.useSingleServer().setAddress("redis://" + host + ":" + port);
        return (Redisson)Redisson.create(config);
    }

}