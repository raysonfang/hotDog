package cn.raysonblog.hotdog.module.siteHotData.vo;

import cn.raysonblog.hotdog.module.siteHotData.entity.SiteHotDataEntity;

import java.io.Serializable;
import java.util.List;

public class SiteTabInfoVO implements Serializable {

    /** 子选项名称 */
    private String siteTabName;
    /** 站点子选项code */
    private String siteTabCode;
    /** 热点数据列表 */
    private List<SiteHotDataVO> siteHotDataVOList;
}
