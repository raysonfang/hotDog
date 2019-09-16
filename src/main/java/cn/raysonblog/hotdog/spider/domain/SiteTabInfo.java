package cn.raysonblog.hotdog.spider.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 站点信息对象类
 * @author raysonfang
 * @date 2019-8-20 15:37:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SiteTabInfo implements Serializable {

    /**
     * 站点代码
     */
    private String siteCode;
    /**
     * 站点内选项代码
     */
    private String siteTabCode;
    /**
     * 目标爬取页url
     */
    private String targetUrl;
    /**
     * 站点名称
     */
    private String site;
    /**
     * 站点url
     */
    private String siteUrl;
    /**
     * 站点host
     */
    private String siteHost;

    /**
     * 站点cookie
     */
    private String cookie;

    /**
     * 是否启用cookie
     */
    private Integer cookieFlag;

    /**
     * quartz工作组名
     */
    private String qrtzJobGroup;

    /**
     * 描述
     */
    private String qrtzJobDescription;

    /**
     * quartz Cron表达式
     */
    private String qrtzCron;

    /**
     * 子节点 类型 ： 0：直链  1： rss源
     */
    private Integer siteTabType;
}
