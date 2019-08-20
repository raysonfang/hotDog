package cn.raysonblog.hotdog.spider.processor.ithome;

import cn.raysonblog.hotdog.spider.annotation.SiteConfig;
import cn.raysonblog.hotdog.spider.processor.AbstractPageProcessor;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 爬取IT之家的24小时阅读排行榜数据
 * @author raysonfang
 * @date 2019-8-20 16:46:26
 */
@SiteConfig(siteCode = "101", tabCode = "10101", desc = "IT之家网站24小时的阅读排行榜")
public class ITHome24HoursPageProcessor extends AbstractPageProcessor {

    @Override
    public void process(Page page) {
        findData(page, ".hot-list .bx ul li");
    }
}
