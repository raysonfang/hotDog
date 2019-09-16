package cn.raysonblog.hotdog.module.siteHotData.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode
public class SiteHotDataVO implements Serializable {

    /**
     * 站点code
     */
    private String siteCode;
    /**
     * 站点tabid
     */
    private String siteTabCode;
    /**
     * 站点数据
     */
    private String data;
    /**
     * 创建时间
     */
    private Date createTime;

    private String siteName;

    private String siteTabName;

}
