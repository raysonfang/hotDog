package cn.raysonblog.hotdog.spider;

import cn.raysonblog.hotdog.core.util.SpringContextHolder;
import cn.raysonblog.hotdog.module.siteTab.entity.SiteTabEntity;
import cn.raysonblog.hotdog.module.siteTab.service.ISiteTabService;
import cn.raysonblog.hotdog.spider.annotation.SiteConfig;
import cn.raysonblog.hotdog.spider.processor.AbstractPageProcessor;
import cn.raysonblog.hotdog.spider.domain.SiteTabInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class SiteSpider {

    @Autowired
    private ISiteTabService siteTabService;

    /**
     * 处理类
     * @return
     */
    public boolean process(){

        // 获取站点信息
        QueryWrapper<SiteTabEntity> queryWrapper = new QueryWrapper<>();
        List<SiteTabInfo> list = siteTabService.listSiteTabInfo();
        Map<String, SiteTabInfo> map2 = new HashedMap();
        for(SiteTabInfo siteTabInfo : list){
            map2.put(siteTabInfo.getSiteCode() + "_" + siteTabInfo.getSiteTabCode(), siteTabInfo);
        }

        Map<String, Object> map = SpringContextHolder.getApplicationContext().getBeansWithAnnotation(SiteConfig.class);
       // 获取站点配置信息
       for(Object object : map.values()){
           if(object instanceof AbstractPageProcessor){
               AbstractPageProcessor pageProcessor = (AbstractPageProcessor)object;
               SiteConfig siteConfig = pageProcessor.getClass().getAnnotation(SiteConfig.class);
               log.info("站点信息：{}， siteCode: {}, tabCode: {}", pageProcessor.getClass(), siteConfig.siteCode(), siteConfig.tabCode());

               // 初始化变量
               SiteTabInfo siteTabInfo = map2.get(siteConfig.siteCode() + "_" + siteConfig.tabCode());
               if(siteTabInfo != null){
                   pageProcessor.setSiteTabInfo(siteTabInfo);
                   pageProcessor.setGbk2Utf8Flag(siteConfig.gbk2Utf8Flag());
                   pageProcessor.setSiteUrlFlag(siteConfig.siteUrlFlag());

                   run(pageProcessor);
               }
           }
       }
       return false;
    }

    public void run(AbstractPageProcessor abstractPageProcessor){
        Spider.create(abstractPageProcessor)
                .addUrl(abstractPageProcessor.siteTabInfo.getTargetUrl())
                .setDownloader(new HttpClientDownloader())
                .thread(1)
                .run();
    }
}
