package com.peng.news.service;

import com.peng.news.model.vo.ResourceVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shan
 * @version 1.0
 * @date 2021/3/24 10:00
 */
@Service
public interface ResourceService {
    List<ResourceVO> getAllResourceWithRoles();

    /**
     * 查询所有一级菜单，并且带有children
     * @return
     */
    List<ResourceVO> getOneLevelMenuWithChildren();
}
