package cn.raysonblog.hotdog.spider.processor.v2ex;

import cn.raysonblog.hotdog.spider.annotation.SiteConfig;
import cn.raysonblog.hotdog.spider.processor.AbstractPageProcessor;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Selectable;

import java.util.*;
import java.util.function.Consumer;

/**
 * V2EX网站 爬虫处理类
 * ==========================
 * SiteConfig注解
 *          siteCode 对应数据库中site表中的site_code字段
 *          tabCode  对应site_tab表中site_tab_code字段
 *          siteUrlFlag 是否需要在爬取到的文章url加域名前缀
 *          desc     站点信息备注说明
 *
 * @author raysonfang
 * @date 2019-8-19 14:37:13
 */
@SiteConfig(siteCode = "100", tabCode = "10001", siteUrlFlag = true, desc = "V2EX的hot标签页面数据爬取")
public class V2EXPageProcessor extends AbstractPageProcessor {

    @Override
    public void process(Page page) {
        findData(page, ".item_title");
    }
}
