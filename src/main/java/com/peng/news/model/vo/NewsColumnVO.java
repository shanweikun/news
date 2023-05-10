package com.peng.news.model.vo;

import com.peng.news.model.po.NewsColumnPO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author shan
 * @version 1.0
 * @date 2021/4/9 12:36
 */
@Data
public class NewsColumnVO extends NewsColumnPO implements Serializable {
//    Integer id;
//
//    String title;
//
//    String description;
//
//    Integer parentId;
//
//    String externalLink;
//
//    Boolean isHasChildren;
//
//    Boolean enabled;
//
//    Integer menuOrder;
//
//    Integer moduleOrder;
//
//    Boolean showImgOnTheRight;
//
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
//    Timestamp createTime;
//
//    Timestamp updateTime;

    /**
     * 子栏目列表
     */
    List<NewsColumnVO> children;

    /**
     * 父栏目
     */
    NewsColumnVO parent;
}
