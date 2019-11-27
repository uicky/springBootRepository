package com.hqyj.modules.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hqyj.modules.dao.CityDao;
import com.hqyj.modules.entity.City;
import com.hqyj.modules.entity.Country;
import com.hqyj.modules.service.CityService;

@Service
public class CityServiceImpl implements CityService {
	@Autowired
	private CityDao cityDao;

	/**
	 * <p> Title: getCities</p>
	 * <p>Description:</p>
	 * @param countryId
	 * @return
	 * @see com.hqyj.modules.service.CityService#getCities(int)
	 */
	@Override
	public List<City> getCities(int countryId) {

		return Optional.ofNullable(cityDao.getCities(countryId)).orElse(Collections.emptyList());
	}

	/**
	 * 
	 * <p> Title: getCountry</p>
	 * <p>Description:query Country by Country Id,结果集中有集合属性 </p>
	 * @param countryId
	 * @return
	 * @see com.hqyj.modules.service.CityService#getCountry(int)
	 */
	@Override
	public Country getCountry(int countryId) {
		return cityDao.getCountry(countryId);
	}

	/**
	 * 
	* <p>Title: getCountryByCountryName</p>
	* <p>Description:URL中传递的参数为字符串 </p>
	* @param countryName
	* @return
	* @see com.hqyj.modules.service.CityService#getCountryByCountryName(java.lang.String)
	 */
	@Override
	public Country getCountryByCountryName(String countryName) {
		return cityDao.getCountryByCountryName(countryName);
	}

}
