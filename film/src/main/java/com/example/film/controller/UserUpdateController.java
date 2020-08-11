package com.example.film.controller;

import com.example.film.service.CheckUserAndPasswordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;

@Controller
public class UserUpdateController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private CheckUserAndPasswordService checkUserAndPasswordService;

    @GetMapping("/addUser")
    @PreAuthorize("hasRole('admin')")
    public String addUser(@PathParam("username")String username, @PathParam("password") String password){
        checkUserAndPasswordService.addUser(username, password);
        logger.warn(String.format("add User,username: %s,password: %s", username, password));
        return "redirect:/userMenu";
    }
}
