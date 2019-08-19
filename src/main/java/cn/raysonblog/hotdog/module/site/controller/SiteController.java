package cn.raysonblog.hotdog.module.site.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.raysonblog.hotdog.module.site.entity.SiteEntity;
import cn.raysonblog.hotdog.module.site.service.ISiteService;

import cn.raysonblog.hotdog.base.controller.BaseController;
import cn.raysonblog.hotdog.core.toolbox.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 站点信息表
 *
 * @author rayson
 * @email 793514387@qq.com
 * @date 2019-08-19 17:49:01
 */
@Api(tags = "Site管理")
@RestController
@RequestMapping("site")
public class SiteController extends BaseController {

    @Autowired
    private ISiteService siteService;

    /**
     * 列表
     */
    @ApiOperation("获取列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public AjaxResult list(@RequestParam Map<String, Object> params){

        return AjaxResult.success("成功");
    }


    /**
     * 信息
     */
    @ApiOperation("根据id获取信息")
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public AjaxResult info(@PathVariable("id") Integer id){
		SiteEntity site = siteService.getById(id);

        return AjaxResult.success(site);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AjaxResult save(@RequestBody SiteEntity site){
		siteService.save(site);

        return AjaxResult.success("成功");
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public AjaxResult update(@RequestBody SiteEntity site){
		siteService.updateById(site);

        return AjaxResult.success("成功");
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public AjaxResult delete(@RequestBody Integer[] ids){
		siteService.removeByIds(Arrays.asList(ids));

        return AjaxResult.success("成功");
    }

}
