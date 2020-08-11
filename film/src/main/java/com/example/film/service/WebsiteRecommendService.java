package com.example.film.service;

import com.example.film.mapper.WebsiteRecommend;
import com.example.film.model.Website;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WebsiteRecommendService {
    @Resource
    private WebsiteRecommend websiteRecommend;

    public List<Website> getAllWebsite(){
        return websiteRecommend.getAllWebsite();
    }

    public String getName(Integer id){
        return websiteRecommend.getName(id);
    }
}
