package com.conductor.shortenurl.util;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class Cache_NUll {
    @Resource
    RedisTemplate<String, String> redisTemplate;

    @Test
    public void nullCache() {
        // Value must not be null!
        redisTemplate.opsForValue().set("apple", "", 10, TimeUnit.SECONDS);
    }
}
