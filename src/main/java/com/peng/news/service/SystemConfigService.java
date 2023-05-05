package com.peng.news.service;

import com.peng.news.model.vo.SystemConfigVO;

/**
 * @author shan
 * @version 1.0
 * @date 2021/3/25 21:08
 */
public interface SystemConfigService {
    /**
     * 设置系统的审核等级
     * @param reviewLevel
     * @return
     */
    String setReviewLevel(int reviewLevel);

    SystemConfigVO loadCurSystemConfig();

    /**
     * 得到当前系统配置的review_level
     * @return
     */
    int getCurReviewLevel();
}
