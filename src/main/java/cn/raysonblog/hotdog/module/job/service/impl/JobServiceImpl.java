package cn.raysonblog.hotdog.module.job.service.impl;
import java.util.List;

import cn.raysonblog.hotdog.base.service.impl.BaseServiceImpl;
import cn.raysonblog.hotdog.module.job.entity.QuartzEntity;
import cn.raysonblog.hotdog.module.job.mapper.JobMapper;
import cn.raysonblog.hotdog.module.job.service.IJobService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class JobServiceImpl extends BaseServiceImpl<JobMapper, QuartzEntity> implements IJobService {

	@Autowired
	private Scheduler scheduler;

	@Override
	public List<QuartzEntity> listQuartzEntity(QuartzEntity quartzEntity) {
		return baseMapper.listQuartzEntity(quartzEntity);
	}

	@Override
	public Long listQuartzEntityCount(QuartzEntity quartzEntity) {
		return baseMapper.listQuartzEntityCount(quartzEntity);
	}

	@Override
	public boolean addJob(QuartzEntity quartzEntity) {
		boolean ret = false;
		try {
			//获取Scheduler实例、废弃、使用自动注入的scheduler、否则spring的service将无法注入
			//Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			//如果是修改  展示旧的 任务
			if (quartzEntity.getOldJobGroup() != null) {
				JobKey key = new JobKey(quartzEntity.getOldJobName(), quartzEntity.getOldJobGroup());
				scheduler.deleteJob(key);
			}
			Class cls = Class.forName(quartzEntity.getJobClassName());
			cls.newInstance();
			//构建job信息
			JobDetail job = JobBuilder.newJob(cls).withIdentity(quartzEntity.getJobName(),
					quartzEntity.getJobGroup())
					.withDescription(quartzEntity.getDescription()).build();
			// 触发时间点
			CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(quartzEntity.getCronExpression());
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger" + quartzEntity.getJobName(), quartzEntity.getJobGroup())
					.startNow().withSchedule(cronScheduleBuilder).build();
			//交由Scheduler安排触发
			scheduler.scheduleJob(job, trigger);
			ret = true;
		}catch (Exception e){
			e.printStackTrace();
			throw new RuntimeException("添加Job错误", e);
		}
		return ret;
	}

	@Override
	public boolean triggerJob(QuartzEntity quartzEntity){
		boolean ret = false;
		try {
			JobKey key = new JobKey(quartzEntity.getJobName(),quartzEntity.getJobGroup());
			scheduler.triggerJob(key);
			ret = true;
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new RuntimeException("触发Job失败", e);
		}
		return ret;
	}

	@Override
	public boolean pauseJob(QuartzEntity quartzEntity) {
		boolean ret = false;
		try {
			JobKey key = new JobKey(quartzEntity.getJobName(),quartzEntity.getJobGroup());
			scheduler.pauseJob(key);
			ret = true;
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new RuntimeException("暂停Job失败", e);
		}
		return ret;
	}

	public boolean resumeJob(QuartzEntity quartzEntity) {
		boolean ret = false;
		try {
			JobKey key = new JobKey(quartzEntity.getJobName(),quartzEntity.getJobGroup());
			scheduler.resumeJob(key);
			ret = true;
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new RuntimeException("暂停Job失败", e);
		}
		return ret;
	}

	public boolean deleteJob(QuartzEntity quartzEntity) {
		boolean ret = false;
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(quartzEntity.getJobName(), quartzEntity.getJobGroup());
			// 停止触发器
			scheduler.pauseTrigger(triggerKey);
			// 移除触发器
			scheduler.unscheduleJob(triggerKey);
			// 删除任务
			scheduler.deleteJob(JobKey.jobKey(quartzEntity.getJobName(), quartzEntity.getJobGroup()));
			ret = true;
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new RuntimeException("暂停Job失败", e);
		}
		return ret;
	}

	public boolean checkExistsJob(QuartzEntity quartzEntity) {
		boolean ret = false;
		try {
			JobKey key = new JobKey(quartzEntity.getJobName(),quartzEntity.getJobGroup());
			ret = scheduler.checkExists(key);
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new RuntimeException("暂停Job失败", e);
		}
		return ret;
	}
}
