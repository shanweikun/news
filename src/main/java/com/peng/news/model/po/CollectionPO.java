package com.peng.news.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 收藏列表
 *
 * @author shuai
 * @date 2023/2/28$
 */

@Data
@TableName("collection")
public class CollectionPO {

    @TableId(type = IdType.AUTO)
    Integer id;

    Integer newsId;

    Integer userId;

    Date createTime;
}
