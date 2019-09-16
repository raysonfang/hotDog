package cn.raysonblog.hotdog.module.job.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import cn.raysonblog.hotdog.base.controller.BaseController;
import cn.raysonblog.hotdog.core.toolbox.AjaxResult;
import cn.raysonblog.hotdog.module.job.entity.QuartzEntity;
import cn.raysonblog.hotdog.module.job.service.IJobService;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(tags="quartz定时任务管理")
@CrossOrigin
@RestController
@RequestMapping(path = "/job")
@Slf4j
public class JobController extends BaseController {
	

    
    @Autowired
    private IJobService jobService;
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value="新建任务")
	@PostMapping("/add")
    public AjaxResult save(QuartzEntity quartzEntity){
    	log.info("新建任务:QuartzEntity={}", quartzEntity);
    	try {
			jobService.addJob(quartzEntity);
		} catch (Exception e) {
			e.printStackTrace();
			return error("新增任务异常");
		}
    	return json("新增任务成功");
    }
    
    @ApiOperation(value="任务列表")
	@GetMapping("/list")
	public AjaxResult list(QuartzEntity quartzEntity
			 , @RequestParam(value = "page", required = false, defaultValue = "1") @Min(1) Integer page //page
	         , @RequestParam(value = "pageCount", required = false, defaultValue = "100") @Max(100) @Min(1) Integer pageCount//pageCount
	        ){
    	log.info("查询任务列表:QuartzEntity={}", quartzEntity);
		List<QuartzEntity> list = jobService.listQuartzEntity(quartzEntity);
		return json(list);
	}
    
    @ApiOperation(value="触发任务")
	@PostMapping("/trigger")
	public  AjaxResult trigger(QuartzEntity quartzEntity, HttpServletResponse response) {
    	log.info("触发任务:QuartzEntity={}", quartzEntity);
		try {
		     jobService.triggerJob(quartzEntity);
		} catch (Exception e) {
			 e.printStackTrace();
			 return error("触发任务失败");
		}
		return json("成功");
	}
    
    @ApiOperation(value="停止任务")
    @PostMapping("/pause")
	public  AjaxResult pause(QuartzEntity quartzEntity,HttpServletResponse response) {
    	log.info("停止任务:QuartzEntity={}", quartzEntity);
		try {
		     jobService.pauseJob(quartzEntity);
		} catch (Exception e) {
			 e.printStackTrace();
			 return error("停止任务失败");
		}
		return json("成功");
	}
    @ApiOperation(value="恢复任务")
	@PostMapping("/resume")
	public  AjaxResult resume(QuartzEntity quartzEntity,HttpServletResponse response) {
		log.info("恢复任务:QuartzEntity={}", quartzEntity);
		try {
		     jobService.resumeJob(quartzEntity);
		} catch (Exception e) {
			 e.printStackTrace();
			 return error("恢复任务失败");
		}
		return json("成功");
	}
	@ApiOperation(value="移除任务")
	@PostMapping("/remove")
	public  AjaxResult remove(QuartzEntity quartzEntity,HttpServletResponse response) {
		log.info("移除任务:QuartzEntity={}", quartzEntity);
		try {  
            jobService.deleteJob(quartzEntity);
        } catch (Exception e) {
        	e.printStackTrace();
            return error("移除任务失败");
        }  
		return json("失败");
	}
}
