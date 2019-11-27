package com.hqyj.modules.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hqyj.modules.entity.City;
import com.hqyj.modules.entity.Country;
import com.hqyj.modules.service.CityService;

/**
 * 
 * <p>
 * Title: CityController
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author zhaopeng
 * @date 2019年11月27日
 */
@Controller
@RequestMapping(value = "/cityController")
public class CityController {
	@Autowired
	private CityService cityService;

	/**
	 * 
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:Query all cities by country id
	 * </p>
	 * 
	 * @param countryId
	 * @return list
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
	* @return Country
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
	@RequestMapping(value="/getCountryByCountryName")
	@ResponseBody
	public Country getCountryByCountryName(@RequestParam String countryName) {
		return cityService.getCountryByCountryName(countryName);
		
	}

}
