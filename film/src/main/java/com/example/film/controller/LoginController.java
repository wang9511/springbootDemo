package com.example.film.controller;

import com.example.film.model.Manager;
import com.example.film.service.CheckUserAndPasswordService;
import com.example.film.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;

@Controller
public class LoginController {

    @Resource
    private MovieService movieService;

    @Resource
    private CheckUserAndPasswordService checkUserAndPasswordService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/checkUAP", method ={RequestMethod.POST, RequestMethod.GET})
    @PreAuthorize("hasAnyRole('admin')")
    public ModelAndView checkUserAndPassword(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("userMainMenu");
        mv.addObject("allMovie", movieService.getAll());
        logger.info("admin log in");
        return mv;
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "sdfdsf";
    }
}
