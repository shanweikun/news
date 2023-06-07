package com.peng.news.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.peng.news.mapper.CollectionMapper;
import com.peng.news.mapper.CommentMapper;
import com.peng.news.mapper.NewsColumnMapper;
import com.peng.news.mapper.NewsMapper;
import com.peng.news.model.enums.NewsStatus;
import com.peng.news.model.po.CollectionPO;
import com.peng.news.model.po.CommentPO;
import com.peng.news.model.po.NewsPO;
import com.peng.news.model.vo.NewsColumnVO;
import com.peng.news.service.FrontendIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shan
 * @version 1.0
 * @date 2021/4/23 16:33
 */
@Service
public class FrontendIndexServiceImpl implements FrontendIndexService {

    @Autowired
    NewsColumnMapper newsColumnMapper;

    @Autowired
    CollectionMapper collectionMapper;

    @Autowired
    CommentMapper commentMapper;
    @Autowired
    NewsMapper newsMapper;


    @Override
    @Cacheable(cacheNames = "com.peng.news.model.vo.NewsColumnVO",key = "'allEnabledOneLevelColsOrderByMenuOrder'")
    public List<NewsColumnVO> allEnabledOneLevelColsOrderByMenuOrder() {
        return newsColumnMapper.getEnabledChildrenNewsColumnListByParentId(null);
    }

    @Override
    @Cacheable(cacheNames = "com.peng.news.model.vo.NewsPO",key = "'getHeadLines'")
    public List<NewsPO> getHeadLines() {
        QueryWrapper<NewsPO> queryWrapper = new QueryWrapper<>();
        wrapNecessaryConditionForQueryNews(queryWrapper);
        queryWrapper.eq("is_headlines", true);
            queryWrapper.orderByDesc("set_headlines_time");

        queryWrapper.select("id", "title");
        List<NewsPO> newsPOS = newsMapper.selectList(queryWrapper);
        return newsPOS;
    }

    @Cacheable(cacheNames = "com.peng.news.model.vo.NewsPO",key = "'carouselNewsList'")
    @Override
    public List<NewsPO> carouselNewsList(Integer amount) {
        //默认5个
        amount = amount == null ? 5 : amount;
        QueryWrapper<NewsPO> queryWrapper = new QueryWrapper<>();
        wrapNecessaryConditionForQueryNews(queryWrapper);
        queryWrapper.eq("is_carousel", true);
        queryWrapper.orderByDesc("set_carousel_time");

        queryWrapper.select("id", "title", "article_fragment_for_show", "show_pub_time", "extra");

        IPage<NewsPO> page = new Page<>(1, amount);
        IPage<NewsPO> selectPage = newsMapper.selectPage(page, queryWrapper);

        return selectPage.getRecords();
    }

    @Cacheable(cacheNames = "com.peng.news.model.vo.NewsPO",key = "'recommendHotNews'")
    @Override
    public List<NewsPO> recommendHotNews() {
        QueryWrapper<NewsPO> newsPOQueryWrapper = new QueryWrapper<>();
        //获取全部新闻
        List<NewsPO> newsPOS = newsMapper.selectList(newsPOQueryWrapper);
        //便利
        for (NewsPO newsPO : newsPOS) {
            //新闻状态必须是已经发布的
            if (newsPO.getContent()!=null&&newsPO.getNewsStatus()==5) {
                //计算距离当前时间的时间差
                Timestamp now = new Timestamp(System.currentTimeMillis());
                long timeDiff = now.getTime() - newsPO.getCreateTime().getTime();
                double hoursAgo = timeDiff / (1000 * 60 * 60);
                //计算评论数
                QueryWrapper<CommentPO> commentPOQueryWrapper = new QueryWrapper<>();
                commentPOQueryWrapper.eq("news_id",newsPO.getId());
                List<CommentPO> commentPOS = commentMapper.selectList(commentPOQueryWrapper);
                //通过浏览数、评论数、时间计算分数(十天之前的新闻就不以时间作为参考了）
                double score = newsPO.getRealReadingCount() * 0.4 + commentPOS.size() * 0.3 + (24 - Math.min(hoursAgo, 240)) * 0.3;
                newsPO.setScore(score);
            }else newsPO.setScore(-100000000);
        }
        //通过分数进行排序
        newsPOS.sort((n1, n2) -> Double.compare(n2.getScore(), n1.getScore()));
        List<NewsPO> newsPOS1 = new ArrayList(newsPOS.subList(0, Math.min(10, newsPOS.size())));
        return newsPOS1;
    }


    /**
     * 给查询新闻封装必要条件
     * @param queryWrapper
     */
    private void wrapNecessaryConditionForQueryNews(QueryWrapper<NewsPO> queryWrapper) {
        //新闻必须是已发布状态
        queryWrapper.eq("news_status", NewsStatus.PUBLISHED.getCode());
    }
}
