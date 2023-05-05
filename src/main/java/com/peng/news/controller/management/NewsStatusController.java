package com.peng.news.controller.management;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 新闻状态查询接口
 * @author shan
 * @version 1.0
 * @date 2021/3/24 10:38
 */
@Api(tags = "新闻状态查询接口")
@RestController
@RequestMapping("/management/news/status")
public class NewsStatusController {
    @GetMapping("/hello")
    public String hello(){
        return this.getClass().getName() + " hello";
    }
}
