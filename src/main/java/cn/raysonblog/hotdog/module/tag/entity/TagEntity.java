package cn.raysonblog.hotdog.module.tag.entity;

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
 * 站点标签信息表
 * 
 * @author rayson
 * @email 793514387@qq.com
 * @date 2019-08-19 17:49:01
 */
@TableName("tag")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class TagEntity extends BaseModel<TagEntity> implements Serializable {
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
	 * 标签名
	 */
		private String tag;
	/**
	 * 排序
	 */
		private Integer sort;
	/**
	 * 状态
	 */
		private Integer state;
	/**
	 * 
	 */
		private Date createDate;
	/**
	 * 
	 */
		private Date updateDate;

}
