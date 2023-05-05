package com.peng.news.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peng.news.mapper.CommentMapper;
import com.peng.news.mapper.NewsMapper;
import com.peng.news.mapper.UserMapper;
import com.peng.news.model.po.CommentPO;
import com.peng.news.model.po.NewsPO;
import com.peng.news.model.vo.CommentVO;
import com.peng.news.service.CommentService;
import com.peng.news.util.UserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 评论
 *
 * @author  shan
 * @date 2023/2/28$
 */

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, CommentPO> implements CommentService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NewsMapper newsMapper;
    /**
     * 用户查看所有评论
     * @return
     */
    @Override
    public List<CommentVO> listCommentService() {
        LambdaQueryWrapper<CommentPO> lambda = new QueryWrapper<CommentPO>().lambda()
                .eq(UserUtils.getUser().getId()!=null,CommentPO::getUserId,UserUtils.getUser().getId());
        List<CommentPO> list = super.list(lambda);
        ArrayList<CommentVO> commentVOS = new ArrayList<>();
        for (CommentPO commentPO : list) {
            CommentVO commentVO = new CommentVO();
            commentVO.setId(commentPO.getId());
            commentVO.setUserVO(userMapper.getUserByUserId(commentPO.getUserId()));
            commentVO.setContent(commentPO.getContent());
            commentVO.setCreateTime(commentPO.getCreateTime());
            commentVO.setNewsPO(newsMapper.selectById(commentPO.getNewsId()));
            commentVOS.add(commentVO);
        }
        return commentVOS;
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
    public Integer delete(Integer id) {
        if (super.removeById(id)) {
            return id;
        }
        return null;
    }

    /**
     * 根据新闻id查询评论
     * @param newsId
     * @return
     */
    @Override
    public List<CommentVO> selectByNewsId(Integer newsId) {
        LambdaQueryWrapper<CommentPO> lambda = new QueryWrapper<CommentPO>().lambda()
                .eq(newsId!=null,CommentPO::getNewsId,newsId);
        List<CommentPO> list = list(lambda);
        List<CommentVO> commentVOS = new ArrayList<>();
        for (CommentPO commentPO : list) {
            CommentVO commentVO = new CommentVO();
            commentVO.setUserVO(userMapper.getUserByUserId(commentPO.getUserId()));
            BeanUtils.copyProperties(commentPO,commentVO);
            commentVOS.add(commentVO);
        }
        return commentVOS;
    }
}
