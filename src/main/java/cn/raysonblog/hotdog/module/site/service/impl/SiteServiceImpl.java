package cn.raysonblog.hotdog.module.site.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;


import cn.raysonblog.hotdog.module.site.mapper.SiteMapper;
import cn.raysonblog.hotdog.module.site.entity.SiteEntity;
import cn.raysonblog.hotdog.module.site.service.ISiteService;
import cn.raysonblog.hotdog.base.service.impl.BaseServiceImpl;

@Service
public class SiteServiceImpl extends BaseServiceImpl<SiteMapper, SiteEntity> implements ISiteService {

}
