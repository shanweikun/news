package com.peng.news.controller.frontend;

import com.peng.news.model.Result;
import com.peng.news.model.vo.NewsVO;
import com.peng.news.service.NewsDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 新闻详情相关接口
 * @author PENG
 * @version 1.0
 * @date 2021/4/24 0:15
 */
@Api(tags = "新闻详情相关接口")
@RestController
@RequestMapping("/frontend/newDetail")
public class NewsDetailController {

    @Autowired
    NewsDetailService newsDetailService;

    @ApiOperation("获取某个新闻id的新闻详情")
    @GetMapping("/{newsId}")
    public Result<NewsVO> getOneNews(@PathVariable int newsId) {
        return Result.success(newsDetailService.getOneNews(newsId));
    }

}
