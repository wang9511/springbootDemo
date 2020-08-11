package com.example.film.mapper;

import com.example.film.model.Manager;
import com.example.film.model.Website;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckUserAndPassword {
    Manager checkUAP(String username, String password);
    void addUser(String username, String password);
    List<Manager> getAllESProductList(@Param("id") Integer id);
}
