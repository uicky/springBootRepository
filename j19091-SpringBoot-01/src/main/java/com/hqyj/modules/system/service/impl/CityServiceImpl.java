package com.hqyj.modules.system.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.modules.system.dao.CityDao;
import com.hqyj.modules.system.entity.City;
import com.hqyj.modules.system.entity.Country;
import com.hqyj.modules.system.service.CityService;

@Service
public class CityServiceImpl implements CityService {
	@Autowired
	private CityDao cityDao;

	@Override
	public List<City> getCities(int countryId) {

		return Optional.ofNullable(cityDao.getCities(countryId)).orElse(Collections.emptyList());
	}

	@Override
	public Country getCountry(int countryId) {

		return cityDao.getCountry(countryId);
	}

	@Override
	public Country getCountryByCountryName(String countryName) {
		return cityDao.getCountryByCountryName(countryName);
	}

	@Override
	public PageInfo<City> getCitiesByPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<City> cities = Optional.ofNullable(cityDao.getCitiesByPage()).orElse(Collections.emptyList());
		return new PageInfo<>(cities);
	}

	@Override
	public City insertCity(City city) {
		cityDao.insertCity(city);
		return city;
	}

	@Override
	public City updateCity(City city) {
		cityDao.updateCity(city);
		return city;
	}

	@Override
	public void deleteCityByCityId(int cityId) {
		cityDao.deleteCityByCityId(cityId);

	}
	/**
	 * 
	* <p>Title: getCitiesByCountryId</p>
	* <p>Description:使用xml文件来查询数据 </p>
	* @param countryId
	* @return
	* @see com.hqyj.modules.system.service.CityService#getCitiesByCountryId(int)
	 */
	@Override
	public List<City> getCitiesByCountryId(int countryId) {
		return cityDao.getCitiesByCountryId(countryId);
	}

}
