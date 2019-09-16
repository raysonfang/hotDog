package cn.raysonblog.hotdog.module.siteHotData.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cn.raysonblog.hotdog.module.site.entity.SiteEntity;
import cn.raysonblog.hotdog.module.site.service.ISiteService;
import cn.raysonblog.hotdog.module.siteHotData.vo.SiteHotDataVO;
import cn.raysonblog.hotdog.spider.SiteSpider;
import cn.raysonblog.hotdog.spider.processor.v2ex.V2EXPageProcessor;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
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
@Api(tags = "站点爬取数据SiteHotData管理")
@RestController
@RequestMapping("siteHotData")
public class SiteHotDataController extends BaseController {

    @Autowired
    private ISiteHotDataService siteHotDataService;

    @Autowired
    private ISiteService siteService;

    @Autowired
    private V2EXPageProcessor v2EXProcessor;

    @Autowired
    private SiteSpider siteSpider;
    /**
     * 列表
     */
    @ApiOperation("获取列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public AjaxResult list(@RequestParam Map<String, Object> params){
        String tagCode = "";
        String siteCode = (String)params.get("siteCode");
        if(StringUtils.isEmpty(siteCode)){
            return new AjaxResult().addError("参数不正确！");
        }
        if("all".equals((String)params.get("site"))){ // 获取分类下的全部网站热点数据
            // 获取分类code
            tagCode = (String)params.get("tagCode");

            // 获取网站列表信息
            if(StringUtils.isEmpty(tagCode)){
                return new AjaxResult().addError("分类code为空!");
            }
            QueryWrapper<SiteEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.orderByAsc("sort");
            queryWrapper.eq("state", 1);
            queryWrapper.eq("site_tag_code", tagCode);
            List<SiteEntity> siteList = siteService.list(queryWrapper);
            for(SiteEntity siteEntity : siteList){
                QueryWrapper<SiteHotDataEntity> queryWrapper1 = new QueryWrapper<>();
                queryWrapper1.orderByAsc("create_time","site_tab_code");
                queryWrapper1.eq("site_code", siteEntity.getSiteCode());
                queryWrapper1.groupBy("site_tab_code");
                queryWrapper1.exists("select max(create_time) from site_hot_data");
                List<SiteHotDataEntity> siteHotDataEntityList = siteHotDataService.list(queryWrapper1);

            }
            return AjaxResult.success(siteHotDataService.list());
        }else{
            List<SiteHotDataVO> siteHotDataEntityList = siteHotDataService.getSiteHotDataListBySiteCode(siteCode);
            return AjaxResult.success(siteHotDataEntityList);

        }
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
