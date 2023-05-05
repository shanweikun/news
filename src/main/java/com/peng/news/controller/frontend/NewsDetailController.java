package com.peng.news.controller.frontend;

import com.peng.news.model.Result;
import com.peng.news.model.po.CommentPO;
import com.peng.news.model.vo.CommentVO;
import com.peng.news.model.vo.NewsVO;
import com.peng.news.service.CommentService;
import com.peng.news.service.NewsDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 新闻详情相关接口
 * @author shan
 * @version 1.0
 * @date 2021/4/24 0:15
 */
@Api(tags = "新闻详情相关接口")
@RestController
@RequestMapping("/frontend/newDetail")
public class NewsDetailController {

    @Autowired
    NewsDetailService newsDetailService;

    @Autowired
    CommentService commentService;
    @ApiOperation("获取某个新闻id的新闻详情")
    @GetMapping("/{newsId}")
    public Result<NewsVO> getOneNews(@PathVariable int newsId) {
        return Result.success(newsDetailService.getOneNews(newsId));
    }

    @ApiOperation("获取某个新闻id的所有评论")
    @GetMapping("/comment/{newsId}")
    public Result<List<CommentVO>> getComments(@PathVariable int newsId) {
        return Result.success(commentService.selectByNewsId(newsId));
    }

    @ApiOperation("添加评论")
    @GetMapping("/save")
    public Result save(CommentPO commentPO) {
        return Result.success(commentService.add(commentPO));
    }
}
