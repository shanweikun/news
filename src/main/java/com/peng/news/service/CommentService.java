package com.peng.news.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.peng.news.model.po.CommentPO;
import com.peng.news.model.vo.CommentVO;

import java.util.List;

public interface CommentService extends IService<CommentPO> {

    /**
     * 用户查看自己评论列表
     * @return
     */
    List<CommentVO> listCommentService();

    /**
     * 添加评论
     * @param collectionPO
     * @return
     */
    CommentPO add(CommentPO collectionPO);

    /**
     * 删除评论
     * @param ids
     * @return
     */
    Integer delete(Integer id);

    /**
     * 根据新闻id查询评论
     * @param newsId
     * @return
     */
    List<CommentVO> selectByNewsId(Integer newsId);
}
