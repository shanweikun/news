package com.peng.news.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {


    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    //3.添加定时任务
    @Scheduled(cron = "0 0 6 * * ?")
    private void configureTasks() {
        redisTemplate.delete("com.peng.news.model.vo.NewsPO::recommendHotNews");
    }
}