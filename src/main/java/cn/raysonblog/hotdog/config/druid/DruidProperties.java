package cn.raysonblog.hotdog.config.druid;

import java.sql.SQLException;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 
 * <p>Title: DruidProperties</p>
 * <p>Description: 连接池配置</p>
 * <p>Company: </p>
 * @author rayson
 * @date 2018-06-07 11:52
 * @version 1.0
 */
@Configuration
public class DruidProperties implements EnvironmentAware {

    Environment env;
      
     public void setEnvironment(Environment env) {  
         this.env = env;
     }

     public void config(DruidDataSource datasource) {
         datasource.setUrl(env.getProperty("spring.datasource.url"));
         datasource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
         datasource.setUsername(env.getProperty("spring.datasource.username"));
         datasource.setPassword(env.getProperty("spring.datasource.password"));
         datasource.setInitialSize(Integer.valueOf(env.getProperty("spring.datasource.initialSize")));
         datasource.setMinIdle(Integer.valueOf(env.getProperty("spring.datasource.minIdle")));
         datasource.setMaxWait(Long.valueOf(env.getProperty("spring.datasource.maxWait")));
         datasource.setMaxActive(Integer.valueOf(env.getProperty("spring.datasource.maxActive")));
         datasource.setMinEvictableIdleTimeMillis(  
                Long.valueOf(env.getProperty("spring.datasource.minEvictableIdleTimeMillis")));
         try {  
            datasource.setFilters("stat,wall");  
         } catch (SQLException e) {  
            e.printStackTrace();  
         }  
    }
}