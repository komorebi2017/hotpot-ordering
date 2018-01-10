package com.hotpot_ordering.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/5 22:22
 * @ Description:
 */

@Data
@ConfigurationProperties(prefix = "projectUrl")
@Component
public class ProjectUrlConfig {

    /**
     * 微信公众平台授权url
     */
    public String wechatMpAuthorize;

    /**
     * 微信开放平台授权url
     */
    public String wechatOpenAuthorize;

    /**
     * 点餐系统
     */
    public String sell;
}
