package cn.raysonblog.hotdog.module.site.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import cn.raysonblog.hotdog.base.entity.BaseModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 站点信息表
 * 
 * @author rayson
 * @email 793514387@qq.com
 * @date 2019-08-20 10:57:31
 */
@TableName("site")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class SiteEntity extends BaseModel<SiteEntity> implements Serializable {
	private static final long serialVersionUID = 1L;



	/**
	 * 站点id
	 */
		@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return this.siteCode;
	}

	private String siteCode;
	/**
	 * 站点名
	 */
		private String site;
	/**
	 * 域名
	 */
		private String siteHost;
	/**
	 * 添加日期
	 */
		private Date createDate;
	/**
	 * 状态： 0：不启用 1：启用
	 */
		private Integer state;
	/**
	 * 更新时间
	 */
		private Date updateDate;
	/**
	 * 标签id
	 */
		private String siteTagCode;
	/**
	 * 站点中文名
	 */
		private String siteZh;
	/**
	 * 爬取目标网页地址
	 */
		private String targetUrl;

	/**
	 * 站点url
	 */
	private String siteUrl;

	/**
	 * 排序
	 */
	private Integer sort;

}
