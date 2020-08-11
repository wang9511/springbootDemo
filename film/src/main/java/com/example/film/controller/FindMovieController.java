package com.example.film.controller;

import com.example.film.service.MovieAndDetailService;
import com.example.film.service.MovieDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.io.Serializable;
import java.sql.PreparedStatement;

@Controller
public class FindMovieController implements Serializable {
    @Resource
    private MovieAndDetailService movieAndDetailService;

    @Resource
    private MovieDetailService movieDetailService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/search")
    @PreAuthorize("hasRole('admin')")
    public ModelAndView findByName(@PathParam("name") String name){
        ModelAndView mv = new ModelAndView();
        mv.addObject("movieDetail", movieAndDetailService.findByName(name));
        mv.addObject("urlList",movieDetailService.findByName(name));
        mv.setViewName("movieDetail");
        logger.info(String.format("find movie by moive name: %s", name));
        return mv;
    }
}
