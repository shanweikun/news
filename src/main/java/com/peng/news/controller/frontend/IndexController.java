package com.peng.news.controller.frontend;

import com.peng.news.model.Result;
import com.peng.news.model.po.NewsPO;
import com.peng.news.model.vo.NewsColumnVO;
import com.peng.news.service.FrontendIndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 门户网站首页的接口
 * @author shan
 * @version 1.0
 * @date 2021/4/23 16:27
 */
@Api(tags = "门户网站首页的接口")
@RestController
@RequestMapping("/frontend/index")
public class IndexController {

    @Autowired
    FrontendIndexService frontendIndexService;

    /**
     * 查询所有开启的一级栏目，并且按菜单序号排序
     * @return
     */
    @ApiOperation(value = "查询所有开启的一级栏目，并且按菜单序号排序",notes = "获取Hello")
    @GetMapping("/cols")
    public Result<List<NewsColumnVO>> allEnabledOneLevelColsOrderByMenuOrder() {
        return Result.success(frontendIndexService.allEnabledOneLevelColsOrderByMenuOrder());
    }

    /**
     * 查询新闻头条
     * @return
     */
    @ApiOperation(value = "查询新闻头条")
    @GetMapping("/headlines")
    public Result getHeadLines(){
        return Result.success(frontendIndexService.getHeadLines());
    }

    /**
     * 查询轮播新闻 2
     * @amount 获取轮播新闻的数量
     * @return
     */
    @ApiOperation("查询轮播新闻")
    @GetMapping("/carouselNews")
    public Result<List<NewsPO>> carouselNewsList(Integer amount) {
        return Result.success(frontendIndexService.carouselNewsList(amount));
    }
    /**
     * 查询热点新闻推荐
     */
    @ApiOperation("新闻推荐")
    @GetMapping("/recommendNews")
    public Result<List<NewsPO>> recommendNews() {
        return Result.fail("");
    }
}
