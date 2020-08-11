package com.example.film.mapper;

import com.example.film.model.Movie;
import com.example.film.model.MovieAndDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieAndDetailMapperCustom {
    MovieAndDetail findByName(String name);
}
