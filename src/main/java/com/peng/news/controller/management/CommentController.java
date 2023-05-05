package com.peng.news.controller.management;

import com.peng.news.model.Result;
import com.peng.news.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CommentController$
 *
 * @author shan
 * @date 2023/3/20$
 */

@Api(tags = "个人中心评论管理   ")
@RestController
@RequestMapping("/management/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @ApiOperation("获取所有评论或者获取个人评论")
    @GetMapping("/getComment")
    public Result getComment(){
        return Result.success(commentService.listCommentService());
    }

    @ApiOperation("删除评论")
    @GetMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(commentService.delete(id));
    }
}
