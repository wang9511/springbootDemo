package com.example.film.controller;

import com.example.film.model.Movie;
import com.example.film.service.MovieDetailService;
import com.example.film.service.MovieService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Resource
    private MovieService movieService;

    @Resource
    private MovieDetailService movieDetailService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/home")
    public ModelAndView homePage(){
        ModelAndView mv = new ModelAndView();
        List<Movie> list = movieService.getAll();
        mv.addObject("allMovie", list);
        mv.setViewName("homePage");
        logger.info("visit home page");;
        return mv;
    }

    @RequestMapping("/random")
    public ModelAndView randomList(){
        List<Movie> list = movieService.randomList(20);
        ModelAndView mv = new ModelAndView();
        mv.addObject("randomList", list);
        mv.setViewName("movieDetail");
        return mv;
    }

    @RequestMapping("/movie/{id}")
    public ModelAndView movieDetail(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView();
        mv.addObject("movieDetail",movieService.findById(id));
        mv.addObject("urlList",movieDetailService.findById(id));
        List<Movie> list = movieService.randomList(20);
        mv.addObject("randomList", list);
        mv.setViewName("movieDetail");
        logger.info("visit movie detail page");
        return mv;
    }


}
