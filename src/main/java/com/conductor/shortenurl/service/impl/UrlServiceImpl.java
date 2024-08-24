package com.conductor.shortenurl.service.impl;

import cn.hutool.core.util.StrUtil;
import com.conductor.shortenurl.entity.UrlMapping;
import com.conductor.shortenurl.exceptions.RetryLimitExceededException;
import com.conductor.shortenurl.generate.ShortUrlGenerateFactory;
import com.conductor.shortenurl.repository.UrlRepository;
import com.conductor.shortenurl.service.UrlService;
import com.conductor.shortenurl.util.HashUtil;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;

import org.apache.commons.lang3.RandomUtils;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author renliangyu857
 */
@Service
public class UrlServiceImpl implements UrlService {

    private static Logger logger = LoggerFactory.getLogger(UrlServiceImpl.class);

    private static final int MAXRETRIES = 3;

    private static final String LOCK_URL = "lock:getLongUrlByShortUrl:";

    private static final int CACHE_NULL_TTL = 10;

    @Autowired
    private UrlRepository urlRepository;

    @Resource
    private RBloomFilter<String> urlBloomFilter;

    @Resource
    private RBloomFilter<String> shortUrlBloomFilter;

    @Resource
    RedisTemplate<String, String> redisTemplate;

    @Resource
    Redisson redisson;


    @Override
    public String generateShortUrl(String longUrl, String type, Integer timeout) {
        String shortUrl = ShortUrlGenerateFactory.getInstance(type).generate(longUrl);  // MurmurHash 生成短链接

        // 布隆过滤器判断长链接存在 && 生成的短链接去查询的长链接与用户输入的长链接相等
        if (urlBloomFilter.contains(longUrl) && Objects.equals(redisTemplate.opsForValue().get(shortUrl), longUrl)) {
                return shortUrl;
        }

        // 否， 短链接布隆过滤器判断短链不存在
        if (shortUrlBloomFilter.contains(shortUrl)) {
            try {
                shortUrl = generateUniqueShortUrl(longUrl, type);
            } catch (RetryLimitExceededException e) {
                System.out.println(e.getMessage());
            }
        }

        // 入数据库
        try {
            urlRepository.saveUrlMapping(new UrlMapping(shortUrl, longUrl, timeout));
        } catch (DuplicateKeyException e) {
            // 判断冲突则重试拼接时间戳再Hash（兜底策略， 布隆过滤器丢失时才可能发生）
            // 也有可能会继续冲突， 也可以增加一个最大重试次数
            shortUrl = HashUtil.base62MurmurHash(longUrl + System.currentTimeMillis());
            urlRepository.saveUrlMapping(new UrlMapping(shortUrl, longUrl, timeout));
        }

        // 入布隆过滤器
        urlBloomFilter.add(longUrl);
        shortUrlBloomFilter.add(shortUrl);

        return shortUrl;
    }

    @Override
    public String getLongUrlByShortUrl(String shortUrl) {
        // 查找Redis中是否有缓存
        String longUrl = redisTemplate.opsForValue().get(shortUrl);
        if (StrUtil.isNotBlank(longUrl)) {
            return longUrl;
        }

        // Redis没有缓存，查询短链接布隆过滤器中是否存在
        if (!shortUrlBloomFilter.contains(shortUrl)) {
            return null;
        }

        // Redis 查询空字符串
        if (Objects.nonNull(longUrl)){
            return null;
        }

        // 获取分布式锁
        RLock lock = redisson.getLock(LOCK_URL + shortUrl);
        lock.lock();
        try {
            // double check
            longUrl = redisTemplate.opsForValue().get(shortUrl);
            if (longUrl != null) {
                return longUrl;
            }

            // Redis没有缓存，从数据库查找
            UrlMapping urlMapping = urlRepository.getLongUrlByShortUrl(shortUrl);

            // 数据库有此短链接，添加缓存
            if (urlMapping != null) {
                long remainTime = urlMapping.getExpireTime().getTime() - new Date().getTime();
                if (remainTime > 0) {
                    redisTemplate.opsForValue().set(urlMapping.getShortUrl(), urlMapping.getLongUrl(),(long) (remainTime * RandomUtils.nextDouble(0.2, 0.4)), TimeUnit.SECONDS);
                    return urlMapping.getLongUrl();
                } else {  // 过期数据，惰性删除
                    urlRepository.deleteUrlMapping(urlMapping.getShortUrl());
                    redisTemplate.delete(shortUrl);
                }
            }

            // 缓存空字符串
            redisTemplate.opsForValue().set(shortUrl, "", CACHE_NULL_TTL, TimeUnit.SECONDS);
            return null;
        } finally {
            lock.unlock();
        }
    }

    public String generateUniqueShortUrl(String longUrl, String type) throws RetryLimitExceededException {

        for (int i = 0; i < MAXRETRIES; i++) {
            String newShortUrl = ShortUrlGenerateFactory.getInstance(type).generate(longUrl + System.currentTimeMillis());
            if (shortUrlBloomFilter.contains(newShortUrl)){
                continue;
            }
            return newShortUrl;
        }

        throw new RetryLimitExceededException("Failed to generate a unique short link after 3 retries.");
    }





}
