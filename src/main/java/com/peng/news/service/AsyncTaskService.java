package com.peng.news.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.peng.news.model.po.NewsPO;

/**
 * 异步任务service
 * @author PENG
 * @version 1.0
 * @date 2021/4/26 21:48
 */
public interface AsyncTaskService extends IService<NewsPO> {

    /**
     * 异步增加新闻的阅读量
     * @param newsId
     */
    void increaseReadingCount(int newsId);
}
