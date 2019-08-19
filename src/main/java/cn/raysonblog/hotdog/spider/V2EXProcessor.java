package cn.raysonblog.hotdog.spider;

import cn.raysonblog.hotdog.module.article.entity.ArticleEntity;
import cn.raysonblog.hotdog.module.article.service.IArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

/**
 * V2EX网站 爬虫处理类
 * @author rayson
 * @date 2019-8-19 14:37:13
 */
@Component
public class V2EXProcessor implements PageProcessor {

    private static final Logger log = LoggerFactory.getLogger(V2EXProcessor.class);

    public static final String url = "https://www.v2ex.com/?tab=hot";

    public static final String BaseHost = "https://www.v2ex.com/";

    @Autowired
    private IArticleService articleService;


    @Override
    public void process(Page page) {
        List<ArticleEntity> list = new ArrayList<>();
        // 开始解析网址
        page.getHtml().$(".item_title").nodes().forEach(new Consumer<Selectable>() {
            @Override
            public void accept(Selectable selectable) {
                String item_url = selectable.$("a", "href").get();
                String item = selectable.$("a", "text").get();

                log.info("标题：{}，链接：{}", item, item_url);

                ArticleEntity articleEntity = new ArticleEntity();
                articleEntity.setTitle(item);
                articleEntity.setUrl(BaseHost + item_url);
                articleEntity.setPageView(0L);
                articleEntity.setUpdateDate(new Date());

                list.add(articleEntity);
            }
        });

        articleService.saveBatch(list);
    }

    @Override
    public Site getSite() {
        return Site.me()
                .setRetryTimes(3)
                .setTimeOut(30000)
                .setSleepTime(1800)        //跟据试验，http://space.bilibili.com/ajax/member/GetInfo接口有IP接入限制，估计是60s内上限150次
                .setCycleRetryTimes(3)
                .setUseGzip(true)
                .addHeader("Host","www.v2ex.com")
                .addHeader("User-Agent","ozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Mobile Safari/537.36")
                .addHeader("Accept","application/json, text/plain, */*")
                .addHeader("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                .addHeader("Accept-Encoding","gzip, deflate, br")
                .addHeader("X-Requested-With","XMLHttpRequest")
                .addHeader("Content-Type","application/x-www-form-urlencoded");
    }

    public static void main(String[] args) {
        Spider.create(new V2EXProcessor())
                .addUrl(url)
                .setDownloader(new HttpClientDownloader())
                .thread(1)
                .run();
    }
}
