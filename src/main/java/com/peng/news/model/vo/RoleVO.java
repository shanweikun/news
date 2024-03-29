package com.peng.news.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author shan
 * @version 1.0
 * @date 2021/3/23 14:16
 */
@Data
public class RoleVO implements Serializable {

    Integer id;

    /**
     * 角色英文名
     */
    String nameEn;

    /**
     * 角色中文名
     */
    String nameZh;

    /**
     * 角色描述
     */
    String description;

    Boolean isSystemAdmin;

    Timestamp createTime;

    Timestamp updateTime;

    /**
     * 当前角色具有的资源id
     */
    List<Integer> resourceIdList;
}
