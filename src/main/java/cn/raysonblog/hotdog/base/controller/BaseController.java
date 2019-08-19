package cn.raysonblog.hotdog.base.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.raysonblog.hotdog.base.entity.BaseModel;
import cn.raysonblog.hotdog.base.entity.PagingandEntity;
import cn.raysonblog.hotdog.core.support.HttpKit;
import cn.raysonblog.hotdog.core.toolbox.AjaxResult;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;


/**
 * 
 * <p>Title: BaseController</p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author rayson
 * @date 2018-03-30 13:49
 * @version 1.0
 */
public class BaseController {
    protected static String SUCCESS = "SUCCESS";
    protected static String ERROR = "ERROR";

    protected static String REDIRECT = "redirect:";
    protected static String FORWARD = "forward:";


   protected HttpServletRequest getHttpServletRequest() {
       return HttpKit.getRequest();
   }

   protected HttpServletResponse getHttpServletResponse() {
       return HttpKit.getResponse();
   }

   protected HttpSession getSession() {
       return HttpKit.getRequest().getSession();
   }

   protected HttpSession getSession(Boolean flag) {
       return HttpKit.getRequest().getSession(flag);
   }

   protected String getPara(String name) {
       return HttpKit.getRequest().getParameter(name);
   }

   protected void setAttr(String name, Object value) {
       HttpKit.getRequest().setAttribute(name, value);
   }
   
/** ============================     ajax  start  =================================================  */
   
   /**   
    * 返回ajaxresult
    * @param data
    * @return AjaxResult
   */
   public AjaxResult json(Object data) {
       return new AjaxResult().success(data);
   }
   
   /**   
    * 返回ajaxresult
    * @param data
    * @param message
    * @return AjaxResult
   */
   public AjaxResult json(Object data, String message) {
       return json(data).setMessage(message);
   }
   
   
   /**   
    * 返回ajaxresult
    * @param data
    * @param message
    * @param code
    * @return AjaxResult
   */
   public AjaxResult json(Object data, String message, String code) {
       return json(data, message).setCode(code);
   }
   
   /**   
    * 返回ajaxresult
    * @param message
    * @return AjaxResult
   */
   public AjaxResult success(String message) {
       return new AjaxResult().addSuccess(message);
   }
   
   /**   
    * 返回ajaxresult
    * @param message
    * @return AjaxResult
   */
   public AjaxResult error(String message) {
       return new AjaxResult().addError(message);
   }
   
   /**   
    * 参数错误返回ajaxresult
    * @param message
    * @return AjaxResult
   */
   public AjaxResult paramsError(String message) {
       return new AjaxResult().addParamError(message);
   }
   
   /**   
    * 返回ajaxresult
    * @param message
    * @return AjaxResult
   */
/*  public AjaxResult warn(String message) {
       return new AjaxResult().addWarn(message);
   }*/
   
   /**   
    * 返回ajaxresult
    * @param message
    * @return AjaxResult
   */
   public AjaxResult fail(String message) {
       return new AjaxResult().addFail(message);
   }
   /** ============================     ajax  end  =================================================  */
   
   
    /**
    * 删除cookie
    */
   protected void deleteCookieByName(String cookieName) {
       Cookie[] cookies = this.getHttpServletRequest().getCookies();
       for (Cookie cookie : cookies) {
           if (cookie.getName().equals(cookieName)) {
               Cookie temp = new Cookie(cookie.getName(), "");
               temp.setMaxAge(0);
               this.getHttpServletResponse().addCookie(temp);
           }
       }
   }
   
   /**
    * 直接响应数据
    * @param resultMsg
    * @throws IOException
    */
   protected void response(HttpServletResponse response, String resultMsg) throws IOException {
       PrintWriter print = response.getWriter();
       try{
           print.write(resultMsg);  
           print.flush();  
       }finally{
           print.close();
       }
       
       return;
   }
   
   protected String validatedError(BindingResult result) {
       List<AjaxResult> errorList = new ArrayList<AjaxResult>();
       for (FieldError error : result.getFieldErrors()) {
           AjaxResult errorResult = new AjaxResult();
           errorResult.setCode(error.getField());
           errorResult.setMessage(error.getDefaultMessage());
           errorList.add(errorResult);
       }
       return JSONObject.toJSONString(errorList);
   }

   public static String readData(HttpServletRequest request) {
		BufferedReader br = null;
		try {
			StringBuilder result = new StringBuilder();
			br = request.getReader();
			for (String line; (line=br.readLine())!=null;) {
				if (result.length() > 0) {
					result.append("\n");
				}
				result.append(line);
			}

			return result.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		finally {
			if (br != null)
				try {br.close();} catch (IOException e) {e.printStackTrace();}
		}
	}
}
