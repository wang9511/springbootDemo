package com.example.film.mapper;

import com.example.film.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieMapperCustom {
    List<Movie> randomList (Integer count);
    String url(Integer url);
    Movie findById(Integer id);
    List<Movie> getAll();
    void deleteById(Integer id);
    void updateMovie(Movie movie);
    void insertMovie(Movie movie);
    List<Movie> findMovieByName(String name);
}
