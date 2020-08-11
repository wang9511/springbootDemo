package com.example.film.service;

import com.example.film.mapper.MovieDetailMapperCustom;
import com.example.film.mapper.MovieMapperCustom;
import com.example.film.model.Movie;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class MovieDetailService {
    @Resource
    private MovieDetailMapperCustom movieDetailMapperCustom;

    @Resource
    private RedisTemplate redisTemplate;

    public List<String> findById(Integer id){
        ValueOperations <Integer, List<String>> operations = redisTemplate.opsForValue();
        Boolean hasKey = redisTemplate.hasKey(id);
        if(hasKey){
            List<String> list = operations.get(id);
            return list;
        }
        List<String> list = movieDetailMapperCustom.findById(id);
        operations.set(id, list, 100, TimeUnit.SECONDS);
        return list;
    }

    public List<String> findByName(String name){
        return movieDetailMapperCustom.findByName(name);
    }
}
