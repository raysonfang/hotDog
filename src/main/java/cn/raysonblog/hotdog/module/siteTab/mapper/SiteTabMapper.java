package cn.raysonblog.hotdog.module.siteTab.mapper;

import cn.raysonblog.hotdog.module.siteTab.entity.SiteTabEntity;
import cn.raysonblog.hotdog.base.mapper.IBaseMapper;
import cn.raysonblog.hotdog.spider.domain.SiteTabInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 
 * 
 * @author raysonfang
 * @email 793514387@qq.com
 * @date 2019-08-20 10:57:31
 */
@Mapper
public interface SiteTabMapper extends IBaseMapper<SiteTabEntity> {

    @Select("select a.*, b.site_url, b.site, b.site_host, b.cookie, b.cookie_flag from site_tab a left join site b on a.site_code = b.site_code where a.state = 1")
    List<SiteTabInfo> listSiteTabInfo();

    @Select("select a.*, b.site_url, b.site, b.site_host, b.cookie, b.cookie_flag " +
            "from site_tab a left join site b on a.site_code = b.site_code " +
            "where a.state = 1 and a.site_code=#{siteCode} and a.site_tab_code = #{siteTabCode}")
    SiteTabInfo getOneSiteTabInfo(SiteTabInfo siteTabInfo);
}
