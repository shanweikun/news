package com.peng.news.controller.management;

import com.peng.news.model.CustomizedPage;
import com.peng.news.model.Result;
import com.peng.news.model.paramBean.NewsBeanForInputterSave;
import com.peng.news.model.paramBean.QueryNewsBeanForInputter;
import com.peng.news.model.po.NewsPO;
import com.peng.news.service.NewsServiceForInputter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 撰写新闻菜单相关接口，也就是传稿人用户能够访问的接口
 * @author shan
 * @version 1.0
 * @date 2021/3/24 10:38
 */
@Api(tags = "撰写新闻菜单相关接口，也就是传稿人用户能够访问的接口")
@RestController
@RequestMapping("/management/news/upload")
public class NewsUploadController {

    @Autowired
    NewsServiceForInputter newsServiceForInputter;

    @GetMapping("/hello")
    public String hello(){
        return this.getClass().getName() + " hello";
    }

    /**
     * 创建新闻或保存新闻，状态设为草稿或完成上传的状态
     * id为null，就创建；否则，保存修改
     * @param tag 1.草稿；2.完成上传
     * @return
     */
    @ApiOperation("创建新闻或保存新闻，状态设为草稿或完成上传的状态")
    @PostMapping("/create/{tag}")
    public Result saveNewsAsDraftOrUpload(@PathVariable int tag, @RequestBody NewsBeanForInputterSave news){
        Integer paramId = news.getId();
        Integer newsId = newsServiceForInputter.saveNewsAsDraftOrUpload(tag, news);

        Result<Object> result = Result.success(tag == 1 ? "保存成功！" : "上传成功！");
        if(paramId == null && tag == 1) {
            //表示是创建草稿，则返回新的草稿的id
            result.setData(newsId);
        }
        return result;
    }


    /**
     * 传稿人用户分页、条件查询草稿列表
     * @return
     */
    @GetMapping("/")
    @ApiOperation("传稿人用户分页、条件查询草稿列表")
    public Result<CustomizedPage<NewsPO>> draftList(Integer page, Integer pageSize, QueryNewsBeanForInputter queryBean){
        return Result.success(newsServiceForInputter.draftList(page, pageSize, queryBean));
    }

    /**
     * 查询当前用户的 id为newsId的草稿
     * @param newsId
     * @return
     */
    @ApiOperation("查询当前用户id为newsid的草稿")
    @GetMapping("/{newsId}")
    public Result<NewsPO> selectCurUserDraft(@PathVariable int newsId){
        return Result.success(newsServiceForInputter.selectCurUserDraft(newsId));
    }

    /**
     * 删除当前用户的 id为newsId的草稿
     * @param newsId
     * @return
     */
    @ApiOperation("删除当前用户的id为newsid的草稿")
    @DeleteMapping("/{newsId}")
    public Result<NewsPO> deleteCurUserDraft(@PathVariable Integer newsId){
        newsServiceForInputter.deleteCurUserDraft(newsId);
        return Result.success("成功删除！");
    }

    /**
     * 上传当前用户的 id为newsId的草稿
     * @param newsId
     * @return
     */
    @ApiOperation("上传当前用户的id为newsid的草稿")
    @PutMapping("/{newsId}")
    public Result uploadCurUserDraft(@PathVariable Integer newsId){
        newsServiceForInputter.uploadCurUserDraft(newsId);
        return Result.success("上传成功！");
    }
}
