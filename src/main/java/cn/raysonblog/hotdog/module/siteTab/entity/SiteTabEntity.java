package cn.raysonblog.hotdog.module.siteTab.entity;

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
 * 
 * 
 * @author rayson
 * @email 793514387@qq.com
 * @date 2019-08-19 17:49:01
 */
@TableName("site_tab")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class SiteTabEntity extends BaseModel<SiteTabEntity> implements Serializable {
	private static final long serialVersionUID = 1L;



	/**
	 * id
	 */
		@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return this.id;
	}
	
	@TableId(type=IdType.AUTO)
			private Integer id;
	/**
	 * 站点对应的榜单名称
	 */
		private String name;
	/**
	 * 站点id
	 */
		private Integer siteId;
	/**
	 * 创建时间
	 */
		private Date createDate;
	/**
	 * 更新时间
	 */
		private Date updateDate;
	/**
	 * 榜单url
	 */
		private String targetUrl;
	/**
	 * 状态
	 */
		private Integer state;

}
