package com.hqyj.modules.system.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.hqyj.modules.system.entity.City;
import com.hqyj.modules.system.entity.Country;
import com.hqyj.modules.system.service.CityService;


/**
* 
* <p>Title: CityController</p>  
* <p>Description: </p>  
* @author zhaopeng
* @date 2019年11月28日
 */
@Controller
@RequestMapping(value = "/cityController")
public class CityController {
	@Resource
	private CityService cityService;

	/**
	 * 
	* <p>Title: </p>  
	* <p>Description: 通过国家id得到所有的城市</p>  
	* @param countryId
	* @return
	 */
	@RequestMapping(value = "/getCities/{countryId}")
	@ResponseBody
	public List<City> getCities(@PathVariable int countryId) {
		return cityService.getCities(countryId);
	}

	/**
	 * 
	* <p>Title: </p>  
	* <p>Description: </p>  
	* @param countryId
	* @return
	 */
	@RequestMapping(value = "/getCountry/{countryId}")
	@ResponseBody
	public Country getCountry(@PathVariable int countryId) {
		return cityService.getCountry(countryId);
	}

	
	/**
	 * 
	* <p>Title: </p>  
	* <p>Description: get country by country name,参数为字符串，使用requestParam注解</p>  
	* @param countryName
	* @return
	 */
	@RequestMapping(value = "/getCountryByCountryName")
	@ResponseBody
	public Country getCountryByCountryName(@RequestParam String countryName) {
		return cityService.getCountryByCountryName(countryName);

	}
	
	/**
	* <p>Title:getCitiesByPage </p>  
	* <p>Description: 实现分页 </p>  
	* @param pageNum
	* @param pageSize
	* @return PageInfo
	 */
	@RequestMapping(value = "/getCitiesByPage/{pageNum}/{pageSize}")
	@ResponseBody
	public PageInfo<City> getCitiesByPage(@PathVariable int pageNum, @PathVariable int pageSize) {
		return cityService.getCitiesByPage(pageNum,pageSize);

	}
	
	/**
	 * 
	* <p>Title: </p>  
	* <p>Description:插入一条数据 ,json提交</p>  
	* @param city
	* @return
	 */
	@PostMapping(value="/insertCity",consumes="application/json")
	@ResponseBody
	public City insertCity(@RequestBody City city) {
		return cityService.insertCity(city);
		
	}
	
	/**
	 * 
	* <p>Title:updateCity </p>  
	* <p>Description:form表单提交,实现修改 </p>  
	* @param city
	* @return
	 */
	@PutMapping(value="/updateCity" ,consumes="application/x-www-form-urlencoded")
	@ResponseBody
	public City updateCity(@ModelAttribute City city) {
		return cityService.updateCity(city);
		
	}
	/**
	 * 
	* <p>Title: </p>  
	* <p>Description: 通过城市id删除一条记录</p>  
	* @param cityId
	* @return
	 */
	@RequestMapping(value="/deleteCityByCityId")
	@ResponseBody
	public void deleteCityByCityId(@RequestParam int cityId) {
		 cityService.deleteCityByCityId(cityId);
		
	}
	/**
	 * 
	* <p>Title: </p>  
	* <p>Description:使用mapper.xml文件进行查询 </p>  
	* @param countryId
	* @return
	 */
	@RequestMapping(value="/getCitiesByCountryId")
	@ResponseBody
	public List<City> getCitiesByCountryId(@RequestParam int countryId) {
		return cityService.getCitiesByCountryId(countryId);
	}

}
