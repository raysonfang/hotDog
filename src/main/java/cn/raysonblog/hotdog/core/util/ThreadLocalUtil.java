package cn.raysonblog.hotdog.core.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * <p>Title: ContextThreadLocalUtil</p>
 * <p>Description: 全局上下文工具类,用于储存一些东西
 * 主要是解析JwtToken中的用户信息 并保存到全局
 * </p>
 * <p>Company: </p>
 * @author rayson
 * @date 2018-08-04 11:38
 * @version 1.0
 */
public class ThreadLocalUtil {
    
    private static final ThreadLocal<Map<Object, Object>> mycontext = new ThreadLocal<Map<Object, Object>>() {
        @Override
        protected Map<Object, Object> initialValue() {
            return new ConcurrentHashMap<Object, Object>();
        }
 
    };
 
    /**
     * 根据key获取值
     * @param key
     * @return
     */
    public static Object getValue(Object key) {
        if(mycontext.get() == null) {
            return null;
        }
        return mycontext.get().get(key);
    }
 
    /**
     * 存储
     * @param key
     * @param value
     * @return
     */
    public static Object setValue(Object key, Object value) {
        Map<Object, Object> cacheMap = mycontext.get();
        if(cacheMap == null) {
            cacheMap = new HashMap<Object, Object>();
            mycontext.set(cacheMap);
        }
        return cacheMap.put(key, value);
    }
 
    /**
     * 根据key移除值
     * @param key
     */
    public static void removeValue(Object key) {
        Map<Object, Object> cacheMap = mycontext.get();
        if(cacheMap != null) {
            cacheMap.remove(key);
        }
    }
 
    /**
     * 重置
     */
    public static void reset() {
        if(mycontext.get() != null) {
            mycontext.get().clear();
        }
    }
}
