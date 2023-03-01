package com.peng.news.model.paramBean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.peng.news.util.ValidateUrlUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 封装发布员发布新闻传递的参数
 * @author PENG
 * @version 1.0
 * @date 2021/4/17 15:34
 */
@Data
@ApiModel("封装发布员发布新闻传递的参数")
public class NewsBeanForPublisherPub {

    /**
     * 新闻栏目id。（要验证传递的栏目存在）
     */
    @ApiModelProperty("新闻栏目id")
    Integer columnId;


    /**
     * 文字来源
     */
    @ApiModelProperty("文字来源")
    String articleSource;

    /**
     * 图片来源
     */
    @ApiModelProperty("图片来源")
    String imgSource;

    /**
     * 是否是图片新闻，默认false
     */
    @ApiModelProperty("是否是图片新闻，默认为false")
    boolean imageNews;

    /**
     * 是否在所在新闻栏目中置顶，默认false
     */
    @ApiModelProperty("是否在所在新闻栏目中指定，默认为false")
    boolean top;

    /**
     * 参与了新闻编辑工作的用户的实名，多个名字用,分隔
     */
    @ApiModelProperty("参与了新闻编辑工作的用户的实名，多个名字用，分割")
    String editors;

    /**
     * 参与了新闻审核工作的用户的实名，逗哥分隔
     */
    @ApiModelProperty("参与了新闻审核工作的用户逇实名，逗号分割")
    String reviewers;

    /**
     * 新闻对外显示的发布时间
     */
    @ApiModelProperty("新闻对外显示的发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    Timestamp showPubTime;

    /**
     * 新闻初始设置阅读量，默认0
     */
    @ApiModelProperty("新闻初始设置阅读量，默认为0")
    int initReadingCount;


    /**
     * 是否轮播。默认false，不轮播
     */
    @ApiModelProperty("是否轮播，默认false，不轮播")
    boolean carousel;

    /**
     * 轮播图地址（如果轮播，就设置用于轮播的地址）
     */
    @ApiModelProperty("轮播图地址")
    String imgUrlForCarousel;

    /**
     * 是否作为头条。默认false，不作为头条
     */
    @ApiModelProperty("是否作为头条，默认false")
    boolean headlines;

    /**
     * 在新闻列表上显示的图片地址
     */
    @ApiModelProperty("在新闻列表上显示的图片地址")
    String imgForShowOnNewsList;

    /**
     * 格式化信息
     */
    @ApiModelProperty("格式化信息")
    public void formatAndValidate() {
        format();
        validate();
    }

    private void format() {
        if(imgSource == null || "".equals(imgSource = imgSource.trim())) {
            imgSource = null;
        }

        if(articleSource == null || "".equals(articleSource = articleSource.trim())) {
            articleSource = null;
        }

        if(editors == null || "".equals(editors = editors.trim())) {
            editors = null;
        }

        if(reviewers == null || "".equals(reviewers = reviewers.trim())) {
            reviewers = null;
        }

        if(imgUrlForCarousel == null || "".equals(imgUrlForCarousel = imgUrlForCarousel.trim())) {
            imgUrlForCarousel = null;
        }

        if(imgForShowOnNewsList == null || "".equals(imgForShowOnNewsList = imgForShowOnNewsList.trim())) {
            imgForShowOnNewsList = null;
        }
    }

    private void validate() {
        if(columnId == null) {
            throw new RuntimeException("请选择要发布到的新闻栏目！");
        }

        //如果要作为轮播发布，必须设置轮播图地址，且必须符合规范
        if(carousel) {
            if(imgUrlForCarousel == null) {
                throw new RuntimeException("轮播发布时，必须设置轮播图地址！");
            }else if(!ValidateUrlUtils.validateUrl(imgUrlForCarousel)) {
                throw new RuntimeException("图片地址不规范，请正确设置轮播图地址！");
            }
        }

        if(imgForShowOnNewsList != null && !ValidateUrlUtils.validateUrl(imgForShowOnNewsList)) {
            throw new RuntimeException("图片地址不规范，请正确设置在新闻列表上显示的图片地址！");
        }
    }
}
