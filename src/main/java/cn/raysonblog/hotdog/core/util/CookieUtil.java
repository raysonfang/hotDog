package cn.raysonblog.hotdog.core.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * <p>Title: CookieUtil</p>
 * <p>Description: Cookie工具类</p>
 * <p>Company: </p>
 * @author rayson
 * @date 2018-03-30 12:32
 * @version 1.0
 */
public class CookieUtil {
	public static final int COOKIE_MAX_AGE = 7 * 24 * 3600;
    public static final int COOKIE_HALF_HOUR = 30 * 60;
    public static final int COOKIE_USER_MAX_AGE = 365 * 24 * 60 * 60;
    /**
     * 根据Cookie名称得到Cookie对象，不存在该对象则返回Null
     * 
     * @param request
     * @param name
     * @return
     */ 
    public static Cookie getCookie(HttpServletRequest request, String name) { 
        Cookie[] cookies = request.getCookies(); 
        if (StrUtil.isEmptyArray(cookies)) { 
            return null; 
        } 
        Cookie cookie = null; 
        for (Cookie c : cookies) {
            if (name.equals(c.getName())) { 
                cookie = c; 
                break; 
            } 
        } 
        return cookie; 
    }
     
    /**
     * 根据Cookie名称直接得到Cookie值
     * 
     * @param request
     * @param name
     * @return
     */ 
    public static String getCookieValue(HttpServletRequest request, String name) { 
        Cookie cookie = getCookie(request, name); 
        if(cookie != null){
            return cookie.getValue();
        }
        return null; 
    }
     
    /**
     * 移除cookie
     * @param request
     * @param response
     * @param name 这个是名称，不是值
     */
    public static void removeCookie(HttpServletRequest request, 
            HttpServletResponse response, String name) { 
        if (null == name) { 
            return; 
        } 
        Cookie cookie = getCookie(request, name); 
        if(null != cookie){ 
            cookie.setPath("/"); 
            cookie.setValue(""); 
            cookie.setMaxAge(0); 
            response.addCookie(cookie);
        } 
    }
     
    /**
     * 添加一条新的Cookie，可以指定过期时间(单位：秒)
     * 
     * @param response
     * @param name
     * @param value
     * @param maxValue
     */ 
    public static void setCookie(HttpServletResponse response, String name, 
            String value, int maxValue) { 
        if (StringUtils.isNoneBlank(name)) { 
            return; 
        } 
        if (null == value) { 
            value = ""; 
        } 
        Cookie cookie = new Cookie(name, value); 
        cookie.setPath("/"); 
        if (maxValue != 0) { 
            cookie.setMaxAge(maxValue); 
        } else { 
            cookie.setMaxAge(COOKIE_HALF_HOUR); 
        } 
        response.addCookie(cookie);
        try {
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
    /**
     * 添加一条新的Cookie，默认30分钟过期时间
     * 
     * @param response
     * @param name
     * @param value
     */ 
    public static void setCookie(HttpServletResponse response, String name, 
            String value) { 
        setCookie(response, name, value, COOKIE_HALF_HOUR); 
    } 
  
    /**
     * 将cookie封装到Map里面
     * @param request
     * @return
     */
    public static Map<String,Cookie> getCookieMap(HttpServletRequest request){ 
        Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
        Cookie[] cookies = request.getCookies();
        if(!StrUtil.isEmptyArray(cookies)){
            for(Cookie cookie : cookies){
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
}
