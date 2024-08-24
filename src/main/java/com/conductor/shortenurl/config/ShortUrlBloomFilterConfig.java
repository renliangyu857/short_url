package com.conductor.shortenurl.config;

import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shiyufei
 */
@Configuration
public class ShortUrlBloomFilterConfig {

    @Bean
    public RBloomFilter<String> shortUrlBloomFilter(RedissonClient redissonClient) {
        RBloomFilter<String> shortUrlBloomFilter = redissonClient.getBloomFilter("shortURL");
        shortUrlBloomFilter.tryInit(10000000L, 0.01);
        return shortUrlBloomFilter;
    }

}
