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
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 
 * 
 * @author raysonfang
 * @email 793514387@qq.com
 * @date 2019-08-20 10:57:31
 */
@TableName("site_tab")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class SiteTabEntity extends BaseModel<SiteTabEntity> implements Serializable {
	private static final long serialVersionUID = 1L;



	/**
	 * 站点榜单代码
	 */
		@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return this.siteTabCode;
	}

	private String siteTabCode;

	/**
	 * 站点对应的榜单名称
	 */
		private String name;
	/**
	 * 站点id
	 */
		private String siteCode;
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

	/**
	 * 排序
	 */
	private Integer sort;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		SiteTabEntity that = (SiteTabEntity) o;

		return new EqualsBuilder()
				.append(siteTabCode, that.siteTabCode)
				.append(siteCode, that.siteCode)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(siteTabCode)
				.append(siteCode)
				.toHashCode();
	}
}
