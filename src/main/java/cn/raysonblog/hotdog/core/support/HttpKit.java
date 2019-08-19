package cn.raysonblog.hotdog.core.support;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.raysonblog.hotdog.core.xss.WafRequestWrapper;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;




/**
 * 
 * <p>Title: HttpKit</p>
 * <p>Description: http请求工具类</p>
 * <p>Company: </p>
 * @author rayson
 * @date 2018-03-06 18:02
 * @version 1.0
 */
public class HttpKit {
    
	 public static String getIp(){
	       return HttpKit.getRequest().getRemoteHost();
	 }
	 /**
	  * 获取项目路径和项目名
	  * @return
	  */
	 public static String getServerPath(){
	     HttpServletRequest request = HttpKit.getRequest();
	     return request.getScheme()+"://"+ request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	 }
	 /**
	  * 获取所有请求的值
	  */
	 public static Map<String, String> getRequestParameters() {
	     HashMap<String, String> values = new HashMap<String, String>();
	     HttpServletRequest request = HttpKit.getRequest();
	     Enumeration enums = request.getParameterNames();
	     while (enums.hasMoreElements()){
	         String paramName = (String) enums.nextElement();
	         String paramValue = request.getParameter(paramName);
	         values.put(paramName, paramValue);
	     }
	     return values;
	 }

	 /**
	  * 获取 HttpServletRequest
	  */
	 public static HttpServletResponse getResponse() {
	     HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
	     return response;
	 }
	    
	 /**
	  * 获取 包装防Xss Sql注入的 HttpServletRequest
	  * @return request
	  */
	 public static HttpServletRequest getRequest() {
	     HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	     return new WafRequestWrapper(request);
	 }
	    
	 /**
	  * 向指定URL发送GET方法的请求
	  *
	  * @param url 发送请求的URL
	  * @param param 请求参数
	  * @return URL 所代表远程资源的响应结果
	  */
	 public static String sendGet(String url, Map<String, String> param) {
	     String result = "";
	     BufferedReader in = null;
	     try {
	         StringBuffer query = new StringBuffer();

	         for (Map.Entry<String, String> kv : param.entrySet()) {
	             query.append(URLEncoder.encode(kv.getKey(), "UTF-8") + "=");
	             query.append(URLEncoder.encode(kv.getValue(), "UTF-8") + "&");
	         }
	         if (query.lastIndexOf("&") > 0) {
	             query.deleteCharAt(query.length() - 1);
	         }

	         String urlNameString = url + "?" + query.toString();
	         System.out.println(urlNameString);
	         URL realUrl = new URL(urlNameString);
	         // 打开和URL之间的连接
	         URLConnection connection = realUrl.openConnection();
	         HttpURLConnection httpURLConnection = (HttpURLConnection)connection;
	         // 设置通用的请求属性
	         httpURLConnection.setRequestProperty("accept", "*/*");
	         httpURLConnection.setRequestProperty("connection", "Keep-Alive");
	         httpURLConnection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	         httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
	         httpURLConnection.setRequestProperty("Content-Type",
	                 "application/x-www-form-urlencoded");
	         // 建立实际的连接
	         httpURLConnection.connect();
	         // 获取所有响应头字段
	         Map<String, List<String>> map = httpURLConnection.getHeaderFields();
	         // 遍历所有的响应头字段
	         for (String key : map.keySet()) {
	             System.out.println(key + "--->" + map.get(key));
	         }
	         if(httpURLConnection.getResponseCode() == 200){
	             System.out.println(233);
    	         // 定义 BufferedReader输入流来读取URL的响应
    	         in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
    	         String line;
    	         while ((line = in.readLine()) != null) {
    	             result += line;
    	         }
	         }
	     } catch (Exception e) {
	         System.out.println("发送GET请求出现异常！" + e);
	         e.printStackTrace();
	     }
	     // 使用finally块来关闭输入流
	     finally {
	         try {
	             if (in != null) {
	                 in.close();
	             }
	         } catch (Exception e2) {
	             e2.printStackTrace();
	         }
	     }
	     return result;
	 }
	 
	 /**
	  * 向指定 URL 发送POST方法的请求
	  *
	  * @param url 发送请求的 URL
	  * @param param  请求参数
	  * @return 所代表远程资源的响应结果
	  */
	 public static String sendPost(String url, Map<String, String> param) {
	     PrintWriter out = null;
	     BufferedReader in = null;
	     String result = "";
	     try {
	         String para = "";
	         for (String key : param.keySet()) {
	             para += (key + "=" + param.get(key) + "&");
	         }
	         if (para.lastIndexOf("&") > 0) {
	             para = para.substring(0, para.length() - 1);
	         }
	         String urlNameString = url + "?" + para;  
	         URL realUrl = new URL(urlNameString);
	         // 打开和URL之间的连接
	         URLConnection conn = realUrl.openConnection();
	         // 设置通用的请求属性
	         conn.setRequestProperty("accept", "*/*");
	         conn.setRequestProperty("connection", "Keep-Alive");
	         conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	         // 发送POST请求必须设置如下两行
	         conn.setDoOutput(true);
	         conn.setDoInput(true);
	         // 获取URLConnection对象对应的输出流
	         out = new PrintWriter(conn.getOutputStream());
	         // 发送请求参数 
	         out.print(param);
	         // flush输出流的缓冲
	         out.flush();
	         // 定义BufferedReader输入流来读取URL的响应
	         in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	         String line;
	         while ((line = in.readLine()) != null) {
	             result += line;
	         }
	     } catch (Exception e) {
	         System.out.println("发送 POST 请求出现异常！" + e);    
	         e.printStackTrace();
	     }
	        // 使用finally块来关闭输出流、输入流
	     finally {
	         try {
	             if (out != null) {
	                 out.close();
	                 }
	             if (in != null) {
	                  in.close();
	                  }
	             } catch (IOException ex) {
	                  ex.printStackTrace();
	                  }
	         }
	     return result;
	     }
}
