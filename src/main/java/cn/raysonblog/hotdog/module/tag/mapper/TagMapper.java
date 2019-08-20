package cn.raysonblog.hotdog.module.tag.mapper;

import cn.raysonblog.hotdog.module.tag.entity.TagEntity;
import cn.raysonblog.hotdog.base.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 站点标签信息表
 * 
 * @author rayson
 * @email 793514387@qq.com
 * @date 2019-08-20 10:57:31
 */
@Mapper
public interface TagMapper extends IBaseMapper<TagEntity> {
	
}
