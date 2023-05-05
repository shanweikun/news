package com.peng.news.controller.frontend;

import com.peng.news.model.CustomizedPage;
import com.peng.news.model.Result;
import com.peng.news.model.po.NewsPO;
import com.peng.news.service.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 搜索新闻接口
 * @author shan
 * @version 1.0
 * @date 2021/4/26 20:02
 */
@Api(tags = "搜索新闻接口")
@RestController
@RequestMapping("/frontend/search")
public class SearchController {

    @Autowired
    SearchService searchService;

    /**
     * 搜索新闻
     * @param page
     * @param pageSize
     * @param condition
     * @return
     */
    @ApiOperation("搜索新闻")
    @GetMapping("/")
    public Result<CustomizedPage<NewsPO>> selectNewsListByCondition(Integer page, Integer pageSize, String condition) {
        return Result.success(searchService.selectNewsListByCondition(page, pageSize, condition));
    }
}
