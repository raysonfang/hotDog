package cn.raysonblog.hotdog.spider;

import cn.raysonblog.hotdog.cache.SiteTabInfoCache;
import cn.raysonblog.hotdog.core.util.SpringContextHolder;
import cn.raysonblog.hotdog.module.job.constants.JobConstants;
import cn.raysonblog.hotdog.module.job.entity.QuartzEntity;
import cn.raysonblog.hotdog.module.job.service.IJobService;
import cn.raysonblog.hotdog.module.siteTab.entity.SiteTabEntity;
import cn.raysonblog.hotdog.module.siteTab.service.ISiteTabService;
import cn.raysonblog.hotdog.spider.annotation.SiteConfig;
import cn.raysonblog.hotdog.spider.processor.AbstractPageProcessor;
import cn.raysonblog.hotdog.spider.domain.SiteTabInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
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
    private IJobService jobService;

    /**
     * 处理类
     * @return
     */
    public boolean process(){
        Map<String, Object> map = SpringContextHolder.getApplicationContext().getBeansWithAnnotation(SiteConfig.class);
       // 获取站点配置信息
       for(Object object : map.values()){
           if(object instanceof AbstractPageProcessor){
               AbstractPageProcessor pageProcessor = (AbstractPageProcessor)object;
               SiteConfig siteConfig = pageProcessor.getClass().getAnnotation(SiteConfig.class);
               log.info("站点信息：{}， siteCode: {}, tabCode: {}", pageProcessor.getClass(), siteConfig.siteCode(), siteConfig.tabCode());

               // 初始化变量
               SiteTabInfo siteTabInfo = pageProcessor.getData(siteConfig.siteCode() + "_" + siteConfig.tabCode());
               if(siteTabInfo != null){
                   if(siteTabInfo.getSiteTabType() == 0) {
                       // 节点添加到任务列表中去执行
                       QuartzEntity quartzEntity = new QuartzEntity();
                       quartzEntity.setJobClassName(pageProcessor.getClass().getCanonicalName());
                       quartzEntity.setCronExpression(StringUtils.isEmpty(siteTabInfo.getQrtzCron())
                               ? JobConstants.DEFUALT_CRON : siteTabInfo.getQrtzCron());
                       quartzEntity.setJobGroup(StringUtils.isEmpty(siteTabInfo.getQrtzJobGroup())
                               ? JobConstants.DEFUALT_GROUP_NAME : siteTabInfo.getQrtzJobGroup());
                       quartzEntity.setJobName(pageProcessor.getClass().getCanonicalName());
                       quartzEntity.setDescription(siteTabInfo.getQrtzJobDescription());
                       if (jobService.checkExistsJob(quartzEntity)) {
                           jobService.deleteJob(quartzEntity);
                       }
                       jobService.addJob(quartzEntity);
                   }
               }
           }
       }
       return false;
    }
}
