package cn.raysonblog.hotdog.config.druid;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.BeanTypeAutoProxyCreator;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;

/**
 * Druid的DataResource配置类  
 * 凡是被Spring管理的类，实现接口 EnvironmentAware 重写方法 setEnvironment 可以在工程启动时，  
 * 获取到系统环境变量和application配置文件中的变量。 还有一种方式是采用注解的方式获取 @value("${变量的key值}")  
 * 获取application配置文件中的变量。 这里采用第一种要方便些  
 * <p>Title: DruidConfig</p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author rayson
 * @date 2018-06-07 11:54
 * @version 1.0
 */
@Configuration
public class DruidConfig {  
      
        @Bean  
        public ServletRegistrationBean druidServlet() {  
            ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();  
            servletRegistrationBean.setServlet(new StatViewServlet());  
            servletRegistrationBean.addUrlMappings("/druid/*");  
            Map<String, String> initParameters = new HashMap<String, String>();  
            // initParameters.put("loginUsername", "druid");// 用户名  
            // initParameters.put("loginPassword", "druid");// 密码  
            initParameters.put("resetEnable", "false");// 禁用HTML页面上的“Reset All”功能  
            initParameters.put("allow", "127.0.0.1"); // IP白名单 (没有配置或者为空，则允许所有访问)  
            // initParameters.put("deny", "192.168.20.38");// IP黑名单  
            // (存在共同时，deny优先于allow)  
            servletRegistrationBean.setInitParameters(initParameters);  
            return servletRegistrationBean;  
        }  
      
        @Bean  
        public FilterRegistrationBean filterRegistrationBean() {  
            FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();  
            filterRegistrationBean.setFilter(new WebStatFilter());  
            filterRegistrationBean.addUrlPatterns("/*");  
            filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");  
            return filterRegistrationBean;  
        }  
      
        // 按照BeanId来拦截配置 用来bean的监控  
        @Bean(value = "druid-stat-interceptor")  
        public DruidStatInterceptor DruidStatInterceptor() {  
            DruidStatInterceptor druidStatInterceptor = new DruidStatInterceptor();
            return druidStatInterceptor;  
        }  
    
        /**
         * druid数据库连接池监控
         */
        @Bean
        public BeanTypeAutoProxyCreator beanTypeAutoProxyCreator() {
            BeanTypeAutoProxyCreator beanTypeAutoProxyCreator = new BeanTypeAutoProxyCreator();
            beanTypeAutoProxyCreator.setTargetBeanType(DruidDataSource.class);
            beanTypeAutoProxyCreator.setInterceptorNames("druid-stat-interceptor");
            return beanTypeAutoProxyCreator;
        }
    }  