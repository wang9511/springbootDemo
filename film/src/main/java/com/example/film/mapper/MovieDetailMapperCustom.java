package com.example.film.mapper;

import com.example.film.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieDetailMapperCustom {
    List<String> findById(Integer id);
    List<String> findByName(String name);
}
