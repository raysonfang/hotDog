package cn.raysonblog.hotdog.module.job.entity;

import java.io.Serializable;

import cn.raysonblog.hotdog.base.entity.BaseModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 任务类
 * 创建者
 * 创建时间	2018年3月28日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class QuartzEntity extends BaseModel<QuartzEntity> implements Serializable{
	
	private String jobName;//任务名称
	private String jobGroup;//任务分组
	private String description;//任务描述
	private String jobClassName;//执行类
	private String cronExpression;//执行时间
	private String triggerName;//执行时间
	private String triggerState;//任务状态
	
	private String oldJobName;//任务名称 用于修改
	private String oldJobGroup;//任务分组 用于修改
	
	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return null;
	}
}
