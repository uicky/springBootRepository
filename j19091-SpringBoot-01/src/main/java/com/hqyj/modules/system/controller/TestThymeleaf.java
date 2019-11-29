package com.hqyj.modules.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/testthymeleaf")
public class TestThymeleaf {
	
	@RequestMapping(value = "/index")
	public String testThymeleaf(ModelMap modelMap) {
		/* modelMap.addAttribute("template", "test/index"); */
		return "index";
	}

}
