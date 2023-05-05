package com.peng.news.model.paramBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author shan
 * @version 1.0
 * @date 2021/4/14 14:55
 */
@Data
@ApiModel("")
public class QueryNewsBeanForEditor {

    /**
     * 标题
     */
    @ApiModelProperty("标题")
    String title;

    /**
     * 外链
     */
    @ApiModelProperty("外链")
    String externalUrl;

    /**
     * 新闻状态
     */
    @ApiModelProperty("新闻状态")
    Integer newsStatus;

    /**
     * 录入人id
     */
    @ApiModelProperty("录入id")
    Integer inputterId;

    /**
     * 最近修改新闻的用户id
     */
    @ApiModelProperty("最近修改新闻的用户id")
    Integer latestEditorId;

    /**
     * 参与编辑的用户
     */
    @ApiModelProperty("参与编辑的用户")
    String participateEditor;

    /**
     * 是否按最近修改时间排序；true-升序，false-降序；null-不按最近修改时间排序，使用默认排序
     */
    @ApiModelProperty("是否按最近修改时间排序")
    Boolean orderByLatestEditTime;


    /**
     * 修剪空格，或将空字符串设为null
     */

    public void trimOrSetNull() {
        if(title == null || "".equals((title = title.trim()))) {
            title = null;
        }

        if(externalUrl == null || "".equals((externalUrl = externalUrl.trim()))) {
            externalUrl = null;
        }

        if(participateEditor == null || "".equals((participateEditor = participateEditor.trim()))) {
            participateEditor = null;
        }
    }
}
