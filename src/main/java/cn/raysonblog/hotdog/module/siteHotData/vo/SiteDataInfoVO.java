package cn.raysonblog.hotdog.module.siteHotData.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 热点数据信息VO对象
 *
 * @author raysonfang
 */
public class SiteDataInfoVO implements Serializable {

    /** 站点名 */
    private String site;

    /** 站点code */
    private String siteCode;

    /**
     * 站点子选项列表
     */
    private List<SiteTabInfoVO> siteTabInfoVOList;


}
