package cn.raysonblog.hotdog.module.siteHotData.mapper;

import cn.raysonblog.hotdog.module.siteHotData.entity.SiteHotDataEntity;
import cn.raysonblog.hotdog.base.mapper.IBaseMapper;
import cn.raysonblog.hotdog.module.siteHotData.vo.SiteHotDataVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 热点数据信息表
 * 
 * @author rayson
 * @email 793514387@qq.com
 * @date 2019-08-20 09:35:28
 */
@Mapper
public interface SiteHotDataMapper extends IBaseMapper<SiteHotDataEntity> {


    @Select("select a.*, b.name as siteTabName, c.site_Zh as siteName from (select *, row_number() over(PARTITION by site_tab_code order by create_time desc) rn from site_hot_data where site_code = #{siteCode}) a, site_tab b, site c where a.rn = 1 and a.site_code = c.site_code and b.site_tab_code = a.site_tab_code")
    List<SiteHotDataVO> getSiteHotDataListBySiteCode(String siteCode);
}
