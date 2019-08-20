package cn.raysonblog.hotdog.module.siteTab.service;

import cn.raysonblog.hotdog.base.service.IBaseService;
import cn.raysonblog.hotdog.module.siteTab.entity.SiteTabEntity;
import cn.raysonblog.hotdog.spider.domain.SiteTabInfo;

import java.util.List;

/**
 * 
 *
 * @author rayson
 * @email 793514387@qq.com
 * @date 2019-08-20 10:57:31
 */
public interface ISiteTabService extends IBaseService<SiteTabEntity> {
    public List<SiteTabInfo> listSiteTabInfo();
}

