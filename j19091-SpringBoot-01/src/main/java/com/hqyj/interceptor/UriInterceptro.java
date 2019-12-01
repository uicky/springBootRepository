package com.hqyj.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

  /**
 	* 
	* <p>Title: UriInterceptro</p>  
	* <p>Description:拦截器</p>  
	* @author zhaopeng
	* @date 2019年11月29日
	*/
@Component
public class UriInterceptro implements HandlerInterceptor {
	private static final Logger LOGGER = LoggerFactory.getLogger(UriInterceptro.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 利用日志打印拦截器执行的情况
		LOGGER.debug("UriInterceptor pre handle");
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		LOGGER.debug("post handler");

		// 如果modelandview为空或者跳转路径为重定向，则不需包装初始页面路径
		if (modelAndView == null || modelAndView.getViewName().startsWith("redirect")) {
			return;
		}

		String uri = request.getServletPath();
		String template = (String) modelAndView.getModelMap().get("template");
		if (StringUtils.isBlank(template)) {
			if (uri.startsWith("/")) {
				uri = uri.substring(1);
			}
			LOGGER.debug("uri:"+uri);//测试uri是否取到
			modelAndView.getModelMap().addAttribute("template", uri.toLowerCase());
		}

		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		LOGGER.debug("UriInterceptor after Completion ");
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

}
