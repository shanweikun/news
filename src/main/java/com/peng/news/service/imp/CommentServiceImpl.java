package com.peng.news.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peng.news.mapper.CommentMapper;
import com.peng.news.model.po.CommentPO;
import com.peng.news.service.CommentService;
import com.peng.news.util.UserUtils;

import java.util.Date;
import java.util.List;

/**
 * 评论
 *
 * @author shuai
 * @date 2023/2/28$
 */


public class CommentServiceImpl extends ServiceImpl<CommentMapper, CommentPO> implements CommentService {


    /**
     * 用户查看所有评论
     * @return
     */
    @Override
    public List<CommentPO> listCommentService() {
        LambdaQueryWrapper<CommentPO> lambda = new QueryWrapper<CommentPO>().lambda()
                .eq(UserUtils.getUser().getId()!=null,CommentPO::getUserId,UserUtils.getUser().getId());
        return super.list(lambda);
    }

    /**
     * 添加评论
     * @param collectionPO
     * @return
     */
    @Override
    public CommentPO add(CommentPO collectionPO) {
        Integer id = UserUtils.getUser().getId();
        collectionPO.setCreateTime(new Date());
        collectionPO.setUserId(id);
        if (super.save(collectionPO)) {
            return collectionPO;
        }
        return null;
    }

    /**
     * 删除评论
     * @param ids
     * @return
     */
    public List<Integer> delete(List<Integer> ids) {
        if (super.removeByIds(ids)) {
            return ids;
        }
        return null;
    }
}
