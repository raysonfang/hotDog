package cn.raysonblog.hotdog.module.tag.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.raysonblog.hotdog.module.tag.entity.TagEntity;
import cn.raysonblog.hotdog.module.tag.service.ITagService;

import cn.raysonblog.hotdog.base.controller.BaseController;
import cn.raysonblog.hotdog.core.toolbox.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 站点标签信息表
 *
 * @author rayson
 * @email 793514387@qq.com
 * @date 2019-08-20 10:57:31
 */
@Api(tags = "站点分类Tag管理")
@RestController
@RequestMapping("tag")
public class TagController extends BaseController {

    @Autowired
    private ITagService tagService;

    /**
     * 列表
     */
    @ApiOperation("获取列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public AjaxResult list(@RequestParam Map<String, Object> params){
        QueryWrapper<TagEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        queryWrapper.eq("state", 1);
        List<TagEntity> list = tagService.list(queryWrapper);
        return AjaxResult.success(list);
    }


    /**
     * 信息
     */
    @ApiOperation("根据id获取信息")
    @RequestMapping(value = "/info/{tagCode}", method = RequestMethod.GET)
    public AjaxResult info(@PathVariable("tagCode") String tagCode){
		TagEntity tag = tagService.getById(tagCode);

        return AjaxResult.success(tag);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AjaxResult save(@RequestBody TagEntity tag){
		tagService.save(tag);

        return AjaxResult.success("成功");
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public AjaxResult update(@RequestBody TagEntity tag){
		tagService.updateById(tag);

        return AjaxResult.success("成功");
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public AjaxResult delete(@RequestBody String[] tagCodes){
		tagService.removeByIds(Arrays.asList(tagCodes));

        return AjaxResult.success("成功");
    }

}
