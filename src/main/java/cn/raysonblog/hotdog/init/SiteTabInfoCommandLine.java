package cn.raysonblog.hotdog.init;

import cn.raysonblog.hotdog.cache.SiteTabInfoCache;
import cn.raysonblog.hotdog.core.util.SpringContextHolder;
import cn.raysonblog.hotdog.module.siteTab.entity.SiteTabEntity;
import cn.raysonblog.hotdog.module.siteTab.service.ISiteTabService;
import cn.raysonblog.hotdog.spider.SiteSpider;
import cn.raysonblog.hotdog.spider.domain.SiteTabInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class SiteTabInfoCommandLine implements CommandLineRunner {
    @Autowired
    SiteSpider siteSpider;

    @Autowired
    ISiteTabService siteTabService;
    @Override
    public void run(String... args) throws Exception {
        // 获取站点信息
        ISiteTabService siteTabService = SpringContextHolder.getBean(ISiteTabService.class);
        QueryWrapper<SiteTabEntity> queryWrapper = new QueryWrapper<>();
        List<SiteTabInfo> list = siteTabService.listSiteTabInfo();
        Map<String, SiteTabInfo> map2 = new HashedMap();
        for(SiteTabInfo siteTabInfo : list){
            map2.put(siteTabInfo.getSiteCode() + "_" + siteTabInfo.getSiteTabCode(), siteTabInfo);
        }

        SiteTabInfoCache.putAll(map2);
        log.info(""+SiteTabInfoCache.size());
        // 执行加载定时任务
        siteSpider.process();
    }


}
