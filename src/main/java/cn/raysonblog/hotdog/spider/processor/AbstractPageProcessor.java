package cn.raysonblog.hotdog.spider.processor;

import cn.raysonblog.hotdog.cache.SiteTabInfoCache;
import cn.raysonblog.hotdog.core.util.SpringContextHolder;
import cn.raysonblog.hotdog.module.siteHotData.entity.SiteHotDataEntity;
import cn.raysonblog.hotdog.module.siteHotData.service.ISiteHotDataService;
import cn.raysonblog.hotdog.module.siteTab.service.ISiteTabService;
import cn.raysonblog.hotdog.spider.annotation.SiteConfig;
import cn.raysonblog.hotdog.spider.domain.SiteTabInfo;
import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.*;
import java.util.function.Consumer;

/**
 * 自定义抽象类 实现部分公用功能
 * @author raysonfang
 * @date 2019-8-20 14:45:00
 */
@Getter
@Setter
@Component
public abstract class AbstractPageProcessor extends QuartzJobBean implements PageProcessor {

    public final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ISiteHotDataService siteHotDataService;

    public SiteTabInfo siteTabInfo;

    @Autowired
    private ISiteTabService siteTabService;

    /**
     * 是否启用cookie
     */
    public boolean cookieFlag = false;

    /**
     * url是否需要拼装网站域名前缀
     */
    public boolean siteUrlFlag = false;

    /**
     * 是否需要gbk转utf8格式
     */
    public boolean gbk2Utf8Flag = false;

    /**
     * 保存站点信息
     */
    public Site site = Site.me()
                            .setRetryTimes(3)
                            .setTimeOut(30000)
                            .setSleepTime(1800)
                            .setCycleRetryTimes(3);

    @Override
    public Site getSite() {
        if(siteTabInfo.getCookieFlag().equals(1)){
            site.addHeader("Cookie", siteTabInfo.getCookie());
        }
        site.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36");

        return site;
    }

    /**
     * 保存爬取后的热点数据到存储到数据库
     * @param hotData 爬取的热点数据
     * @return
     */
    protected boolean saveData(List hotData){
        SiteHotDataEntity siteHotDataEntity = new SiteHotDataEntity();
        siteHotDataEntity.setSiteCode(siteTabInfo.getSiteCode());
        siteHotDataEntity.setSiteTabCode(siteTabInfo.getSiteTabCode());
        siteHotDataEntity.setData(JSON.toJSONString(hotData));
        siteHotDataEntity.setCreateTime(Calendar.getInstance().getTime());
        siteHotDataEntity.setUpdateTime(Calendar.getInstance().getTime());
        return siteHotDataService.save(siteHotDataEntity);
    }

    /**
     * 下载的页面中查找出热点数据
     * @param page 下载好的页面数据
     * @param selectElement 选择元素的位置
     */
    protected void findData(Page page, String selectElement){
        findData(page, selectElement, "a", "a");
    }


    /**
     * 下载的页面中查找出热点数据
     * @param page 下载好的页面数据
     * @param selectElement 选择元素的位置
     */
    protected void findData(Page page, String selectElement, String hrefElement, String titleElement){
        List<Map<String, String>> list = new ArrayList<>();

        // 开始解析网址
        page.getHtml().$(selectElement).nodes().forEach(new Consumer<Selectable>() {
            @Override
            public void accept(Selectable selectable) {
                String url = selectable.$(hrefElement, "href").get();
                String title = selectable.$(titleElement, "text").get();

                //log.info("标题：{}，链接：{}", title, url);
                Map hashMap = new HashMap();
                hashMap.put("title", gbk2Utf8Flag ? title : title);
                hashMap.put("url",  (siteUrlFlag ? siteTabInfo.getSiteUrl() : "") + url);
                list.add(hashMap);
            }
        });
        if(!list.isEmpty()){
            saveData(list);
        }
    }

    @Override
    public void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SiteConfig siteConfig = this.getClass().getAnnotation(SiteConfig.class);
        // 初始化变量

        SiteTabInfo siteTabInfo = getData(siteConfig.siteCode() + "_" + siteConfig.tabCode());
        if(siteTabInfo != null){
            this.setSiteTabInfo(siteTabInfo);
            this.setGbk2Utf8Flag(siteConfig.gbk2Utf8Flag());
            this.setSiteUrlFlag(siteConfig.siteUrlFlag());
            run(this);
        }
    }
    public SiteTabInfo getData(String key) {
        String[] code = key.split("_");
        SiteTabInfo siteTabInfo = new SiteTabInfo();
        siteTabInfo.setSiteCode(code[0]);
        siteTabInfo.setSiteTabCode(code[1]);
        siteTabInfo = siteTabService.getOneSiteTabInfo(siteTabInfo);
        return siteTabInfo;
    }
    public void run(AbstractPageProcessor abstractPageProcessor){

        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        Spider.create(abstractPageProcessor)
                .addUrl(abstractPageProcessor.siteTabInfo.getTargetUrl())
                .setDownloader(httpClientDownloader)
                .thread(1)
                .run();
    }
}
