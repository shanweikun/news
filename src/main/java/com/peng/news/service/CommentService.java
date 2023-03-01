package com.peng.news.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.peng.news.model.po.CollectionPO;
import com.peng.news.model.po.CommentPO;

import java.util.List;

public interface CommentService extends IService<CommentPO> {

    /**
     * 用户查看自己收藏列表
     * @return
     */
    List<CommentPO> listCommentService();

    /**
     * 添加收藏
     * @param collectionPO
     * @return
     */
    CommentPO add(CommentPO collectionPO);

    /**
     * 删除收藏
     * @param ids
     * @return
     */
    List<Integer> delete(List<Integer> ids);
}
