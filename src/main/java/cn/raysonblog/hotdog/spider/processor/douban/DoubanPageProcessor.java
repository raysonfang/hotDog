package cn.raysonblog.hotdog.spider.processor.douban;

import cn.raysonblog.hotdog.spider.annotation.SiteConfig;
import cn.raysonblog.hotdog.spider.processor.AbstractPageProcessor;
import us.codecraft.webmagic.Page;

@SiteConfig(siteCode = "104", tabCode = "10401", desc = "豆瓣讨论精选")
public class DoubanPageProcessor extends AbstractPageProcessor {

    @Override
    public void process(Page page) {
        findData(page, ".channel-item .bd");
    }
}
