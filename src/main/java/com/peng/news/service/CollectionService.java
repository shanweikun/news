package com.peng.news.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.peng.news.model.po.CollectionPO;

import java.util.List;

/**
 * 收藏
 */
public interface CollectionService extends IService<CollectionPO> {
    /**
     * 用户查看自己收藏列表
     * @return
     */
    List<CollectionPO> listCollectionPO();

    /**
     * 添加收藏
     * @param collectionPO
     * @return
     */
    CollectionPO add(CollectionPO collectionPO);

    /**
     * 删除收藏
     * @param ids
     * @return
     */
    List<Integer> delete(List<Integer> ids);

}
