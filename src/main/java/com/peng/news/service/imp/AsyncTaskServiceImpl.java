package com.peng.news.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peng.news.service.AsyncTaskService;
import com.peng.news.mapper.NewsMapper;
import com.peng.news.model.po.NewsPO;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author shan
 * @version 1.0
 * @date 2021/4/26 21:48
 */
@Service
public class AsyncTaskServiceImpl extends ServiceImpl<NewsMapper,NewsPO> implements AsyncTaskService {


    /**
     * 方法必须同步，否则会丢失更新
     * @param newsId
     */
    @Async
    @Override
    public synchronized void increaseReadingCount(int newsId) {
        //先查询旧值
        LambdaQueryWrapper<NewsPO> qw = new QueryWrapper<NewsPO>().lambda()
                .select(NewsPO::getRealReadingCount)
                .eq(true,NewsPO::getId,newsId);
//        QueryWrapper<NewsPO> queryWrapper = new QueryWrapper<NewsPO>().select("real_reading_count").eq("id", newsId);
        int oldValue = this.getOne(qw).getRealReadingCount();

        //设置新值
        LambdaUpdateWrapper<NewsPO> lambda = new UpdateWrapper<NewsPO>().lambda()
                .set(true,NewsPO::getRealReadingCount,oldValue+1)
                .eq(true,NewsPO::getId,newsId);
//        UpdateWrapper<NewsPO> updateWrapper = new UpdateWrapper<NewsPO>().set("real_reading_count", oldValue + 1).eq("id", newsId);
//        newsMapper.update(null, updateWrapper);
        this.update(lambda);
    }
}
