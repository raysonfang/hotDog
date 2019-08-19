package cn.raysonblog.hotdog.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

/**
 * 
 * <p>Title: FastjsonConfig</p>
 * <p>Description: fastjson配置类</p>
 * <p>Company: </p>
 * @author rayson
 * @date 2018-06-07 12:01
 * @version 1.0
 */
@Configuration
public class FastjsonConfig {

   @Bean
   public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
       FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
       FastJsonConfig fastJsonConfig = new FastJsonConfig();
       fastJsonConfig.setSerializerFeatures(
               SerializerFeature.PrettyFormat,
               SerializerFeature.WriteClassName,
               SerializerFeature.WriteMapNullValue
       );
       List<MediaType> fastMediaTypes = new ArrayList<MediaType>();
       fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
       
       fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
       ValueFilter valueFilter = new ValueFilter() {
           public Object process(Object o, String s, Object o1) {
               if (null == o1) {
                   o1 = "";
               }
               return o1;
           }
       };
       fastJsonConfig.setSerializeFilters(valueFilter);
       converter.setSupportedMediaTypes(fastMediaTypes);
       converter.setFastJsonConfig(fastJsonConfig);
       return converter;
   }

}
