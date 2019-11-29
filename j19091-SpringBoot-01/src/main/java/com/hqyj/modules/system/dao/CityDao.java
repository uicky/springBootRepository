package com.hqyj.modules.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.hqyj.modules.system.entity.City;
import com.hqyj.modules.system.entity.Country;



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
	* <p>Title: </p>  
	* <p>Description: </p>  
	* @param countryName
	* @return
	 */
	@Select("select * from m_country where country_name=#{countryName}")
	@ResultMap(value="countryResult")
	Country getCountryByCountryName(String countryName);
	
  /**
	* <p>Title:getCitiesByPage </p>  
	* <p>Description: 查询所有的城市数据，实现分页 </p>  
	* @return List
	*/
	@Select("select * from m_city")
	List<City> getCitiesByPage();

	
	/**
	* <p>Title: </p>  
	* <p>Description:插入一条数据，option注解标签useGeneratedKeys=true表示使用数据库自动增长的主键，
	* keyColumn用于指定数据库table中的主键，keyProperty用于指定传入对象的成员变量。 </p>  
	* @param city
	 */
	@Insert("INSERT INTO m_city (city_name,country_id) VALUES(#{cityName},#{countryId})")
	@Options(useGeneratedKeys=true,keyColumn="city_id",keyProperty="cityId")
	void insertCity(City city);

	
	/**
	 * 
	* <p>Title: </p>  
	* <p>Description:修改城市信息 </p>  
	* @param city
	 */
	@Update("UPDATE m_city SET city_name=#{cityName} WHERE city_id=#{cityId}")
	void updateCity(City city);

	/**
	 * 
	* <p>Title: </p>  
	* <p>Description:删除一条记录 </p>  
	* @param cityId
	 */
	@Delete("DELETE FROM m_city WHERE city_id=#{cityId}")
	void deleteCityByCityId(int cityId);


	/**
	 * 
	* <p>Title: </p>  
	* <p>Description:通过xml配置文件查询所有的城市 </p>  
	* @param countryId
	* @return
	 */
	List<City> getCitiesByCountryId(int countryId);

}
