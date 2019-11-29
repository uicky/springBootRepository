package com.hqyj.modules.system.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hqyj.modules.system.entity.City;
import com.hqyj.modules.system.entity.Country;


public interface CityService {

	List<City> getCities(int countryId);

	Country getCountry(int countryId);

	Country getCountryByCountryName(String countryName);

	PageInfo<City> getCitiesByPage(int pageNum, int pageSize);

	City insertCity(City city);

	City updateCity(City city);

	void deleteCityByCityId(int cityId);

	List<City> getCitiesByCountryId(int countryId);

}
