package cn.raysonblog.hotdog.module.siteHotData.controller;

import java.util.Arrays;

import cn.raysonblog.hotdog.spider.SiteSpider;
import cn.raysonblog.hotdog.spider.processor.v2ex.V2EXPageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.raysonblog.hotdog.module.siteHotData.entity.SiteHotDataEntity;
import cn.raysonblog.hotdog.module.siteHotData.service.ISiteHotDataService;

import cn.raysonblog.hotdog.base.controller.BaseController;
import cn.raysonblog.hotdog.core.toolbox.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 热点数据信息表
 *
 * @author rayson
 * @email 793514387@qq.com
 * @date 2019-08-20 09:35:28
 */
@Api(tags = "SiteHotData管理")
@RestController
@RequestMapping("siteHotData")
public class SiteHotDataController extends BaseController {

    @Autowired
    private ISiteHotDataService siteHotDataService;

    @Autowired
    private V2EXPageProcessor v2EXProcessor;

    @Autowired
    private SiteSpider siteSpider;
    /**
     * 列表
     */
    @ApiOperation("获取列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public AjaxResult list(){

        return AjaxResult.success(siteHotDataService.list());
    }


    /**
     * 信息
     */
    @ApiOperation("根据id获取信息")
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public AjaxResult info(@PathVariable("id") Long id){
		SiteHotDataEntity siteHotData = siteHotDataService.getById(id);

        return AjaxResult.success(siteHotData);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AjaxResult save(@RequestBody SiteHotDataEntity siteHotData){
		siteHotDataService.save(siteHotData);

        return AjaxResult.success("成功");
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public AjaxResult update(@RequestBody SiteHotDataEntity siteHotData){
		siteHotDataService.updateById(siteHotData);

        return AjaxResult.success("成功");
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public AjaxResult delete(@RequestBody Long[] ids){
		siteHotDataService.removeByIds(Arrays.asList(ids));

        return AjaxResult.success("成功");
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public AjaxResult start(){
        /*Spider.create(v2EXProcessor)
                .addUrl(V2EXProcessor.url)
                .setDownloader(new HttpClientDownloader())
                .thread(1)
                .run();*/
        siteSpider.process();
        return AjaxResult.success("");
    }

}
