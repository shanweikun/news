package com.peng.news.controller.management;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公告管理接口
 * @author shan
 * @version 1.0
 * @date 2021/3/24 10:46
 */
@Api(tags = "公共管理接口")
@RestController
@RequestMapping("/management/notice")
public class AnnouncementController {

    @GetMapping("/hello")
    public String hello(){
        return this.getClass().getName() + " hello";
    }
}
