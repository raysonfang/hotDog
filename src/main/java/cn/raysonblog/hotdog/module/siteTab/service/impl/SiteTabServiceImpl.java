package cn.raysonblog.hotdog.module.siteTab.service.impl;

import cn.raysonblog.hotdog.spider.domain.SiteTabInfo;
import org.springframework.stereotype.Service;

import java.util.List;


import cn.raysonblog.hotdog.module.siteTab.mapper.SiteTabMapper;
import cn.raysonblog.hotdog.module.siteTab.entity.SiteTabEntity;
import cn.raysonblog.hotdog.module.siteTab.service.ISiteTabService;
import cn.raysonblog.hotdog.base.service.impl.BaseServiceImpl;

@Service
public class SiteTabServiceImpl extends BaseServiceImpl<SiteTabMapper, SiteTabEntity> implements ISiteTabService {

    public List<SiteTabInfo> listSiteTabInfo(){

        return baseMapper.listSiteTabInfo();
    }
}
