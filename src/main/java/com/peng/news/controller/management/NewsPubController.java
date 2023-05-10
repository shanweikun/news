package com.peng.news.controller.management;

import com.peng.news.model.po.UserPO;
import com.peng.news.util.DateTimeFormatUtils;
import com.peng.news.model.CustomizedPage;
import com.peng.news.model.Result;
import com.peng.news.model.enums.NewsStatus;
import com.peng.news.model.paramBean.CarouselParamBean;
import com.peng.news.model.paramBean.NewsBeanForPublisherPub;
import com.peng.news.model.paramBean.QueryPublishedNewsBeanForPublisher;
import com.peng.news.model.paramBean.QueryUnpublishedNewsBeanForPublisher;
import com.peng.news.model.po.NewsPO;
import com.peng.news.model.vo.NewsColumnVO;
import com.peng.news.model.vo.NewsVO;
import com.peng.news.service.NewsColumnService;
import com.peng.news.service.NewsServiceForPublisher;
import com.peng.news.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 新闻发布相关接口
 * @author shan
 * @version 1.0
 * @date 2021/3/24 10:44
 */
@Api(tags ="新闻发布相关接口" )
@RestController
@RequestMapping("/management/news/pub")
public class NewsPubController {

    @Autowired
    NewsServiceForPublisher newsServiceForPublisher;

    @Autowired
    NewsColumnService newsColumnService;

    @Autowired
    UserService userService;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @ApiOperation("某功能")
    @GetMapping("/hello")
    public String hello(){
        return this.getClass().getName() + " hello[Get]";
    }


    /**
     * 分页、条件查询 待发布状态的 新闻列表
     * @param page
     * @param pageSize
     * @param queryBean
     * @return
     */
    @ApiOperation("分页、条件查询、待发布状态的新闻列表")
    @GetMapping("/unpublished")
    public Result<CustomizedPage<NewsVO>> unpublishedNewsList(Integer page, Integer pageSize, QueryUnpublishedNewsBeanForPublisher queryBean){
        return Result.success(newsServiceForPublisher.unpublishedNewsList(page, pageSize, queryBean));
    }

    /**
     * 查询所有待发布状态列表
     * @return
     */
    @ApiOperation("查询所有待发布状态列表")
    @GetMapping("/toBeReleasedStatusList")
    public Result<NewsStatus[]> toBeReleasedStatusList() {
        return Result.success(NewsStatus.TO_BE_RELEASED_STATUS_ARRAY);
    }

    /**
     * 查询新闻栏目列表，带有子栏目；作为新闻栏目级联选择器的数据
     * @return
     */
    @ApiOperation("查询新闻栏目列表，带有子栏目，作为新闻栏目级联选择器的数据")
    @GetMapping("/allNewsColumn")
    public Result<List<NewsColumnVO>> allNewsColumn() {
        return Result.success(newsColumnService.newsColumnSelectData());
    }

    /**
     * 将 待发布状态的 新闻打回修改。新闻被打回修改，就会回到中转站。
     * @param newsId
     * @param suggestion 打回意见
     * @return
     */
    @ApiOperation("将待发布状态的新闻打回修改，新闻被打回修改，就会回到中转站")
    @PutMapping("/remodification/{newsId}")
    public Result remodification(@PathVariable int newsId, String suggestion) {
        newsServiceForPublisher.remodification(newsId, suggestion);
        return Result.success("成功打回！");
    }

    /**
     * 发布一个新闻
     * @param newsId
     * @param pubInfo 发布设置相关信息
     * @return
     */
    @ApiOperation("发布一个新闻")
    @PutMapping("/{newsId}")
    public Result publishOneNews(@PathVariable int newsId,@RequestBody NewsBeanForPublisherPub pubInfo) {
        newsServiceForPublisher.publishOneNews(newsId, pubInfo);
        return Result.success("发布成功！");
    }


    /**
     * 分页、条件查询 已发布新闻列表
     * @param page
     * @param pageSize
     * @return
     */
    @ApiOperation("分页、条件查询，已发布新闻列表")
    @GetMapping("/published")
    public Result<CustomizedPage<NewsVO>> publishedNewsList(Integer page, Integer pageSize, QueryPublishedNewsBeanForPublisher queryBean) {
        return Result.success(newsServiceForPublisher.publishedNewsList(page, pageSize, queryBean));
    }

    /**
     * 查询用户下拉列表数据
     * @return
     */
    @ApiOperation("查询用户下拉列表数据")
    @GetMapping("/userSelectData")
    public Result<List<UserPO>> userSelectData() {
        return Result.success(userService.userSelectData());
    }

    /**
     * 撤销发布新闻
     * @param newsId
     * @return
     */
    @ApiOperation("撤回发布新闻")
    @PutMapping("/revoke/{newsId}")
    public Result revokePub(@PathVariable int newsId) {
        newsServiceForPublisher.revokePub(newsId);
        return Result.success("撤销成功！");
    }

    /**
     * 新闻是否轮播
     * @param newsId
     * @param paramBean 封装请求参数
     * @return
     */
    @ApiOperation("新闻是否轮播")
    @PutMapping("/carousel/{newsId}")
    public Result<String> carouselManage(@PathVariable int newsId, @RequestBody CarouselParamBean paramBean) {
        NewsPO newsPO = newsServiceForPublisher.carouselManage(newsId, paramBean);
        Result<String> result = Result.success(paramBean.getIsCarousel() ? "成功设为轮播！" : "成功取消轮播！");
        if(paramBean.getIsCarousel()) {
            //设为轮播的话，就返回设为轮播的时间
            result.setData(DateTimeFormatUtils.format(newsPO.getSetCarouselTime().toLocalDateTime()));
        }
        redisTemplate.delete("com.peng.news.model.vo.NewsPO::carouselNewsList");
        return result;
    }

    /**
     * 新闻是否设为头条
     * @param newsId
     * @param tag 1-设为头条；2-取消设为头条；3-非法请求
     * @return
     */
    @ApiOperation("新闻是否设为头条")
    @PutMapping("/headlines/{newsId}")
    public Result<String> headlinesManage(@PathVariable int newsId, int tag) {
        NewsPO newsPO = newsServiceForPublisher.headlinesManage(newsId, tag);
        Result<String> result = Result.success(tag == 1 ? "成功设为头条！" : "成功取消设为头条！");
        if(tag == 1) {

            //设为头条的话，就返回设为头条的时间
            result.setData(DateTimeFormatUtils.format(newsPO.getSetHeadlinesTime().toLocalDateTime()));
        }
        redisTemplate.delete("com.peng.news.model.vo.NewsPO::getHeadLines");
        return result;
    }

    /**
     * 新闻是否置顶
     * @param newsId
     * @param tag 1-设为置顶；2-取消置顶；3-非法请求
     * @return
     */
    @ApiOperation("新闻是否置顶")
    @PutMapping("/top/{newsId}")
    public Result<String> topManage(@PathVariable int newsId, int tag) {
        NewsPO newsPO = newsServiceForPublisher.topManage(newsId, tag);
        Result<String> result = Result.success(tag == 1 ? "置顶成功！" : "成功取消置顶！");
        if(tag == 1) {
            //设置置顶的话，就返回置顶时间
            result.setData(DateTimeFormatUtils.format(newsPO.getSetTopTime().toLocalDateTime()));
        }

        return result;
    }
}
