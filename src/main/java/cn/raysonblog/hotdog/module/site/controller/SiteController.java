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
 * @date 2019-08-20 10:57:31
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
    @RequestMapping(value = "/info/{siteCode}", method = RequestMethod.GET)
    public AjaxResult info(@PathVariable("siteCode") String siteCode){
		SiteEntity site = siteService.getById(siteCode);

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
    public AjaxResult delete(@RequestBody String[] siteCodes){
		siteService.removeByIds(Arrays.asList(siteCodes));

        return AjaxResult.success("成功");
    }

}
