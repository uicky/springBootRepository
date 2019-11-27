package com.hqyj.modules.service;

import java.util.List;

import com.hqyj.modules.entity.City;
import com.hqyj.modules.entity.Country;

public interface CityService {

	List<City> getCities(int countryId);

	Country getCountry(int countryId);

	Country getCountryByCountryName(String countryName);

}
