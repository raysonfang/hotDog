package cn.raysonblog.hotdog.module.siteTab.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.raysonblog.hotdog.module.siteTab.entity.SiteTabEntity;
import cn.raysonblog.hotdog.module.siteTab.service.ISiteTabService;

import cn.raysonblog.hotdog.base.controller.BaseController;
import cn.raysonblog.hotdog.core.toolbox.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 *
 * @author rayson
 * @email 793514387@qq.com
 * @date 2019-08-20 10:57:31
 */
@Api(tags = "SiteTab管理")
@RestController
@RequestMapping("siteTab")
public class SiteTabController extends BaseController {

    @Autowired
    private ISiteTabService siteTabService;

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
    @RequestMapping(value = "/info/{siteTabCode}", method = RequestMethod.GET)
    public AjaxResult info(@PathVariable("siteTabCode") String siteTabCode){
		SiteTabEntity siteTab = siteTabService.getById(siteTabCode);

        return AjaxResult.success(siteTab);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AjaxResult save(@RequestBody SiteTabEntity siteTab){
		siteTabService.save(siteTab);

        return AjaxResult.success("成功");
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public AjaxResult update(@RequestBody SiteTabEntity siteTab){
		siteTabService.updateById(siteTab);

        return AjaxResult.success("成功");
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public AjaxResult delete(@RequestBody String[] siteTabCodes){
		siteTabService.removeByIds(Arrays.asList(siteTabCodes));

        return AjaxResult.success("成功");
    }

}
