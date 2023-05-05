package com.peng.news.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peng.news.mapper.CollectionMapper;
import com.peng.news.model.po.CollectionPO;
import com.peng.news.model.vo.CollectionVO;
import com.peng.news.service.CollectionService;
import com.peng.news.service.NewsColumnService;
import com.peng.news.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 收藏
 *
 * @author  shan
 * @date 2023/2/28$
 */
@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, CollectionPO> implements CollectionService {

    @Autowired
    private NewsPublicServiceImpl newsPublicService;

    @Autowired
    private NewsColumnService newsColumnService;
    @Override
    public List<CollectionVO> listCollectionPO(){
        LambdaQueryWrapper<CollectionPO> lambda = new QueryWrapper<CollectionPO>().lambda()
                .eq(UserUtils.getUser().getId()!=null,CollectionPO::getUserId,UserUtils.getUser().getId())
                .orderByDesc(CollectionPO::getCreateTime);
        List<CollectionPO> list = list(lambda);
        ArrayList<CollectionVO> collectionVOS = new ArrayList<>();
        for (CollectionPO collectionPO : list) {
            CollectionVO collectionVO = new CollectionVO();
            collectionVO.setId(collectionPO.getId());
            collectionVO.setNewsPO(newsPublicService.selectOneNotDraftsNews(collectionPO.getNewsId()));
            collectionVO.setNewsColumnPO(newsColumnService.getById(collectionPO.getColumnId()));
            collectionVOS.add(collectionVO);
        }
        return collectionVOS;
    }
    /**
     * 添加收藏
     * @param collectionPO
     * @return
     */
    @Override
    public CollectionPO add(CollectionPO collectionPO) {
        Integer id = UserUtils.getUser().getId();
        collectionPO.setUserId(id);
        collectionPO.setCreateTime(new Date());
        LambdaQueryWrapper<CollectionPO> lambda = new QueryWrapper<CollectionPO>().lambda()
                .eq(collectionPO.getColumnId()!=null,CollectionPO::getColumnId,collectionPO.getColumnId())
                .eq(collectionPO.getNewsId()!=null,CollectionPO::getNewsId,collectionPO.getNewsId())
                .eq(collectionPO.getUserId()!=null,CollectionPO::getUserId,collectionPO.getUserId());
        if (super.list(lambda).size()==0) {
            if (super.save(collectionPO)) {
                return collectionPO;
            }
        }
        return null;
    }

    /**
     * 删除收藏
     * @param id
     * @return
     */
    @Override
    public Integer delete(Integer id) {
        if (super.removeById(id)) {
            return id;
        }
        return null;
    }


}
