package com.peng.news.model.vo;

import com.peng.news.model.po.NewsPO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * CommentVO$
 *
 * @author shan
 * @date 2023/3/20$
 */

@Data
public class CommentVO implements Serializable {
    Integer id;

    String content;

    NewsPO newsPO;


    Date createTime;

    UserVO userVO;


}
