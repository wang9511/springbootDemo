package com.example.film.redisConfig.Service;

import com.example.film.redisConfig.RedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

public class RedisServiceImpl implements RedisService {
    private RedisTemplate redisTemplate;

    ValueOperations<String, String> operations = redisTemplate.opsForValue();

    @Override
    public void set(String key, String value) {
        operations.set(key, value);
    }

    @Override
    public String get(String key) {
        return operations.get(key);
    }

    @Override
    public boolean expire(String key, long expire) {
        return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    @Override
    public void remove(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public Long increment(String key, long delta) {
        return operations.increment(key, delta);
    }
}
