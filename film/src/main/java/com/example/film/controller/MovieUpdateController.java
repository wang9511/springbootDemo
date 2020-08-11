package com.example.film.controller;

import com.example.film.model.Manager;
import com.example.film.model.Movie;
import com.example.film.service.CheckUserAndPasswordService;
import com.example.film.service.MovieService;
import com.fasterxml.jackson.datatype.jsr310.ser.YearSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.websocket.server.PathParam;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MovieUpdateController {

    @Resource
    private MovieService movieService;

    @Resource
    private CheckUserAndPasswordService checkUserAndPasswordService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/userMenu")
    @PreAuthorize("hasRole('admin')")
    public ModelAndView userMenu(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("userMainMenu");
        mv.addObject("allMovie", movieService.getAll());
        logger.info("admin visits the manager menu");
        return mv;
    }

    @GetMapping("/find/{id}")
    @PreAuthorize("hasRole('admin')")
    public ModelAndView findById(@PathVariable("id") Integer id){
        Movie movie = movieService.findById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("movie", movie);
        mv.setViewName("updateMovieInfo");
        logger.info(String.format("get movie by movie id: %d", id));
        return mv;
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('admin')")
    public ModelAndView deleteById(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("userMainMenu");
        movieService.deleteById(id);
        mv.addObject("allMovie", movieService.getAll());
        logger.info(String.format("delete movie by movie id: %d", id));
        return mv;
    }

    @GetMapping("/update")
    @PreAuthorize("hasRole('admin')")
    public String updateById(@PathParam("id") Integer id, @PathParam("content") String content, @PathParam("hot")Integer hot, @PathParam("imageName")String imageName, @PathParam("name") String name, @PathParam("publishDate")Date publishDate, @PathParam("title")String title ){
        Movie movie = new Movie();
        movie.setId(id);
        movie.setHot(hot);
        movie.setContent(content);
        movie.setImageName(imageName);
        movie.setName(name);
        movie.setTitle(title);
        movie.setPublishDate(publishDate);
        movieService.updateMovie(movie);
        logger.info(String.format("update movie set content=%s, hot=%d, imageName=%s, name=%s, title=%s", content, hot, imageName, name, title));
        return "forward:/userMenu";
    }

    @GetMapping("/insertMovie")
    @PreAuthorize("hasRole('admin')")
    public String insertMovie(@PathParam("id") Integer id, @PathParam("content") String content, @PathParam("hot")Integer hot, @PathParam("imageName")String imageName, @PathParam("name") String name, @PathParam("publishDate")Date publishDate, @PathParam("title")String title ){
        Movie movie = new Movie();
        movie.setId(id);
        movie.setHot(hot);
        movie.setContent(content);
        movie.setImageName(imageName);
        movie.setName(name);
        movie.setTitle(title);
        movie.setPublishDate(publishDate);
        movieService.insertMovie(movie);
        logger.info(String.format("insert into movie (content, hot, imageName, name, title) values(%s, %d, %s, %s, %s)", content, hot, imageName, name, title));
        return "redirect:/userMenu";
    }

    @GetMapping("/findMovieByName")
    @PreAuthorize("hasRole('admin')")
    public ModelAndView findMovieByName(@PathParam("name") String name){
        ModelAndView mv = new ModelAndView();
        mv.addObject("movieList",movieService.findMovieByName(name));
        mv.setViewName("findMovieResult");
        logger.info(String.format("find movie by name: %s", name));
        return mv;
    }

    //只需要加上下面这段即可，注意不能忘记注解
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {

        //转换日期
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
    }


}
