package com.example.film.service;

import com.example.film.mapper.MovieAndDetailMapperCustom;
import com.example.film.model.Movie;
import com.example.film.model.MovieAndDetail;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@Service
public class MovieAndDetailService implements Serializable {
    @Resource
    private MovieAndDetailMapperCustom movieAndDetailMapperCustom;

    @Resource
    private RedisTemplate redisTemplate;

    public MovieAndDetail findByName(String name){
        ValueOperations<String, MovieAndDetail> operations = redisTemplate.opsForValue();
        String key = name;
        Boolean hasKey = redisTemplate.hasKey(key);
        if(hasKey){
            MovieAndDetail movieAndDetail = operations.get(key);
            return movieAndDetail;
        }
        MovieAndDetail movieAndDetail = movieAndDetailMapperCustom.findByName(name);;
        operations.set(key, movieAndDetail, 100, TimeUnit.SECONDS);
        return movieAndDetail;
    }
}
