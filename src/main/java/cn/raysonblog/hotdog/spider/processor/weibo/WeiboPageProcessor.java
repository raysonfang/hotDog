package cn.raysonblog.hotdog.spider.processor.weibo;

import cn.raysonblog.hotdog.spider.annotation.SiteConfig;
import cn.raysonblog.hotdog.spider.processor.AbstractPageProcessor;
import us.codecraft.webmagic.Page;

@SiteConfig(siteCode = "103", tabCode = "10301", siteUrlFlag = true, desc = "微博热搜榜 数据获取")
public class WeiboPageProcessor extends AbstractPageProcessor {
    @Override
    public void process(Page page) {
        findData(page, ".data .td-02");
    }
}
