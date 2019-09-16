package cn.raysonblog.hotdog.module.site.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
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
 * @author raysonfang
 * @email 793514387@qq.com
 * @date 2019-08-20 10:57:31
 */
@Api(tags = "站点Site管理")
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
        String tagCode = (String)params.get("tagCode");
        if(StringUtils.isEmpty(tagCode)){
            return new AjaxResult().addError("分类code为空!");
        }
        QueryWrapper<SiteEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        queryWrapper.eq("state", 1);
        queryWrapper.eq("site_tag_code", tagCode);
        List<SiteEntity> list = siteService.list(queryWrapper);
        return AjaxResult.success(list);
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
