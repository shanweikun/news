package com.peng.news.model.vo;

import com.peng.news.model.po.NewsPO;
import lombok.Data;

import java.util.Date;

/**
 * CommentVO$
 *
 * @author shan
 * @date 2023/3/20$
 */

@Data
public class CommentVO {
    Integer id;

    String content;

    NewsPO newsPO;


    Date createTime;

    UserVO userVO;


}
