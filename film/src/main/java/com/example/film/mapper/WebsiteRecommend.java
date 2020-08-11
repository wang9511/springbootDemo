package com.example.film.mapper;

import com.example.film.model.Website;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebsiteRecommend {
    List<Website> getAllWebsite();
    String getName(Integer id);
}
