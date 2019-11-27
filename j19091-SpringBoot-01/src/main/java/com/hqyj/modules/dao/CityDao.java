package com.hqyj.modules.dao;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.hqyj.modules.entity.City;
import com.hqyj.modules.entity.Country;

@Repository
@Mapper
public interface CityDao {

	/**
	 * 
	* <p>Title: </p>  
	* <p>Description: </p>  
	* @param countryId
	* @return
	 */
	@Select("select * from m_city where country_id = #{countryId}")
	List<City> getCities(int countryId);
	
	
	/**
	 * 
	* <p>Title: </p>  
	* <p>Description: 通过国家id查询出国家对应的信息并包含国家下面对应的城市列表 </p>  
	* @param countryId
	* @return country
	 */
	@Select("select * from m_country where country_id=#{countryId} ")
	@Results(id="countryResult",value= {
			@Result(column="country_id",property="countryId"),
			@Result(column="country_id",property="cities",javaType=List.class,many=@Many(select="com.hqyj.modules.dao.CityDao.getCities"))
	})
	Country getCountry(int countryId);

	/**
	 * 
	* <p>Title: </p>  
	* <p>Description: </p>  
	* @param countryName
	* @return
	 */
	@Select("select * from m_country where country_name=#{countryName}")
	@ResultMap(value="countryResult")
	Country getCountryByCountryName(String countryName);

}
