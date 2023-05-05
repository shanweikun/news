package com.peng.news.service;

import com.peng.news.model.vo.NewsVO;

/**
 * 新闻详情相关服务
 * @author shan
 * @version 1.0
 * @date 2021/4/24 0:16
 */
public interface NewsDetailService {

    NewsVO getOneNews(Integer newsId);
}
