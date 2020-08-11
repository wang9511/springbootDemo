package com.example.film.springtask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Scheduled(cron = "0 0/10 * ? * ?")
    public void test(){
        logger.info("过了2分钟，定时添加日志");
    }
}
