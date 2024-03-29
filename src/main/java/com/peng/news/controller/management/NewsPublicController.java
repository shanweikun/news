package com.peng.news.controller.management;

import com.peng.news.model.Result;
import com.peng.news.model.po.NewsPO;
import com.peng.news.service.NewsPublicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 新闻管理的公共操作接口
 * @author shan
 * @version 1.0
 * @date 2021/3/24 10:36
 */
@Api(tags = "新闻管理的公共操作接口")
@RestController
@RequestMapping("/management/news/public")
public class NewsPublicController {

    @Autowired
    NewsPublicService newsPublicService;



    /**
     * 查询一个不是草稿的新闻。
     * 因为草稿是传稿人私有的，所以公共服务不能查询草稿。
     * @param newsId
     * @return
     */
    @ApiOperation("查询一个不是草稿的新闻")
    @GetMapping("/view/{newsId}")
    public Result<NewsPO> selectNotDraftNews(@PathVariable int newsId) {
        return Result.success(newsPublicService.selectOneNotDraftsNews(newsId));
    }
}
