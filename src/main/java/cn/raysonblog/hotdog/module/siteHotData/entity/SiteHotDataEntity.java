package cn.raysonblog.hotdog.module.siteHotData.entity;

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
 * 热点数据信息表
 * 
 * @author rayson
 * @email 793514387@qq.com
 * @date 2019-08-20 10:57:31
 */
@TableName("site_hot_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class SiteHotDataEntity extends BaseModel<SiteHotDataEntity> implements Serializable {
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
	/**
	 * 更新时间
	 */
		private Date updateTime;

}
