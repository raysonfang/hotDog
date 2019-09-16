package cn.raysonblog.hotdog.module.article.entity;

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
 * @author raysonfang
 * @email 793514387@qq.com
 * @date 2019-08-20 10:57:31
 */
@TableName("article")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ArticleEntity extends BaseModel<ArticleEntity> implements Serializable {
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
			private Long id;
	/**
	 * 文章标题
	 */
		private String title;
	/**
	 * 文件链接
	 */
		private String url;
	/**
	 * 发布时间
	 */
		private Date publishDate;
	/**
	 * 创建时间
	 */
		private Date createDate;
	/**
	 * 作者
	 */
		private String author;
	/**
	 * 是否原创
	 */
		private Integer original;
	/**
	 * 站点id
	 */
		private String siteCode;
	/**
	 * 更新时间
	 */
		private Date updateDate;
	/**
	 * 排序
	 */
		private Integer sort;
	/**
	 * 文章浏览量
	 */
		private Long pageView;
	/**
	 * 站点tabid
	 */
		private String siteTabCode;

}
