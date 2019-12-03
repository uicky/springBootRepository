package com.hqyj.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hqyj.filter.ParameterFilter;
import com.hqyj.interceptor.UriInterceptro;

@Configuration
@AutoConfigureAfter({ WebMvcAutoConfiguration.class })
public class WebMvcConfig implements WebMvcConfigurer {

	// 注入自定义的拦截器
	@Autowired
	private UriInterceptro uriInterceptro;

	/**
	 * 添加拦截器
	 */

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(uriInterceptro).addPathPatterns("/**");
	}

	/**
	 * 注册参数过滤器
	 */
	@Bean
	public FilterRegistrationBean<ParameterFilter> filterRegist() {

		FilterRegistrationBean<ParameterFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new ParameterFilter());

		return filterRegistrationBean;
	}

}
