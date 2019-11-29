package com.hqyj.modules.account.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.hqyj.modules.account.entity.User;

@Repository
@Mapper
public interface UserDao {

	/** 通过用户id查询用户对象 */
	@Select("select * from m_user where user_id=#{userId}")
	User queryUserByUserId(int userId);

	/** 添加用户 */
	@Insert("insert into m_user values(null,#{userName},#{password},#{createDate})")
	@Options(keyColumn="user_id",keyProperty="userId",useGeneratedKeys=true)
	void addUserByUser(User user);

	/**修改用户*/
	@Update("update m_user set user_name=#{userName},password=#{password} where user_id=#{userId}")
	void updateUserByUser(User user);

	
	/**删除用户*/
	@Delete("delete from m_user where user_id=#{userId}")
	int deleteUserByUserId(int userId);

}
