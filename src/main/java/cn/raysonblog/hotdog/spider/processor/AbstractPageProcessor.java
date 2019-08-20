package cn.raysonblog.hotdog.spider.processor;

import cn.raysonblog.hotdog.module.siteHotData.entity.SiteHotDataEntity;
import cn.raysonblog.hotdog.module.siteHotData.service.ISiteHotDataService;
import cn.raysonblog.hotdog.spider.domain.SiteTabInfo;
import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
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
public abstract class AbstractPageProcessor implements PageProcessor {

    public final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ISiteHotDataService siteHotDataService;

    public SiteTabInfo siteTabInfo;

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

    @Override
    public Site getSite() {
        return Site.me()
                .setRetryTimes(3)
                .setTimeOut(30000)
                .setSleepTime(1800)
                .setCycleRetryTimes(3)
                .setUseGzip(true)
                .addHeader("User-Agent","ozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Mobile Safari/537.36")
                .addHeader("Accept","application/json, text/plain, */*")
                .addHeader("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                .addHeader("Accept-Encoding","gzip, deflate, br")
                .addHeader("X-Requested-With","XMLHttpRequest")
                .addHeader("Content-Type","application/x-www-form-urlencoded");
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
        List<Map<String, String>> list = new ArrayList<>();
        // 开始解析网址
        page.getHtml().$(selectElement).nodes().forEach(new Consumer<Selectable>() {
            @Override
            public void accept(Selectable selectable) {
                String url = selectable.$("a", "href").get();
                String title = selectable.$("a", "text").get();

                //log.info("标题：{}，链接：{}", title, url);
                Map hashMap = new HashMap();
                hashMap.put("title", gbk2Utf8Flag ? title : title);
                hashMap.put("url",  (siteUrlFlag ? siteTabInfo.getSiteUrl() : "") + url);
                list.add(hashMap);
            }
        });

        saveData(list);
    }


}
