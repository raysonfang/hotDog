package cn.raysonblog.hotdog.core.xss;


import java.io.IOException;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@WebFilter(urlPatterns="/*")
@Order(value=1)
public class XSSFilter implements Filter {

	 FilterConfig filterConfig = null;

	 private List<String> urlExclusion = null;
	 
	public void destroy() {
		this.filterConfig = null;
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		 HttpServletRequest httpServletRequest = (HttpServletRequest) request;
	        String servletPath = httpServletRequest.getServletPath();

	        if (urlExclusion != null && urlExclusion.contains(servletPath)) {
	            chain.doFilter(request, response);
	        } else if(!"GET".equalsIgnoreCase(httpServletRequest.getMethod())){
	            chain.doFilter(request, response);
	        }else {
	            chain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) request), response);
	        }
		
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

}
