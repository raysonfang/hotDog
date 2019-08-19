package cn.raysonblog.hotdog.config;


import cn.raysonblog.hotdog.config.druid.DruidProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * MybatisPlus配置
 *
 * @author raysonfang
 * @Date 2019-8-18 17:04:23
 */
@Configuration
public class MybatisPlusConfig {

    @Autowired
    DruidProperties druidProperties;

    /**
     * druid数据库连接池
     */
    @Bean(initMethod = "init", destroyMethod="close")
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        return dataSource;
    }
}