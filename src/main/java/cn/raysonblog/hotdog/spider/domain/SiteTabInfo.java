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
}
