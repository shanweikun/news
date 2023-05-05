package com.peng.news.model.vo;

import com.peng.news.model.po.NewsColumnPO;
import com.peng.news.model.po.NewsPO;
import lombok.Data;

/**
 * CollectionVO$
 *
 * @author shan
 * @date 2023/3/3$
 */

@Data
public class CollectionVO {
    private Integer id;
    private NewsPO newsPO;
    private NewsColumnPO newsColumnPO;
}
