package cn.raysonblog.hotdog.module.siteHotData.service.impl;

import cn.raysonblog.hotdog.module.siteHotData.vo.SiteHotDataVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


import cn.raysonblog.hotdog.module.siteHotData.mapper.SiteHotDataMapper;
import cn.raysonblog.hotdog.module.siteHotData.entity.SiteHotDataEntity;
import cn.raysonblog.hotdog.module.siteHotData.service.ISiteHotDataService;
import cn.raysonblog.hotdog.base.service.impl.BaseServiceImpl;

@Service
public class SiteHotDataServiceImpl extends BaseServiceImpl<SiteHotDataMapper, SiteHotDataEntity> implements ISiteHotDataService {
    @Override
    public List<SiteHotDataVO> getSiteHotDataListBySiteCode(String siteCode) {

        return baseMapper.getSiteHotDataListBySiteCode(siteCode);
    }
}
