package com.peng.news.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Transient;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author shan
 * @version 1.0
 * @date 2021/4/12 20:23
 */
@Data
@TableName("news")
public class NewsPO implements Serializable {

    private static final long serialVersionUID = -3911255650485738676L;
    @TableId(type = IdType.AUTO)
    Integer id;

    /**
     * 标题
     */
    String title;

    /**
     * 用于新闻列表显示的文章片段
     */
    String articleFragmentForShow;

    /**
     * 正文
     */
    String content;

    /**
     * 外键，新闻栏目id
     */
    Integer columnId;

    /**
     * 新闻图片来源
     */
    String imgSource;

    /**
     * 文字来源
     */
    String articleSource;

    /**
     * 新闻状态
     * 0-草稿，1-上传成功（备注：属于中转新闻），
     * 2-审核中，3-审核失败（备注：属于中转新闻），
     * 4-审核成功，待发布，5-已发布，6-撤销发布，
     * 7-打回修改（备注：属于中转新闻）',
     */
    Integer newsStatus;

    /**
     * 是否轮播
     */
    Boolean isCarousel;

    /**
     * 设置轮播的时机
     */
    Timestamp setCarouselTime;

    /**
     * 是否作为头条
     */
    Boolean isHeadlines;

    /**
     * 设置为头条的时机
     */
    Timestamp setHeadlinesTime;

    /**
     * 是否是图片新闻
     */
    Boolean isImageNews;

    /**
     * 是否在所在新闻栏目中置顶
     */
    Boolean isTop;

    /**
     * 新闻设置置顶的时机
     */
    Timestamp setTopTime;

    /**
     * 外网的新闻链接
     */
    String externalUrl;

    /**
     * 外键，录入（创建）新闻的用户id
     */
    Integer inputterId;


    /**
     * 新闻的创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    Timestamp createTime;

    /**
     * 新闻完成录入的时间
     */
    Timestamp completeInputTime;

    /**
     * 参与了新闻编辑工作的用户的实名，多个名字用,分隔
     */
    String editors;

    /**
     * 参与了新闻审核工作的用户的实名，逗哥分隔
     */
    String reviewers;

    /**
     * 外键，提交审核的用户id
     */
    Integer submitterId;

    /**
     * 送审时间
     */
    Timestamp submitTime;

    /**
     * 最近一次对新闻进行了编辑的用户的id
     */
    Integer latestEditorId;

    /**
     * 最近修改日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    Timestamp latestEditTime;

    /**
     * 新闻当前所处的审核轮次
     */
    Integer currentReviewEpoch;

    /**
     * 新闻上一轮审核的通过时间。如果新闻处于待发布状态，则表示新闻通过终审的时间
     */
    Timestamp previousEpochReviewPassTime;

    /**
     * 发布新闻的用户id
     */
    Integer publisherId;

    /**
     * 新闻实际的发布时间
     */
    Timestamp realPubTime;

    /**
     * 新闻对外显示的发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    Timestamp showPubTime;

    /**
     * 新闻初始设置阅读量
     */
    Integer initReadingCount;

    /**
     * 当前新闻显示在新闻列表上的图片
     */
    String imgForShowOnNewsList;

    /**
     * 新闻实际阅读量
     */
    Integer realReadingCount;

    public NewsPO(){

    }
    public NewsPO(Integer id, String title, String articleFragmentForShow, String content, Integer columnId, String imgSource, String articleSource, Integer newsStatus, Boolean isCarousel, Timestamp setCarouselTime, Boolean isHeadlines, Timestamp setHeadlinesTime, Boolean isImageNews, Boolean isTop, Timestamp setTopTime, String externalUrl, Integer inputterId, Timestamp createTime, Timestamp completeInputTime, String editors, String reviewers, Integer submitterId, Timestamp submitTime, Integer latestEditorId, Timestamp latestEditTime, Integer currentReviewEpoch, Timestamp previousEpochReviewPassTime, Integer publisherId, Timestamp realPubTime, Timestamp showPubTime, Integer initReadingCount, String imgForShowOnNewsList, Integer realReadingCount, String extra, double score) {
        this.id = id;
        this.title = title;
        this.articleFragmentForShow = articleFragmentForShow;
        this.content = content;
        this.columnId = columnId;
        this.imgSource = imgSource;
        this.articleSource = articleSource;
        this.newsStatus = newsStatus;
        this.isCarousel = isCarousel;
        this.setCarouselTime = setCarouselTime;
        this.isHeadlines = isHeadlines;
        this.setHeadlinesTime = setHeadlinesTime;
        this.isImageNews = isImageNews;
        this.isTop = isTop;
        this.setTopTime = setTopTime;
        this.externalUrl = externalUrl;
        this.inputterId = inputterId;
        this.createTime = createTime;
        this.completeInputTime = completeInputTime;
        this.editors = editors;
        this.reviewers = reviewers;
        this.submitterId = submitterId;
        this.submitTime = submitTime;
        this.latestEditorId = latestEditorId;
        this.latestEditTime = latestEditTime;
        this.currentReviewEpoch = currentReviewEpoch;
        this.previousEpochReviewPassTime = previousEpochReviewPassTime;
        this.publisherId = publisherId;
        this.realPubTime = realPubTime;
        this.showPubTime = showPubTime;
        this.initReadingCount = initReadingCount;
        this.imgForShowOnNewsList = imgForShowOnNewsList;
        this.realReadingCount = realReadingCount;
        this.extra = extra;
        this.score = score;
    }

    /**
     * 额外信息，以JSON字符串形式存储
     */
    String extra;

    @Transient
    double score;
}
