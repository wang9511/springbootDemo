package com.example.film.service;

import com.example.film.mapper.MovieMapperCustom;
import com.example.film.model.Movie;
import org.omg.CORBA.TIMEOUT;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class MovieService {
    @Resource
    private MovieMapperCustom movieMapperCustom;

    @Resource
    private RedisTemplate redisTemplate;

    public List<Movie> randomList(Integer count){
        return movieMapperCustom.randomList(count);
    }

    public String url(Integer url){
        return movieMapperCustom.url(url);
    }

    public Movie findById(Integer id){
        ValueOperations <String,Movie> operations = redisTemplate.opsForValue();
        String key = "movie_"+id;
        boolean hasKey = redisTemplate.hasKey(key);
        if(hasKey){
            Movie movie = operations.get(key);
            System.out.println("存在");
            return movie;
        }
        Movie movie = movieMapperCustom.findById(id);
        operations.set(key, movie, 1000, TimeUnit.SECONDS);
        System.out.println("不存在");
        return movie;
    }

    public List<Movie> getAll(){
        return movieMapperCustom.getAll();
    }

    public void deleteById(Integer id){
        movieMapperCustom.deleteById(id);
    }

    public void updateMovie(Movie movie){
        movieMapperCustom.updateMovie(movie);
    }

    public void insertMovie(Movie movie){
        movieMapperCustom.insertMovie(movie);
    }

    public List<Movie> findMovieByName(String name){
        ValueOperations<String, List<Movie>> operations = redisTemplate.opsForValue();
        Boolean hasKey = redisTemplate.hasKey(name);
        if(hasKey){
            List<Movie> list = operations.get(name);
            return list;
        }
        List<Movie> list = movieMapperCustom.findMovieByName(name);
        operations.set(name, list, 100, TimeUnit.SECONDS);
        return list;
    }
}
