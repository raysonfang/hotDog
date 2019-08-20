package cn.raysonblog.hotdog.spider.annotation;


import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 站点信息注解类
 * @author raysonfang
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface SiteConfig {


    @AliasFor("siteCode")
    String value() default "";

    /**
     * 站点code代码
     * @return
     */
    @AliasFor("value")
    String siteCode() default "";

    /**
     * 站点内的标签代码
     * @return
     */
    String tabCode() default "";

    /**
     * 站点hosturl
     * @return
     */
    String siteUrl() default "";

    /**
     * 网站备注描述
     * @return
     */
    String desc() default "";

    /**
     * 是否需要GBK2转UTF-8
     * @return
     */
    boolean gbk2Utf8Flag() default false;

    /**
     * url是否需要添加前缀
     * @return
     */
    boolean siteUrlFlag() default false;
}
