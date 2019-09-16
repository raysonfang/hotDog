package cn.raysonblog.hotdog.module.job.mapper;

import java.util.List;

import cn.raysonblog.hotdog.base.mapper.IBaseMapper;
import cn.raysonblog.hotdog.module.job.entity.QuartzEntity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface JobMapper extends IBaseMapper<QuartzEntity> {
	
	public List<QuartzEntity> listQuartzEntity(QuartzEntity quartz);
	
	public Long listQuartzEntityCount(QuartzEntity quartz);
}
