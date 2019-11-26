package com.hqyj.modules.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hqyj.modules.ov.ApplicationOVBean;

@RestController
@RequestMapping(value = "/userController")
public class TestController {
	//注入接受配置文件的bean
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
	public String getConfig() {
		StringBuilder sb = new StringBuilder();
		sb.append(applicationOVBean.getName()).append("--name<br>").append(applicationOVBean.getAge())
				.append("--age<br>").append(applicationOVBean.getDescription()).append("--description<br>")
				.append(applicationOVBean.getRandom()).append("--random");
		return sb.toString();

	}

}
