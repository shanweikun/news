package com.peng.news.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peng.news.mapper.CollectionMapper;
import com.peng.news.model.po.CollectionPO;
import com.peng.news.service.CollectionService;
import com.peng.news.util.UserUtils;

import java.util.Date;
import java.util.List;

/**
 * 收藏
 *
 * @author shuai
 * @date 2023/2/28$
 */

public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, CollectionPO> implements CollectionService {
    @Override
    public List<CollectionPO> listCollectionPO(){
        LambdaQueryWrapper<CollectionPO> lambda = new QueryWrapper<CollectionPO>().lambda()
                .eq(UserUtils.getUser().getId()!=null,CollectionPO::getUserId,UserUtils.getUser().getId());
        return list(lambda);
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
        if (super.save(collectionPO)) {
            return collectionPO;
        }
        return null;
    }

    /**
     * 删除收藏
     * @param ids
     * @return
     */
    @Override
    public List<Integer> delete(List<Integer> ids) {
        if (super.removeByIds(ids)) {
            return ids;
        }
        return null;
    }
}
