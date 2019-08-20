package cn.raysonblog.hotdog.module.article.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.raysonblog.hotdog.module.article.entity.ArticleEntity;
import cn.raysonblog.hotdog.module.article.service.IArticleService;

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
@Api(tags = "Article管理")
@RestController
@RequestMapping("article")
public class ArticleController extends BaseController {

    @Autowired
    private IArticleService articleService;

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
    public AjaxResult info(@PathVariable("id") Long id){
		ArticleEntity article = articleService.getById(id);

        return AjaxResult.success(article);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AjaxResult save(@RequestBody ArticleEntity article){
		articleService.save(article);

        return AjaxResult.success("成功");
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public AjaxResult update(@RequestBody ArticleEntity article){
		articleService.updateById(article);

        return AjaxResult.success("成功");
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public AjaxResult delete(@RequestBody Long[] ids){
		articleService.removeByIds(Arrays.asList(ids));

        return AjaxResult.success("成功");
    }

}
