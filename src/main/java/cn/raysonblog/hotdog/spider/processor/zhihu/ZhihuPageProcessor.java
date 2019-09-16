package cn.raysonblog.hotdog.spider.processor.zhihu;

import cn.raysonblog.hotdog.spider.annotation.SiteConfig;
import cn.raysonblog.hotdog.spider.processor.AbstractPageProcessor;
import us.codecraft.webmagic.Page;

@SiteConfig(siteCode = "102", tabCode = "10201", desc = "知乎热榜")
public class ZhihuPageProcessor extends AbstractPageProcessor {

    @Override
    public void process(Page page) {
        findData(page, ".HotList-list .HotItem-content", "a", ".HotItem-title");
    }
}
