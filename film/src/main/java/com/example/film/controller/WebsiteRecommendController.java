package com.example.film.controller;

import com.example.film.service.WebsiteRecommendService;
import com.mysql.jdbc.log.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;

@Controller
public class WebsiteRecommendController {
    @Resource
    private WebsiteRecommendService websiteRecommendService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/websiteList")
    @PreAuthorize("hasAnyRole('admin')")
    public ModelAndView getAllWebsite(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("websiteList", websiteRecommendService.getAllWebsite());
        mv.setViewName("websiteRecommend");
        logger.info("admin get all website urls");
        return mv;
    }

//    @RequestMapping(value = "/websiteList/id")
//    @PreAuthorize("hasRole('admin')")
//    public ModelAndView getNameById(@PathParam("id")Integer id){
//        ModelAndView mv = new ModelAndView();
//        mv.addObject("websiteList", websiteRecommendService.getName(id));
//        mv.setViewName("websiteRecommend");
//        logger.info(String.format("get website url by id:%d", id));
//        return mv;
//    }
}
