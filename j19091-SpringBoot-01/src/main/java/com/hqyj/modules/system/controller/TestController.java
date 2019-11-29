package com.hqyj.modules.system.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hqyj.modules.system.ov.ApplicationOVBean;

@Controller
@RequestMapping(value = "/testController")
@ResponseBody
public class TestController {
	private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);

	// 注入接受配置文件的bean
	@Autowired
	private ApplicationOVBean applicationOVBean;

	@RequestMapping(value = "/login.do")
	public String login() {
		return "this is a Spring boot App";

	}

	/*
	 * 读取配置文件
	 */
	@RequestMapping(value = "/getConfig")
	@ResponseBody
	public String getConfig() {
		StringBuilder sb = new StringBuilder();
		sb.append(applicationOVBean.getName()).append("--name<br>").append(applicationOVBean.getAge())
				.append("--age<br>").append(applicationOVBean.getDescription()).append("--description<br>")
				.append(applicationOVBean.getRandom()).append("--random");
		return sb.toString();

	}

	/**
	 * 读取日志测试方法
	 * 
	 */
	@RequestMapping(value="/testLogger")
	@ResponseBody
	public String testLogger() {
		LOGGER.trace("this is a trace logger");
		LOGGER.debug("this is a debug logger");
		LOGGER.info("this is a info logger");
		LOGGER.warn("this is a warn logger");
		LOGGER.error("this is a error logger");
		return "this is a testLogger ";
	}

	@RequestMapping(value="/testFilter")
	@ResponseBody
   public String testFilter(HttpServletRequest request, @RequestParam(name="key",defaultValue="123",required=false) String value ) {
	
		//第一种得到参数的方式
		String parameter = request.getParameter("key");
		//第二种得到参数的方式,直接使用参数value
		
		return "the method values is {"+parameter+"},{"+value+"}";
	   
   }

}
