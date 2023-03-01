package com.peng.news.model.dto;

import com.peng.news.model.CustomizedPage;
import com.peng.news.model.po.NewsPO;
import com.peng.news.model.vo.NewsColumnVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 封装新闻列表
 * @author PENG
 * @version 1.0
 * @date 2021/4/23 19:42
 */
@Data
@ApiModel("封装新闻列表")
public class NewsListDTO {

    /**
     * 栏目信息。表示哪个栏目的新闻列表
     */
    @ApiModelProperty("栏目信息，表示那个栏目的新闻列表")
    private NewsColumnVO column;

    /**
     * 分页查询结果
     */
    @ApiModelProperty("分页查询结果")
    private CustomizedPage<NewsPO> news;
}
