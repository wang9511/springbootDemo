package com.example.film.service;

import com.example.film.mapper.CheckUserAndPassword;
import com.example.film.model.Manager;
import com.mysql.jdbc.jdbc2.optional.SuspendableXAConnection;
import org.mybatis.generator.runtime.dynamic.sql.elements.BasicSelectManyMethodGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class CheckUserAndPasswordService {
    @Resource
    private CheckUserAndPassword checkUserAndPassword;

    @Resource
    private RedisTemplate redisTemplate;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public Manager checkUAP(String username, String password){
        ValueOperations <String, Manager> operations = redisTemplate.opsForValue();
        String key = username + password;
        Boolean hasKey = redisTemplate.hasKey(key);
        if(hasKey){
            Manager manager = operations.get(key);
            System.out.println("存在");
            logger.info("find admin in redis cache");
            return manager;
        }
        Manager manager = checkUserAndPassword.checkUAP(username, password);
        operations.set(key, manager, 1000, TimeUnit.SECONDS);
        System.out.println("不存在");
        logger.info("get admin from database and set it to redis cache");
        return manager;
    }

    public void addUser(String username, String password){
        checkUserAndPassword.addUser(username, password);
    }
}
